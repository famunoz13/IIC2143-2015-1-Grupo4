package app;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import structures.Cuenta;
import structures.EstadoOrden;
import structures.Mesa;
import structures.Orden;

public class GenerarCuentaWindow extends JFrame implements ActionListener{
  private static final long serialVersionUID = 1L;
  private MainWindow main;
  private JButton button, button2;
  private JComboBox<Mesa> cb_options;
  
  public GenerarCuentaWindow(MainWindow m) {
    super("Generar cuenta");
    
    main = m;
    
    if(main.getOrdenes().size() == 0){
      setVisible(false);
      dispose();
      
      JOptionPane.showMessageDialog(main,
          "No se pueden crear cuentas",
          "No hay órdenes disponibles",
          JOptionPane.WARNING_MESSAGE);
      return;
    }
    
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    this.setTitle("Generar cuenta");

    //Layout
    setLayout(new GridBagLayout());
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;    
    
    gbc.insets = new Insets(5,16,5,16);
    gbc.weightx = 1;
    
    JLabel label = new JLabel("Generar cuenta para la mesa:",JLabel.CENTER);
    gbc.gridx = 0;
    gbc.gridy = 0;
    add(label,gbc);
    
    
    cb_options = new JComboBox<>();
    
    for(Mesa mesa:main.getMesas()){
      //Revisar si la mesa tienes ordenes entregadas
      boolean b_orden = false;
      
      for(Orden o:main.getOrdenes()){
        if(o.getMesa() == mesa && o.getEstado() == EstadoOrden.ENTREGADA){
          b_orden = true;
          break;
        }
      }
      if(b_orden){
        cb_options.addItem(mesa);
      }
    }
    
    if(cb_options.getItemCount() == 0){
      setVisible(false);
      dispose();
      
      JOptionPane.showMessageDialog(main,
          "No se pueden crear cuentas",
          "No hay órdenes disponibles.",
          JOptionPane.WARNING_MESSAGE);
      return;
    }
    
    
    cb_options.setSelectedIndex(0);
    gbc.gridx = 0;
    gbc.gridy = 1;
    add(cb_options,gbc);
    
    
    gbc.insets = new Insets(2,16,2,16);
    
    JLabel label4 = new JLabel("Las ordenes no antendidas se cancelaran",JLabel.CENTER);
    gbc.gridx = 0;
    gbc.gridy = 2;
    add(label4,gbc);
    
    gbc.insets = new Insets(5,16,5,16);
    
    JPanel p = new JPanel(new FlowLayout());
    gbc.fill = GridBagConstraints.NONE;   
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.weighty = 0;
    gbc.weightx = 0;
    add(p,gbc);
    
    
    button = new JButton("Aceptar");
    p.add(button);
    
    button2 = new JButton("Cancelar");
    p.add(button2);
    
    
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        setVisible(false);
        dispose();
        
        //Juntar todas las órdenes de la mesa y ponerlas en una cuenta
        ArrayList<Orden> ordenes = main.getOrdenes();
        ArrayList<Orden> ordenes_cuenta = new ArrayList<>();
        
        boolean del;
        Mesa selected_mesa = (Mesa) cb_options.getSelectedItem();
        do{
          del = false;
          for(Orden o:ordenes){
            if(o.getMesa() == selected_mesa && o.getEstado() == EstadoOrden.ENTREGADA){
              ordenes_cuenta.add(o);
              //Eliminar todas órdenes de la lista
              main.removeOrden(o);
              del = true;
              break;
            }
          }
        }while(del);
        
        //Añadir cuenta
        main.AddCuenta(new Cuenta(1,selected_mesa,ordenes_cuenta));
    }
    });
    
    button2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        setVisible(false);
        dispose();
    }
    });
    
    cb_options.addActionListener(this);
    
    this.pack();
    this.setVisible(true);
    
    setLocationRelativeTo(null);
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    
  }
}
