package app;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import structures.MenuItem;
import structures.Orden;

public class VerOrdenWindow extends JFrame implements ActionListener{
  private static final long serialVersionUID = 1L;

  private Orden orden;
  
  public VerOrdenWindow(Orden o) {
    super("Ver Orden");
    setSize(300, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    this.orden = o;
    
    this.setTitle("Ver Orden");

    //Layout
    setLayout(new GridBagLayout());
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;    
    
    gbc.insets = new Insets(5,16,5,16);
    gbc.weightx = 1;
    
    JLabel label = new JLabel("Orden: #" + orden.getId(),JLabel.CENTER);
    gbc.gridx = 0;
    gbc.gridy = 0;
    add(label,gbc);
    
    JLabel label2 = new JLabel("Mesa: #" + orden.getMesa().getId(),JLabel.LEFT);
    gbc.gridx = 0;
    gbc.gridy = 1;
    add(label2,gbc);
    
    JLabel label3 = new JLabel("Estado: " + orden.getEstado().toString() ,JLabel.LEFT);
    gbc.gridx = 0;
    gbc.gridy = 2;
    add(label3,gbc);
    
    gbc.insets = new Insets(2,16,2,16);
    
    JLabel label4 = new JLabel("Detalle",JLabel.CENTER);
    gbc.gridx = 0;
    gbc.gridy = 3;
    add(label4,gbc);
    
    JPanel panel = new JPanel();
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.weighty = 1;
    add(new JScrollPane(panel,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),gbc);
  
    panel.setLayout(new GridLayout(orden.getItems().size(), 2));
    
    Iterator<Integer> it = orden.getCantidades().iterator();
    int i = it.next();
    for(MenuItem p:orden.getItems()){
      JLabel plato = new JLabel(p.getNombre(),JLabel.LEFT);
      plato.setBorder(new EmptyBorder(6, 6, 6, 6));
      panel.add(plato);
      JLabel cantidad = new JLabel(i+"",JLabel.CENTER);
      cantidad.setBorder(new EmptyBorder(6, 6, 6, 6));
      panel.add(cantidad);
      if(it.hasNext())
        i = it.next();
    }
    
    gbc.insets = new Insets(5,16,5,16);
    
    gbc.fill = GridBagConstraints.NONE;
    JButton button = new JButton("Aceptar");
    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.weighty = 0;
    gbc.weightx = 0;
    add(button,gbc);
    
    button.addActionListener(this);
    
    this.pack();
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    setVisible(false);
    dispose();
  }
}
