package structures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Orden implements Serializable {
  private static final long serialVersionUID = 1L;
  private int id;
   private Mesa mesa;
   private EstadoOrden estado;
   private ArrayList<MenuItem> items;
   private ArrayList<Integer> cantidades;
   private String nota;
   private Date fecha;
   
   
   public Orden(int id, Mesa mesa, ArrayList<MenuItem> items, ArrayList<Integer> cantidades, String nota) {
    this.id = id;
    this.mesa = mesa;
    this.estado = EstadoOrden.ESPERA;
    this.items = items;
    this.cantidades = cantidades;
    this.nota = nota;
    this.fecha = new Date();
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
  
  public String getNota() {
    return nota;
  }
  
  public void setNota(String nota) {
    this.nota = nota;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }
  
}
