package administration;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.Random;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import structures.MenuItem;

public class Menus_itemWindow extends JFrame implements ActionListener{
  private static final long serialVersionUID = 1L;
  private MenuItem item;
  private JButton button1, button2;
  private Menus_backend backend;
  
  public Menus_itemWindow(Menus_backend m, MenuItem e, final int mode) {
    super("Item del menú id(" + e.getId() +")");
    
    backend = m;
    item = e;
    
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setTitle("Item del menú id(" + e.getId() +")");

    //Layout
    setLayout(new GridBagLayout());
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;    
    
    gbc.insets = new Insets(5,16,5,16);
    gbc.weightx = 1;
    
    //Nombre
    JLabel label_nombre = new JLabel("Nombre:");
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    add(label_nombre,gbc);
    
    final JTextField tf_nombre = new JTextField(item.getNombre());
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 2;
    gbc.weightx = 1;
    add(tf_nombre,gbc);
    
    //Tipo
    JLabel label_tipo = new JLabel("Tipo:");
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 2;
    add(label_tipo,gbc);
   
    final JTextField tf_tipo = new JTextField(item.getTipo());
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 2;
    gbc.weightx = 1;
    add(tf_tipo,gbc);
    
    //Descripción
    JLabel label_descripcion = new JLabel("Descripción:");
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 2;
    add(label_descripcion,gbc);
   
    final JTextField tf_descripcion = new JTextField(item.getDescripcion());
    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.gridwidth = 2;
    gbc.weightx = 1;
    add(tf_descripcion,gbc);
    
    //Descripción
    JLabel label_precio = new JLabel("Precio:");
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 6;
    gbc.gridwidth = 2;
    add(label_precio,gbc);
   
    final JTextField tf_precio = new JTextField(item.getPrecio() + "");
    gbc.gridx = 0;
    gbc.gridy = 7;
    gbc.gridwidth = 2;
    gbc.weightx = 1;
    add(tf_precio,gbc);
    
    //Costo
    JLabel label_costo = new JLabel("Costo:");
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 8;
    gbc.gridwidth = 2;
    add(label_costo,gbc);
   
    final JTextField tf_costo = new JTextField(item.getCosto() + "");
    gbc.gridx = 0;
    gbc.gridy = 9;
    gbc.gridwidth = 2;
    gbc.weightx = 1;
    add(tf_costo,gbc);
    
    //Opciones
    JPanel p = new JPanel(new FlowLayout());
    gbc.fill = GridBagConstraints.NONE;   
    gbc.gridx = 0;
    gbc.gridy = 10;
    gbc.weighty = 0;
    gbc.weightx = 0;
    gbc.gridwidth = 2;
    add(p,gbc);
    
    
    GridBagConstraints gbc2 = new GridBagConstraints();
    gbc2.fill = GridBagConstraints.BOTH;    
    gbc2.insets = new Insets(4,4,4,4);
    

    button1 = new JButton("Guardar");
    p.add(button1);
    
    button2 = new JButton("Cancelar");
    p.add(button2);
    
    final Menus_itemWindow window = this;
    button1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        String nombre = tf_nombre.getText().trim();
        String tipo = tf_tipo.getText().trim();
        String descripcion = tf_descripcion.getText();
        
        if(nombre.length() == 0){
          JOptionPane.showMessageDialog(window,
              "El nombre no puede estar vacío",
              "Error al guardar",
              JOptionPane.WARNING_MESSAGE);
          return;
        }
        
        int precio = 0;
        int costo = 0;
        
        try {
          precio = Integer.parseInt(tf_precio.getText());
        } catch (NumberFormatException e) {
          JOptionPane.showMessageDialog(window,
              "Precio no válido",
              "Error al guardar",
              JOptionPane.WARNING_MESSAGE);
          return;
        }
        
        if(precio < 0){
          JOptionPane.showMessageDialog(window,
              "Precio no válido",
              "Error al guardar",
              JOptionPane.WARNING_MESSAGE);
          return;
        }
        
        try {
          costo = Integer.parseInt(tf_costo.getText());
        } catch (NumberFormatException e) {
          JOptionPane.showMessageDialog(window,
              "Costo no válido",
              "Error al guardar",
              JOptionPane.WARNING_MESSAGE);
          return;
        }
        if(costo < 0){
          JOptionPane.showMessageDialog(window,
              "Costo no válido",
              "Error al guardar",
              JOptionPane.WARNING_MESSAGE);
        }
        
        item.setNombre(nombre);
        item.setTipo(tipo);
        item.setDescripcion(descripcion);
        item.setPrecio(precio);
        item.setCosto(costo);
       
        if(mode == 1){
          backend.UpdateMenu(item);
        }else{
          backend.AddToMenu(item);
        }
        
        setVisible(false);
        dispose();
        
        return;
    }
    });
    
    button2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        setVisible(false);
        dispose();
    }
    });
    
    
    this.pack();
    this.setVisible(true);
    
    setLocationRelativeTo(null);
  }

  
  @Override
  public void actionPerformed(ActionEvent arg0) {
    
  }
}
