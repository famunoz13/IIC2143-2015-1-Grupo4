package app;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Listeners.ListenerBtnAsignarMesa;
import Listeners.ListenerBtnGenerarCuenta;
import Listeners.ListenerBtnGenerarOrden;
import Listeners.ListenerBtnLiberarMesas;
import structures.*;
import util.JCuenta;
import util.JMesas;
import util.JOrden;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.File;
import java.util.ArrayList;

public class MainWindow extends JFrame {
  private static final long serialVersionUID = 1L;
  private JPanel panel, panel_mesas, panel_ordenes, panel_cuentas, panel_info;

  private JPanel j_ordenes,j_cuentas;
  private JScrollPane scroll_cuentas, scroll_ordenes;
  
  private ArrayList<Mesa> mesas;
  private Menu menu;

  private ArrayList<Orden> ordenes;
  private ArrayList<Cuenta> cuentas;

  private JButton btn_asignar, btn_liberar, btn_gnorden, btn_gncuenta;

  private int id_orden = 1, id_cuenta = 1;
  
  public MainWindow() {
    super("Restaurant");
    setSize(960, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Load data
    mesas = new ArrayList<>();
    loadMesasXML("mesas.xml");

    menu = new Menu();
    loadMenuXML("menu.xml");

    // UI
    panel = new JPanel(new GridLayout(1, 1));
    add(panel);

    JTabbedPane tabbedPane = new JTabbedPane();

    JComponent tab_general = makeGeneralPanel(" #1");
    tabbedPane.addTab("General", null, tab_general, "Flujo general");

    JComponent tab_administracion = makeTextPanel("Administrar");
    tabbedPane.addTab("Administrar", null, tab_administracion, "Administrar");

    JComponent tab_opcion = makeTextPanel("Opciones");
    tabbedPane.addTab("Opciones", null, tab_opcion, "Opciones");

    panel.add(tabbedPane, BorderLayout.CENTER);

    //Add Listeners
    btn_asignar.addActionListener(new ListenerBtnAsignarMesa(this));
    btn_liberar.addActionListener(new ListenerBtnLiberarMesas(this));
    btn_gnorden.addActionListener(new ListenerBtnGenerarOrden(this));
    btn_gncuenta.addActionListener(new ListenerBtnGenerarCuenta(this));
    
    setLocationRelativeTo(null);
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

  private JComponent makeGeneralPanel(String text) {
    JPanel panel = new JPanel(false);
    panel.setLayout(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints();

    // Panel mesas
    initPanelMesas();
    c.weightx = 0.44;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 0;
    c.insets = new Insets(6, 6, 6, 6);
    panel.add(panel_mesas, c);

    // Panel ordenes
    initPanelOrdenes();
    c.fill = GridBagConstraints.BOTH;
    c.weightx = 0.28;
    c.weighty = 1;
    c.gridx = 1;
    c.gridy = 0;
    panel.add(panel_ordenes, c);

    // Panel cuentas
    initPanelCuentas();
    c.fill = GridBagConstraints.BOTH;
    c.weightx = 0.15;
    c.weighty = 1;
    c.gridx = 2;
    c.gridy = 0;
    panel.add(panel_cuentas, c);

    // Panel info
    initPanelInfo();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 0;
    c.weightx = 1.0;
    c.weighty = 0.0;
    c.gridx = 0;
    c.gridy = 1;
    c.insets = new Insets(0, 6, 6, 6);
    c.gridwidth = 3;
    panel.add(panel_info, c);

    return panel;
  }

  private void initPanelMesas() {
    panel_mesas = new JPanel(new BorderLayout());

    JLabel titulo = new JLabel("Mesas", JLabel.CENTER);
    panel_mesas.add(titulo, BorderLayout.PAGE_START);
    titulo.setFont(new Font("Sans", Font.PLAIN, 18));
    titulo.setBorder(new EmptyBorder(4,4,4,4));
    
    JMesas jmesas = new JMesas(mesas);
    panel_mesas.add(jmesas, BorderLayout.CENTER);
  }

  private void initPanelOrdenes() {
    panel_ordenes = new JPanel(new BorderLayout());

    // Título
    JLabel titulo = new JLabel("Órdenes", JLabel.CENTER);
    panel_ordenes.add(titulo, BorderLayout.PAGE_START);
    titulo.setFont(new Font("Sans", Font.PLAIN, 18));
    titulo.setBorder(new EmptyBorder(4,4,4,4));
    
    // Lista
    j_ordenes = new JPanel();
    j_ordenes.setLayout(new GridLayout(4, 1));
    ordenes = new ArrayList<>();

    

    scroll_ordenes =  new JScrollPane(j_ordenes,
      JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    panel_ordenes.add(scroll_ordenes, BorderLayout.CENTER);
  }

  private void initPanelCuentas() {
    panel_cuentas = new JPanel(new BorderLayout());

    JLabel titulo = new JLabel("Cuentas", JLabel.CENTER);
    panel_cuentas.add(titulo, BorderLayout.PAGE_START);
    titulo.setFont(new Font("Sans", Font.PLAIN, 18));
    titulo.setBorder(new EmptyBorder(4,4,4,4));
    
    // Lista
    j_cuentas = new JPanel();
    j_cuentas.setLayout(new GridLayout(4, 1));
    cuentas = new ArrayList<>();

    scroll_cuentas = new JScrollPane(j_cuentas,
      JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    panel_cuentas.add(scroll_cuentas, BorderLayout.CENTER);

    JPanel j_opciones = new JPanel();
    j_opciones.setLayout(new GridLayout(4, 1, 2, 6));
    j_opciones.setBorder(new EmptyBorder(8, 8, 8, 8));
    
    btn_asignar = new JButton("Asignar Mesa");
    btn_liberar = new JButton("Liberar Mesa");
    btn_gnorden = new JButton("Generar Orden");
    btn_gncuenta = new JButton("Generar cuenta");
    
    j_opciones.add(btn_asignar);
    j_opciones.add(btn_liberar);
    j_opciones.add(btn_gnorden);
    j_opciones.add(btn_gncuenta);
    
    panel_cuentas.add(j_opciones, BorderLayout.SOUTH);

  }

  private void initPanelInfo() {
    panel_info = new JPanel(new BorderLayout());

    JLabel titulo = new JLabel("", JLabel.CENTER);
    panel_info.add(titulo, BorderLayout.PAGE_START);

  }

  private JComponent makeTextPanel(String text) {
    JPanel panel = new JPanel(false);
    JLabel filler = new JLabel(text);
    filler.setHorizontalAlignment(JLabel.CENTER);
    panel.setLayout(new GridLayout(1, 1));
    panel.add(filler);
    return panel;
  }

  public void AddOrden(Orden orden) {
    ordenes.add(orden);
    orden.setId(id_orden++);
    
    if(ordenes.size() <= 4)
      j_ordenes.setLayout(new GridLayout(4, 1));
    else
      j_ordenes.setLayout(new GridLayout(ordenes.size(), 1));
    
    JOrden jorden = new JOrden(this,orden);
    j_ordenes.add(jorden);
    j_ordenes.updateUI();
  }
  
  public void removeOrden(Orden orden){
    for(Component c:j_ordenes.getComponents()){
      if(c.getClass() == JOrden.class){
        JOrden jo = (JOrden)c;
        if(jo.getOrden() == orden){
          j_ordenes.remove(jo);
          ordenes.remove(orden);
          
          if(ordenes.size() <= 4)
            j_ordenes.setLayout(new GridLayout(4, 1));
          else
            j_ordenes.setLayout(new GridLayout(ordenes.size(), 1));
          
          break;
        }
      }
    }
    
    j_ordenes.updateUI();
  }
  
  public void AddCuenta(Cuenta cuenta) {
    cuentas.add(cuenta);
    cuenta.setId(id_cuenta++);
    
    if(cuentas.size() <= 4)
      j_cuentas.setLayout(new GridLayout(4, 1));
    else
      j_cuentas.setLayout(new GridLayout(cuentas.size(), 1));
    
    JCuenta jcuenta = new JCuenta(this, cuenta);
    j_cuentas.add(jcuenta);
    j_cuentas.updateUI();
  }

  public void removeCuenta(Cuenta cuenta){
    for(Component c:j_cuentas.getComponents()){
      if(c.getClass() == JCuenta.class){
        JCuenta jo = (JCuenta)c;
        if(jo.getCuenta() == cuenta){
          j_cuentas.remove(jo);
          cuentas.remove(cuenta);
          
          if(cuentas.size() <= 4)
            j_cuentas.setLayout(new GridLayout(4, 1));
          else
            j_cuentas.setLayout(new GridLayout(cuentas.size(), 1));
          
          break;
        }
      }
    }
    
    j_cuentas.updateUI();
  }
  
  public void updateMesas() {
    panel_mesas.updateUI();
  }
  
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
}
