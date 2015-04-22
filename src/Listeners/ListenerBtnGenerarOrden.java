package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import structures.Menu;
import structures.MenuItem;
import structures.Mesa;
import structures.Orden;
import app.MainWindow;


public class ListenerBtnGenerarOrden implements ActionListener {
  private MainWindow main;
  private ArrayList<Mesa> mesas;
  
  public ListenerBtnGenerarOrden(MainWindow main){
    this.main = main;
    this.mesas = main.getMesas();
  }
  int c = 1;
  @Override
  public void actionPerformed(ActionEvent arg0) {
    Menu menu = main.getMenu();
    
    Random rand = new Random();
    int nplatos = rand.nextInt(4) + 1;
    
    ArrayList<MenuItem> items = new ArrayList<>();
    ArrayList<Integer> q = new ArrayList<>();
    
    for(int i=0;i<nplatos;i++){
      items.add(menu.get(rand.nextInt(menu.size())));
      q.add(rand.nextInt(4)+1);
    }
    
    main.AddOrden(new Orden(c++,mesas.get(rand.nextInt(mesas.size())),items,q));
  }

}
