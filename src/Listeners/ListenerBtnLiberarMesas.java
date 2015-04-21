package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import structures.Mesa;
import app.MainWindow;

public class ListenerBtnLiberarMesas implements ActionListener {
  @SuppressWarnings("unused")
  private MainWindow main;
  @SuppressWarnings("unused")
  private ArrayList<Mesa> mesas;
  
  public ListenerBtnLiberarMesas(MainWindow main){
    this.main = main;
    this.mesas = main.getMesas();
  }
  
  @Override
  public void actionPerformed(ActionEvent arg0) {
    System.out.println("Liberando mesa :D");
  }

}
