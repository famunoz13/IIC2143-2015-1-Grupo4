package administration;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import structures.Menu;
import structures.MenuItem;

public class Admin_frontend extends JPanel {
	
	private static final long serialVersionUID = 1L;
	  private JPanel left_panel, right_panel;
	  private JButton btn_view, btn_add, btn_remove, btn_exit;
	  
	  //private JButton addUser_button;
	  private JTextField user_textfield;
	  private JPasswordField pass_textfield;
	  private JButton btn_AddUser, btn_RemoveUser;
	  
	  private JTable table;
	  
	  public Admin_frontend(){
	    setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    
	    //################# Elementos ##################
	    
	    //Label: titulo
	    JLabel user_label = new JLabel("- Administradores -");
	    c.weightx = 1;
	    c.weighty = 0;
	    c.fill = GridBagConstraints.CENTER;
	    c.gridx = 0;
	    c.gridy = 0;
	    c.gridwidth = 2;
	    c.insets = new Insets(6, 6, 6, 6);
	    add(user_label, c);
	    
	    c.gridwidth = 1;
	    
	    //Left panel
	    initLeftPanel();
	    c.weightx = 0.2;
	    c.weighty = 1;
	    c.fill = GridBagConstraints.BOTH;
	    c.gridx = 0;
	    c.gridy = 1;
	    c.insets = new Insets(6, 6, 6, 6);
	    add(left_panel, c);
	    
	    //Right panel
	    initRightPanel();
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
	    
	    btn_view   = new JButton("Ver administradores");
      c.weightx = 1;
      c.weighty = 0;
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 0;
      c.gridy = 0;
      c.ipadx = 16;
      c.ipady = 16;
      c.insets = new Insets(6, 6, 6, 6);
      left_panel.add(btn_view, c);
    
	    btn_add   = new JButton("Agregar administrador");
	    c.weightx = 1;
	    c.weighty = 0;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 1;
	    c.ipadx = 16;
	    c.ipady = 16;
	    c.insets = new Insets(6, 6, 6, 6);
	    left_panel.add(btn_add, c);
	    
	    btn_remove   = new JButton("Eliminar administrador");
	    c.weightx = 1;
	    c.weighty = 0;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 2;
	    c.insets = new Insets(6, 6, 6, 6);
	    left_panel.add(btn_remove, c);

		    
	    btn_exit   = new JButton("Salir");
	    c.weightx = 1;
	    c.weighty = 1;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 3;
	    c.insets = new Insets(20, 6, 6, 6);
	    left_panel.add(btn_exit, c);
		    
	  }
	  
	  private void initRightPanel(){
		  right_panel = new JPanel(new GridBagLayout());
		  btn_AddUser = new JButton("Agregar Usuario");
		  btn_RemoveUser = new JButton("Remover Usuario");
		  
		  setTableView();
	  }
	  
	  
	  private String[] columnNames = {"id", "Nombre", "Contraseña"};
	  
	  public void setTableView(){
      right_panel.removeAll();
      right_panel.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    
	    /*Object[][] data = new Object[menu.getItems().size()][6];
	    int j=0;
	    for(MenuItem i:menu.getItems()){
	      Object[] row = new Object[6];
	      row[0] = i.getId();
	      row[1] = i.getNombre();
	      row[2] = i.getTipo();
	      data[j] = row;
	      
	      j++;
	    }*/
	    
	    DefaultTableModel model = new DefaultTableModel(null, columnNames);
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
	    
	    table.getColumnModel().getColumn(0).setMinWidth(20);
	    table.getColumnModel().getColumn(0).setPreferredWidth(20);
	    
	    table.getColumnModel().getColumn(1).setMinWidth(90);
	    table.getColumnModel().getColumn(1).setPreferredWidth(120);
	    
	    right_panel.updateUI();
	  }
	  
	  
	  public void setAddView(){
		  right_panel.removeAll();
		  right_panel.setLayout(new GridBagLayout());
		  GridBagConstraints c = new GridBagConstraints();
      c.anchor = GridBagConstraints.NORTH; 
      
		  //Espacio izquierdo
	    JLabel space0 = new JLabel("");
	    c.weightx = 0.40;
	    c.weighty = 1;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 0;
	    c.gridheight = 8;
	    c.insets = new Insets(6, 6, 6, 6);
	    right_panel.add(space0, c);
	    
	    //Espacio derecho
	    JLabel space1 = new JLabel("");
	    c.weightx = 0.40;
	    c.weighty = 1;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 2;
	    c.gridy = 0;
	    c.gridheight = 8;
	    c.insets = new Insets(6, 6, 6, 6);
	    right_panel.add(space1, c);
	    
	    c.gridheight = 1;
	    
	    //Espacio superior
	    JLabel space2 = new JLabel("");
	    c.weightx = 0.20;
	    c.weighty = 0.2;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 1;
	    c.gridy = 0;
	    c.insets = new Insets(6, 6, 6, 6);
	    right_panel.add(space2, c);
	    
	    //Espacio inferior
	    JLabel space3 = new JLabel("");
	    c.weightx = 0.20;
	    c.weighty = 0.8;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 2;
	    c.gridy = 6;
	    c.insets = new Insets(6, 6, 6, 6);
	    right_panel.add(space3, c);
	    
	    //################# Elementos ##################
	    
	    //Label: usuario
	    JLabel user_label = new JLabel("Usuario administrador:");
	    c.weightx = 0.20;
	    c.weighty = 0;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 1;
	    c.gridy = 1;
	    c.insets = new Insets(6, 6, 6, 6);
	    right_panel.add(user_label, c);
	    
	    //Textfield: usuario
	    user_textfield = new JTextField("admin");
	    c.weightx = 0.20;
	    c.weighty = 0;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 1;
	    c.gridy = 2;
	    c.insets = new Insets(6, 6, 6, 6);
	    right_panel.add(user_textfield, c);
	    
	    //Label: contraseña
	    JLabel pass_label = new JLabel("Contraseña:");
	    c.weightx = 0.20;
	    c.weighty = 0;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 1;
	    c.gridy = 3;
	    c.insets = new Insets(6, 6, 6, 6);
	    right_panel.add(pass_label, c);
	    
	    //Textfield: contraseña
	    pass_textfield = new JPasswordField("12345");
	    c.weightx = 0.20;
	    c.weighty = 0;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 1;
	    c.gridy = 4;
	    c.insets = new Insets(6, 6, 6, 6);
	    right_panel.add(pass_textfield, c);
	    
	    //Login button
	    //btn_AddUser = new JButton("Agregar Usuario");
	    c.weightx = 0.20;
	    c.weighty = 0;
	    c.fill = GridBagConstraints.CENTER;
	    c.gridx = 1;
	    c.gridy = 5;
	    c.insets = new Insets(6, 6, 6, 6);
	    right_panel.add(btn_AddUser, c);
	    
	    
	    
	    right_panel.updateUI();
		  
	  }
	  
	  @SuppressWarnings("deprecation")
	  public void addUser(LogicaAdministracion l)
	  {
		  l.getUsers().put(user_textfield.getText(), pass_textfield.getText());
	  }
	  
	  public void setRemoveView(){
		  right_panel.removeAll();
		  right_panel.setLayout(new GridBagLayout());
		  GridBagConstraints c = new GridBagConstraints();
		  c.anchor = GridBagConstraints.NORTH;  
		  
		  //Espacio izquierdo
	    JLabel space0 = new JLabel("");
	    c.weightx = 0.40;
	    c.weighty = 1;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 0;
	    c.insets = new Insets(6, 6, 6, 6);
	    c.gridheight = 5;
	    right_panel.add(space0, c);
	    
	    c.gridheight = 1;
	    //Espacio superior
	    JLabel space2 = new JLabel("");
	    c.weightx = 0.20;
	    c.weighty = 0.2;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 1;
	    c.gridy = 0;
	    c.insets = new Insets(6, 6, 6, 6);
	    right_panel.add(space2, c);
	    
	    
	    //################# Elementos ##################
	    
	    //Label: usuario
	    JLabel user_label = new JLabel("Usuario a remover:");
	    c.weightx = 0.20;
	    c.weighty = 0;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 1;
	    c.gridy = 1;
	    c.insets = new Insets(6, 6, 6, 6);
	    right_panel.add(user_label, c);
	    
	    //Textfield: usuario
	    user_textfield = new JTextField("admin");
	    c.weightx = 0.20;
	    c.weighty = 0;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 1;
	    c.gridy = 2;
	    c.insets = new Insets(6, 6, 6, 6);
	    right_panel.add(user_textfield, c);
	    
	    //Login button
	    c.weightx = 0.20;
	    c.weighty = 0;
	    c.fill = GridBagConstraints.CENTER;
	    c.gridx = 1;
	    c.gridy = 3;
	    c.insets = new Insets(6, 6, 6, 6);
	    right_panel.add(btn_RemoveUser, c);
	    
	    
	     //Espacio inferior
      JLabel space3 = new JLabel("");
      c.weightx = 0.20;
      c.weighty = 0.8;
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 1;
      c.gridy = 4;
      c.insets = new Insets(6, 6, 6, 6);
      right_panel.add(space3, c);
	    
      //Espacio derecho
      JLabel space1 = new JLabel("");
      c.weightx = 0.40;
      c.weighty = 1;
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 2;
      c.gridy = 0;
      c.gridheight = 5;
      c.insets = new Insets(6, 6, 6, 6);
      right_panel.add(space1, c);
      
      
	    right_panel.updateUI();
		  
	  }
	  
	  public void removeUser(LogicaAdministracion l){
		  if (l.getUsers().containsKey(user_textfield.getText())) {
			  l.getUsers().remove(user_textfield.getText());
		  }
	  }
	  
	  public JButton getBtn_ver(){
	    return btn_view;
	  }
	  
	  
	  public JTable getTable(){
	    return table;
	  }
	  
	  public JButton getBtn_Add(){
		  return btn_add;
	  }
	  public JButton getBtn_Remove(){
		  return btn_remove;
	  }
	  public JButton getBtn_Exit(){
		  return btn_exit;
	  }
	  public JButton getBtn_AddUser(){
		  return btn_AddUser;
	  }
	  public JButton getBtn_RemoveUser(){
		  return btn_RemoveUser;
	  }
	  
	  

}
