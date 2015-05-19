package Tests;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import structures.EstadoMesa;
import structures.Menu;
import structures.Mesa;
import app.Restaurant;

public class RestaurantTest {
  Restaurant rest;

  @Test
  public void testhayMesaLibre1() {
    rest = new Restaurant();
    for(Mesa mesa:rest.getMesas()){
      mesa.setEstado(EstadoMesa.OCUPADA);
    }
    
    assertFalse("test verificador de mesas ocupadas pasado", rest.hayMesaLibre());
  }
  
  @Test
  public void testhayMesaLibre2() {
    rest = new Restaurant();
    for(Mesa mesa:rest.getMesas()){
      mesa.setEstado(EstadoMesa.OCUPADA);
    } 
    rest.getMesas().get(0).setEstado(EstadoMesa.LIBRE);
    
    assertTrue("test verificador de mesas libres pasado", rest.hayMesaLibre());
  }

  @Test
  public void testloadMenuXML() {
    rest = new Restaurant("", "menuprueba.xml");
    
    assertTrue("test verificador de carga de menu pasado", rest.getMenu().getItems().get(0).getId()==1 &&
        rest.getMenu().getItems().get(0).getNombre().equals("Arroz con carne") &&
        rest.getMenu().getItems().get(0).getTipo().equals("plato") &&
        rest.getMenu().getItems().get(0).getPrecio()==2000 &&
        rest.getMenu().getItems().get(0).getDescripcion().equals("Básicamente arroz con carne") &&
        rest.getMenu().getItems().get(1).getId()==2 &&
        rest.getMenu().getItems().get(1).getNombre().equals("Jugo de naranja") &&
        rest.getMenu().getItems().get(1).getTipo().equals("jugo") &&
        rest.getMenu().getItems().get(1).getPrecio()==800 &&
        rest.getMenu().getItems().get(1).getDescripcion().equals("Básicamente jugo de naranja"));
  }
  
  @Test
  public void testloadMesasXML() {
    rest = new Restaurant("mesasprueba.xml", "");
    
    assertTrue("test verificador de carga de mesas pasado", rest.getMesas().get(0).getId()==1 &&
        rest.getMesas().get(0).getCapacidad()==6 &&
        rest.getMesas().get(0).getPosx()==10 &&
        rest.getMesas().get(0).getPosy()==10 &&
        rest.getMesas().get(1).getId()==2 &&
        rest.getMesas().get(1).getCapacidad()==4 &&
        rest.getMesas().get(1).getPosx()==80 &&
        rest.getMesas().get(1).getPosy()==20);
  }
}
