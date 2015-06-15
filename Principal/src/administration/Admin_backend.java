package administration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin_backend {
	private LogicaAdministracion administracion;
	private Admin_frontend frontend;
	
	public Admin_backend(LogicaAdministracion l, Admin_frontend f) {
		administracion = l;
		frontend = f;
		
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
		    		frontend.addUser(administracion);
		    	}
		    });
		    
		    frontend.getBtn_RemoveUser().addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		frontend.removeUser(administracion);
		    	}
		    });
	}

}
