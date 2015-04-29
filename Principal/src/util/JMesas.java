package util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import structures.Mesa;

public class JMesas extends JPanel {
  private static final long serialVersionUID = 1L;

  private int r_mesas = 60;
  private ArrayList<Mesa> mesas;
  
  public JMesas(ArrayList<Mesa> mesas){
    this.mesas = mesas;
    
    setBackground(Color.white); 
    
    Border border = BorderFactory.createLineBorder(Color.lightGray);
    setBorder(border);
  }
  
  private void doDrawing(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      
      for (Mesa mesa : mesas) {
        switch(mesa.getEstado()){
        case LIBRE:
          g2d.setColor(Color.GREEN);
          break;
        case OCUPADA:
          g2d.setColor(Color.RED);
          break;
        case OTRO:
          g2d.setColor(Color.YELLOW);
          break;
        default:
          g2d.setColor(Color.blue);
          break;
        }
        
        g2d.fillRect((int)(mesa.getPosx() - r_mesas/2), (int)(mesa.getPosy() - r_mesas/2), r_mesas, r_mesas);
        
        g2d.setColor(Color.black);
        g2d.drawString("#"+mesa.getId(), mesa.getPosx()-10, mesa.getPosy()+5);
      }
  }

  @Override
  public void paintComponent(Graphics g) {
      
      super.paintComponent(g);
      doDrawing(g);
  }
}
