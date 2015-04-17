package structures;

import java.util.ArrayList;
import java.util.Iterator;

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
  
  public Iterator<MenuItem> getIterator(){
    return items.iterator();
  }
  
}
