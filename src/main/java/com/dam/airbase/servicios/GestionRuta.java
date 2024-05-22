package com.dam.airbase.servicios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.dam.airbase.entidades.Ruta;
import com.dam.airbase.interfaces.Constantes;

public class GestionRuta {
	
	//Atributos
	public static final Random r = new Random();
	
	public String rutaArchivo = "ficheros/Rutas.txt";
	
	public static final String TIEMPOVUELO = "ficheros/tiemposVuelo.txt";
	
	protected final static HashMap<String, ArrayList<String>> HMpaises =  new HashMap<String, ArrayList<String>>();
		     
	private String paises = "  Africa: Sudafrica,Nigeria,Kenia,Marruecos,Ghana,Senegal,Etiopia\n"
			+ "  America:Estados Unidos,Canada,Mexico,Argentina,Brasil,Colombia,Peru\n"
			+ "  Antartida: Orcadas,Carlini,Marambio,Petrel,Bellingshausen,Palmer,Halley\n"
			+ "  Asia: Japon,China,India,Indionesia,Corea del Sur,Singapur,Iran\n"
			+ "  Europa: Italia,Alemania,Francia,España,Inglaterra,Polonia,Ucrania\n"
			+ "  Oceania: Australia,Fiyi,Samoa,Islas Salomon,Nauru,Tonga,Palaos";
		
	public static ArrayList <Ruta> rutas;
	
	int horas = 0;
	//Constructores
	public GestionRuta() {
		inicializarRutas();		
	}
	
	//------------------------------- metodos	
	public void inicializarRutas() {
		rutas = new ArrayList <Ruta>();
		// ahora gestionamos las Rutas desde el fichero
		cargarDesdeArchivoCSV();	
	}
		
	public ArrayList<Ruta> obtenerRutas(){
		return rutas;
	}
	
	public void mostrarPaises() {
		System.out.println("\n"+paises+"\n");	
	}
		
	private int horasRuta (String ContinenteOrigen, String ContinenteDestino) {
		int tiempoRuta=0;
			try (BufferedReader br = new BufferedReader(new FileReader(TIEMPOVUELO))) {
		        String linea;
		        while ((linea = br.readLine()) != null) {
		            String[] datos = linea.split(",");
		            ContinenteOrigen = datos[0];
		            ContinenteDestino = datos[1];
		            
		            int min = Integer.parseInt(datos[2].strip());
		            int max = Integer.parseInt(datos[3].strip());
		            
		            tiempoRuta = generarRandom(min,max);
		            return tiempoRuta;
		        }
		    } catch (IOException e) {
		        System.err.println("  Error al calcular el tiempo de la ruta: " + e.getMessage());
		    }
		    return tiempoRuta;
	}
	
	public void archivoRuta() {	
		File fichero = new File(TIEMPOVUELO);
		try {
			if(fichero.createNewFile()) {
				System.out.println("  Archivo creado correctamente...");
			}else {
				System.out.println("");
			}
		}catch (IOException e) {
			escribirError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public int generarRandom(int min, int max) {
		//Funcion que genera numeros aleatorios a partir de un maximo y un minimo	
		return r.nextInt(max-min+1)+min;
	}
	
	public boolean validarRuta(String continente, String pais) {
		boolean correct = false;
		for(String key:HMpaises.keySet()) {
			//con la clave obtengo el valor
			if(continente.equals(key)) {
				for(int i = 0; i<7;i++) {
					if(pais.equals(HMpaises.get(key).get(i))) {
						correct = true;
					}
				}
			}
		}
		return correct;		
	}
	
	public int altaRuta(ArrayList<Ruta> rutas) {
		boolean ok=false;
		String continenteOrigen="";
		String paisOrigen="";
		String continenteDestino="";
		String paisDestino="";
		
		while(ok==false) {
			System.out.println("  Introduce el continente de origen");
			continenteOrigen = Constantes.sc.next();
			System.out.println("  Introduce el pais de origen");
			paisOrigen = Constantes.sc.next();
			if(validarRuta(continenteOrigen,paisOrigen)==false) {
				System.out.println("  Error. Introducelos de nuevo");
				System.out.println(paises+"\n");
				ok=false;
			}else if(validarRuta(continenteOrigen,paisOrigen)==true){
				ok=true;	
			}
		}
		ok=false;
		while(ok==false) {
			System.out.println("  Introduce el continente de destino");
			continenteDestino = Constantes.sc.next();
			System.out.println("  Introduce el pais de destino");
			paisDestino = Constantes.sc.next();
			if(validarRuta(continenteDestino,paisDestino)==false) {
				System.out.println("  Error. Introducelos de nuevo");
				System.out.println(paises+"\n");
				ok=false;
			}else {
				ok=true;
			}	
		}
		horas = horasRuta(continenteOrigen,continenteDestino);
		rutas.add(new Ruta (paisOrigen, paisDestino, horas));
		return horas;
	}
	
	public void mostrarRuta() {
		int h = altaRuta(rutas);
		System.out.println("   La duracion de la ruta es: "+ h +" horas\n");	
		// escribimos el return en el archivo
		actualizarArchivoCSV();
	}
	
	public void verRutas(ArrayList<Ruta> rutas) {	
		leerRutas(rutaArchivo);
	}	
	
	public  ArrayList <Ruta> leerRutas(String rutaArchivo) {
			ArrayList <Ruta> rutas = new ArrayList<>();
	        try {
				BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo));
	            String linea;          
	            // Leer encabezados (ignorar primera línea)
	            reader.readLine();
	            while ((linea = reader.readLine()) != null) {
	                String[] campos = linea.split(",");
	                String paisOrigen = campos[0];
	                String paisDestino = campos[1];
	                int horasVuelo = Integer.parseInt(campos[2]);

	                Ruta ruta = new Ruta(paisOrigen, paisDestino, horasVuelo);
	                rutas.add(ruta);
	                // par que salga las rutas que hemos creado
	                for(int i =0;i< campos.length;i++) {
		            	System.out.print("  " + campos[i]);
		            }
	                System.out.println();
	            }
	            
	            reader.close();
	            System.out.println("\n Las rutas se han leído desde el archivo correctamente.");
	        } catch (IOException e) {
	            escribirError("  Error al leer las rutas del archivo: " + e.getMessage());
	        }
	        return rutas;
	  }
	
	private void escribirError(String error) {
		File f = new File("logs/properties.log");
		FileWriter fw;
		try {
			if(f.exists()) {
				fw = new FileWriter(f, true);
				BufferedWriter bw = new BufferedWriter(fw); 
			    bw.write("["+LocalDate.now()+"]"+"["+LocalTime.now()+"]"+error+"\n" );
			    bw.flush();
			    bw.close(); 
			    fw.close(); 
			}else if(f.length()>=1000) {
				for (int i = 0; i<10;i++) {
					String arch="logs/properties";
					
					fw = new FileWriter(arch+i+".log", true);
					BufferedWriter bw = new BufferedWriter(fw); 
				    bw.write("["+LocalDate.now()+"]"+"["+LocalTime.now()+"]"+error+"\n" );
				    bw.flush();
				    bw.close(); 
				    fw.close(); 
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void cargarDesdeArchivoCSV() {   
			//Esta funcion lo que hace es leer el archivo nada mas se ejecuta el programa.
			//Mientras leemos el archivo se van guardando las ruta en un arrayList hasta el final del archivo
		    try {
		    	 BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo));
		         String linea;   
		         //Para saltar la primera linea
		         reader.readLine();
		         // Leer cada línea del archivo CSV
		         while((linea = reader.readLine()) != null)  {
		             // Dividir la línea en campos usando la coma como delimitador
		             String[] campos = linea.split(",");
		             // Crear una nueva ruta 	
		             String paisOrigen = campos[0];
		             String paisDestino = campos[1];
		             int h = Integer.parseInt(campos[2]);
		             Ruta ruta = new Ruta(paisOrigen, paisDestino, h);
		             rutas.add(ruta);
		         }
		         reader.close();
		     } catch (IOException e) {
		    	 escribirError("  No se pudo cargar desde el archivo: " + e.getMessage());	         
		     }
	}
	
	private void actualizarArchivoCSV() {
		    try (FileWriter writer = new FileWriter(rutaArchivo)) {		    	
		   	 System.out.println("  Escrito Correctamente...");
		        writer.append("  Pais Origen: Pais Destino : Horas de la ruta\n");
		        for (Ruta ruta : rutas) {
		            writer.append(ruta.getPaisOrigen() + "," +
		            			  ruta.getPaisDestino() + "," +
		                          ruta.getH() +"\n");
		        }   
		    } catch (IOException e) {
		    	escribirError(e.getMessage());
		        System.out.println("  Error al actualizar el archivo txt: " + e.getMessage());
		    }
	}
	
	// esta funcion a sido creada solo y EXCLUSIVAMENTE par los TEST
	public void eliminarRuta(ArrayList<Ruta> rutas, String paisOrigen, String paisDestino) {
		    ArrayList<Ruta> rutasTemporales = new ArrayList<>();
		    for (Ruta ruta : rutas) {
		        // Comparamos los países de origen y destino de la ruta actual con los proporcionados
		        if (!ruta.getPaisOrigen().equalsIgnoreCase(paisOrigen) || !ruta.getPaisDestino().equalsIgnoreCase(paisDestino)) {
		            // Agregamos las rutas que no coinciden a la lista temporal
		            rutasTemporales.add(ruta);
		        } else {
		            System.out.println("   Ruta eliminada: " + ruta.getPaisOrigen() + " - " + ruta.getPaisDestino());
		        }
		    }
		    // Limpiamos la lista original y agregamos las rutas no eliminadas
		    rutas.clear();
		    rutas.addAll(rutasTemporales);
		    if (rutasTemporales.size() == rutas.size()) {
		        System.out.println("   No se encontró la ruta con los países proporcionados.");
		    }
	}
	
	public void leerArchivoContinente() {
		try {
			File f = new File("continentes.txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String s ; 		
			while ((s = br.readLine()) != null) {
				ArrayList<String> paises= new ArrayList<>();
				String valores[] = s.split(",");
				String continente = valores[0];
				for(int i = 1;i<valores.length;i++) {
					String pais = valores[i];
	                paises.add(pais);
					HMpaises.put(continente, paises);
				}
			}

			br.close();
			fr.close();
		}catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
