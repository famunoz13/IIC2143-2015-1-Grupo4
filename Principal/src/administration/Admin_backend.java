package administration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import structures.Usuario;

public class Admin_backend {
	private LogicaAdministracion administracion;
	private Admin_frontend frontend;
	private ArrayList<Usuario> users;
	
	public Admin_backend(LogicaAdministracion l, Admin_frontend f) {
		administracion = l;
		frontend = f;
		users = administracion.getUsers();
		
    frontend.getBtn_ver().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        frontend.setTableView();
      }
    });
		
    frontend.getBtn_Add().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        frontend.setAddView();
      }
    });
    
    frontend.getBtn_Remove().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        frontend.setRemoveView();
      }
    });
    
    frontend.getBtn_Exit().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        administracion.setMenuPanel();
      }
    });
    
    frontend.getBtn_AddUser().addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    	  String username = frontend.user_textfield.getText();
    	  String password = String.valueOf(frontend.pass_textfield.getPassword());
    	  
    	  int id = 0;
    	  for(Usuario u:users){
    	    if(u.getId() > id)
    	      id = u.getId();
    	  }
    	  id = id + 1;
    	  
    	  if(username.length() < 3 || password.length() < 3){
          JOptionPane.showMessageDialog(frontend,
              "Usuario o contraseña muy corta (al menos 3 caracteres)",
              "Error al crear usuario",
              JOptionPane.WARNING_MESSAGE);
          return;
    	  }
    	  
    	  users.add(new Usuario(id, username, password));
    	  
    	  JOptionPane.showMessageDialog(frontend,
            "Usuario creado con éxito",
            "Mensaje",
            JOptionPane.INFORMATION_MESSAGE);
        frontend.setTableView();
        administracion.SaveUsersXML();
    	}
    });
    
    frontend.getBtn_RemoveUser().addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    	  Usuario contained = administracion.getUserByUsername(frontend.user_textfield.getText());
    	  
        if (contained != null) {
          users.remove(contained);
          JOptionPane.showMessageDialog(frontend,
              "Usuario eliminado con éxito",
              "Mensaje",
              JOptionPane.INFORMATION_MESSAGE);
          frontend.setTableView();
          administracion.SaveUsersXML();
        }else{
          JOptionPane.showMessageDialog(frontend,
              "El usuario no existe",
              "Error al eliminar",
              JOptionPane.WARNING_MESSAGE);
        }
    	}
    });
	}

}
