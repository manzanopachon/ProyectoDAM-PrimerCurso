package com.dam.airbase.entidades;

public abstract class Registro {

	//ATRIBUTOS
	protected String codigo;
	protected String nombre;
	protected int horasVuelo;
	protected boolean operativo;
	
	
	//CONSTRUCTORES
	public Registro() {}

	public Registro(String codigo, String nombre, int horasVuelo,boolean operativo) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.horasVuelo = horasVuelo;
		this.operativo = operativo;
	}
	
	//GETTERS Y SETTERS
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the horasVuelo
	 */
	public int getHorasVuelo() {
		return horasVuelo;
	}

	/**
	 * @param horasVuelo the horasVuelo to set
	 */
	public void setHorasVuelo(int horasVuelo) {
		this.horasVuelo = horasVuelo;
	}
	
	
	//Metodo toString
	@Override
	public String toString() {
		return "Registro [codigo=" + codigo + ", nombre=" + nombre + ", horasVuelo=" + horasVuelo + "]";
	}

	
}
