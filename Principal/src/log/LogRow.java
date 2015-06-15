package log;

import java.util.Date;

public class LogRow {
  private int id;
  private int id_item;
  private String tipo;
  private String nombre;
  private int cantidad;
  private int precio;
  private Date fecha;
  private int costo;
  
  public LogRow(int id){
    this.id = id;
  }

  public int getId(){
    return id;
  }
  
  public int getId_item() {
    return id_item;
  }

  public void setId_item(int id_item) {
    this.id_item = id_item;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getCantidad() {
    return cantidad;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  public int getPrecio() {
    return precio;
  }

  public void setPrecio(int precio) {
    this.precio = precio;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public int getCosto() {
    return costo;
  }

  public void setCosto(int costo) {
    this.costo = costo;
  }
  
  
}
