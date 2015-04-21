package util;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import structures.*;

public class JCuenta extends JPanel {
  private Cuenta cuenta;
  
  JLabel label,label2;
  JButton btn_ingresar,btn_cancelar;
  
  private static final long serialVersionUID = 1L;

  public JCuenta(Cuenta c) {
    cuenta = c;
    
    setLayout(new GridBagLayout());
    setBorder(new EmptyBorder(10, 10, 10, 10));
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;    
    
    gbc.insets = new Insets(3,0,3,0);
    
    
    label = new JLabel("Cuenta #" + cuenta.getId(),JLabel.CENTER);
    gbc.gridx = 0;
    gbc.gridy = 0;
    add(label,gbc);
    
    label2 = new JLabel("Qwert yuio asd",JLabel.LEFT);
    gbc.gridx = 0;
    gbc.gridy = 1;
    add(label2,gbc);
    
    btn_ingresar = new JButton("Ingresar pago");
    gbc.gridx = 0;
    gbc.gridy = 2;
    add(btn_ingresar,gbc);
    
    JPanel p = new JPanel(new FlowLayout());

    gbc.gridx = 0;
    gbc.gridy = 3;
    
    JLabel status = new JLabel("Espera");
    btn_cancelar = new JButton("Cancelar");
    
    p.add(status);
    p.add(btn_cancelar);
    
    add(p,gbc);
    
    p.setBackground(Color.white); 
    setBackground(Color.white); 
    
    Border border = BorderFactory.createLineBorder(Color.lightGray);
    setBorder(border);
  }

}
