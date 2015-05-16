package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import structures.Cuenta;
import app.IngresarPagoWindow;
import app.Restaurant;


public class ListenerBtnIngresarPago implements ActionListener {
  private Restaurant main;
  private Cuenta cuenta;
  
  public ListenerBtnIngresarPago(Restaurant main, Cuenta c){
    this.main = main;
    this.cuenta = c;
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    new IngresarPagoWindow(main, cuenta);
  }

}
