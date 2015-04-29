package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import structures.Mesa;
import structures.Orden;
import app.MainWindow;
import app.VerOrdenWindow;

public class ListenerBtnVerOrden implements ActionListener {
  @SuppressWarnings("unused")
  private MainWindow main;
  @SuppressWarnings("unused")
  private ArrayList<Mesa> mesas;
  
  private Orden orden;
  
  public ListenerBtnVerOrden(MainWindow main, Orden o){
    this.main = main;
    this.mesas = main.getMesas();
    orden = o;

  }
  
  @Override
  public void actionPerformed(ActionEvent arg0) {
    new VerOrdenWindow(orden);
  }

}
