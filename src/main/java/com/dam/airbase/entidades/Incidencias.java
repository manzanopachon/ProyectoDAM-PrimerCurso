	package com.dam.airbase.entidades;
	
	public class Incidencias {
	//Atributos
	protected String numeroIncidencia;
	protected String codigoRef;
	protected String descripccion;
	protected Boolean operativo;
	protected String fecha;
	
	//constructores 
	public Incidencias(String numeroIncidencia,String codigoRef, String descripccion,Boolean operativo,String fecha) {
		this.numeroIncidencia = numeroIncidencia;
		this.codigoRef = codigoRef;
		this.descripccion = descripccion;
		this.operativo = operativo;
		this.fecha = fecha;
		
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public void setCodigoRef(String codigoRef) {
		this.codigoRef = codigoRef;
	}
	public Incidencias() {}
	//metodos
	
	/**
	 * 
	 * @return
	 */
	public String getNumeroIncidencia() {
		return numeroIncidencia;
	}
	/**
	 * 
	 * @param numeroIncidencia
	 */
	public void setNumeroIncidencia(String numeroIncidencia) {
		this.numeroIncidencia = numeroIncidencia;
	}
	/**
	 * 
	 * @return
	 */
	public String getDescripccion() {
		return descripccion;
	}
	/**
	 * 
	 * @param descripccion
	 */
	public void setDescripccion(String descripccion) {
		this.descripccion = descripccion;
	}
	/**
	 * 
	 * @return
	 */
	public String getCodigoRef() {
		return codigoRef;
	}
	/**
	 * 
	 * @param codigoPiloto
	 */
	public void setCodigoPiloto(String codigoRef) {
		this.codigoRef = codigoRef;
	}
	/**
	 * 
	 * @return
	 */
	public Boolean getOperativo() {
		return operativo;
	}
	/**
	 * 
	 * @param operativo
	 */
	public void setOperativo(Boolean operativo) {
		this.operativo = operativo;
	}
	
	public static void add(Incidencias incidencias) {
		// TODO Auto-generated method stub
		
	}

}

