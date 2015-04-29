package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.GenerarOrdenWindow;
import app.MainWindow;


public class ListenerBtnGenerarOrden implements ActionListener {
  private MainWindow main;
  
  public ListenerBtnGenerarOrden(MainWindow main){
    this.main = main;
  }
  int c = 1;
  @Override
  public void actionPerformed(ActionEvent arg0) {
    new GenerarOrdenWindow(main);
  }

}
