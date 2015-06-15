package structures;

import java.util.ArrayList;

public class Cuenta {
  private int id;
  private Mesa mesa;
  private ArrayList<Orden> ordenes;
  private EstadoCuenta estado;
  private int total;
 
  public Cuenta(int id, Mesa mesa, ArrayList<Orden> ordenes) {
    super();
    this.id = id;
    this.mesa = mesa;
    this.ordenes = ordenes;
    estado = EstadoCuenta.PENDIENTE;
    calcularTotal();
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
  public EstadoCuenta getEstado() {
    return estado;
  }
  public void setEstado(EstadoCuenta estado) {
    this.estado = estado;
  }
  public int getTotal() {
    return total;
  }
  private void calcularTotal() {
    total = 0;
    
    for(Orden o:ordenes){
      for(int i = 0; i < o.getItems().size(); i++){
        total += o.getItems().get(i).getPrecio()*o.getCantidades().get(i);
      }
    }
  }
}
