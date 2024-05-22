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
import java.util.Random;
import java.util.Scanner;

import com.dam.airbase.entidades.Avion;
import com.dam.airbase.entidades.Registro;
import com.dam.airbase.interfaces.CRUDInterface;
import com.dam.airbase.interfaces.Constantes;
import com.dam.airbase.respositorio.AvionesRepositorio;

public class GestionAvionCRUD implements CRUDInterface {
	
	public ArrayList<Avion>aviones;
	private Random random = new Random();
	public String rutaArchivo = "ficheros/Aviones.txt";
	private AvionesRepositorio ar = new AvionesRepositorio();

	public void inicializar(Registro r) {
		aviones = new ArrayList <Avion>();
		/* NO ES NECESARIO INICIALIZARLO, LO CREAMOS DESDE 0
		aviones.add(new Avion("A001","F22",100,true));
		aviones.add(new Avion("A002","F15",250,true));
		aviones.add(new Avion("A003","Typhoon",700,true));
		aviones.add(new Avion("A004","Rafle",400,true));
		*/
		cargarDesdeArchivoCSV();
	}
	
	public ArrayList<Avion> obtenerAviones(){	
		return aviones;
	}

	public void alta(Registro r) {
		Avion a = (Avion)r;
		boolean ok =true;
		String codigo;
		do {
			System.out.println("Introduce el codigo del avion");
			codigo = Constantes.sc.next();
			ok = !ifExist(a,codigo,ok);
		}while(ok==true);
		System.out.println("Introduce el nombre del avion");
		String nombre = Constantes.sc.next();
		int horas = random.nextInt(201);
		boolean operativo = true;	
		aviones.add(new Avion (codigo,nombre,horas,operativo)); //esto casca y no sé por que
		ar.insertarAvion (codigo, nombre, horas, operativo);
		actualizarArchivoCSV();
	}
	
	public void baja(Registro r) {
		boolean ok = true;
		Avion p = (Avion)r;
		String codigo;
		do {
			System.out.println("Introduce el codigo del avion que quieres dar de baja");
			codigo = Constantes.sc.next();
			ok = ifExist(p,codigo,ok);
			for(int i = 0; i<aviones.size();i++) {
				if(codigo.equals(aviones.get(i).getCodigo())) {
					aviones.remove(i);
					aviones = ar.eliminarAvion(codigo);
				}
			}	
		} while(ok==true);
		actualizarArchivoCSV();
	}
	
	
	
	public void modificar(Registro r) {
		boolean ok = true;
		Avion p = (Avion)r;
		String codigo;
		String mod="";
		String dato="";
		do {
			System.out.println("Introduce el codigo del avion que quieres modificar");
			codigo = Constantes.sc.next();
			ok = ifExist(p,codigo,ok);
			for(int i = 0; i<aviones.size();i++) {
				if(codigo.equals(aviones.get(i).getCodigo())) {
					System.out.println(aviones.get(i));
					System.out.print("Que dato quieres modificar: ");
					mod= Constantes.sc.next();
					if(mod.charAt(0)=='C' || mod.charAt(0)=='c') {
						System.out.println("Introduce el nuevo codigo: ");
						dato = Constantes.sc.next();
						aviones.get(i).setCodigo(dato);
						aviones = ar.modificarAvion(mod.charAt(0), dato, codigo);
					}else if(mod.charAt(0)=='N' || mod.charAt(0)=='n') {
						System.out.println("Introduce el nuevo nombre: ");
						dato = Constantes.sc.next();
						aviones.get(i).setNombre(dato);
						aviones = ar.modificarAvion(mod.charAt(0), dato, codigo);
					}else if(mod.charAt(0)=='O' || mod.charAt(0)=='o'){
						System.out.println("Dime si está operativo[Si][No]: ");
						dato = Constantes.sc.next();
						if(dato.equals("Si")) {
							aviones.get(i).setOperativo(true);
							aviones = ar.modificarAvion(mod.charAt(0), "1", codigo);
						}else {
							aviones.get(i).setOperativo(false);
							aviones = ar.modificarAvion(mod.charAt(0), "0", codigo);
						}					
					}else {
						System.out.println("Introduce las nuevas horas de vuelo: ");
						int d = Constantes.sc.nextInt();
						aviones.get(i).setHorasVuelo(d);
						String castearInt = ""+d;
						aviones = ar.modificarAvion(mod.charAt(0), castearInt, codigo);
					}
				}
			}		
		} while(ok==true);
		actualizarArchivoCSV();
	}
	
	public void ver(Registro r) {
		/* ANTES USABAMOS EL ARRAYLIST PARA LEER LOS DATOS, AHORA USAMOS EL ARCHIVO DIRECAMENTE
		for(int i = 0;i < aviones.size(); i++) {
			System.out.println(aviones.get(i).getCodigo()+ ", " + aviones.get(i).getNombre() + ", " + aviones.get(i).getHorasVuelo() + " Horas de vuelo" + ", Operativo: " + aviones.get(i).getOperativo());
		}*/
		leerAviones(rutaArchivo);
		ArrayList<Avion> aviones = ar.leerAviones();
	}
	
	public boolean ifExist(Registro r,String codigo, boolean ok) {
		for (int i = 0;i<aviones.size() && ok==true;i++) {
			if(codigo.equals(aviones.get(i).getCodigo())) {
				System.out.println("Ya hay un avion así("+aviones.get(i).getNombre()+")");
				ok = false;
			} else {
				ok = true;
			}
		}
		return ok;
	}
	
	public String elegirCarga(boolean carga,Scanner sc,String[][]t ) {
		/*En esta funcion entra como parametro un booleano, Scanner sc y una matriz t.
		boolean carga:
			TRUE-->Provisiones-->ARMAMENTO MUNICION EXPLOSIVOS
			FALSE-->Suministro-->TROPAS VEHICULOS GASOLINA
		String [][] t:
			Se recorre la matriz para verificar que la carga es correcta, si no es así se pide de nuevo	
		Devuelve el tipo de carga que va a transportar
		*/
		boolean verificar=true;
		String texto;
		do {
			if (carga ==true) {
				System.out.println("¿Que tipo de provision va a transportar?");
				System.out.println("[Armamento, Munición, Explosivos]");
				texto=sc.next();
				String c=texto;
				for (int i = 0; i<t.length;i++) {
					for(int j =0;j<t[i].length;j++) {
						if(c.equals("Armamento")||c.equals("Municion")||c.equals("Explosivos")) {
							verificar=true;
						}
						else {
							verificar = false;
						}
					}
				}
				if(verificar ==false) {
					System.out.println("ERROR");
				}
			} else {
				System.out.println("¿Que tipo de suministro va a transportar?");
				System.out.println("[Tropas, Vehiculos, Gasolina]");
				texto=sc.next();
				String c=texto;
				for (int i = 0; i<t.length;i++) {
					for(int j =0;j<t[i].length;j++) {
						if(c.equals("Tropas")||c.equals("Vehiculos")||c.equals("Gasolina")) {
							verificar=true;
						}else {
							verificar = false;				
						}
					}
				}
				if(verificar ==false) {
					System.out.println("ERROR");
				}
				
			}
		}while(verificar==false);
		return texto;
	}
	
	public void mostrarCarga(String carga) {
		//Funcion que devuelve el mensaje de cual es la carga que se va a transportar
		System.out.println("La carga que se llevará en el avión es: "+carga );
	}
	
	public String[][] tiposCargamento() {
		//Matriz en la que se guarda los tipos de cargamento que puede llevar un avion
		String [][] t= new String [3][3];
		t[0][0] ="Armamento";
		t[0][1] ="Municion";
		t[0][2] ="Explosivos";
		
		t[1][0] ="Tropas";
		t[1][1] ="Vehiculos";
		t[1][2] ="Gasolina";
		return t;
	}
	
	public boolean tipoCarga(Scanner sc) {
		//Que tipo de vuelo es [Provisiones o Suministro]
		boolean vuelo = true; //true significa que es de Provisiones
		boolean verificar = false; //para verificar si se ha escrito correctamente
		while(verificar == false) {
			System.out.println("Que tipo de vuelo es: [Provisiones o Suministros]");
			char texto = sc.next().charAt(0);
			if (texto=='S'|| texto=='s') {
				vuelo=false;
				verificar = true;
			} else if(texto=='P'|| texto=='p') {
				vuelo=true;
				verificar = true;
			}
			if (verificar == false) {
				System.out.println("ERROR"
						+ "\n----------Debe ser Provisiones o Suministro----------");
			}	
		}
		return vuelo;
	}
	
	public void validarAvion2() {
		System.out.println("\n  == TIPOS DE RUTA QUE PUEDEN HACER LOS AVIONES ==\n");
		for(int i = 0;i<aviones.size();i++) {
			validarAvion(Constantes.sc,aviones.get(i).getNombre(),aviones.get(i).getHorasVuelo());
			System.out.println();
		}	
	}
	
	public int cargaPiloto() {
		int var= 0;
		boolean t =tipoCarga(Constantes.sc);
		if(t==true) {
			var = 1;
		}else if(t==false) {
			var = 2;
		}else {
			var = 3;
		}
		return var;
	}
	
	public void validarAvion(Scanner sc, String string, int horas) {
		//Que tipo de mision puede ir el avion
		if (horas<=100) {
			System.out.println("  " + string+" está cualificado para rutas de transporte");
		} else if (horas>100 && horas<=300) {
			System.out.println("  " + string+" está cualificado para transporte y bombarderos");
		}
		else {
			System.out.println("  " + string+" puede ser usado para cualquier tipo de misión");
		}	
	}
	
	public int sumarHoras(int horaRuta, int hora) {
		//Funcion que suma la duracion de la ruta y las horas de vuelo.
		hora = horaRuta+hora;
		return hora;
	}
	
	public void mostrarHorasActualizado(String nombre,int horas) {
		//Se muestra las horas totales del piloto o avion
		try{
			int p=GestionRuta.rutas.get(0).getH();
			horas=sumarHoras(aviones.get(aviones.size()-1).getHorasVuelo(),p);
			System.out.println("ACTUALIZACION\n"
					+ "\t"+nombre+" tiene "+ horas+" horas de vuelo ");
		}catch(IndexOutOfBoundsException e){
			
		}System.out.println("ACTUALIZACION\n"
				+ "\t"+nombre+" tiene "+ horas+" horas de vuelo ");
	}
	
	public void mostrarHoras() {
		mostrarHorasActualizado(aviones.get(aviones.size()-1).getNombre(),aviones.get(aviones.size()-1).getHorasVuelo());
	}
	
	public void archivoAvion() {
		File fichero = new File(rutaArchivo);
		try {
			if(fichero.createNewFile()) {
				System.out.println("Archivo creado correctamente");
			}else {
				System.out.println("");
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Avion> leerAviones(String rutaArchivo) {
	    ArrayList<Avion> aviones = new ArrayList<>();
	    try {
	        BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo));
	        String linea;
	        // Leer encabezados (ignorar primera línea)
	        reader.readLine();
	        // Leer datos de los aviones
	        while ((linea = reader.readLine()) != null) {
	        	String[] campos = linea.split(",");
	            String codigo = campos[0];
	            String nombre = campos[1];
	            int horasVuelo = Integer.parseInt(campos[2]);
	            boolean operativo = Boolean.parseBoolean(campos[3]);
	            Avion avion = new Avion(codigo, nombre, horasVuelo, operativo);
	            aviones.add(avion);
	            for(int i =0;i< campos.length;i++) {
		          	System.out.print(campos[i]+",");   	
		      	}
	            System.out.println();
	        }
	        reader.close();
	        System.out.println("\nLos datos de los aviones se han leído desde el archivo correctamente.");
	        
	        } catch (IOException e) {
	            System.out.println("Error al leer los datos de los aviones desde el archivo : " + e.getMessage());
	        }
	        return aviones;
	    }
	 
	 private void actualizarArchivoCSV() {
	        try (FileWriter writer = new FileWriter(rutaArchivo)) {
	            // Escribir los encabezados en el archivo  si es necesario
	        	System.out.println("Escrito Correctamente...");
	            writer.append("Codigo,Nombre,HorasVuelo,Operativo\n");
	            // Escribir cada avion en el archivo 
	            for (Avion avion : aviones) {
	                writer.append(avion.getCodigo() + "," +
	                			avion.getNombre() + "," +
	                			avion.getHorasVuelo() + "," +
	                              (avion.getOperativo() ? true : false) + "\n");
	            }
	        } catch (IOException e) {
	            System.out.println("Error al actualizar el archivo txt: " + e.getMessage());
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
	                // Dividir la línea en campos usando la coma como delimitador
	                String[] campos = linea.split(",");
	                // Crear un nuevo avion y agregarlo a la lista
	                String codigo = campos[0];
	                String nombre = campos[1];
	                int horasVuelo = Integer.parseInt(campos[2]);
	                boolean operativo = Boolean.parseBoolean(campos[3]);
	                Avion avion = new Avion(codigo, nombre, horasVuelo, operativo);
	                aviones.add(avion);
	            }
	            reader.close();
	        } catch (IOException e) {
	            System.out.println("No se pudo cargar desde el archivo: " + e.getMessage());
	        }
	    }
	 
	 public void escribirError(String error) {
			File f = new File("logs/properties.log");
			FileWriter fw;
			try {
				fw = new FileWriter(f, true);
				BufferedWriter bw = new BufferedWriter(fw); 
			    bw.write("["+LocalDate.now()+"]"+"["+LocalTime.now()+"]"+error+"\n" );
			    bw.flush();
			    bw.close(); 
			    fw.close();
			}catch (IOException e) {
				
				e.printStackTrace();
			}
		    
	}
}