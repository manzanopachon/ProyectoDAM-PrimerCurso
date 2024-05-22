package com.dam.airbase.servicios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.dam.airbase.entidades.Piloto;
import com.dam.airbase.entidades.Registro;
import com.dam.airbase.interfaces.CRUDInterface;
import com.dam.airbase.interfaces.Constantes;
import com.dam.airbase.respositorio.ConexionBaseDatos;
import com.dam.airbase.respositorio.PilotosRepositorio;

public class GestionPilotoCRUD implements CRUDInterface {
	
	//Atributos
	public ArrayList <Piloto> pilotos;
	private Random random = new Random();
	private String rutaArchivo = "ficheros/Pilotos.txt";
	protected PilotosRepositorio pr = new PilotosRepositorio();
	
	//Metodos
	public void inicializar(Registro r) {
		pilotos = new ArrayList <Piloto>();
		cargarDesdeArchivoCSV();
	}
	public ArrayList<Piloto> obtenerPilotos(){	
		return pilotos;
	}
	
	public void alta(Registro r) {
		if (pilotos == null) {
	       // Inicia la lista de pilotos en caso de que esté null
			pilotos = new ArrayList<Piloto>();
	    }
		boolean ok = true;
		Piloto p = (Piloto)r;
		String codigo;
		do {
			System.out.println("  Introduce el codigo del piloto");
			codigo = Constantes.sc.next();
			ok = !ifExist(p,codigo,ok); //Devuelve False y se cambia con '!' a true
		} while(ok);
		System.out.println("  Introduce el nombre del piloto");
		String nombre = Constantes.sc.next();
		int horas = hVuelos();
		System.out.println("  ¿Admin? ");
		System.out.println(" [1]. SI   [2]. NO ");
		String admin = Constantes.sc.next();
		boolean ad = false;
		if (admin.equals("1" )|| admin.equals("SI" )||admin.equals( "si" )||admin.equals ("Si")) {
			System.out.println("Dime el codigo de Administrador");
			String codigoAdministrador = Constantes.sc.next();
			//La contraseña es el dni
			switch (codigoAdministrador){
			case "49167575":
				System.out.println("Bienvenido Almirante Mauricio");
				ad = true;
				break;
			case "1945":
				System.out.println("Bienvenido Almirante Constantino");
				ad = true;
				break;
			case "12345678":
				System.out.println("Bienvenido Almirante Omar");
				ad = true;
				break;
			case "1234":
				System.out.println("Bienvenido Almirante Faly");
				ad = true;
				break;
			default:
				ad = false;
				break;
			}
		}
		boolean operativo = true;
		pilotos.add(new Piloto (codigo, nombre, horas, operativo,ad));
		pr.insertarPiloto(codigo, nombre, horas, operativo,ad);
		actualizarArchivoCSV();
	}
	
	public void baja(Registro r) {
		boolean ok = true;
		Piloto p = (Piloto)r;
		String codigo;
		do {
			System.out.println("  Introduce el codigo del piloto que quieres dar de baja");
			codigo = Constantes.sc.next();
			ok = ifExist(p,codigo,ok);
			for(int i = 0; i<pilotos.size();i++) {
				if(codigo.equals(pilotos.get(i).getCodigo())) {
					pilotos.remove(i);
					pilotos = pr.eliminarPiloto(codigo);							
					try {
						escribirError(pilotos.get(i).getNombre()+" eliminado correctamente");
					}catch(IndexOutOfBoundsException e) {
						escribirError("Piloto eliminado");
					}
				}
			}	
		} while(ok==true);
		//Aqui actualizo el archivo
		actualizarArchivoCSV();
	}
	
	public void modificar(Registro r) {
		boolean ok = false;
		Piloto p = (Piloto)r;
		String codigo;
		String mod="";
		String dato="";
		do {
			System.out.println("  Introduce el codigo del piloto que quieres modificar");
			codigo = Constantes.sc.next();
			ok = ifExist(p,codigo,ok);
			for(int i = 0; i<pilotos.size();i++) {
				if(codigo.equals(pilotos.get(i).getCodigo())) {
					System.out.println(pilotos.get(i));
					System.out.print("  Que dato quieres modificar: ");
					mod= Constantes.sc.next();
					if(mod.charAt(0)=='C' || mod.charAt(0)=='c') {
						System.out.println("  Introduce el nuevo codigo: ");
						dato = Constantes.sc.next();
						pilotos.get(i).setCodigo(dato);
						pilotos = pr.modificarPiloto(mod.charAt(0), dato, codigo);
					}else if(mod.charAt(0)=='N' || mod.charAt(0)=='n') {
						System.out.println("  Introduce el nuevo nombre: ");
						dato = Constantes.sc.next();
						pilotos.get(i).setNombre(dato);
						pilotos = pr.modificarPiloto(mod.charAt(0), dato, codigo);
					}else if(mod.charAt(0)=='O' || mod.charAt(0)=='o'){
						System.out.println("  Dime si está operativo[Si][No]: ");
						String a = Constantes.sc.next();
						if(a.equals("Si")) {
							pilotos.get(i).setOperativo(true);
							pilotos = pr.modificarPiloto(mod.charAt(0), "1", codigo);
						}else {
							pilotos.get(i).setOperativo(false);
							pilotos = pr.modificarPiloto(mod.charAt(0), "0", codigo);
						}
					
					}else if(mod.charAt(0)=='H' || mod.charAt(0)=='h'){
						System.out.println("Introduce las nuevas horas de vuelo: ");
						int d = Constantes.sc.nextInt();
						pilotos.get(i).setHorasVuelo(d);
						String castearInt = ""+d;
						pilotos = pr.modificarPiloto(mod.charAt(0), castearInt, codigo);
					}else if(mod.charAt(0)=='A' || mod.charAt(0)=='a'){
						System.out.println("  Esta opción aun no esta Operativa pero la cambiaremos ");
						pilotos.get(i).setAdmin(true);
					}else {
						System.out.println("  Escoge una opción Valida por favor ");
						escribirError("ERROR en modificar datos del piloto: "+pilotos.get(i).getNombre());
					}
				}
			}	
		} while(ok);
		actualizarArchivoCSV();
	}
	
	public int hVuelos() {
		int h=0;
		h=random.nextInt(1500);
		return h;
	}
	
	public void ver(Registro r) {
		/* ANTES USABAMOS EL ARRAYLIST PARA LEER LOS DATOS, AHORA USAMOS EL ARCHIVO DIRECAMENTE
		 for(int i = 0;i < pilotos.size(); i++) {
			System.out.println(pilotos.get(i).getCodigo()+ ", " + pilotos.get(i).getNombre()+ ", " + pilotos.get(i).getHorasVuelo()+" horas de vuelo" + ", Operativo: " + pilotos.get(i).getOperativo()+" Administrador: "+pilotos.get(i).getAdmin());
		}*/
		
		//Esto es para leerlos desde el archivo
		leerPilotos(rutaArchivo);
		
		//Para leerlo desde la base de datos
		ArrayList<Piloto> pilotos = pr.leerPilotos();
		for(Piloto p : pilotos) {
			System.out.println(p);
		}
	}

	public boolean ifExist(Registro r, String codigo,boolean ok) {
		for (int i = 0;i<pilotos.size() && ok==true;i++) {
			if(codigo.equals(pilotos.get(i).getCodigo())) {
				System.out.println("Existe un piloto así("+pilotos.get(i).getNombre()+")");
				escribirError("Piloto escrito existente: "+pilotos.get(i).getNombre());
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
	 boolean verificar;
        String texto;
        do {
            verificar = true;
            if (carga) {
                System.out.println("  ¿Qué tipo de provisiones va a transportar?");
                System.out.println("      [Armamento, Munición, Explosivos]");
            } else {
                System.out.println("  ¿Qué tipo de suministro va a transportar?");
                System.out.println("      [Tropas, Vehículos, Gasolina]");
            }
            texto = sc.next();
            for (String[] strings : t) {
                for (String s : strings) {
                    if (carga) {
                        if (!texto.equals("Armamento") && !texto.equals("Munición") && !texto.equals("Explosivos") &&
                        	!texto.equals("armamento") && !texto.equals("munición") && !texto.equals("explosivos") ) {
                            verificar = false;
                        }
                    } else {
                        if (!texto.equals("Tropas") && !texto.equals("Vehículos") && !texto.equals("Gasolina") &&
                        	!texto.equals("tropas") && !texto.equals("vehículos") && !texto.equals("gasolina") ) {
                            verificar = false;
                        }
                    }
                }
            }
            if (!verificar) {
                escribirError("Error en elegir tipo de " + (carga ? "provisiones" : "suministro"));
                System.out.println("ERROR");
            } else {
                escribirError("Tipo de " + (carga ? "provisiones" : "suministro") + " elegido correctamente");
            }
        } while (!verificar);
        return texto;
    }
	 
	public int cargaPiloto() {
		int var= 0;
		boolean t =tipoCarga(Constantes.sc);
		if(t) {
			var = 1;
		}else if(t==false) {
			var = 2;
		}else {
			var = 3;
		}
		return var;
	}
	
	public void mostrarCarga(String carga) {
		//Funcion que devuelve el mensaje de cual es la carga que se va a transportar
		System.out.println("  La carga que se llevará en el avión es: "+carga );
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
		while(verificar) {
			System.out.println("  Que tipo de vuelo es: [Provisiones o Suministros]");
			char texto = sc.next().charAt(0);
			if (texto=='S'|| texto=='s') {
				vuelo=false;
				verificar = true;
				escribirError("Suministro elegido correctamente");
			} else if(texto=='P'|| texto=='p') {
				vuelo=true;
				verificar = true;
				escribirError("Provision elegido correctamente");
			}
			if (verificar == false) {
				escribirError("ERROR en elegir suministros o privisiones");
				System.out.println("ERROR"
						+ "\n---------- Debe ser Provisiones o Suministro ----------");
			}	
		}
		return vuelo;
	}
	
	public void validarPiloto2() {
		System.out.println("\n == TIPOS DE RUTA QUE PUEDEN HACER LOS PILOTOS == \n");
		for(int i = 0; i<pilotos.size();i++) {
			validarPiloto(Constantes.sc,pilotos.get(i).getNombre(),pilotos.get(i).getHorasVuelo());
			System.out.println();
		}
	}
	
	public void validarPiloto(Scanner sc,String string, int exp) {
		//En esta funcion vamos a ver que piloto esta cualificado para llevar el avion dependiendo del tipoVuelo
		if (exp<100) {
			//Si el piloto tiene menos de 100 horas podra pilotar solo aviones comerciales
			System.out.println("  " + string +"  solo puede pilotar aviones de transporte");
		} else if(exp>=100 && exp<=500) {
			System.out.println("  " + string +"  puede pilotar aviones de transporte o bombarderos, "
					+ "pero solo para misiones de suministro");
		} else {
			System.out.println("  " + string +"  puede manejar cualquier tipo de avión sin depender de la misión"
					+ ",es decir, aviones de transporte, bombarderos y aviones de combate");
		}
		escribirError(string+" asignado a una mision correctamente");
	}
	
	public int sumarHoras(int horaRuta, int hora) {
		//Funcion que suma la duracion de la ruta y las horas de vuelo.
		hora = horaRuta+hora;
		return hora;
	}
	
	public void mostrarHorasActualizado(String nombre,int horas) {
		//Se muestra las horas totales del piloto o avion
		horas=sumarHoras(pilotos.get(pilotos.size()-1).getHorasVuelo(),0);
		System.out.println("ACTUALIZACION\n"
				+ "\t"+nombre+" tiene "+ horas+" horas de vuelo ");
	}
	public void mostrarHoras() {
		mostrarHorasActualizado(pilotos.get(pilotos.size()-1).getNombre(),pilotos.get(pilotos.size()-1).getHorasVuelo());
	}
	
	public void archivoPiloto() {	
		File fichero = new File(rutaArchivo);
		try {
			if(fichero.createNewFile()) {
				System.out.println("Archivo creado correctamente");
			}else {
				System.out.println("");
			}
		}catch (IOException e) {
			escribirError("ERROR en crear archivo");
		}
	}
	 
	 public ArrayList<Piloto> leerPilotos(String rutaArchivo) {
	        ArrayList<Piloto> pilotos = new ArrayList<>();
	        try {
	            BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo));
	            String linea;
	            // Leer encabezados (ignorar primera línea)
	            reader.readLine();
	            // Leer datos de los pilotos
	            while ((linea = reader.readLine()) != null) {
	                String[] campos = linea.split(",");
	                String codigo = campos[0];
	                String nombre = campos[1];
	                int horasVuelo = Integer.parseInt(campos[2]);
	                boolean operativo = Boolean.parseBoolean(campos[3]);
	                Piloto piloto = new Piloto(codigo, nombre, horasVuelo, operativo,false);
	                pilotos.add(piloto);
	                /*
	                for(int i =0;i< campos.length;i++) {
		            	System.out.print(campos[i]+",");
		            }
	                System.out.println();*/
	            }
	            reader.close();
	            System.out.println();
	        } catch (IOException e) {
	        	escribirError("Error al leer los datos de los pilotos desde el archivo :"+ e.getMessage());
	        }
	        return pilotos;
	    }
	 
	 private void actualizarArchivoCSV() {
		    try {
		        ConexionBaseDatos conexionBBDD = new ConexionBaseDatos();
		        Connection conexion = conexionBBDD.conectar();
		        String sql = "SELECT * FROM Piloto";
		        PreparedStatement ps = conexion.prepareStatement(sql);
		        ResultSet rs = ps.executeQuery();
		        FileWriter fw = new FileWriter(rutaArchivo);

		        // Escribir la cabecera del archivo CSV
		        fw.append("Codigo,Nombre,HorasVuelo,Operativo,Admin\n");

		        // Escribir los datos de los pilotos en el archivo CSV
		        while (rs.next()) {
		            String codigo = rs.getString("codigo");
		            String nombre = rs.getString("nombre");
		            int horasVuelo = rs.getInt("horasVuelo");
		            boolean operativo = rs.getBoolean("operativo");
		            boolean admin = rs.getBoolean("admin");
		            fw.append(codigo + "," + nombre + "," + horasVuelo + "," + operativo + "," + admin + "\n");
		        }
		        fw.flush();
		        fw.close();
		        //System.out.println("Archivo piloto.txt creado correctamente.");
		    } catch (SQLException | IOException e) {
		        escribirError("Error al actualizar el archivo txt: " + e.getMessage());
		    }
		}

	 private void cargarDesdeArchivoCSV() {   
		//Esta funcion lo que hace es leer el archivo nada mas se ejecuta el programa.
		//Mientras leemos el archivo se van guardando los pilotos en un arrayList hasta el final del archivo
	    try {
	    	 BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo));
	         String linea;   
	         //Para saltar la primera linea
	         reader.readLine();
	         // Leer cada línea del archivo CSV
	         while((linea = reader.readLine()) != null)  {
	             // Dividir la línea en campos usando la coma como delimitador
	             String[] campos = linea.split(",");
	             // Crear un nuevo Piloto y agregarlo a la lista	
	             String codigo = campos[0];
	             String nombre = campos[1];
	             int horasVuelo = Integer.parseInt(campos[2]);
	             boolean operativo = Boolean.parseBoolean(campos[3]);
	             Piloto piloto = new Piloto(codigo, nombre, horasVuelo, operativo,false);
	             pilotos.add(piloto);
	         }
	         reader.close();
	     } catch (IOException e) {
	    	 escribirError("No se pudo cargar desde el archivo: " + e.getMessage());	         
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

