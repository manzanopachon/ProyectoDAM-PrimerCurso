package com.dam.airbase.respositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dam.airbase.entidades.Piloto;

public class PilotosRepositorio {

	private ConexionBaseDatos conexionBBDD = new ConexionBaseDatos();
	
	/*public void crearDataBase(){
		
	        try (Connection conexion = conexionBBDD.conectar()) {
	            conexionBBDD.crearBaseDeDatosYTablas(conexion);
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	*/
	public ArrayList<Piloto> leerPilotos() {
		
		ArrayList<Piloto> pilotos = new ArrayList<Piloto>();
		
	     try (Connection conexion = conexionBBDD.conectar()) {
	            conexionBBDD.crearBaseDeDatosYTablas(conexion);
        	
        	
            String sql = "SELECT codigo, nombre, horasVuelo, operativo,admin FROM Piloto";
            PreparedStatement ps = conexion.prepareStatement(sql);
             
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
            	Piloto p = new Piloto(); 
            	
            	p.setCodigo(rs.getString("codigo"));
            	p.setNombre(rs.getString("nombre"));
            	p.setHorasVuelo(rs.getInt("horasVuelo"));
            	p.setOperativo(rs.getBoolean("operativo"));
            	p.setAdmin(rs.getBoolean("admin"));
            	
            	pilotos.add(p);
            }
            
            rs.close();
            ps.close();
            conexionBBDD.desconectar(conexion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return pilotos;
	}
	
	
	
	
	public ArrayList<Piloto> insertarPiloto(String codigo, String nombre, int horasVuelo,boolean operativo,boolean admin) {
		
		ArrayList<Piloto> pilotos = new ArrayList<Piloto>();	
	     try (Connection conexion = conexionBBDD.conectar()) {
	            conexionBBDD.crearBaseDeDatosYTablas(conexion);
        	
        	int op =0;
        	int ad= 0;
            if(operativo==true) {
            	op=1;
            }
            if(admin==true) {
            	ad=1;
            }
            String sql = "INSERT INTO Piloto(codigo, nombre, horasVuelo, operativo,admin) VALUES('"+codigo+"','" + nombre + "','" + horasVuelo + "','" + op + "','" + ad + "')";
            PreparedStatement ps = conexion.prepareStatement(sql);
             
            int i = ps.executeUpdate();
            if (i > 0) {
            	System.out.println("Usuario introducido correctamente");
            }
            ps.close();
            conexionBBDD.desconectar(conexion);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return pilotos;
	}
	
	public ArrayList<Piloto> eliminarPiloto(String id) {
		ArrayList<Piloto> pilotos = new ArrayList<Piloto>();
	     try (Connection conexion = conexionBBDD.conectar()) {
	            conexionBBDD.crearBaseDeDatosYTablas(conexion);
        	
        	
            String sql = "delete from Piloto WHERE codigo = '"+id+"'";
            PreparedStatement ps = conexion.prepareStatement(sql);
             
            int i = ps.executeUpdate();
            if (i > 0) {
            	System.out.println("Piloto eliminado correctamente");
            }
            ps.close();
            conexionBBDD.desconectar(conexion);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return pilotos;
	}
	
	public ArrayList<Piloto> modificarPiloto(char modificar, String datoIntroducido, String codigo) {
		ArrayList<Piloto> pilotos = new ArrayList<Piloto>();
	     try (Connection conexion = conexionBBDD.conectar()) {
	            conexionBBDD.crearBaseDeDatosYTablas(conexion);
	        String sql = "";
	        switch (modificar) {
	            case 'C', 'c':
	                sql = "UPDATE Piloto SET codigo = ? WHERE codigo = ?";
	                break;
	            case 'N', 'n':
	                sql = "UPDATE Piloto SET nombre = ? WHERE codigo = ?";
	                break;
	            case 'O', 'o':
	                sql = "UPDATE Piloto SET operativo = ? WHERE codigo = ?";
	                break;
	            case 'H', 'h':
	                sql = "UPDATE Piloto SET horasVuelo = ? WHERE codigo = ?";
	                break;
	            case 'A', 'a':
	                sql = "UPDATE Piloto SET admin = ? WHERE codigo = ?";
	                break;
	            default:
	                break;
	        }
	        
	        PreparedStatement ps = conexion.prepareStatement(sql);
	        ps.setString(1, datoIntroducido);
	        ps.setString(2, codigo);
	        
	        int i = ps.executeUpdate();
	        if (i > 0) {
	            System.out.println("Piloto modificado correctamente");
	        } else {
	            System.out.println("No se encontró ningún piloto para modificar");
	        }
	        
	        ps.close();
	        conexionBBDD.desconectar(conexion);
	    } catch (SQLException e) {
	        System.out.println("Error al modificar el piloto: " + e.getMessage());
	    }
	    return pilotos;
	}

	
}
