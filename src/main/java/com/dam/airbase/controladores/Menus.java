package com.dam.airbase.controladores;

import java.util.InputMismatchException;

import com.dam.airbase.entidades.Avion;
import com.dam.airbase.entidades.Piloto;
import com.dam.airbase.entidades.Registro;
import com.dam.airbase.interfaces.Constantes;
import com.dam.airbase.respositorio.IncidenciasRepositorio;
import com.dam.airbase.servicios.GestionAvionCRUD;
import com.dam.airbase.servicios.GestionIncidencias;
import com.dam.airbase.servicios.GestionPilotoCRUD;
import com.dam.airbase.servicios.GestionRuta;
import com.dam.airbase.servicios.Propiedad;

public class Menus {
	static int opcion = 0;
	
	//Llamamos a los Servicios, y lo Instanciamos para poderlos inicializar
	private static Registro r;
	private static GestionRuta gr = new GestionRuta();
	private static GestionPilotoCRUD gpc = new GestionPilotoCRUD();
	private static GestionAvionCRUD gac  = new GestionAvionCRUD();
	private static GestionIncidencias gi = new GestionIncidencias();
	private static Menus m = new Menus();
	private static Propiedad p = new Propiedad();
	private static IncidenciasRepositorio iP = new IncidenciasRepositorio();
	
	public void menu(String[] args) {
		p.crearArchivo();
		gr.archivoRuta();
		//gpc.baseDatos();
		
		//Creamos las variables Hijo del Padre
		Piloto p = (Piloto)r;
		Avion a = (Avion)r;
		
		//Inicializamos arrayList
		gpc.inicializar(p);
		gpc.archivoPiloto();
		gac.inicializar(a);
		gi.inicializarIncidencias();
		
		gr.leerArchivoContinente();
		
		gr.inicializarRutas();
		
		cabecera();
		menuX();
	}
	public void menuAdmin() {
		
	}
	
		
	public void menuX() {
        int opcion;
        do {
        	try {
	            menuP();
	            opcion = Constantes.sc.nextInt();
	
	            switch (opcion) {
	                case 1:
	                    menuGestionP(gpc, "Pilotos");
	                    break;
	                case 2:
	                    menuGestionA(gac, "Aviones");
	                    break;
	                case 3:
	                    menuGestionIncidencias(gi, gpc, gac);
	                    break;
	                case 4:
	                    menuGestionRuta(gr, "Rutas");
	                    break;
	                case 5:
	                    salir();
	                    break;
	                default:
	                	 error();
	            }
	        }catch (InputMismatchException e) {
	            Constantes.sc.nextLine(); 
	            System.out.println("  ✘-< Error: Debes ingresar un número entero. >-✘");
	            opcion = 0;
	        } 
    	}while (opcion != 5);
	}
	
	public void menuGestionRuta(GestionRuta gr, String entidad) {
		int opcion;
        do {
        	try {
	            m.gestionR();
	
	            opcion = Constantes.sc.nextInt();
	
	            switch (opcion) {
	                case 1:
	                
	                	String carga = gpc.elegirCarga(gpc.tipoCarga(Constantes.sc),Constantes.sc,gpc.tiposCargamento());
	            		gpc.mostrarCarga(carga);
	            		gpc.validarPiloto2();
	            		gac.validarAvion2();
	            		gr.mostrarPaises();
	                	gr.mostrarRuta();
	                    break;
	                case 2:
	                    gr.mostrarPaises();
	                    break;
	                case 3:
	                    gr.verRutas(gr.obtenerRutas());
	                    break;
	                case 4:
	                    System.out.println(" --~{ Volviendo al Menú Principal.}~-- ");
	                    break;
	                default:
	                	 m.error();
	            }
        	}catch (InputMismatchException e) {
	            Constantes.sc.nextLine(); 
	            System.out.println("Error: Debes ingresar un número entero.");
	            opcion = 0;
	        } 
        } while (opcion != 4);
    }

	public void menuGestionA(GestionAvionCRUD gestion, String entidad) {
		int opcion;
        do {
        	try {
	            m.menuGestion(entidad);
	            opcion = Constantes.sc.nextInt();
	
	            switch (opcion) {
	                case 1:
	                    gestion.alta(null); // Puedes pasar un Registro si es necesario
	                    break;
	                case 2:
	                    gestion.baja(null);
	                    break;
	                case 3:
	                    gestion.modificar(null);
	                    break;
	                case 4:
	                    gestion.ver(null);
	                    break;
	                case 5:
	                    System.out.println(" --~{ Volviendo al Menú Principal.}~-- ");
	                    break;
	                default:
	                   m.error();
	            }
        	}catch (InputMismatchException e) {
	            Constantes.sc.nextLine(); 
	            System.out.println("Error: Debes ingresar un número entero.");
	            opcion = 0;
	        } 
        } while (opcion != 5);

		
	}

	// Método para mostrar menú y gestionar CRUD
    public void menuGestionP(GestionPilotoCRUD gestion, String entidad) {
        int opcion;
        do {
            try {
	        	m.menuGestion(entidad);
	            opcion = Constantes.sc.nextInt();
	
	            switch (opcion) {
	                case 1:
	                    gestion.alta(null); // Puedes pasar un Registro si es necesario
	                    
	                    break;
	                case 2:
	                    gestion.baja(null);
	                    break;
	                case 3:
	                    gestion.modificar(null);
	                    break;
	                case 4:
						System.out.println(" \n"
										+"            Pilotos De su Base de datos \n"
										+ "==========================================================\n"
										+ " Codigo | Nombre | Horas de Vuelo | Operativo | Admin \n");
	                    gestion.ver(null);
	                	
	                    break;
	                case 5:
	                    System.out.println(" --~{ Volviendo al Menú Principal.}~-- ");
	                    break;
	                default:
	                	 m.error();
	            }
            }catch (InputMismatchException e) {
	            Constantes.sc.nextLine(); 
	            System.out.println("Error: Debes ingresar un número entero.");
	            opcion = 0;
	        } 
        } while (opcion != 5);
    }
	
	public void menuGestionIncidencias(
            GestionIncidencias gestionIncidencias, GestionPilotoCRUD gestionPilotoCRUD,
            GestionAvionCRUD gestionAvionCRUD) {
        int opcion;
        do {
        	try {
	            m.menuInciden();
	
	            opcion = Constantes.sc.nextInt();
	
	            switch (opcion) {
	                case 1:
	                    gestionIncidencias.incidenciaPiloto(gestionPilotoCRUD.obtenerPilotos());
	                    break;
	                case 2:
	                    gestionIncidencias.incidenciaAvion(gestionAvionCRUD.obtenerAviones());
	                    break;
	                case 3:
	                	gestionIncidencias.verIncidencias(iP.LeerIncidencias());
	                	gestionIncidencias.verIncidenciasHash(iP.LeerIncidenciasHash());
	                    break;
	                case 4:
	                    System.out.println(" --~{ Volviendo al Menú Principal.}~-- ");
	                    break;
	                default:
	                	 m.error();
	            }
        	}catch (InputMismatchException e) {
	            Constantes.sc.nextLine(); 
	            System.out.println("Error: Debes ingresar un número entero.");
	            opcion = 0;
	        } 
        } while (opcion != 4);
    }
	


	
// Camisa de ONCE VARAS (complicamos el codigo Demasiado Funciones no utilizadas
	public void menuPrincipal() {
		System.out.println("	Bienvenido Almirante ");
		System.out.println("	  ¿Como deseas acceder?");
		System.out.println("	  [1]. Administrador ");
		System.out.println("	  [2]. Piloto");
		System.out.println("	  [6]. Salir Programa");
		System.out.println("");
		System.out.println(" Opción : ");
	
	}
	
	public void piloto() {
		System.out.println("	Bienvenido Piloto ");
		System.out.println("	  [1]. Ficha ");
		System.out.println("	  [2]. Incidencias");
		System.out.println("	  [3]. Misiones ");
		System.out.println("	  [4]. Aviones Pilotados");
		System.out.println("	  [5]. Proxima Misión");
		System.out.println("	  [6]. Salir Programa");
		System.out.println("");
		System.out.println(" Opción : ");
		
	}
	
	public void ficha() {
		System.out.println("	Bienvenido Piloto ");
		System.out.println("	  [1]. Horas de Vuelo ");
		System.out.println("	  [2]. Misiones Realizadas");
		System.out.println("	  [3]. Incidencias ");
		System.out.println("	  [4]. volver");
		System.out.println("	  [5]. Salir Programa");
		System.out.println("");
		System.out.println(" Opción : " );
	}
	
	public void piloInciden() {
		System.out.println("	Bienvenido Piloto "); //añadir Nombre del Piloto
		System.out.println("	  [1]. Registrar Incidencia ");
		System.out.println("	  [2]. Historico Incidencia ");
		System.out.println("	  [3]. Incidencias ");
		System.out.println("	  [4]. Volver");
		System.out.println("	  [5]. Salir Programa");
		System.out.println("");
		System.out.println(" Opción : ");
		
	}
	
	public void piloAvion() {
		System.out.println("	Bienvenido Piloto ");
		System.out.println("	  [1]. Aviones Pilotados ");
		System.out.println("	  [2]. Volver");
		System.out.println("	  [3]. Salir Programa");
		System.out.println("");
		System.out.println(" Opción : ");
		
	}
	
	public void piloMision() {
		System.out.println("	Bienvenido Piloto ");
		System.out.println("	  [1]. Misiones Realizadas ");
		System.out.println("	  [2]. Elegir Mision");
		System.out.println("	  [3]. Volver");
		System.out.println("	  [4]. Salir Programa");
		System.out.println("");
		System.out.println(" Opción : ");
		
	}
	
	public void admin() {
		System.out.println("	Bienvenido Admin ");
		System.out.println("	  [1]. Pilotos ");
		System.out.println("	  [2]. Aviones");
		System.out.println("	  [3]. Incidencias ");
		System.out.println("	  [4]. Generar Misiones");
		System.out.println("	  [5]. Hisotirco");
		System.out.println("	  [6]. Salir Programa");
		System.out.println("");
		System.out.println(" Opción : ");
		
	}	
	
	public void historial() {
		System.out.println("	Bienvenido Admin ");
		System.out.println("	  [1]. Pilotos Agregados");
		System.out.println("	  [2]. Piolotos Eliminados ");
		System.out.println("	  [3]. Aviones Agregados ");
		System.out.println("	  [4]. Aviones Eliminados");
		System.out.println("	  [5]. Misiones Realizadas");
		System.out.println("	  [6]. Misiones Fallidas");
		System.out.println("	  [7]. Volver");
		System.out.println("	  [8]. Salir Programa");
		System.out.println("");
		System.out.println(" Opción : ");
		
	}
	
	public void incidencia() {
		System.out.println("	Bienvenido Admin ");
		System.out.println("	  [1]. Estado de Pilotos ");
		System.out.println("	  [2]. Estado de Aviones ");
		System.out.println("	  [3]. Incidencias ");
		System.out.println("	  [5]. Volver ");
		System.out.println("	  [6]. Salir Programa ");
		System.out.println("");
		System.out.println(" Opción : ");
		
	}
	
	public void avion() {
		System.out.println("	Bienvenido Admin ");
		System.out.println("	  [1]. Alta de Avión ");
		System.out.println("	  [2]. Baja de Avión");
		System.out.println("	  [3]. Incidencias ");
		System.out.println("	  [4]. Historial de un Avion");
		System.out.println("	  [5]. Volver");
		System.out.println("	  [6]. Salir Programa");
		System.out.println("");
		System.out.println(" Opción : ");
	
	}
	
	public void pilo() {
		System.out.println("	Bienvenido Admin ");
		System.out.println("	  [1]. Alta de Piloto ");
		System.out.println("	  [2]. Baja de Piloto");
		System.out.println("	  [3]. Incidencias ");
		System.out.println("	  [4]. Generar Misiones");
		System.out.println("	  [5]. Volver");
		System.out.println("	  [6]. Salir Programa");
		System.out.println("");
		System.out.println(" Opción : ");
		
	}
//---------------------- Realmente Los que Utilizo ------------------------------------
	
	public void menuP() {
		System.out.println("\n ====== Menú Principal ======");
        System.out.println("   [1]. Gestión de Pilotos");
        System.out.println("   [2]. Gestión de Aviones");
        System.out.println("   [3]. Gestión de Incidencias");
        System.out.println("   [4]. Gestión de Rutas");
        System.out.println("   [5]. Salir");
        System.out.print("  Seleccione una opción: ");
		
	}
	
	public void gestionR() {
		System.out.println("\n ====== Menú de Gestión de Rutas ====== ");
        System.out.println("   [1]. Crear Misión ");
        System.out.println("   [2]. Mostrar Paises Disponibles ");
        System.out.println("   [3]. Ver Rutas ");
        System.out.println("   [4]. Volver al Menú Principal ");
        System.out.print("  Seleccione una opción: ");
		
	}
	public void menuGestion(String entidad) {
		System.out.println("\n ====== Menú de Gestión de " + entidad + " ====== ");
        System.out.println("   [1]. Alta " + entidad);
        System.out.println("   [2]. Baja " + entidad);
        System.out.println("   [3]. Modificar " + entidad);
        System.out.println("   [4]. Ver " + entidad);
        System.out.println("   [5]. Volver al Menú Principal");
        System.out.print("  Seleccione una opción: ");
		
	}
	public void menuInciden() {
		System.out.println("\n ====== Menú de Gestión de Incidencias ====== ");
        System.out.println("   [1]. Incidencia de Piloto");
        System.out.println("   [2]. Incidencia de Avión");
        System.out.println("   [3]. Ver Incidencias");
        System.out.println("   [4]. Volver al Menú Principal");
        System.out.print("  Seleccione una opción: ");
		
	}
	
	public void volver() {
		System.out.println("\n ====== Menú de Gestión de Incidencias ====== ");
        System.out.println("   [1]. Incidencia de Piloto");
        System.out.println("   [2]. Incidencia de Avión");
        System.out.println("   [3]. Ver Incidencias");
        System.out.println("   [4]. Volver al Menú Principal");
        System.out.print("  Seleccione una opción: ");
		
	}
	
	public void salir() {
		System.out.println("");
		System.out.println("\r\n"
				+ "   █▀▀ █ █▄ █   █▀▄ █▀▀ █     █▀█ █▀█ █▀█ █▀▀ █▀█ ▄▀█ █▀▄▀█ █▀▄\r\n"
				+ "   █▀  █ █ ▀█   █▄▀ ██▄ █▄▄   █▀▀ █▀▄ █▄▀ █▄█ █▀▄ █▀█ █ ▀ █ █▀█ Ⓐ");
		System.out.println("");
		System.exit(1);
		
	}
	public void cabecera() {
		System.out.println("");
		System.out.println("===============================================================================");
		System.out.println("   ░█▀▄░█▀█░█▄█░░░█▀▀░█▀▀░█▀▀░▀█▀░█▀█░█▀▄░░░█▀▀░█░█░█▀▀░▀█▀░█▀▀░█▄█░░░▀█▀░▀█▀░\r\n"
				+ "   ░█░█░█▀█░█░█░░░▀▀█░█▀▀░█░░░░█░░█░█░█▀▄░░░▀▀█░░█░░▀▀█░░█░░█▀▀░█░█░░░░█░░░█░░\r\n"
				+ "   ░▀▀░░▀░▀░▀░▀░░░▀▀▀░▀▀▀░▀▀▀░░▀░░▀▀▀░▀░▀░░░▀▀▀░░▀░░▀▀▀░░▀░░▀▀▀░▀░▀░░░▀▀▀░▀▀▀░ ");
		System.out.println("=============================================================================== Ⓐ");
		System.out.println("");
		System.out.println("");
		System.out.println("\r\n"
				+ "   █ █▄ █ █ █▀▀ █ █▀█   █▀▄ █▀▀ █     █▀█ █▀█ █▀█ █▀▀ █▀█ █▀█ █▀▄▀█ █▀▄\r\n"
				+ "   █ █ ▀█ █ █▄▄ █ █▄▀   █▄▀ ██▄ █▄▄   █▀▀ █▀▄ █▄█ █▄█ █▀▄ █▀█ █ ▀ █ █▀█ Ⓐ");
		System.out.println("");	
	}	
	public void error() {
		System.out.println("");
		System.out.println("   --~{ Opción no válida. Intente de nuevo.}~--  ");
		System.out.println("");
		

	}
}