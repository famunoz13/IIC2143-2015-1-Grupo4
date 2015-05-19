package sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import logic.Cocina;
import structures.Orden;

/*Clase server de cocina*/

public class CocinaListener implements Runnable{
	
	int port;
	Cocina c;
	ServerSocket listener;
	Socket socket;
	private Thread t;
	
	public CocinaListener(int puerto, Cocina co)
	{
		port = puerto;
		c = co;
	}
	
	public void start()
	{
		if(t == null)
		{
			t = new Thread(this);
			t.start();
		}
	}
	
	
	@Override
	public void run() {
		
		while(true)
		{
			try
			{
				listener = new ServerSocket(port);
				socket = listener.accept();
				InputStream is = socket.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				Message m = (Message)ois.readObject();
				c.decifrarMensaje(m);
				is.close();
				ois.close();
				socket.close();
				listener.close();
				
			}
			catch(Exception e)
			{
				continue;
			}
		}
		
	}

}
