package structures;

import java.util.ArrayList;

public class Orden {
   private int id;
   private Mesa mesa;
   private EstadoOrden estado;
   private ArrayList<MenuItem> items;
   private ArrayList<Integer> cantidades;
 
   public Orden(int id, Mesa mesa, ArrayList<MenuItem> items, ArrayList<Integer> cantidades) {
    this.id = id;
    this.mesa = mesa;
    this.estado = EstadoOrden.ESPERA;
    this.items = items;
    this.cantidades = cantidades;
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
  public EstadoOrden getEstado() {
    return estado;
  }
  public void setEstado(EstadoOrden estado) {
    this.estado = estado;
  }
  public ArrayList<MenuItem> getItems() {
    return items;
  }
  public void setItems(ArrayList<MenuItem> items) {
    this.items = items;
  }
  public ArrayList<Integer> getCantidades() {
    return cantidades;
  }
  public void setCantidades(ArrayList<Integer> cantidades) {
    this.cantidades = cantidades;
  }

}
