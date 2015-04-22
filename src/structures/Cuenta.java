package structures;

import java.util.ArrayList;

public class Cuenta {
  private int id;
  private Mesa mesa;
  private ArrayList<Orden> ordenes;
 
  public Cuenta(int id, Mesa mesa, ArrayList<Orden> ordenes) {
    super();
    this.id = id;
    this.mesa = mesa;
    this.ordenes = ordenes;
  }
  
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public Mesa getMesa() {
    return mesa;
  }
  public void setMesa(Mesa mesa) {
    this.mesa = mesa;
  }
  public ArrayList<Orden> getOrdenes() {
    return ordenes;
  }

}
