package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import structures.Orden;
import app.Restaurant;

public class ListenerBtnCancelarOrden implements ActionListener {
  private Restaurant main;
  private Orden orden;
  
  public ListenerBtnCancelarOrden(Restaurant main, Orden o){
    this.main = main;
    this.orden = o;
  }
  
  @Override
  public void actionPerformed(ActionEvent arg0) {
    int reply = JOptionPane.showConfirmDialog(null, "La orden ser� completamente eliminada", 
        "Cancelar orden",  JOptionPane.YES_NO_OPTION);
    if (reply == JOptionPane.YES_OPTION)
    {
      main.cancelarOrden(orden);
      main.removeOrden(orden);
    }
    
  }

}
