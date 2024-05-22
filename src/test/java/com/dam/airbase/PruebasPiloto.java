package com.dam.airbase;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dam.airbase.entidades.Piloto;
import com.dam.airbase.interfaces.Constantes;
import com.dam.airbase.servicios.GestionPilotoCRUD;


class PruebasPiloto {
	
	 private GestionPilotoCRUD gestion= new GestionPilotoCRUD();
	 //No es necesario public ArrayList <Piloto> pilotos= new ArrayList <Piloto>();
	 
	 @BeforeEach
	 void setUp() {
		 gestion = new GestionPilotoCRUD();
	     gestion.inicializar(new Piloto("P001", "Faly", 200, true,true));
	 }

	@Test
	void existePiloto() {
		//1. Comprueba si existe un piloto(Juan Mauricio)	1
		Piloto piloto = new Piloto("P001","Faly",200,true,true);
		assertNotNull(piloto);
		
	}
	
	@Test
	void testToString() {
		//2. Verifica que el metódo toString funciona correctamente (Juan Mauricio)
		Piloto piloto = new Piloto("P005", "Jose", 500, false,true);
	    String esperado = "Piloto [codigo=P005, nombre=Jose, horasVuelo=500]";
	    assertEquals(esperado, piloto.toString());
	     
	    }
	 
	@Test
	void testIfExist() {
		//3. Comprueba que si existe un piloto con un codigo existente da false el método ifExist() (Juan Mauricio)
		boolean ok = true;
	    String codigoExistente = "P001"; // piloto existente
	    boolean existente = gestion.ifExist(new Piloto(), codigoExistente, ok);
	    assertFalse(existente);

	    
	}

	@Test
	void testObtenerPilotos() {
	       
		//4. Verifica que la lista de pilotos no sea nula antes de inicializarla (Juan Mauricio)
	    assertNotNull(gestion.obtenerPilotos());

	}
	
	@Test
	void testAlta() {
		//5. Verifica que se pueda agregar un nuevo piloto y que exista después de agregarlo (Juan Mauricio)

		Piloto p = new Piloto();

       assertEquals(gestion.ifExist(p, "P001", true),false);
	       
   }
	  
	@Test
	  void testBaja() {
		// Verifica que se pueda dar de baja a un piloto existente (Juan Mauricio)
	    Piloto pilotoExistente = new Piloto("P002", "Constantino", 200, true,true);
	    gestion.baja(pilotoExistente);
	    assertEquals(gestion.ifExist(pilotoExistente, "P002", true),true);
	    
	  }

	@Test
	void testModificar() {
	      // Verifica que se pueda modificar la información de un piloto existente (Juan Mauricio)
	      Piloto pilotoExistente = new Piloto("P003", "Omar", 200, true,true);
	        
	      // Modifica el nombre del piloto
	      gestion.modificar(pilotoExistente);
	      assertEquals("Omar", pilotoExistente.getNombre());
	   
	}

	 

	@Test
	void testElegirCarga() {
	    // Verifica que se elija correctamente el tipo de carga. En este caso Armamento (Juan Mauricio)
	    String[][] tiposCargamento = gestion.tiposCargamento();
	     
	    assertEquals("Amramento", gestion.elegirCarga(true, Constantes.sc, tiposCargamento));
	}
	    
	@Test
	 
	void testTipoCargaProvisiones() {
		//Verifica que se elije la opcion "Provisiones" correctamente (Juan Mauricio)
		boolean var = gestion.tipoCarga(Constantes.sc);
		
		assertTrue(var);
	}

	@Test
    void testSumarHoras() {
        // Verifica que la función sumarHoras sume correctamente las horas de ruta y las horas de vuelo (Juan Mauricio)

        int horaRuta = 10;
        int horaVuelo = 20;
        int resultado = gestion.sumarHoras(horaRuta, horaVuelo);

        assertEquals(30, resultado);
    }
}
