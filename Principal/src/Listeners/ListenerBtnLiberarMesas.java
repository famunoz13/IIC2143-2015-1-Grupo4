package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import structures.Mesa;
import app.LiberarMesaWindow;
import app.Restaurant;

public class ListenerBtnLiberarMesas implements ActionListener {
  private Restaurant main;
  @SuppressWarnings("unused")
  private ArrayList<Mesa> mesas;
  
  public ListenerBtnLiberarMesas(Restaurant main){
    this.main = main;
    this.mesas = main.getMesas();
  }
  
  @Override
  public void actionPerformed(ActionEvent arg0) {
    new LiberarMesaWindow(main);
  }

}
