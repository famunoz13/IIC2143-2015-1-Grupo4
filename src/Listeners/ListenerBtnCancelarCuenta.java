package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import structures.Cuenta;
import structures.Orden;
import app.MainWindow;

public class ListenerBtnCancelarCuenta implements ActionListener {
  private MainWindow main;
  private Cuenta cuenta;
  
  public ListenerBtnCancelarCuenta(MainWindow main, Cuenta c){
    this.main = main;
    this.cuenta = c;
  }
  
  @Override
  public void actionPerformed(ActionEvent arg0) {
    int reply = JOptionPane.showConfirmDialog(null, "La orden sera completamente elimnada", 
        "Canelar orden",  JOptionPane.YES_NO_OPTION);
    if (reply == JOptionPane.YES_OPTION)
    {
      main.removeCuenta(cuenta);
      
      for(Orden o:cuenta.getOrdenes()){
        main.AddOrden(o);
      }
      
    }
    
  }

}
