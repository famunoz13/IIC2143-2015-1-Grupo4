package administration;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import structures.Menu;
import structures.MenuItem;

public class Menus_frontend extends JPanel{
  private static final long serialVersionUID = 1L;
  private JPanel left_panel, right_panel;
  private JButton btn_crear, btn_modificar, btn_eliminar, btn_guardar, btn_exit;
  private JTable table;

  
  public Menus_frontend(Menu menu){
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    
    //################# Elementos ##################
    
    //Label: titulo
    JLabel user_label = new JLabel("- Editar Menú -");
    c.weightx = 1;
    c.weighty = 0;
    c.fill = GridBagConstraints.CENTER;
    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth = 2;
    c.insets = new Insets(6, 6, 6, 6);
    add(user_label, c);
    
    c.gridwidth = 1;
    
    //Buttons bar
    initLeftPanel();
    c.weightx = 0.2;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 1;
    c.insets = new Insets(6, 6, 6, 6);
    add(left_panel, c);
    
    //edit area
    initRightPanel(menu);
    c.weightx = 0.8;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 1;
    c.gridy = 1;
    c.insets = new Insets(6, 6, 6, 6);
    add(right_panel, c);
      
  }

  private void initLeftPanel(){
    left_panel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.anchor = GridBagConstraints.NORTH;
    
    btn_crear   = new JButton("Crear ítem del menú");
    c.weightx = 1;
    c.weighty = 0;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    c.ipadx = 16;
    c.ipady = 16;
    c.insets = new Insets(6, 6, 6, 6);
    left_panel.add(btn_crear, c);

    
    btn_modificar   = new JButton("Modificar selección");
    c.weightx = 1;
    c.weighty = 0;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 1;
    c.insets = new Insets(6, 6, 6, 6);
    left_panel.add(btn_modificar, c);

    
    btn_eliminar   = new JButton("Eliminar selección");
    c.weightx = 1;
    c.weighty = 0;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 2;
    c.insets = new Insets(6, 6, 6, 6);
    left_panel.add(btn_eliminar, c);
    
    
    btn_guardar   = new JButton("Guardar y salir");
    c.weightx = 1;
    c.weighty = 0;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 3;
    c.insets = new Insets(20, 6, 6, 6);
    left_panel.add(btn_guardar, c);
    
    btn_exit   = new JButton("Salir");
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 4;
    c.insets = new Insets(6, 6, 6, 6);
    left_panel.add(btn_exit, c);
    
  }

  private String[] columnNames = {"id", "Nombre", "Tipo", "Descripción", "Precio($)", "Costo($)"};
  
  private void initRightPanel(Menu menu){
    right_panel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    
    Object[][] data = new Object[menu.getItems().size()][6];
    int j=0;
    for(MenuItem i:menu.getItems()){
      Object[] row = new Object[6];
      row[0] = i.getId();
      row[1] = i.getNombre();
      row[2] = i.getTipo();
      row[3] = i.getDescripcion();
      row[4] = i.getPrecio();
      row[5] = i.getCosto();
      data[j] = row;
      
      j++;
    }
    
    DefaultTableModel model = new DefaultTableModel(data, columnNames);
    table = new JTable(model);
    JScrollPane scrollPane = new JScrollPane(table);
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 1;
    c.insets = new Insets(2, 2, 2, 2);
    right_panel.add(scrollPane, c);
    
    table.setFillsViewportHeight(true);
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
    table.getColumnModel().getColumn(0).setMinWidth(20);
    table.getColumnModel().getColumn(0).setPreferredWidth(20);
    
    table.getColumnModel().getColumn(1).setMinWidth(90);
    table.getColumnModel().getColumn(1).setPreferredWidth(120);
    
    table.getColumnModel().getColumn(4).setMinWidth(50);
    table.getColumnModel().getColumn(4).setPreferredWidth(50);
    
    
    table.getColumnModel().getColumn(3).setMinWidth(150);
    table.getColumnModel().getColumn(3).setPreferredWidth(200);
    
    table.getColumnModel().getColumn(4).setMinWidth(50);
    table.getColumnModel().getColumn(4).setPreferredWidth(50);
    
    table.getColumnModel().getColumn(5).setMinWidth(50);
    table.getColumnModel().getColumn(5).setPreferredWidth(50);
  }
  
  public JButton getBtn_crear() {
    return btn_crear;
  }

  public JButton getBtn_modificar() {
    return btn_modificar;
  }

  public JButton getBtn_guardar(){
    return btn_guardar;
  }
  
  public JButton getBtn_eliminar() {
    return btn_eliminar;
  }

  public JButton getBtn_exit() {
    return btn_exit;
  }
  
  public JTable getTable(){
    return table;
  }
}
