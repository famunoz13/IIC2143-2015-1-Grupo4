package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import app.VerOrdenWindow;
import sockets.Client;
import sockets.CocinaListener;
import sockets.Message;
import structures.Orden;
import frontend.CocinaWindow;
import frontend.JOrden;

public class Cocina {

  private CocinaWindow frontend;
  private CocinaListener server;
  private Client client;
  
	public Cocina(){
		frontend = new CocinaWindow(this);
		frontend.setVisible(true);
		
		client = new Client("127.0.0.1", 3040);
		server = new CocinaListener(3034, this);
		server.start();
		

		//Recibir y liberar ordenes
//		recibirOrden(new Orden(1,null, null,null,""));
//		recibirOrden(new Orden(2,null, null,null,""));
//		recibirOrden(new Orden(3,null, null,null,""));
//		recibirOrden(new Orden(4,null, null,null,""));
		//addOrden(new Orden(100,null, null,null,""));
	}

	public void decifrarMensaje(Message m){
	  // si es una orden nueva se agrega
	  if(m.getTipo() == 0)
		  addOrden(m.getOrden());
	  
	  // si es una orden entregada, se libera
	  if(m.getTipo() == 1)
		  liberarOrden(m.getOrden());
	  
	  // si se cancelo la orden, se elimina
	  if(m.getTipo() == 2)
		  liberarOrden(m.getOrden());
	}
	
	public void liberarOrden(Orden o){
	  frontend.removeOrden(o);
	}
	
	public void addOrden(Orden o){
	  //Añade ordenes a la fila de espera
	  JOrden j = frontend.addOrden(FILA.ESPERA, o);
	  addEsperaListeners(j);
	}

  private void addEsperaListeners(final JOrden j) {
    removeAllListeners(j.getBtn_ver());
    removeAllListeners(j.getBtn_mid());
    removeAllListeners(j.getBtn_cancelar());
    
    j.getBtn_ver().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        new VerOrdenWindow(j.getOrden());
      }
    });
    
    j.getBtn_mid().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        frontend.Move(j.getOrden(), FILA.ESPERA, FILA.ATENDIENDO);
        addAtendiendoListeners(j);
      }
    });
    
    j.getBtn_cancelar().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        int dialogResult = JOptionPane.showConfirmDialog(null, 
            "La orden será elminada completamente\n ¿Está seguro que desea realizar esta acción?",
            "Advertencia",
            JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
          client.CancelarOrden(j.getOrden());
          frontend.removeOrden(j.getOrden());
        }
      }
    });
  }

  private void addAtendiendoListeners(final JOrden j) {
    removeAllListeners(j.getBtn_ver());
    removeAllListeners(j.getBtn_mid());
    removeAllListeners(j.getBtn_cancelar());
    
    j.getBtn_ver().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        new VerOrdenWindow(j.getOrden());
      }
    });
    
    j.getBtn_mid().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        frontend.Move(j.getOrden(), FILA.ATENDIENDO, FILA.ATENDIDA);
        addAtendidasListeners(j);
      }
    });
    
    j.getBtn_cancelar().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        frontend.Move(j.getOrden(), FILA.ATENDIDA, FILA.ESPERA);
        addEsperaListeners(j);
      }
    });
  }
  
  private void addAtendidasListeners(final JOrden j) {
    removeAllListeners(j.getBtn_ver());
    removeAllListeners(j.getBtn_mid());
    removeAllListeners(j.getBtn_cancelar());
    
    client.IndicarOrdenPreparada(j.getOrden());
    
    j.getBtn_ver().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        new VerOrdenWindow(j.getOrden());
      }
    });
    
    j.getBtn_cancelar().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
    	client.DesprepararOrden(j.getOrden());
        frontend.Move(j.getOrden(), FILA.ATENDIDA, FILA.ATENDIENDO);
        addAtendiendoListeners(j);
      }
    });
  }
  
  private void removeAllListeners(JButton j) {
    for(ActionListener al : j.getActionListeners())
      j.removeActionListener(al);
  }
  
}
