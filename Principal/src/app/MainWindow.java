package app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicTabbedPaneUI.TabbedPaneLayout;

import structures.Cuenta;
import structures.Orden;
import util.JCuenta;
import util.JMesas;
import util.JOrden;

public class MainWindow extends JFrame {
  private static final long serialVersionUID = 1L;

  private Restaurant logica;
  
  private JPanel panel, panel_mesas, panel_ordenes, panel_cuentas, panel_info;

  private JPanel j_ordenes,j_cuentas;
  private JScrollPane scroll_cuentas, scroll_ordenes;
  
  public JButton btn_asignar, btn_liberar, btn_gnorden, btn_gncuenta;
  
  private JComponent tab_general;
  private JTabbedPane tabbedPane ;
  public MainWindow(Restaurant r) {
    super("Restaurant");
    setSize(960, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //References
    logica = r;
    
    // UI
    panel = new JPanel(new GridLayout(1, 1));
    add(panel);

    tabbedPane = new JTabbedPane();

    tab_general = makeGeneralPanel(" #1");
    tabbedPane.addTab("General", null, tab_general, "Flujo general");

    JComponent tab_administracion = logica.getAdministracion().getPanel();
    tabbedPane.addTab("Administrar", null, tab_administracion, "Administrar");

    JComponent tab_opcion = makeTextPanel("Opciones");
    tabbedPane.addTab("Opciones", null, tab_opcion, "Opciones");

    panel.add(tabbedPane, BorderLayout.CENTER);
    
    setLocationRelativeTo(null);
    
    setLocationRelativeTo(null);
  }
  
  public void reloadGeneral(Restaurant r) {
    tabbedPane.remove(tab_general);
    tab_general.removeAll();
    tab_general.revalidate();
    tab_general.repaint();
    tab_general = makeGeneralPanel(" #1");
    tabbedPane.insertTab("General", null, tab_general, "", 0);
    tabbedPane.setSelectedComponent(tab_general);
    

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
    
    JMesas jmesas = new JMesas(logica.getMesas());
    panel_mesas.add(jmesas, BorderLayout.CENTER);
  }

  private void initPanelOrdenes() {
    panel_ordenes = new JPanel(new BorderLayout());

    // Título
    JLabel titulo = new JLabel("�rdenes", JLabel.CENTER);
    panel_ordenes.add(titulo, BorderLayout.PAGE_START);
    titulo.setFont(new Font("Sans", Font.PLAIN, 18));
    titulo.setBorder(new EmptyBorder(4,4,4,4));
    
    // Lista
    j_ordenes = new JPanel();
    j_ordenes.setLayout(new GridLayout(4, 1));

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
    btn_gncuenta = new JButton("Generar Cuenta");
    
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
    int ordenes_size = logica.getOrdenes().size();
    
    if(ordenes_size <= 4)
      j_ordenes.setLayout(new GridLayout(4, 1));
    else
      j_ordenes.setLayout(new GridLayout(ordenes_size, 1));
    
    JOrden jorden = new JOrden(logica, orden);
    j_ordenes.add(jorden);
    j_ordenes.updateUI();
  }
  
  public void removeOrden(Orden orden){
    int ordenes_size = logica.getOrdenes().size();
    
    for(Component c:j_ordenes.getComponents()){
      if(c.getClass() == JOrden.class){
        JOrden jo = (JOrden)c;
        if(jo.getOrden() == orden){
          j_ordenes.remove(jo);
          logica.getOrdenes().remove(orden);
          
          if(ordenes_size <= 4)
            j_ordenes.setLayout(new GridLayout(4, 1));
          else
            j_ordenes.setLayout(new GridLayout(ordenes_size, 1));
          
          break;
        }
      }
    }
    
    j_ordenes.updateUI();
  }
  
  public void AddCuenta(Cuenta cuenta) {
    int cuentas_size = logica.getCuentas().size();
    
    if(cuentas_size <= 4)
      j_cuentas.setLayout(new GridLayout(4, 1));
    else
      j_cuentas.setLayout(new GridLayout(cuentas_size, 1));
    
    JCuenta jcuenta = new JCuenta(logica, cuenta);
    j_cuentas.add(jcuenta);
    j_cuentas.updateUI();
  }

  public void removeCuenta(Cuenta cuenta){
    int cuentas_size = logica.getCuentas().size();
    
    for(Component c:j_cuentas.getComponents()){
      if(c.getClass() == JCuenta.class){
        JCuenta jo = (JCuenta)c;
        if(jo.getCuenta() == cuenta){
          j_cuentas.remove(jo);
          logica.getCuentas().remove(cuenta);
          
          if(cuentas_size <= 4)
            j_cuentas.setLayout(new GridLayout(4, 1));
          else
            j_cuentas.setLayout(new GridLayout(cuentas_size, 1));
          
          break;
        }
      }
    }
    
    j_cuentas.updateUI();
  }
  
  public void updateMesas() {
    panel_mesas.updateUI();
  }

}
