package administration;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class login_form extends JPanel{
  private static final long serialVersionUID = 1L;
  private JButton login_button;
  private JTextField user_textfield;
  private JPasswordField pass_textfield;
  
  public login_form(){
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
    c.gridy = 6;
    c.insets = new Insets(6, 6, 6, 6);
    add(space3, c);
    
    //################# Elementos ##################
    
    //Label: usuario
    JLabel user_label = new JLabel("Usuario administrador:");
    c.weightx = 0.20;
    c.weighty = 0;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 1;
    c.gridy = 1;
    c.insets = new Insets(6, 6, 6, 6);
    add(user_label, c);
    
    //Textfield: usuario
    user_textfield = new JTextField("admin");
    c.weightx = 0.20;
    c.weighty = 0;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 1;
    c.gridy = 2;
    c.insets = new Insets(6, 6, 6, 6);
    add(user_textfield, c);
    
    //Label: contraseña
    JLabel pass_label = new JLabel("Contraseña:");
    c.weightx = 0.20;
    c.weighty = 0;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 1;
    c.gridy = 3;
    c.insets = new Insets(6, 6, 6, 6);
    add(pass_label, c);
    
    //Textfield: contraseña
    pass_textfield = new JPasswordField("12345");
    c.weightx = 0.20;
    c.weighty = 0;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 1;
    c.gridy = 4;
    c.insets = new Insets(6, 6, 6, 6);
    add(pass_textfield, c);
    
    //Login button
    login_button = new JButton("Ingresar");
    c.weightx = 0.20;
    c.weighty = 0;
    c.fill = GridBagConstraints.CENTER;
    c.gridx = 1;
    c.gridy = 5;
    c.insets = new Insets(6, 6, 6, 6);
    add(login_button, c);
    
  }
  
  public JButton getButton(){
    return login_button;
  }
  
  public String getUser(){
    return user_textfield.getText();
  }
  
  public String getPassword(){
    return String.valueOf(pass_textfield.getPassword());
  }
}
