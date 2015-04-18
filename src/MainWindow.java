import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{
  private static final long serialVersionUID = 1L;
  private JPanel panel, panel_mesas, panel_ordenes, panel_cuentas, panel_info;
  
  
  public MainWindow() {
    super("Restaurant");
    setSize(800, 480);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
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
    
  }
  
  private JComponent makeGeneralPanel(String text) {
    JPanel panel = new JPanel(false);
    panel.setLayout(new GridBagLayout());
    
    GridBagConstraints c = new GridBagConstraints();
    //c.fill = GridBagConstraints.HORIZONTAL;
    
    //Panel mesas
    initPanelMesas();
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 0;
    c.insets = new Insets(6,6,6,6);
    panel.add(panel_mesas, c);

    //Panel ordenes
    initPanelOrdenes();
    c.fill = GridBagConstraints.BOTH;
    c.weightx = 0;
    //c.ipadx = 50;
    c.weighty = 1;
    c.gridx = 1;
    c.gridy = 0;
    panel.add(panel_ordenes, c);

    //Panel cuentas
    initPanelCuentas();
    c.fill = GridBagConstraints.BOTH;
    c.weightx = 0;
    c.weighty = 1;
    c.gridx = 2;
    c.gridy = 0;
    panel.add(panel_cuentas, c);
    
    //Panel info
    JButton button;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 0;
    c.weightx = 1.0;
    c.weighty = 0.0;
    c.gridx = 0;
    c.gridy = 1;
    //c.anchor = GridBagConstraints.PAGE_END;
    c.insets = new Insets(0,6,6,6);
    c.gridwidth = 3;
    button = new JButton("5");
    panel.add(button, c);
    
    return panel;
  }
  
  private void initPanelMesas(){
    panel_mesas = new JPanel(new BorderLayout());
    
    JLabel titulo = new JLabel("Mesas",JLabel.CENTER);
    panel_mesas.add(titulo, BorderLayout.PAGE_START);
    
    JButton button = new JButton("Button 1");
    panel_mesas.add(button, BorderLayout.CENTER);
  }
  
  private void initPanelOrdenes(){
    panel_ordenes = new JPanel(new BorderLayout());
    
    JLabel titulo = new JLabel("Órdenes",JLabel.CENTER);
    panel_ordenes.add(titulo, BorderLayout.PAGE_START);
    
    JButton button = new JButton("Button 2");
    panel_ordenes.add(button, BorderLayout.CENTER);
  }
  
  private void initPanelCuentas(){
    panel_cuentas = new JPanel(new BorderLayout());
    
    JLabel titulo = new JLabel("Cuentas",JLabel.CENTER);
    panel_cuentas.add(titulo, BorderLayout.PAGE_START);
    
    JButton button = new JButton("Button 3");
    panel_cuentas.add(button, BorderLayout.CENTER);
    
  }


  private JComponent makeTextPanel(String text) {
    JPanel panel = new JPanel(false);
    JLabel filler = new JLabel(text);
    filler.setHorizontalAlignment(JLabel.CENTER);
    panel.setLayout(new GridLayout(1, 1));
    panel.add(filler);
    return panel;
  }
}
