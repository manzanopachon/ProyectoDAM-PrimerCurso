package com.dam.airbase.entidades;

public class Avion extends Registro {

	//CONSTRUCTOR de la clase Avion
	public Avion (String codigo, String nombre, int horasVuelo, boolean operativo) {
		super( codigo, nombre, horasVuelo, operativo);
	}
	public Avion () {
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
	@Override
	public String toString() {
		return "Avion [codigo=" + codigo + ", nombre=" + nombre + ", horasVuelo=" + horasVuelo + ", operativo="
				+ operativo + "]";
	}
	
}
