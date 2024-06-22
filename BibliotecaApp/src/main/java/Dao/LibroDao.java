/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Config.Conexion;
import Models.Libro;
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
public class LibroDao {
    
    private Connection connection;
    private Conexion conexion;
    
    public LibroDao(){
        conexion = new Conexion();
        connection = conexion.getConexion();
    }
    
    public void crearLibro(Libro libro){
       String sql= "INSERT INTO libro (titulo, autor, editorial, fechaPublicacion, isbn) VALUES (?, ?, ?, ?, ?) ";
       try(PreparedStatement statement = connection.prepareStatement(sql)){
           statement.setString(1, libro.getTitulo());
           statement.setString(2, libro.getAutor());
           statement.setString(3, libro.getEditorial());
           statement.setInt(4, libro.getFechaPublicacion());
           statement.setString(1, libro.getIsbn());
           statement.executeUpdate();
           
       }
       catch(SQLException e){
           System.out.println("Error al crear el libro" + e.getMessage());
       }
       
    }
    
    public void actualizarLibro(Libro libro){
       String sql= "UPDATE libro SET titulo=?, autor=?, editorial=?, fechaPublicacion=?, isbn=? WHERE libroID =?";
       try(PreparedStatement statement = connection.prepareStatement(sql)){
           statement.setString(1, libro.getTitulo());
           statement.setString(2, libro.getAutor());
           statement.setString(3, libro.getEditorial());
           statement.setInt(4, libro.getFechaPublicacion());
           statement.setString(5, libro.getIsbn());
           statement.setInt(6, libro.getLibroID());
           statement.executeUpdate();
           
       }
       catch(SQLException e){
           System.out.println("Error al actualizar el libro" + e.getMessage());
       }
       
    }
    
    public void eliminarLibro(int id){
        String sql = "DELETE FROM libro WHERE libroID =?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("Error al eliminar el libro" + e.getMessage());
        }
    }
    
    public List<Libro> mostrarLibro(){
        List<Libro> listLibro = new ArrayList<>();
        String sql = "SELECT * FROM libro";
        try(Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)){
            while(resultSet.next()){
                int id = resultSet.getInt("libroID");
                String titulo = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");
                String editorial = resultSet.getString("editorial");
                int fecha = resultSet.getInt("fechaPublicacion");
                String isbn = resultSet.getString("isbn");
                Libro libro = new Libro(id, titulo, autor, editorial, fecha, isbn);
                listLibro.add(libro);               
            }
            
        }
        catch(SQLException e){
            System.out.println("Error al mostrar la lista de libros" + e.getMessage());            
        }
        
        return listLibro;        
    }
    
}
