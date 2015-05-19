package sockets;

import java.io.*;
import java.net.Socket;

import structures.Orden;

public class Client {
	
	Socket socket;
	int port;
	String address;
	
	public Client(String address, int port)
	{
		this.address = address;
		this.port = port;
	}
	
	public void IndicarOrdenPreparada(Orden orden)
	{
		try 
		{
			socket = new Socket(address, port);
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			
			oos.writeObject(orden);
			oos.flush();
			oos.close();
			os.close();
		} 
		catch (Exception e) 
		{
			
		}
	}

}
