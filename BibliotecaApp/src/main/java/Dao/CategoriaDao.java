/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.Connection;
import Config.Conexion;
import Models.Categoria;
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
public class CategoriaDao {
    
    private Connection connection;
    private Conexion conexion;

    public CategoriaDao (){
        
        conexion = new Conexion();
        connection = conexion.getConexion();    
    }
    
    public void crearCategoria(Categoria categoria){
        
        String sql = "INSERT INTO categoria (nombre) VALUES (?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1, categoria.getNombre());
                statement.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("Error al crear la categoria" + e.getMessage());            
        }
        
    }
    public void actualizarCategoria(Categoria categoria){
        
        String sql = "UPDATE categoria SET nombre = ? WHERE categoriaID = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, categoria.getNombre());
            statement.setInt(2, categoria.getCategoriaID());            
        }
        catch(SQLException e){
            System.out.println("Error al actualizar los datos" + e.getMessage());
        }
        
    }
    
    public void eliminarCategoria(int id){
        
        String sql = "DELETE FROM categoria WHERE categoriaID = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("Error al eliminar el registro" + e.getMessage());
        }
    }
    
    public List<Categoria> leerCategoria(){
        List<Categoria> listCategoria = new ArrayList<>();
        String sql = "SELECT * FROM categoria ";
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
                    while(resultSet.next()){
                        int id = resultSet.getInt("categoriaID");
                        String nombre = resultSet.getString("nombre");
                        Categoria categoria = new Categoria(id, nombre);
                        listCategoria.add(categoria);                        
                    }
                    return listCategoria;
            
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
