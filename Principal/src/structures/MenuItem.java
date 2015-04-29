package structures;

public class MenuItem {
  private int id;
  private String tipo;
  
  private int precio;
  private String nombre, descripcion;
  
  public MenuItem(int id, String tipo, int precio, String nombre, String descripcion) {
    this.id = id;
    this.tipo = tipo;
    this.precio = precio;
    this.nombre = nombre;
    this.descripcion = descripcion;
  }
  
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getTipo() {
    return tipo;
  }
  public void setTipo(String tipo) {
    this.tipo = tipo;
  }
  public int getPrecio() {
    return precio;
  }
  public void setPrecio(int precio) {
    this.precio = precio;
  }
  public String getNombre() {
    return nombre;
  }
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  public String getDescripcion() {
    return descripcion;
  }
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
}
