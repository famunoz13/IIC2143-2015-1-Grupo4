package app;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import administration.LogicaAdministracion;
import Listeners.ListenerBtnAsignarMesa;
import Listeners.ListenerBtnGenerarCuenta;
import Listeners.ListenerBtnGenerarOrden;
import Listeners.ListenerBtnLiberarMesas;
import structures.*;

import java.io.File;
import java.util.ArrayList;

public class Restaurant{
  private ArrayList<Mesa> mesas;
  private Menu menu;

  private ArrayList<Orden> ordenes;
  private ArrayList<Cuenta> cuentas;

  private MainWindow main_window;

  private LogicaAdministracion administracion;
  
  private int id_orden = 1, id_cuenta = 1;
  
  public Restaurant() {
    // Load data
    mesas = new ArrayList<>();
    loadMesasXML("mesas.xml");

    menu = new Menu();
    loadMenuXML("menu.xml");

    ordenes = new ArrayList<>();
    cuentas = new ArrayList<>();
    
    //Create admin logic
    administracion = new LogicaAdministracion(this);
    
    //Create main window
    main_window = new MainWindow(this);
    main_window.setVisible(true);
    
    //Add Listeners
    main_window.btn_asignar.addActionListener(new ListenerBtnAsignarMesa(this));
    main_window.btn_liberar.addActionListener(new ListenerBtnLiberarMesas(this));
    main_window.btn_gnorden.addActionListener(new ListenerBtnGenerarOrden(this));
    main_window.btn_gncuenta.addActionListener(new ListenerBtnGenerarCuenta(this));
    
  }

  public Restaurant(String mesasXML, String menuXML) {
    // Load data
    mesas = new ArrayList<>();
    if(!mesasXML.equals(""))
      loadMesasXML(mesasXML);
    else
      loadMesasXML("mesas.xml");
      
    menu = new Menu();
    if(!menuXML.equals(""))
      loadMenuXML(menuXML);
    else
      loadMenuXML("menu.xml");
    
    ordenes = new ArrayList<>();
    cuentas = new ArrayList<>();
    
    //Create admin logic
    administracion = new LogicaAdministracion(this);
    
    //Create main window
    main_window = new MainWindow(this);
    main_window.setVisible(true);
    
    //Add Listeners
    main_window.btn_asignar.addActionListener(new ListenerBtnAsignarMesa(this));
    main_window.btn_liberar.addActionListener(new ListenerBtnLiberarMesas(this));
    main_window.btn_gnorden.addActionListener(new ListenerBtnGenerarOrden(this));
    main_window.btn_gncuenta.addActionListener(new ListenerBtnGenerarCuenta(this));
    
  }
  
  private void loadMesasXML(String path) {
    try {
      File fXmlFile = new File(path);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(fXmlFile);
      doc.getDocumentElement().normalize();

      NodeList nList = doc.getElementsByTagName("mesa");

      for (int temp = 0; temp < nList.getLength(); temp++) {
        Node nNode = nList.item(temp);

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
          Element eElement = (Element) nNode;

          int id = Integer.parseInt(eElement.getAttribute("id"));
          int posx = Integer.parseInt(eElement.getAttribute("posx"));
          int posy = Integer.parseInt(eElement.getAttribute("posy"));
          int capacidad = Integer.parseInt(eElement.getAttribute("capacidad"));

          mesas.add(new Mesa(id, posx, posy, capacidad));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void loadMenuXML(String path) {
    try {
      File fXmlFile = new File(path);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(fXmlFile);
      doc.getDocumentElement().normalize();

      NodeList nList = doc.getElementsByTagName("menuitem");

      for (int temp = 0; temp < nList.getLength(); temp++) {
        Node nNode = nList.item(temp);

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
          Element eElement = (Element) nNode;

          int id = Integer.parseInt(eElement.getAttribute("id"));
          String tipo = eElement.getAttribute("tipo");
          int precio = Integer.parseInt(eElement.getAttribute("precio"));
          String nombre = eElement.getAttribute("nombre");
          String descripcion = eElement.getAttribute("descripcion");

          menu.add(new MenuItem(id, tipo, precio, nombre, descripcion));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  
  public void AddOrden(Orden orden) {
    ordenes.add(orden);
    if(orden.getId()==0)
      orden.setId(id_orden++);
    
    main_window.AddOrden(orden);
  }
  
  public void removeOrden(Orden orden){
    main_window.removeOrden(orden);
  }
  
  public void AddCuenta(Cuenta cuenta) {
    cuentas.add(cuenta);
    cuenta.setId(id_cuenta++);
    
    main_window.AddCuenta(cuenta);
  }

  public void removeCuenta(Cuenta cuenta){
    main_window.removeCuenta(cuenta);
  }
  
  //Getters
  public ArrayList<Mesa> getMesas() {
    return mesas;
  }

  public ArrayList<Orden> getOrdenes() {
    return ordenes;
  }
  
  public ArrayList<Cuenta> getCuentas() {
    return cuentas;
  }
  
  public Menu getMenu() {
    return menu;
  }
  
  public boolean hayMesaLibre() {
    for(Mesa mesa:this.getMesas()){
      if(mesa.getEstado() == EstadoMesa.LIBRE){
        return true;
      }
    }
    return false;
  }

  public MainWindow getMainWindow(){
    return main_window;
  }

  public LogicaAdministracion getAdministracion(){
    return administracion;
  }

  public void reload() {
    // Load data
    mesas = new ArrayList<>();
    loadMesasXML("mesas.xml");

    menu = new Menu();
    loadMenuXML("menu.xml");

    ordenes = new ArrayList<>();
    cuentas = new ArrayList<>();
    
    //Create admin logic
    administracion = new LogicaAdministracion(this);
    
    //Create main window
    main_window.reloadGeneral(this);
    main_window.setVisible(true);
    
    //Add Listeners
    main_window.btn_asignar.addActionListener(new ListenerBtnAsignarMesa(this));
    main_window.btn_liberar.addActionListener(new ListenerBtnLiberarMesas(this));
    main_window.btn_gnorden.addActionListener(new ListenerBtnGenerarOrden(this));
    main_window.btn_gncuenta.addActionListener(new ListenerBtnGenerarCuenta(this));
    
  }
}
