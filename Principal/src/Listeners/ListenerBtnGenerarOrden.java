package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.GenerarOrdenWindow;
import app.Restaurant;


public class ListenerBtnGenerarOrden implements ActionListener {
  private Restaurant main;
  
  public ListenerBtnGenerarOrden(Restaurant main){
    this.main = main;
  }
  int c = 1;
  @Override
  public void actionPerformed(ActionEvent arg0) {
    new GenerarOrdenWindow(main);
  }

}
