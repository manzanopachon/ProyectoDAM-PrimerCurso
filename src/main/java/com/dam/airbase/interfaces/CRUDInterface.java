package com.dam.airbase.interfaces;

import java.util.Scanner;

import com.dam.airbase.entidades.Registro;

public interface CRUDInterface {
	public void alta(Registro r);
	public void baja(Registro r);
	public void modificar(Registro r);
	public void ver(Registro r);
	public void inicializar(Registro r);
	public boolean ifExist(Registro r, String codigo, boolean ok);
	public String elegirCarga(boolean carga,Scanner sc,String[][]t );
	public void mostrarCarga(String carga);
	public String[][] tiposCargamento();
	public int cargaPiloto();
	public boolean tipoCarga(Scanner sc);
	public int sumarHoras(int horaRuta, int hora) ;
	public void mostrarHorasActualizado(String nombre,int horas);
	

}
