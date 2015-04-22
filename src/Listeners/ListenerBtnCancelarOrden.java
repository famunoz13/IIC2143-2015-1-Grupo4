package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import structures.Orden;
import app.MainWindow;

public class ListenerBtnCancelarOrden implements ActionListener {
  private MainWindow main;
  private Orden orden;
  
  public ListenerBtnCancelarOrden(MainWindow main, Orden o){
    this.main = main;
    this.orden = o;
  }
  
  @Override
  public void actionPerformed(ActionEvent arg0) {
    int reply = JOptionPane.showConfirmDialog(null, "La orden sera completamente elimnada", 
        "Canelar orden",  JOptionPane.YES_NO_OPTION);
    if (reply == JOptionPane.YES_OPTION)
    {
      main.removeOrden(orden);
    }
    
  }

}
