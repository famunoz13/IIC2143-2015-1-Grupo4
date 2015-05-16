package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import structures.Mesa;
import structures.Orden;
import app.Restaurant;
import app.VerOrdenWindow;

public class ListenerBtnVerOrden implements ActionListener {
  @SuppressWarnings("unused")
  private Restaurant main;
  @SuppressWarnings("unused")
  private ArrayList<Mesa> mesas;
  
  private Orden orden;
  
  public ListenerBtnVerOrden(Restaurant main, Orden o){
    this.main = main;
    this.mesas = main.getMesas();
    orden = o;

  }
  
  @Override
  public void actionPerformed(ActionEvent arg0) {
    new VerOrdenWindow(orden);
  }

}
