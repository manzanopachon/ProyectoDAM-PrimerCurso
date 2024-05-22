package com.dam.airbase.entidades;


public class Ruta{
	//Atributos
	protected String paisOrigen;
	protected String paisDestino;
	protected int h;
	
	//CONSTRUCTORES
	public Ruta() {}
	
	public Ruta(String paisOrigen, String paisDestino, int h) {
		super();
		this.paisOrigen = paisOrigen;
		this.paisDestino = paisDestino;
		this.h = h;
	}

	
	//GETTERS Y SETTERS
	/**
	 * @return the paisOrigen
	 */
	public String getPaisOrigen() {
		return paisOrigen;
	}

	/**
	 * @param paisOrigen the paisOrigen to set
	 */
	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	/**
	 * @return the paisDestino
	 */
	public String getPaisDestino() {
		return paisDestino;
	}

	/**
	 * @param paisDestino the paisDestino to set
	 */
	public void setPaisDestino(String paisDestino) {
		this.paisDestino = paisDestino;
	}

	/**
	 * @return the h
	 */
	public int getH() {
		return h;
	}

	/**
	 * @param h the h to set
	 */
	public void setH(int h) {
		this.h = h;
	}	
}