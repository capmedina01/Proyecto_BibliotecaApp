/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Config.Conexion;
import Models.LibroCategoria;
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
public class LibroCategoriaDao {
    private Connection connection;
    private Conexion conexion;

    public LibroCategoriaDao (){
        
        conexion = new Conexion();
        connection = conexion.getConexion();    
    }
    
    public void crearLCategoria(LibroCategoria lcategoria){
        
        String sql = "INSERT INTO libroCategoria (libroID, categoriaID) VALUES (?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1, lcategoria.getLibroID());
                statement.setInt(2, lcategoria.getCategoriaID());
                statement.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("Error al crear la categoria" + e.getMessage());            
        }
        
    }
    public void actualizarLCategoria(LibroCategoria lcategoria){
        
        String sql = "UPDATE libroCategoria SET libroID = ?, categoriaID = ? WHERE categoriaID = ? AND libroID = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, lcategoria.getLibroID());
            statement.setInt(2, lcategoria.getCategoriaID());
            statement.executeUpdate();
            
        }
        catch(SQLException e){
            System.out.println("Error al actualizar los datos" + e.getMessage());
        }
        
    }
    
    public void eliminarLCategoria(int libroID, int categoriaID){
        
        String sql = "DELETE FROM libroCategoria WHERE categoriaID = ? AND libroID = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, libroID);
            statement.setInt(1, categoriaID);
            statement.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("Error al eliminar el registro" + e.getMessage());
        }
    }
    
    public List<LibroCategoria> leerCategoria(){
        List<LibroCategoria> listCategoria = new ArrayList<>();
        String sql = "SELECT * FROM libroCategoria ";
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
                    while(resultSet.next()){
                        int catID = resultSet.getInt("categoriaID");
                        int libID = resultSet.getInt("libroID");
                        LibroCategoria categoria = new LibroCategoria(catID, libID);
                        listCategoria.add(categoria);                        
                    }
            
        }
        catch(SQLException e){
            System.out.println("Error al leer los datos" + e.getMessage());
            
        }
        return listCategoria;
    }
    
    public void cerrarConexion(){
        conexion.closeConnection(connection);  
    
    }
}
