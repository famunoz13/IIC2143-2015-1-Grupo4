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

import app.MainWindow;
import Listeners.ListenerBtnCancelarCuenta;
import Listeners.ListenerBtnIngresarPago;
import structures.*;

public class JCuenta extends JPanel {
  private Cuenta cuenta;
  
  private MainWindow main;
  
  private JLabel label,label2;
  private JButton btn_ingresar,btn_cancelar;
  
  private static final long serialVersionUID = 1L;
  
  public JCuenta(MainWindow m, Cuenta c) {
    main = m;
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
    
    label2 = new JLabel(c.getMesa().toString(),JLabel.LEFT);
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
    
    //Listener
    btn_ingresar.addActionListener(new ListenerBtnIngresarPago(main, cuenta));
    btn_cancelar.addActionListener(new ListenerBtnCancelarCuenta(main, cuenta));
  }
  
  public Cuenta getCuenta(){
    return cuenta;
  }

}
