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

public class AsignarMesaWindow extends JFrame implements ActionListener{
  private static final long serialVersionUID = 1L;
  private MainWindow main;
  private JButton button, button2;
  private JComboBox<Mesa> cb_options;
  private JComboBox<Integer> cb_cantidad;
  
  public AsignarMesaWindow(MainWindow m) {
    super("Asignar mesa");
    
    main = m;
    
    if(!m.hayMesaLibre()){
      setVisible(false);
      dispose();
      
      JOptionPane.showMessageDialog(main,
        "No hay mesas libres para asignar",
        "Error al asignar mesa",
        JOptionPane.WARNING_MESSAGE);
      return;
    }
    
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    this.setTitle("Asignar mesa");

    //Layout
    setLayout(new GridBagLayout());
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;    
    
    gbc.insets = new Insets(5,16,5,16);
    gbc.weightx = 1;
    
    JLabel label = new JLabel("Asignar la mesa:",JLabel.CENTER);
    gbc.gridx = 0;
    gbc.gridy = 0;
    add(label,gbc);
    
    cb_options = new JComboBox<>();
    for(Mesa mesa:main.getMesas()){
      //Revisar si la mesa esta libre
      if(mesa.getEstado() == EstadoMesa.LIBRE){
        cb_options.addItem(mesa);
      }
    }
    
    cb_options.setSelectedIndex(0);
    
    gbc.insets = new Insets(5,60,5,60);
    gbc.gridx = 0;
    gbc.gridy = 1;
    add(cb_options,gbc);
    
    gbc.insets = new Insets(20,16,2,16);
    
    JLabel label4 = new JLabel("Indique la cantidad de personas:",JLabel.CENTER);
    gbc.gridx = 0;
    gbc.gridy = 2;
    add(label4,gbc);
    
    cb_cantidad = new JComboBox<Integer>();
    for(int i = 1; i<=((Mesa)cb_options.getSelectedItem()).getCapacidad(); i++){
        cb_cantidad.addItem(i);
    }
    
    cb_cantidad.setSelectedIndex(0);
    
    gbc.insets = new Insets(2,85,2,85);
    gbc.gridx = 0;
    gbc.gridy = 3;
    add(cb_cantidad,gbc);
    
    gbc.insets = new Insets(5,16,5,16);
    
    JPanel p = new JPanel(new FlowLayout());
    gbc.fill = GridBagConstraints.NONE;   
    gbc.gridx = 0;
    gbc.gridy = 4;
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
        
        Mesa selected_mesa = (Mesa) cb_options.getSelectedItem();
        selected_mesa.setEstado(EstadoMesa.OCUPADA);
        selected_mesa.setGente((int)cb_cantidad.getSelectedItem());
        main.updateMesas();
      }
    });
    
    button2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        setVisible(false);
        dispose();
      }
    });
    
    cb_options.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {  	
        cb_cantidad.removeAllItems();    	
        for(int i = 1; i<=((Mesa)cb_options.getSelectedItem()).getCapacidad(); i++){
          cb_cantidad.addItem(i);
        }
      }
    });
    
    this.pack();
  }
  
  @Override
  public void actionPerformed(ActionEvent arg0) {
    
  }
}
