package com.dam.airbase.respositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.dam.airbase.entidades.Incidencias;



public class IncidenciasRepositorio {
	private ConexionBaseDatos conexionBBDD = new ConexionBaseDatos();
	
	public ArrayList<Incidencias> LeerIncidencias() {
		ArrayList<Incidencias> incidencia = new ArrayList<Incidencias>();
		try(Connection conexion = conexionBBDD.conectar()) {
			 String sql = "SELECT * FROM incidencias";
	            PreparedStatement ps = conexion.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	            	Incidencias i = new Incidencias();
	            	i.setNumeroIncidencia(rs.getString("numeroIncidencia"));
	            	if(rs.getString("codigoReferenciaPiloto")== null) {
		            	i.setCodigoRef(rs.getString("codigoReferenciaAvion"));
	            	} else {
		            	i.setCodigoRef(rs.getString("codigoReferenciaPiloto"));
	            	}
	            	i.setDescripccion(rs.getString("descripcion"));
	            	i.setOperativo(rs.getBoolean("operativo"));
	            	i.setFecha(rs.getString("Fecha"));
	            	incidencia.add(i);
	            }
	            rs.close();
	            ps.close();
	            conexionBBDD.desconectar(conexion);
		 } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
		return incidencia;
	}
public ArrayList<Incidencias> insertarIncidencias(String numIncidencia, String codRef,String descrip, boolean operativo, String fecha,String definicion) {
		
		ArrayList<Incidencias> incidencia = new ArrayList<Incidencias>();	
	     try (Connection conexion = conexionBBDD.conectar()) {
	            conexionBBDD.crearBaseDeDatosYTablas(conexion);
        	
        	int op =0;
        	int ad= 0;
            if(operativo==true) {
            	op=1;
            }
            String sql = "INSERT INTO Incidencias(numeroIncidencia, codigoReferencia"+definicion+", descripcion, operativo,fecha) VALUES('"+numIncidencia+"','" + codRef + "','" + descrip + "','" + op + "','" + fecha + "')";
            PreparedStatement ps = conexion.prepareStatement(sql);
             
            int i = ps.executeUpdate();
            if (i > 0) {
            	System.out.println("Incidencia creada");
            }
            ps.close();
            conexionBBDD.desconectar(conexion);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
	     return incidencia;
}

public HashMap<String,ArrayList<Incidencias>> LeerIncidenciasHash() {
	HashMap<String,ArrayList<Incidencias>> incidencia = new HashMap <String,ArrayList<Incidencias>>();
	Scanner dato = new Scanner(System.in);
	System.out.println("Escriba el codigo del piloto o avión que desee consultar");
	String codigo = dato.next();
	if(RsPilotos(codigo) != null) {
		Incidencias i = new Incidencias();
		try {
		ArrayList<Incidencias> array = new ArrayList<Incidencias>();
		while(RsPilotos(codigo).next()) {
		i.setNumeroIncidencia(RsPilotos(codigo).getString("numeroIncidencia"));
		i.setNumeroIncidencia(RsPilotos(codigo).getString("codigoReferenciaPiloto"));
		i.setNumeroIncidencia(RsPilotos(codigo).getString("descripcion"));
		i.setOperativo(RsPilotos(codigo).getBoolean("operativo"));
		i.setFecha(RsPilotos(codigo).getString("Fecha"));
		array.add(i);
		}
		incidencia.put(codigo, array);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	} else if(RsAviones(codigo) != null) {
		Incidencias b = new Incidencias();
		try {
		ArrayList<Incidencias> array = new ArrayList<Incidencias>();
		while(RsPilotos(codigo).next()) {
		b.setNumeroIncidencia(RsPilotos(codigo).getString("numeroIncidencia"));
		b.setNumeroIncidencia(RsPilotos(codigo).getString("codigoReferenciaAvion"));
		b.setNumeroIncidencia(RsPilotos(codigo).getString("descripcion"));
		b.setOperativo(RsPilotos(codigo).getBoolean("operativo"));
		b.setFecha(RsPilotos(codigo).getString("Fecha"));
		array.add(b);
		}
		incidencia.put(codigo, array);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	} else {
		System.out.println("ERROR: Piloto o Avión no encontrado en la base de datos");
	}
	
	return incidencia;
}
public ResultSet RsPilotos(String codigo) {
	ResultSet resultado= null;
	try (Connection conexion = conexionBBDD.conectar()){
		 String sql = "select numeroIncidencia, descripcion, operativo, fecha from incidencias i where codigoReferenciaPiloto =" +"'"+codigo+"'";
		 PreparedStatement ps = conexion.prepareStatement(sql);
         ResultSet rs = ps.executeQuery();
         resultado = rs;
         rs.close();
         ps.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return resultado;
}

public ResultSet RsAviones(String codigo) {
	ResultSet resultado= null;
	try (Connection conexion = conexionBBDD.conectar()){
		 String sql = "select numeroIncidencia, descripcion, operativo, fecha from incidencias i where codigoReferenciaAvion =" +"'"+codigo+"'";
		 PreparedStatement ps = conexion.prepareStatement(sql);
         ResultSet rs = ps.executeQuery();
         resultado = rs;
         rs.close();
         ps.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return resultado;
}
}
