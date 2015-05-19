package frontend;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import logic.FILA;
import structures.Orden;

public class JOrden extends JPanel{
  private static final long serialVersionUID = 1L;
  private JLabel label;
  private JButton btn_ver,btn_mid,btn_cancelar;
  private Orden orden;
  
  public JOrden(Orden o){
    orden = o;
    
    setLayout(new GridBagLayout());
    setBorder(new EmptyBorder(10, 10, 10, 10));
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;    
    
    gbc.insets = new Insets(16,8,4,8);
    gbc.weightx = 1;
    gbc.weighty = 0;
    
    label = new JLabel("Orden #" + orden.getId(),JLabel.CENTER);
    gbc.gridx = 0;
    gbc.gridy = 0;
    add(label,gbc);
    
    gbc.insets = new Insets(2,8,2,8);
    
    btn_ver = new JButton("Ver orden");
    gbc.gridx = 0;
    gbc.gridy = 1;
    add(btn_ver,gbc);
    
    btn_mid = new JButton("Marcar como entregada");
    gbc.gridx = 0;
    gbc.gridy = 2;
    add(btn_mid,gbc);
    
    gbc.insets = new Insets(16,8,16,8);
    btn_cancelar = new JButton("Cancelar");
    gbc.gridx = 0;
    gbc.gridy = 3;
    add(btn_cancelar,gbc);
    
    
    setBackground(Color.white); 
    
    Border border = BorderFactory.createLineBorder(Color.lightGray);
    setBorder(border);
  }

  public void UpdateView(FILA fila){
    switch(fila){
    case ATENDIDA:
      btn_mid.setVisible(false);
      btn_cancelar.setText("Marcar como no lista");
      break;
    case ATENDIENDO:
      btn_mid.setVisible(true);
      btn_mid.setText("Marcar como lista");
      btn_cancelar.setText("Desatender");
      break;
    case ESPERA:default:
      btn_mid.setVisible(true);
      btn_mid.setText("Atender");
      btn_cancelar.setText("Cancelar");
      break;
    
    }
  }
  
  public JButton getBtn_ver() {
    return btn_ver;
  }

  public JButton getBtn_mid() {
    return btn_mid;
  }

  public JButton getBtn_cancelar() {
    return btn_cancelar;
  }

  public Orden getOrden() {
    return orden;
  }
  
  
}
