package com.dam.airbase;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.dam.airbase.entidades.Ruta;
import com.dam.airbase.servicios.GestionRuta;


//Los datos que te piden siempre se van a usar [Europa,Italia,Europa,España]
class PruebasRuta {

	private  GestionRuta gestionRuta = new GestionRuta();
	
	@Test
	void testAltaRuta() {
        // Verifica que la función altaRuta añada correctamente una nueva ruta a la lista y sea mayor que 0 (Juan Mauricio)
      
        ArrayList<Ruta> rutas = new ArrayList<>();

        int horas = gestionRuta.altaRuta(rutas);

        assertTrue(horas > 0);
    }	
	
	 @Test
	 void testValidarRuta() {
	    // Verifica que la función validarRuta valide correctamente una ruta existente (Juan Mauricio)
	       
	    boolean resultado = gestionRuta.validarRuta("Europa", "España");
        assertTrue(resultado);
	 }

	 @Test
	 void testValidarRutaInvalida() {
	    // Verifica que la función validarRuta invalide correctamente una ruta inexistente (Juan Mauricio)
	 
	    boolean resultado = gestionRuta.validarRuta("Europa", "Japon");

	    assertFalse(resultado);
   }
	 @Test
	 void testObtenerRutas() {
		 // Verifica que la función obtenerRutas devuelva correctamente la lista de rutas(Juan Mauricio)
	        
	     ArrayList<Ruta> rutas = gestionRuta.obtenerRutas();
	     assertNotNull(rutas);

	}
	
	
	 @Test
	 void eliminarRutaTest() {
		 //Comprueba que se elimina una ruta correctamente
	     GestionRuta gestionRuta = new GestionRuta();
	     ArrayList<Ruta> rutas = new ArrayList<>();
	     rutas.add(new Ruta("España", "Italia",4));
	     gestionRuta.eliminarRuta(rutas, "España", "Italia");

	     assertTrue(rutas.isEmpty());
	 }	 
	 
	 
	 
}
