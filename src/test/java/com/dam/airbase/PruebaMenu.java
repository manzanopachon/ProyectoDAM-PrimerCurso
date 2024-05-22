package com.dam.airbase;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.dam.airbase.controladores.Menus;
import com.dam.airbase.entidades.Avion;
import com.dam.airbase.entidades.Piloto;

import com.dam.airbase.servicios.GestionAvionCRUD;
import com.dam.airbase.servicios.GestionIncidencias;
import com.dam.airbase.servicios.GestionPilotoCRUD;
import com.dam.airbase.servicios.GestionRuta;



class PruebaMenu {

	private static final String String = null;

	private static Menus m = new Menus();
	private static GestionRuta gr = new GestionRuta();
	private static GestionPilotoCRUD gpc = new GestionPilotoCRUD();
	private static GestionAvionCRUD gac  = new GestionAvionCRUD();
	private static GestionIncidencias gi = new GestionIncidencias();

	
	@BeforeEach
	 void setUp() {
		// inicializamos la arraylist pilotos
	     gpc.inicializar(new Piloto("P001", "Faly", 200, true,true));
	     gpc.inicializar(new Piloto("P002","Constantino",200,true,false));
	     gpc.inicializar(new Piloto("P003","Omar",200,true, true));
	     gpc.inicializar(new Piloto("P004","Mauricio",200,true, false));
	     
		// inicializamos la arraylist aviones
	     gac.inicializar(new Avion("A001","F22",100,true));
	     gac.inicializar(new Avion("A002","F15",250,true));
	     gac.inicializar(new Avion("A003","Typhoon",700,true));
	     gac.inicializar(new Avion("A004","Rafle e",400,true));
	 }
	
	/* Fundamental inicializar las arraylist si queremos comprobar funciones
	 *  que muestren cosas de las array list
	 */
	
	@Test
	void testiniciaList() {
		
		gpc.validarPiloto2();
		gac.validarAvion2();
		assertNotNull(gac);
		
	}
	
	@Test
	void testObtenerPilotos() {
		
	    assertNotNull(gpc.obtenerPilotos());
	    assertNotNull(gac.obtenerAviones());
	}
	
	@Test
	void testIncidencias() {
		gi.incidenciaPiloto(null);
		assertNotNull(gac);
	}
	
	
	@Test
	void testMenuRuta() {
		m.menuGestionRuta(gr , String);
	}

	@Test
	void testMenuPiloto() {
		String Piloto = "Piloto";
		m.menuGestionP(gpc, Piloto );
		assertNotNull(Piloto);
	}
	
	@Test
	void testMenuAvion() {
		String Avion = "Avion";
		m.menuGestionA(gac, Avion);
		assertNotNull(Avion);
	}

	@Test
	void testMenuX() {
		
		m.menuX();
	}
	
}
