package structures;

public class Mesa {
  private double posx,posy;
	private int id;
	private EstadoMesa estado;
	private int capacidad, gente;
	
  public Mesa(int id, double posx, double posy, int capacidad, int gente) {
    this.id = id;
    this.posx = posx;
    this.posy = posy;
    this.estado = EstadoMesa.LIBRE;
    this.capacidad = capacidad;
    this.gente = gente;
  }
	
  public double getPosx() {
    return posx;
  }
  public void setPosx(double posx) {
    this.posx = posx;
  }
  public double getPosy() {
    return posy;
  }
  public void setPosy(double posy) {
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
