/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Config.Conexion;
import Models.LibroCategoria;
import Models.Rol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kalic
 */
public class RolDao {
    
    private Connection connection;
    private Conexion conexion;

    public RolDao (){
        
        conexion = new Conexion();
        connection = conexion.getConexion();    
    }
    
    public void crearLRol(Rol rol){
        
        String sql = "INSERT INTO libroCategoria (nombre) VALUES (?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1, rol.getNombre());
                statement.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("Error al crear el registro" + e.getMessage());            
        }
        
    }
    public void actualizarRol(Rol rol){
        
        String sql = "UPDATE rol SET nombre = ? WHERE rolID = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, rol.getNombre());
            statement.setInt(2, rol.getRolID());
            statement.executeUpdate();
            
        }
        catch(SQLException e){
            System.out.println("Error al actualizar los datos" + e.getMessage());
        }
        
    }
    
    public void eliminarLCategoria(int id){
        
        String sql = "DELETE FROM rol WHERE rolID = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);            
            statement.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("Error al eliminar el registro" + e.getMessage());
        }
    }
    
    public List<Rol> leerRol(){
        List<Rol> listRol = new ArrayList<>();
        String sql = "SELECT * FROM rol ";
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
                    while(resultSet.next()){
                        int rolID = resultSet.getInt("rolID");
                        String nombre = resultSet.getString("nombre");
                        Rol rol = new Rol(rolID, nombre);
                        listRol.add(rol);                        
                    }
            
        }
        catch(SQLException e){
            System.out.println("Error al leer los datos" + e.getMessage());
            
        }
        return listRol;
    }
    
    public void cerrarConexion(){
        conexion.closeConnection(connection);  
    
    }
    
}
