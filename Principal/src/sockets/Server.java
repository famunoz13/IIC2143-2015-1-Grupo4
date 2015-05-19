package sockets;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import app.Restaurant;
import structures.Orden;

/*Clase server de programa principal*/

public class Server implements Runnable{
	
	int port;
	boolean connected;
	ServerSocket listener;
	Socket socket;
	private Thread t;
	Restaurant r;
	
	
	public Server(int port, Restaurant res)
	{
		this.port = port;
		connected = false;
		r = res;
	}
	
	public void start()
	{
		if(t == null)
		{
			t = new Thread(this);
			t.start();
		}
	}
	
	

	// El programa principal recibe un mensaje de la cocina
	@Override
	public void run() {
		while(true)
		{
			
			try
			{
				listener = new ServerSocket(port);
				socket = listener.accept();
				connected = true;
				InputStream is = socket.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				Orden orden = (Orden)ois.readObject();
				
				/* Falta procesar el mensaje */
				r.recibir(orden);
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
