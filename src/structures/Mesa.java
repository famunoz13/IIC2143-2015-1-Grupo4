package structures;

public class Mesa {
  private int posx,posy;
	private int id;
	private EstadoMesa estado;
	private int capacidad, gente;
	
  public Mesa(int id, int posx, int posy, int capacidad) {
    this.id = id;
    this.posx = posx;
    this.posy = posy;
    this.estado = EstadoMesa.LIBRE;
    this.capacidad = capacidad;
    this.gente = 0;
  }
	
  public int getPosx() {
    return posx;
  }
  public void setPosx(int posx) {
    this.posx = posx;
  }
  public int getPosy() {
    return posy;
  }
  public void setPosy(int posy) {
    this.posy = posy;
  }
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public EstadoMesa getEstado() {
    return estado;
  }
  public void setEstado(EstadoMesa estado) {
    this.estado = estado;
  }
  public int getCapacidad() {
    return capacidad;
  }
  public void setCapacidad(int capacidad) {
    this.capacidad = capacidad;
  }
  public int getGente() {
    return gente;
  }
  public void setGente(int gente) {
    this.gente = gente;
  }
}
