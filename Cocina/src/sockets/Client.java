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
	
	public void CancelarOrden(Orden orden)
	{
		Enviar(0, orden);
	}
	
	public void IndicarOrdenPreparada(Orden orden)
	{
		Enviar(1, orden);
	}
	
	public void DesprepararOrden(Orden orden)
	{
		Enviar(2, orden);
	}
	
	public void Enviar(int tipo, Orden orden)
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
