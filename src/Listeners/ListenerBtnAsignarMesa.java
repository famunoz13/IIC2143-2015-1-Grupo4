package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import structures.Mesa;
import app.AsignarMesaWindow;
import app.MainWindow;

public class ListenerBtnAsignarMesa implements ActionListener {
  private MainWindow main;
  @SuppressWarnings("unused")
  private ArrayList<Mesa> mesas;
  
  public ListenerBtnAsignarMesa(MainWindow main){
    this.main = main;
    this.mesas = main.getMesas();
  }
  
  @Override
  public void actionPerformed(ActionEvent arg0) {
    AsignarMesaWindow w = new AsignarMesaWindow(main);
    w.setVisible(true);
  }

}
