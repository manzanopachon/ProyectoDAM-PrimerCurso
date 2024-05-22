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
import java.util.Scanner;

import com.dam.airbase.entidades.Avion;
import com.dam.airbase.entidades.Incidencias;
import com.dam.airbase.entidades.Piloto;
import com.dam.airbase.respositorio.IncidenciasRepositorio;


public class GestionIncidencias {
	Scanner dato = new Scanner(System.in);
	public String rutaArchivo = "ficheros/Incidencias.txt";
	public ArrayList<Incidencias> incidencia;
	protected IncidenciasRepositorio ir = new IncidenciasRepositorio();
	
	public void inicializarIncidencias() {
		incidencia = new ArrayList <Incidencias>();
		cargarDesdeArchivoCSV();
	}
	
	public ArrayList<Incidencias> obtenerIncidencias(){
		
		return incidencia;
	}
	public void incidenciaPiloto(ArrayList<Piloto> pilotos) {
		boolean ok = false;
		String definicion = "Piloto";
		int seleccion = 0;
		String numIncidencia = "";
		String codPil = "";
		String descrip = "";
		String op = "";
		Boolean operativo = null;
		System.out.println("Introduce el numero de incidencia");
		numIncidencia = dato.next();
		while(ok == false) {
		System.out.println("Introduce el código del piloto");
		codPil = dato.next();
		for(int i = 0;i<pilotos.size() && ok ==false;i++) {
			if(codPil.equals(pilotos.get(i).getCodigo())) {
				seleccion = i;
				ok = true;
			} else {ok = false;}
		}
		if(ok == false) {
			System.out.println("Ningun piloto encontrado, vuelva a introducir");
		}
		} 
		System.out.println("Piloto seleccionado: " + pilotos.get(seleccion).getNombre());
		System.out.println("Introduczca una descripción de la incidencia");
		descrip = dato.nextLine();
		descrip = dato.nextLine();
		while (ok == true) {
		System.out.println("¿Apto para el servicio?");
		op = dato.next();
		if(op.charAt(0) =='n'||op.charAt(0)=='N') {
			pilotos.get(seleccion).setOperativo(false);
			ok = false;
			operativo = false;
		} else if(op.charAt(0) =='s'||op.charAt(0)=='S') {
			pilotos.get(seleccion).setOperativo(true);
			ok = false;
			operativo = true;
		} else {
			System.out.println("Error, respuesta no valida");
			ok = true;
		}
		}
		String fecha = LocalDate.now().toString();
		incidencia.add(new Incidencias(numIncidencia,codPil,descrip,operativo,fecha));
		ir.insertarIncidencias(numIncidencia, codPil, descrip, operativo, fecha,definicion);
		actualizarArchivoCSV();
	}
	
	public void incidenciaAvion(ArrayList<Avion> aviones) {
		boolean ok = false;
		String definicion = "Avion";
		int seleccion = 0;
		Boolean operativo = null;
		String numIncidencia = "";
		String codAv = "";
		String descrip = "";
		String op = "";
		System.out.println("Introduce el numero de incidencia");
		numIncidencia = dato.next();
		while(ok == false) {
		System.out.println("Introduce el código del avión");
		codAv = dato.next();
		for(int i = 0;i<aviones.size() && ok ==false;i++) {
			if(codAv.equals(aviones.get(i).getCodigo())) {
				seleccion = i;
				ok = true;
			} else {ok = false;}
		}
		if(ok == false) {
			System.out.println("Ningun avión encontrado, vuelva a introducir");
		}
		} 
		System.out.println("Avión seleccionado: " + aviones.get(seleccion).getNombre());
		System.out.println("Introduczca una descripción de la incidencia");
		descrip = dato.nextLine();
		descrip = dato.nextLine();
		while (ok == true) {
		System.out.println("¿Apto para el servicio?");
		op = dato.next();
		if(op.charAt(0) =='n'||op.charAt(0)=='N') {
			aviones.get(seleccion).setOperativo(false);
			ok = false;
			operativo = false;
		} else if(op.charAt(0) =='s'||op.charAt(0)=='S') {
			aviones.get(seleccion).setOperativo(true);
			ok = false;
			operativo = true;
		} else {
			System.out.println("Error, respuesta no valida");
			ok = true;
		}
		}
		String fecha = LocalDate.now().toString();
		incidencia.add(new Incidencias(numIncidencia,codAv,descrip,operativo,fecha));
		ir.insertarIncidencias(numIncidencia, codAv, descrip, operativo, fecha,definicion);
		actualizarArchivoCSV();
	}
	
	
/*	
	public void incidenciaAvion(ArrayList<Avion> aviones) {
		boolean ok = false;
		int seleccion = 0;
		String numIncidencia = "";
		String codAv = "";
		String descrip ="";
		String op = "";
		System.out.println("Introduce el numero de incidencia");
		numIncidencia = dato.next();
		while(ok==false) {
		System.out.println("Introduce el código del avión");
		codAv = dato.next();
		for(int i = 0;i<aviones.size() && ok == false;i++) {
			if(codAv.equals(aviones.get(i).getCodigo())) {
				seleccion = i;
				ok = true;
			} else {ok = false;}
		}
		if(ok == false) {
			System.out.println("Ningún avión encontrado");
		}
		}
		System.out.println("Avión seleccionado: " +aviones.get(seleccion).getNombre());
		System.out.println("Introduzca una descripción de la incidencia");
		descrip = dato.nextLine();
		descrip = dato.nextLine();
		while (ok == true) {
		System.out.println("¿Apto para el servicio?");
		op = dato.next();
		if(op.charAt(0) =='n'||op.charAt(0)=='N') {
			aviones.get(seleccion).setOperativo(false);
			ok = false;
		} else if(op.charAt(0) =='s'||op.charAt(0)=='S') {
			aviones.get(seleccion).setOperativo(true);
			ok = false;
		} else {
			System.out.println("Error, respuesta no valida");
			ok = true;
		}
		}
		incidencia.add(new Incidencias(numIncidencia,codAv,descrip,op));
		actualizarArchivoCSV();
	}
*/
	public void verIncidencias(ArrayList<Incidencias> incidencia) {
		for(int i = 0;i<incidencia.size();i++) {
			System.out.println("[Numero de incidencia: "+incidencia.get(i).getNumeroIncidencia() + 
					"] [código ref: "+incidencia.get(i).getCodigoRef()+"] [incidencia: "+incidencia.get(i).getDescripccion()
					+"] [operativo: "+incidencia.get(i).getOperativo()+"] [Fecha: "+incidencia.get(i).getFecha()+"]");
		}
	}
	
	public void verIncidenciasHash(HashMap<String,ArrayList<Incidencias>>hash) {
		for(String s : hash.keySet()) {
			ArrayList<Incidencias> in = hash.get(s);
			for(int i=0;i<in.size();i++) {
				in.toString();
			}
		}
	}
	
public void archivoIncidencia() {
		
		File fichero = new File(rutaArchivo);
		try {
			if(fichero.createNewFile()) {
				System.out.println("Archivo creado correctamente (Incidencias.txt)");
	
			}else {
				System.out.println("Error al crear archivo. Ya existe (Incidencias.txt)");
				
			}
		}catch (IOException e) {
			escribirError(e.getMessage());
			e.printStackTrace();
		}
		
	}

private void cargarDesdeArchivoCSV() {   
	//Esta funcion lo que hace es leer el archivo nada mas se ejecuta el programa.
	//Mientras leemos el archivo se van guardando los aviones en un arrayList hasta el final del archivo
    try {
    	 BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo));
         String linea;   
         //Para saltar la primera linea
         reader.readLine();
         // Leer cada línea del archivo CSV
         while((linea = reader.readLine()) != null)  {

             String[] campos = linea.split(",");

             String codigoincidencia = campos[0];
             String codigo = campos[1];
             String descripcion = campos[2];
             Boolean operativo = null;
             if(campos[3].equals("si")) {
            	 operativo = true;
             } else {
            	 operativo = false;
             }
             String fecha = campos[4];
             Incidencias incidencia2 = new Incidencias(codigoincidencia, codigo, descripcion, operativo,fecha);
             incidencia.add(incidencia2);
         }
         reader.close();
     } catch (IOException e) {
    	 escribirError(e.getMessage());
         System.out.println("No se pudo cargar desde el archivo: " + e.getMessage());
     }
 }
	
public ArrayList<Incidencias> leerIncidencias(String rutaArchivo) {
	ArrayList<Incidencias>incidencia2 = new ArrayList<>();
	try  {
        BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo));
        String linea;

        reader.readLine();

        while ((linea = reader.readLine()) != null) {
            String[] campos = linea.split(",");
            String codigoincidencia = campos[0];
            String codigo = campos[1];
            String descripcion= campos[2];
            Boolean operativo = null;
            if(campos[3].equals("si")) {
           	 operativo = true;
            } else {
           	 operativo = false;
            }
            String fecha = campos[4];
            Incidencias incidencias = new Incidencias(codigoincidencia, codigo, descripcion, operativo,fecha);
            incidencia.add(incidencias);
            for(int i =0;i< campos.length;i++) {
            	System.out.print(campos[i]+",");
            }
            System.out.println();
        }
        reader.close();
        System.out.println("\nLos datos de los pilotos se han leído desde el archivo correctamente.");
    } catch (IOException e) {
    	escribirError(e.getMessage());
        System.out.println("Error al leer los datos de los pilotos desde el archivo : " + e.getMessage());
    }
    return incidencia2;
}

private void actualizarArchivoCSV() {
	 
    try (FileWriter writer = new FileWriter(rutaArchivo)) {
    	
   	 System.out.println("Escrito Correctamente...");
        writer.append("Numero incidencia,Código,Descripción,Operativo\n");

        for (Incidencias incidencia2 : incidencia) {
            writer.append(incidencia2.getNumeroIncidencia() + "," +
                          incidencia2.getCodigoRef() + "," +
                          incidencia2.getDescripccion() + "," +
                          incidencia2.getOperativo() + "," +
                         incidencia2.getFecha()+"\n");
            				
        }
       
    } catch (IOException e) {
    	escribirError(e.getMessage());
        System.out.println("Error al actualizar el archivo txt: " + e.getMessage());
    }
}

public void escribirError(String error) {
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
}
