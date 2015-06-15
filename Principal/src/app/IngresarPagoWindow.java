package app;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.border.EmptyBorder;

import log.Logger;
import structures.Cuenta;
import structures.EstadoCuenta;
import structures.Orden;

public class IngresarPagoWindow extends JFrame implements ActionListener{
  private static final long serialVersionUID = 1L;
  private Restaurant main;
  private JButton button1, button2, button3;
  private JPanel boleta;
  
  private int pago, propina, total;
  private JLabel prev, label_pago_monto, label_propina_monto, label_vuelto_monto;
  private Cuenta cuenta;
  
  public IngresarPagoWindow(Restaurant m, Cuenta c) {
    super("Ingresar pago");
    
    main = m;
    cuenta = c;
    
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setTitle("Ingresar pago");

    total = cuenta.getTotal();
    
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
    
    JLabel label3 = new JLabel("$"+ total);
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.weightx = 1;
    add(label3,gbc);
    
    JLabel label4 = new JLabel("Propina: ");
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.weightx = 0;
    add(label4, gbc);
    
    JLabel label5 = new JLabel("$");
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.weightx = 1;
    add(label5, gbc);
    
    gbc.insets = new Insets(5,28,5,100);
    final JTextField textfield = new JTextField();
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.weightx = 1;
    add(textfield,gbc);
    
    gbc.insets = new Insets(5,16,5,16);
    JLabel label6 = new JLabel("Pago: ");
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    gbc.weightx = 0;
    add(label6, gbc);
    
    JLabel label7 = new JLabel("$");
    gbc.gridx = 1;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    gbc.weightx = 1;
    add(label7, gbc);
    
    gbc.insets = new Insets(5,28,5,100);
    final JTextField textfield2 = new JTextField();
    gbc.gridx = 1;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    gbc.weightx = 1;
    add(textfield2,gbc);
    
    gbc.insets = new Insets(5,16,5,16);
    button1 = new JButton("Ingresar pago");
    gbc.fill = GridBagConstraints.NONE;
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 2;
    add(button1,gbc);
    
    initBoleta();
    
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.gridwidth = 2;
    add(boleta,gbc);
    
    JPanel p = new JPanel(new FlowLayout());
    gbc.fill = GridBagConstraints.NONE;   
    gbc.gridx = 0;
    gbc.gridy = 6;
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
          pago = Integer.parseInt(textfield2.getText());
          propina = Integer.parseInt(textfield.getText());
          if(pago < propina + total){
            prev.setText("- Falta dinero para el pago -");
          }else{
            prev.setText("");
            label_pago_monto.setText("$" + pago);
            label_propina_monto.setText("$" + propina);
            label_vuelto_monto.setText("$" + (pago - total - propina));
            button1.setEnabled(false);
            button1.setText("PAGO INGRESADO");
            button2.setEnabled(true);
            button3.setEnabled(false);
            textfield.setEnabled(false);
            textfield2.setEnabled(false);
            
            cuenta.setEstado(EstadoCuenta.PAGADA);
            Logger.getInstance().log(cuenta);
            main.removeCuenta(cuenta);
            
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
          }
        } catch (NumberFormatException e) {
          prev.setText("- Error en el input -");
        }
      }
    });
    
    button2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    
    setLocationRelativeTo(null);
  }

  private void initBoleta() {
    boleta = new JPanel();
    
    boleta.setBorder(new EmptyBorder(20,20,20,20));
    boleta.setLayout(new GridBagLayout());
    
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.fill = GridBagConstraints.BOTH;    
    gbc.insets = new Insets(16,16,16,16);
    gbc.weightx = 1;
    
    JLabel titulo = new JLabel("Boleta", JLabel.CENTER); 
    Font f = titulo.getFont();
    titulo.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weighty = 0;
    gbc.weightx = 1;
    gbc.gridwidth = 2;
    boleta.add(titulo,gbc);
    
    gbc.insets = new Insets(4,16,4,16);
    int i = 1;
    for(Orden o:cuenta.getOrdenes()){
      for(int j = 0; j < o.getItems().size(); j++){
        
        JLabel label = new JLabel(o.getItems().get(j).getNombre()); 
        gbc.fill = GridBagConstraints.BOTH;   
        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.weighty = 0;
        gbc.weightx = 1;
        gbc.gridwidth = 1;
        boleta.add(label,gbc);
        
        JLabel label2 = new JLabel("$" + o.getItems().get(j).getPrecio(),JLabel.RIGHT); 
        gbc.fill = GridBagConstraints.NONE;   
        gbc.gridx = 1;
        gbc.gridy = i;
        gbc.weighty = 0;
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        boleta.add(label2,gbc);
        
        JLabel label3 = new JLabel("x " + o.getCantidades().get(j),JLabel.RIGHT); 
        gbc.fill = GridBagConstraints.NONE;   
        gbc.gridx = 2;
        gbc.gridy = i;
        gbc.weighty = 0;
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        boleta.add(label3,gbc);
        
        i++;
      }
    }
    
    gbc.insets = new Insets(16,16,2,16);
    
    JLabel label_total = new JLabel("Total:"); 
    gbc.fill = GridBagConstraints.BOTH;   
    gbc.gridx = 0;
    gbc.gridy = i;
    gbc.weighty = 0;
    gbc.weightx = 1;
    gbc.gridwidth = 1;
    boleta.add(label_total,gbc);
    
    JLabel label_sum = new JLabel("$" + cuenta.getTotal(),JLabel.RIGHT); 
    gbc.fill = GridBagConstraints.NONE;   
    gbc.gridx = 1;
    gbc.gridy = i;
    gbc.weighty = 0;
    gbc.weightx = 0;
    gbc.gridwidth = 1;
    boleta.add(label_sum,gbc);
    
    gbc.insets = new Insets(2,16,2,16);
    i++;
    
    JLabel label_propina = new JLabel("Propina:"); 
    gbc.fill = GridBagConstraints.BOTH;   
    gbc.gridx = 0;
    gbc.gridy = i;
    gbc.weighty = 0;
    gbc.weightx = 1;
    gbc.gridwidth = 1;
    boleta.add(label_propina,gbc);
    
    label_propina_monto = new JLabel("",JLabel.RIGHT); 
    gbc.fill = GridBagConstraints.NONE;   
    gbc.gridx = 1;
    gbc.gridy = i;
    gbc.weighty = 0;
    gbc.weightx = 0;
    gbc.gridwidth = 1;
    boleta.add(label_propina_monto,gbc);
    
    gbc.insets = new Insets(2,16,2,16);
    i++;
    
    JLabel label_pago = new JLabel("Pago:"); 
    gbc.fill = GridBagConstraints.BOTH;   
    gbc.gridx = 0;
    gbc.gridy = i;
    gbc.weighty = 0;
    gbc.weightx = 1;
    gbc.gridwidth = 1;
    boleta.add(label_pago,gbc);
    
    label_pago_monto = new JLabel("",JLabel.RIGHT); 
    gbc.fill = GridBagConstraints.NONE;   
    gbc.gridx = 1;
    gbc.gridy = i;
    gbc.weighty = 0;
    gbc.weightx = 0;
    gbc.gridwidth = 1;
    boleta.add(label_pago_monto,gbc);
    
    gbc.insets = new Insets(2,16,2,16);
    i++;
    
    JLabel label_vuelto = new JLabel("Vuelto:"); 
    gbc.fill = GridBagConstraints.BOTH;   
    gbc.gridx = 0;
    gbc.gridy = i;
    gbc.weighty = 0;
    gbc.weightx = 1;
    gbc.gridwidth = 1;
    boleta.add(label_vuelto,gbc);
    
    label_vuelto_monto = new JLabel("",JLabel.RIGHT); 
    gbc.fill = GridBagConstraints.NONE;   
    gbc.gridx = 1;
    gbc.gridy = i;
    gbc.weighty = 0;
    gbc.weightx = 0;
    gbc.gridwidth = 1;
    boleta.add(label_vuelto_monto,gbc);
    
    gbc.insets = new Insets(4,16,4,16);
    i++;
    
    prev = new JLabel(" ",JLabel.CENTER);
    gbc.fill = GridBagConstraints.BOTH;   
    gbc.gridx = 0;
    gbc.gridy = i;
    gbc.weighty = 0;
    gbc.weightx = 1;
    gbc.gridwidth = 2;
    boleta.add(prev,gbc);
    
    boleta.setBackground(Color.WHITE);
    this.setVisible(true);
  }

  public void generarBoleta(){
    
  }
  
  @Override
  public void actionPerformed(ActionEvent arg0) {
    
  }
}
