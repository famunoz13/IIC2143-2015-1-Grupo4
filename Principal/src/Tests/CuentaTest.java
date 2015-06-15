package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import structures.Orden;
import app.Restaurant;
import structures.MenuItem;
import structures.Cuenta;

public class CuentaTest {
  private Restaurant rest;
  private ArrayList<Orden> ordenes;
  private Cuenta cuenta;
  
  @Before
  public void setUp() throws Exception {
    rest = new Restaurant("mesasprueba.xml", "menuprueba.xml");
    ordenes = new ArrayList<Orden>();
    
    ArrayList<MenuItem> items1 = new ArrayList<MenuItem>();
    ArrayList<MenuItem> items2 = new ArrayList<MenuItem>();
    items1.add(rest.getMenu().get(0));
    items2.add(rest.getMenu().get(0));
    items2.add(rest.getMenu().get(1));
    
    ArrayList<Integer> cantidades1 = new ArrayList<Integer>();
    ArrayList<Integer> cantidades2 = new ArrayList<Integer>();
    cantidades1.add(3);
    cantidades2.add(1);
    cantidades2.add(4);
    
    ordenes.add(new Orden(1, rest.getMesas().get(0), items1, cantidades1 , ""));
    ordenes.add(new Orden(2, rest.getMesas().get(0), items2, cantidades2 , ""));
    
    cuenta = new Cuenta(1, rest.getMesas().get(0), ordenes);
  }

  @Test
  public void testcalcularTotal() {
    assertEquals("test calcular total de cuenta pasado", cuenta.getTotal(), 11200);
  }
}
