package administration;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class admin_menu extends JPanel{
  private static final long serialVersionUID = 1L;
  private JButton btn_editor, btn_exit;
  
  public admin_menu(){
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    //Espacio izquierdo
    JLabel space0 = new JLabel("");
    c.weightx = 0.40;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 0;
    c.insets = new Insets(6, 6, 6, 6);
    add(space0, c);
    
    //Espacio derecho
    JLabel space1 = new JLabel("");
    c.weightx = 0.40;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 2;
    c.gridy = 0;
    c.insets = new Insets(6, 6, 6, 6);
    add(space1, c);
    
    //Espacio superior
    JLabel space2 = new JLabel("");
    c.weightx = 0.20;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 1;
    c.gridy = 0;
    c.insets = new Insets(6, 6, 6, 6);
    add(space2, c);
    
    //Espacio inferior
    JLabel space3 = new JLabel("");
    c.weightx = 0.20;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 2;
    c.gridy = 3;
    c.insets = new Insets(6, 6, 6, 6);
    add(space3, c);
    
    //################# Elementos ##################
    
    //Login button
    btn_editor = new JButton("Editar mesas");
    c.weightx = 0.20;
    c.weighty = 0;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 1;
    c.gridy = 1;
    c.insets = new Insets(6, 6, 6, 6);
    c.ipadx = 20;
    c.ipady = 20;
    add(btn_editor, c);
    
    //Login button
    btn_exit = new JButton("Salir");
    c.weightx = 0.20;
    c.weighty = 0;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 1;
    c.gridy = 2;
    c.ipadx = 20;
    c.ipady = 20;
    c.insets = new Insets(20, 6, 6, 6);
    add(btn_exit, c);
    
  }
  
  public JButton getBtnEditor(){
    return btn_editor;
  }
  
  public JButton getBtnExit(){
    return btn_exit;
  }
}