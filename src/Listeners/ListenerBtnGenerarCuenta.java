package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import structures.Mesa;
import app.GenerarCuentaWindow;
import app.MainWindow;


public class ListenerBtnGenerarCuenta implements ActionListener {
  private MainWindow main;
  @SuppressWarnings("unused")
  private ArrayList<Mesa> mesas;
  
  public ListenerBtnGenerarCuenta(MainWindow main){
    this.main = main;
    this.mesas = main.getMesas();
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    GenerarCuentaWindow w = new GenerarCuentaWindow(main);
    w.setVisible(true);
  }

}
