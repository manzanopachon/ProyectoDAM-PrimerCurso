package com.dam.airbase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.dam.airbase.servicios.GestionIncidencias;
import com.dam.airbase.entidades.Piloto;
import java.util.ArrayList;
import com.dam.airbase.entidades.Avion;
public class PruebasIncidencias {
	private GestionIncidencias gi = new GestionIncidencias();
	@BeforeEach
	void setUp() {
		gi = new GestionIncidencias();
		gi.inicializarIncidencias();
	}
	@Test
	void testIndicendiaPiloto() {
		ArrayList<Piloto>pilotos = new ArrayList<Piloto>();
		pilotos.add(new Piloto("P001","Constantino",200,true,false));
		pilotos.add(new Piloto("P002","Alejandro",200,true,true));
		gi.incidenciaPiloto(pilotos);
		
	}
	
	@Test
	void testIncidenciaAvion() {
		ArrayList<Avion>aviones = new ArrayList<Avion>();
		aviones.add(new Avion("A001","F22",200,true));
		aviones.add(new Avion("A002","F16",200,true));
	}
}
