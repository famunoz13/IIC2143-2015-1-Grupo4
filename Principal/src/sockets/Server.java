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
				Message m = (Message)ois.readObject();
				Orden orden = m.getOrden();
				
				
				// tipo == 0 indica que la orden fue cancelada
				if(m.tipo == 0)
				{
					r.removeOrden2(orden);
				}
				
				// tipo == 1 indica que la orden esta preparada
				if(m.tipo == 1)
				{
					r.recibir(orden);
				}
				
				// tipo == 2 indica que la orden no esta preparada
				if(m.tipo == 2)
				{
					r.desprepararOrden(orden);
				}
				
				
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
