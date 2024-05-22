package com.dam.airbase.servicios;

import java.io.File;
import java.io.IOException;

public class Propiedad {
public String archivo="logs/properties.log";
	public void crearArchivo() {
		try {
			//Creamos la carpeta log
			File f = new File("logs");
			//Comprobamos que se ha creado
			if(f.mkdir()!=false) {
				System.out.println("  Carpeta creada ☑");
			}else {
				System.out.println();
				//System.out.println("  La carpeta ya existe  ☑");
				//Se debe borrar la carpeta
				f.delete();
			}
			
			//Creamos el archivo .log
			File a = new File(archivo);
			//Comprobamos que se ha creado
			if(a.createNewFile()) {
				System.out.println("  💾 Archivo creado correctamente 💾 ");
			}else {
				System.out.println("");
			}
			
			}catch (IOException e) {
				e.printStackTrace();
			}	
	}
}
