package app;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import structures.Cuenta;
import structures.MenuItem;
import structures.Orden;

public class IngresarPagoWindow extends JFrame implements ActionListener{
  private static final long serialVersionUID = 1L;
  private MainWindow main;
  private JButton button1, button2, button3;
  private JPanel boleta;
  
  private int pago, total;
  private JLabel prev;
  private Cuenta cuenta;
  
  public IngresarPagoWindow(MainWindow m, Cuenta c) {
    super("Ingresar pago");
    
    main = m;
    cuenta = c;
    
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setTitle("Ingresar pago");

    total = 0;
    
    for(Orden o:cuenta.getOrdenes()){
      for(MenuItem i:o.getItems()){
        total += i.getPrecio();
      }
    }
    
    
    //Layout
    setLayout(new GridBagLayout());
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;    
    
    gbc.insets = new Insets(5,16,5,16);
    gbc.weightx = 1;
    
    JLabel label = new JLabel("Ingresar pago para la cuenta #" + cuenta.getId(),JLabel.CENTER);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    add(label,gbc);
 

    JLabel label2 = new JLabel("Total: ");
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.weightx = 0;
    add(label2,gbc);
    
    JLabel label3 = new JLabel(total + "");
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.weightx = 1;
    add(label3,gbc);
    
    JLabel label4 = new JLabel("Pago: ");
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.weightx = 0;
    add(label4,gbc);
    
    final JTextField textfield = new JTextField();
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.weightx = 1;
    add(textfield,gbc);
    
    
    button1 = new JButton("Ingresar pago");
    gbc.fill = GridBagConstraints.NONE;
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 2;
    add(button1,gbc);
    
    
    boleta = new JPanel();
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 2;
    add(boleta,gbc);
    
    prev = new JLabel(" ",JLabel.CENTER);
    boleta.add(prev);
    
    JPanel p = new JPanel(new FlowLayout());
    gbc.fill = GridBagConstraints.NONE;   
    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.weighty = 0;
    gbc.weightx = 0;
    gbc.gridwidth = 2;
    add(p,gbc);
    

    
    
    button2 = new JButton("Imprimir Boleta");
    p.add(button2);
    button2.setEnabled(false);
    
    button3 = new JButton("Cancelar");
    p.add(button3);
    
    
    button1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        try{
          pago = Integer.parseInt(textfield.getText());
          if(pago < total){
            prev.setText("Falta dinero para el pago");
          }else{
            prev.setText("Vuelto: " + (pago - total));
            button1.setEnabled(false);
            button2.setEnabled(true);
            button3.setEnabled(false);
            textfield.setEnabled(false);
            
            main.removeCuenta(cuenta);
          }
        } catch (NumberFormatException e) {
          prev.setText("Error en el input");
        }
      }
    });
    
    button2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        setVisible(false);
        dispose();
    }
    });
    
    button3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        setVisible(false);
        dispose();
    }
    });
    
    
    this.pack();
  }

  public void generarBoleta(){
    
  }
  
  @Override
  public void actionPerformed(ActionEvent arg0) {
    
  }
}
