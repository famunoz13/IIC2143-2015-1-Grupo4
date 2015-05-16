package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import structures.Mesa;
import app.AsignarMesaWindow;
import app.Restaurant;

public class ListenerBtnAsignarMesa implements ActionListener {
  private Restaurant main;
  @SuppressWarnings("unused")
  private ArrayList<Mesa> mesas;
  
  public ListenerBtnAsignarMesa(Restaurant main){
    this.main = main;
    this.mesas = main.getMesas();
  }
  
  @Override
  public void actionPerformed(ActionEvent arg0) {
    new AsignarMesaWindow(main);
  }

}
