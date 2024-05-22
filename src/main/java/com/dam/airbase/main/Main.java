package com.dam.airbase.main;

import com.dam.airbase.controladores.Menus;

public class Main {
	
	static int opcion = 0;
	
	//Llamamos a los Servicios, y lo Instanciamos para poderlos inicializar
	private static Menus m = new Menus();
	
	public static void main(String[] args) {
		m.menu(args);
	}
}

