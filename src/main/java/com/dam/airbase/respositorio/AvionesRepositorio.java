package com.dam.airbase.respositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dam.airbase.entidades.Avion;


public class AvionesRepositorio {

    private ConexionBaseDatos conexionBBDD = new ConexionBaseDatos();
    

    public ArrayList<Avion> leerAviones() {
        
        ArrayList<Avion> aviones = new ArrayList<Avion>();  
        
         try (Connection conexion = conexionBBDD.conectar()) {
                conexionBBDD.crearBaseDeDatosYTablas(conexion);
            
            String sql = "SELECT codigo, nombre, horasVuelo, operativo FROM Avion";
            PreparedStatement ps = conexion.prepareStatement(sql);
             
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Avion p = new Avion(); 
                
                p.setCodigo(rs.getString("codigo"));
                p.setNombre(rs.getString("nombre"));
                p.setHorasVuelo(rs.getInt("horasVuelo"));
                p.setOperativo(rs.getBoolean("operativo"));
            
                
                aviones.add(p);
            }
            
            rs.close();
            ps.close();
            conexionBBDD.desconectar(conexion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return aviones;
    }
    
    public ArrayList<Avion> insertarAvion(String codigo, String nombre, int horasVuelo, boolean operativo) {
        
        ArrayList<Avion> aviones = new ArrayList<Avion>();    
         try (Connection conexion = conexionBBDD.conectar()) {
                conexionBBDD.crearBaseDeDatosYTablas(conexion);
            
            int op = 0;
            int ad = 0;
            if (operativo) {
                op = 1;
            }
           
            String sql = "INSERT INTO Avion(codigo, nombre, horasVuelo, operativo ) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, codigo);
            ps.setString(2, nombre);
            ps.setInt(3, horasVuelo);
            ps.setInt(4, op);
           
             
            int i = ps.executeUpdate();
            if (i > 0) {
                System.out.println("Avion introducido correctamente");
            }
            ps.close();
            conexionBBDD.desconectar(conexion);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return aviones;
    }
    
    public ArrayList<Avion> eliminarAvion(String id) {
        ArrayList<Avion> aviones = new ArrayList<Avion>();
         try (Connection conexion = conexionBBDD.conectar()) {
                conexionBBDD.crearBaseDeDatosYTablas(conexion);
            
            String sql = "DELETE FROM Avion WHERE codigo = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, id);
             
            int i = ps.executeUpdate();
            if (i > 0) {
                System.out.println("Avion eliminado correctamente");
            }
            ps.close();
            conexionBBDD.desconectar(conexion);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return aviones;
    }
    
    public ArrayList<Avion> modificarAvion(char modificar, String datoIntroducido, String codigo) {
        ArrayList<Avion> aviones = new ArrayList<Avion>();
         try (Connection conexion = conexionBBDD.conectar()) {
                conexionBBDD.crearBaseDeDatosYTablas(conexion);
            String sql = "";
            switch (modificar) {
                case 'C', 'c':
                    sql = "UPDATE Avion SET codigo = ? WHERE codigo = ?";
                    break;
                case 'N', 'n':
                    sql = "UPDATE Avion SET nombre = ? WHERE codigo = ?";
                    break;
                case 'O', 'o':
                    sql = "UPDATE Avion SET operativo = ? WHERE codigo = ?";
                    break;
                case 'H', 'h':
                    sql = "UPDATE Avion SET horasVuelo = ? WHERE codigo = ?";
                    break;
               
                default:
                    break;
            }
            
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, datoIntroducido);
            ps.setString(2, codigo);
            
            int i = ps.executeUpdate();
            if (i > 0) {
                System.out.println("Avion modificado correctamente");
            } else {
                System.out.println("No se encontró ningún avion  para modificar");
            }
            
            ps.close();
            conexionBBDD.desconectar(conexion);
        } catch (SQLException e) {
            System.out.println("Error al modificar el avion: " + e.getMessage());
        }
        return aviones;
    }

}
