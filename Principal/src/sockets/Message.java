package sockets;

import java.io.Serializable;

import structures.Orden;

public class Message implements Serializable{
  private static final long serialVersionUID = 1L;
  int tipo; //0 = orden nueva, 1 = orden entregada
	Orden orden;
	
	public Message (int tipo, Orden orden)
	{
		this.tipo = tipo;
		this.orden = orden;
		
	}
	
	public int getTipo(){
		return tipo;
	}
	public Orden getOrden(){
		return orden;
	}
	

}
