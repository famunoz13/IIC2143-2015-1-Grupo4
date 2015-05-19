package sockets;

import java.io.*;
import java.net.*;

import structures.Orden;

public class Sender {

	Socket socket;
	int port;
	String address;
	
	public Sender(String address, int port)
	{
		
		this.address = address;
		this.port = port;
		
	}
	
	public void Send(int tipo, Orden orden)
	{
		try 
		{
			socket = new Socket(address, port);
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			Message m = new Message(tipo, orden);
			oos.writeObject(m);
			oos.flush();
			oos.close();
			os.close();
		} 
		catch (Exception e) 
		{
			
		}
	}
}
