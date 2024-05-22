package com.dam.airbase.entidades;

public class Piloto extends Registro {
	protected boolean admin;
	
	//CONSTRUCTOR de la clase piloto
	public Piloto (String codigo, String nombre, int horasVuelo,boolean operativo,boolean admin) {
		super(codigo, nombre, horasVuelo, operativo);
		this.admin = admin;
	}
	public Piloto(String codigo, String nombre, int horasVuelo,boolean operativo) {
		super(codigo, nombre, horasVuelo, operativo);
	}
	
	public Piloto () {
		super();
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
	/**
	 * 
	 * @param operativo
	 */
	public void setOperativo(boolean operativo) {
		this.operativo = operativo;
	}
	/**
	 * 
	 * @return
	 */
	public boolean getOperativo() {
		return operativo;
	}
	/**
	 * 
	 * @param admin
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	/**
	 * 
	 * @return
	 */
	public boolean getAdmin() {
		return admin;
	}
	
	//Metodo toString
	@Override
	public String toString() {
		String pilotos = " "+ codigo +" | "+ nombre +" | "+ horasVuelo +" | "+ operativo +" | "+ admin;
		//return "Piloto [codigo = " + codigo + ", nombre = " + nombre + ", horasVuelo = " + horasVuelo + ", operativo = " + operativo + " administrativo = " + admin + "]";
		return pilotos;
	}
		
}
