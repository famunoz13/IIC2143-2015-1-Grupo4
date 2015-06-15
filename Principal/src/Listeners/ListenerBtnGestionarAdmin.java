package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import administration.LogicaAdministracion;
import administration.ManageAdminWindow;

public class ListenerBtnGestionarAdmin implements ActionListener {

	LogicaAdministracion logica;
	
	public ListenerBtnGestionarAdmin (LogicaAdministracion l)
	{
		logica = l;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		new ManageAdminWindow(logica);
	}
	

}
