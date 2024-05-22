package com.dam.airbase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import com.dam.airbase.entidades.Avion;
import com.dam.airbase.entidades.Registro;
import com.dam.airbase.interfaces.Constantes;
import com.dam.airbase.servicios.GestionAvionCRUD;

class PruebasAviones {

	GestionAvionCRUD gac = new GestionAvionCRUD();
    @Test
    void testCrearAvion() {
    	// Test para verificar la creación de un avión correctamente.
        Avion avion = new Avion("A005", "Boeing 747", 300, true);
        
        assertEquals("A005", avion.getCodigo());
        assertEquals("Boeing 747", avion.getNombre());
        assertEquals(300, avion.getHorasVuelo());
        assertTrue(avion.getOperativo());
    }

   
    @Test
    void testInicializarAviones() {
    	 // Comprueba la inicialización de la lista de aviones en GestionAvionCRUD.
       
        Registro registro = new Avion("A006", "Airbus A380", 150, true);
        gac.inicializar(registro);
        ArrayList<Avion> aviones = gac.obtenerAviones();
        assertNotNull(aviones);
    }

   
    @Test
    void testAltaAvion() {
    	 //Verifica la operación de alta en GestionAvionCRUD.
       
        Registro registro = new Avion("A007", "Cessna 172", 50, true);
        gac.inicializar(registro);

        // Se agrega un nuevo avión con la operación de alta.
        gac.alta(registro);

        ArrayList<Avion> aviones = gac.obtenerAviones();
        assertEquals(5, aviones.size()); // Se espera que la lista de aviones tenga un nuevo elemento.
    }

   
    @Test
    void testBajaAvion() {
    	 // Comprueba la operación de baja en GestionAvionCRUD.
       
        Registro registro = new Avion("A008", "Embraer E190", 80, true);
        gac.inicializar(registro);

        // Se elimina un avión existente con la operación de baja.
        gac.baja(registro);

        ArrayList<Avion> aviones = gac.obtenerAviones();
        assertEquals(3, aviones.size()); // Se espera que la lista de aviones tenga un elemento menos.
    }

   
    @Test
    void testModificarAvion() {
    	 // Comprueba la operación de modificar en GestionAvionCRUD.
       
        Registro registro = new Avion("A009", "Bombardier Global 6000", 120, true);
        gac.inicializar(registro);
        gac.modificar(registro);

        ArrayList<Avion> aviones = gac.obtenerAviones();
        assertEquals(4, aviones.size()); // Se espera que la lista de aviones tenga el mismo número de elementos.
    }

   
    @Test
    void testVerAviones() {
    	 // verifica la operación de ver en GestionAvionCRUD.
      
        Registro registro = new Avion("A010", "Lockheed Martin C-130", 200, true);
        gac.inicializar(registro);

        // Se muestra la información de los aviones con la operación de ver.
        gac.ver(registro);
    }

 
    @Test
    void testAltaAvionDuplicado() {
    	// Comprobar que no se permita agregar un avión con un código duplicado.
       
        Registro registro1 = new Avion("A011", "Boeing 787", 180, true);
        Registro registro2 = new Avion("A011", "Airbus A350", 200, false);

        gac.inicializar(registro1);

        // Se intenta agregar un avión con un código duplicado.
        gac.alta(registro2);

        ArrayList<Avion> aviones = gac.obtenerAviones();
        assertEquals(5, aviones.size());
    }
    
    @Test
    void testElegirCargaValida() {
    	// Comprueba que se valide correctamente el tipo de carga, en este caso Armamento.
    
    	String[][] tiposCargamento = gac.tiposCargamento();
	    assertEquals("Municion", gac.elegirCarga(true, Constantes.sc, tiposCargamento));
    }
    
    
    @Test
    void testTipoCargaSuministro() {
    	// Comprueba que se valide correctamente el tipo de vuelo.
        boolean tipoVuelo = !(gac.tipoCarga(new Scanner("Suministros\n")));
        assertTrue(tipoVuelo);
    }
    
    @Test
    void testSumarHoras() {
    	//Verifica que las horas de vuelo se sumen correctamente.
        
        int horasActuales = 120;
        int horasTotales = gac.sumarHoras(80, horasActuales);
        assertEquals(200, horasTotales); // Se espera que las horas totales sean 200.
    }
    
    void testMostrarHorasActualizado() {
    	// Verifica que se muestren las horas actualizadas correctamente.
       
        Registro registro = new Avion("A012", "Airbus A320", 90, true);
        gac.mostrarHorasActualizado(registro.getNombre(), registro.getHorasVuelo());

       
    }

}
