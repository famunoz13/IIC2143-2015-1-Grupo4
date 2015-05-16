package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import structures.Cuenta;
import structures.Orden;
import app.Restaurant;

public class ListenerBtnCancelarCuenta implements ActionListener {
  private Restaurant main;
  private Cuenta cuenta;
  
  public ListenerBtnCancelarCuenta(Restaurant main, Cuenta c){
    this.main = main;
    this.cuenta = c;
  }
  
  @Override
  public void actionPerformed(ActionEvent arg0) {
    int reply = JOptionPane.showConfirmDialog(null, "La cuenta ser� eliminada", 
        "Cancelar cuenta",  JOptionPane.YES_NO_OPTION);
    if (reply == JOptionPane.YES_OPTION)
    {
      main.removeCuenta(cuenta);
      
      for(Orden o:cuenta.getOrdenes()){
        main.AddOrden(o);
      }
      
    }
    
  }

}
