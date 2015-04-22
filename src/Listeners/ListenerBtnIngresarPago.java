package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import structures.Cuenta;
import app.IngresarPagoWindow;
import app.MainWindow;


public class ListenerBtnIngresarPago implements ActionListener {
  private MainWindow main;
  private Cuenta cuenta;
  
  public ListenerBtnIngresarPago(MainWindow main, Cuenta c){
    this.main = main;
    this.cuenta = c;
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    IngresarPagoWindow w = new IngresarPagoWindow(main, cuenta);
    w.setVisible(true);
  }

}
