package structures;

import java.util.ArrayList;

public class Menu {
  private ArrayList<MenuItem> items;
  
  public Menu(){
    items = new ArrayList<MenuItem>();
  }
  
  public void add(MenuItem e){
    items.add(e);
  }
  
  public void remove(MenuItem e){
    items.remove(e);
  }
  
  public ArrayList<MenuItem> getItems(){
    return items;
  }
  
  public MenuItem get(int i){
    return items.get(i);
  }
  
  public int size(){
    return items.size();
  }
}
