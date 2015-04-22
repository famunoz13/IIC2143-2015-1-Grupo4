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
import Listeners.ListenerBtnCancelarOrden;
import Listeners.ListenerBtnVerOrden;
import structures.*;

public class JOrden extends JPanel {
  private MainWindow main;
  
  private Orden orden;
  
  JLabel label,label2;
  JButton btn_ver,btn_marcar,btn_cancelar;
  
  private static final long serialVersionUID = 1L;

  public JOrden(MainWindow m, Orden o) {
    main = m;
    orden = o;
    
    setLayout(new GridBagLayout());
    setBorder(new EmptyBorder(10, 10, 10, 10));
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;    
    
    gbc.insets = new Insets(3,0,3,0);
    
    
    label = new JLabel("Orden #" + orden.getId(),JLabel.CENTER);
    gbc.gridx = 0;
    gbc.gridy = 0;
    add(label,gbc);
    
    label2 = new JLabel(o.getMesa().toString(),JLabel.LEFT);
    gbc.gridx = 0;
    gbc.gridy = 1;
    add(label2,gbc);
    
    btn_ver = new JButton("Ver orden");
    gbc.gridx = 0;
    gbc.gridy = 2;
    add(btn_ver,gbc);
    
    btn_marcar = new JButton("Marcar como entregada");
    gbc.gridx = 0;
    gbc.gridy = 3;
    add(btn_marcar,gbc);
    
    JPanel p = new JPanel(new FlowLayout());

    gbc.gridx = 0;
    gbc.gridy = 4;
    
    JLabel status = new JLabel("Espera");
    btn_cancelar = new JButton("Cancelar");
    
    p.add(status);
    p.add(btn_cancelar);
    
    add(p,gbc);
    
    status.setOpaque(true);
    status.setBackground(Color.CYAN); 
    status.setBorder(new EmptyBorder(4,8,4,8));
    
    p.setBackground(Color.white); 
    setBackground(Color.white); 
    
    Border border = BorderFactory.createLineBorder(Color.lightGray);
    setBorder(border);
    
    //Listeners
    btn_ver.addActionListener(new ListenerBtnVerOrden(main,orden));
    btn_cancelar.addActionListener(new ListenerBtnCancelarOrden(main, orden));
  }

  public Orden getOrden(){
    return orden;
  }
}
