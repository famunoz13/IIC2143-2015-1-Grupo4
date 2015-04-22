package app;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import structures.Mesa;
import structures.EstadoMesa;
import structures.Orden;
import structures.Cuenta;
import structures.EstadoOrden;
import structures.EstadoCuenta;

public class LiberarMesaWindow extends JFrame implements ActionListener{
  private static final long serialVersionUID = 1L;
  private MainWindow main;
  private JButton button, button2;
  private JComboBox<Mesa> cb_options;

  public LiberarMesaWindow(MainWindow m) {
    super("Liberar mesa");
    
    main = m;
    
    boolean b_mesas_ocupadas = false;
    for(Mesa mesa:main.getMesas()){
      //Revisar si hay mesas ocupadas
      if(mesa.getEstado() == EstadoMesa.OCUPADA || mesa.getEstado() == EstadoMesa.OTRO){
        b_mesas_ocupadas = true;
      }
    }
    
    if(!b_mesas_ocupadas){
      setVisible(false);
      dispose();
      
      JOptionPane.showMessageDialog(main,
        "No hay mesas ocupadas para liberar",
        "Error al liberar mesa",
        JOptionPane.WARNING_MESSAGE);
      
      return;
    }
    
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    this.setTitle("Liberar mesa");

    //Layout
    setLayout(new GridBagLayout());
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;    
    
    gbc.insets = new Insets(5,16,5,16);
    gbc.weightx = 1;
    
    JLabel label = new JLabel("Liberar la mesa:",JLabel.CENTER);
    gbc.gridx = 0;
    gbc.gridy = 0;
    add(label,gbc);
    
    cb_options = new JComboBox<>();
    for(Mesa mesa:main.getMesas()){
      //Revisar si la mesa esta ocupada
      if(mesa.getEstado() == EstadoMesa.OCUPADA || mesa.getEstado() == EstadoMesa.OTRO){
        cb_options.addItem(mesa);
      }
    }
    
    cb_options.setSelectedIndex(0);
    
    gbc.insets = new Insets(5,60,5,60);
    gbc.gridx = 0;
    gbc.gridy = 1;
    add(cb_options,gbc);
    
    gbc.insets = new Insets(5,16,5,16);
    
    JPanel p = new JPanel(new FlowLayout());
    gbc.fill = GridBagConstraints.NONE;   
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weighty = 0;
    gbc.weightx = 0;
    add(p,gbc);
    
    button = new JButton("Aceptar");
    p.add(button);
    
    button2 = new JButton("Cancelar");
    p.add(button2);
    
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        Mesa selected_mesa = (Mesa) cb_options.getSelectedItem();
        boolean ordenes_pendientes = false;
        boolean cuentas_pendientes = false;
        
        for(Orden orden:main.getOrdenes()){
          //Revisar si la mesa tiene ordenes pendientes
          if(orden.getMesa().equals(selected_mesa) && orden.getEstado() != EstadoOrden.ENTREGADA){
            ordenes_pendientes = true;
          }
        }
        
        for(Cuenta cuenta:main.getCuentas()){
          //Revisar si la mesa tiene cuentas pendientes
          if(cuenta.getMesa().equals(selected_mesa) && cuenta.getEstado() == EstadoCuenta.PENDIENTE){
            cuentas_pendientes = true;
          }
        }
        
        if(!ordenes_pendientes && !cuentas_pendientes) {
          setVisible(false);
          dispose();
          
          selected_mesa.setEstado(EstadoMesa.LIBRE);
          selected_mesa.setGente(0);
          main.updateMesas();
        } else {
          JOptionPane.showMessageDialog(main,
            "La mesa tiene órdenes o cuentas pendientes",
            "Error al liberar mesa",
            JOptionPane.WARNING_MESSAGE);
        }
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
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    
  }
}
