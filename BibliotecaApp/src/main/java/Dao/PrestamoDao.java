/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Config.Conexion;
import Models.Prestamo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kalic
 */
public class PrestamoDao {
    
    private Connection connection;
    private Conexion conexion;
    
    public PrestamoDao(){
        Conexion conexion = new Conexion();
        connection = conexion.getConexion();
    }
    
    public void crearPrestamo(Prestamo prestamo){
       String sql = "INSER INTO prestamo (libroID, usuarioID, fechaPrestamo, fechaDevolucion) Values (?,?,?,?)";
       try(PreparedStatement statement = connection.prepareStatement(sql)){
           
           statement.setInt(1, prestamo.getLibroID());
           statement.setInt(2, prestamo.getUsuarioID());
           LocalDate fechaPrestamo = prestamo.getFechaPrestamo();
            Date sqlFechaPrestamo = Date.valueOf(fechaPrestamo);
            statement.setDate(3, sqlFechaPrestamo);
            LocalDate fechaDevolucion = prestamo.getFechaDevolucion();
            Date sqlFechaDevolucion = Date.valueOf(fechaDevolucion);
            statement.setDate(4, sqlFechaDevolucion);
            statement.executeUpdate();
       }
       catch(SQLException e){
           System.out.println("error al crear el registro de prestamo" + e.getMessage());         
       }        
    }
    
    public void actualizarPrestamo(Prestamo prestamo){
        String sql ="UPDATE prestamo SET libroID=?, usuarioID=?, fechaPrestamo=?, fechaDevolucion=?, WHERE prestamoID=?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, prestamo.getLibroID());
            statement.setInt(2, prestamo.getUsuarioID());
            LocalDate fechaPrestamo = prestamo.getFechaPrestamo();
            Date sqlFechaPrestamo = Date.valueOf(fechaPrestamo);
            statement.setDate(3, sqlFechaPrestamo);
            LocalDate fechaDevolucion = prestamo.getFechaDevolucion();
            Date sqlFechaDevolucion = Date.valueOf(fechaDevolucion);
            statement.setDate(4, sqlFechaDevolucion);
            statement.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("Error al actualizar el registro" + e.getMessage());
        }          
    }
    
    public void eliminarPrestamo(int id){
        String sql = "DELETE prestamo WHERE prestamoID =?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(0, id);
            statement.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("Error al eliminar el registro" + e.getMessage());
        }
    }
    
    public List<Prestamo> mostrarPrestamo(){
        List<Prestamo> listPrestamo = new ArrayList<>();
        String sql ="SELECT * FROM prestamo";
        try(Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)){
            while(resultSet.next()){
                int id = resultSet.getInt("prestamoID");
                int libroID = resultSet.getInt("libroID");
                int usuarioID = resultSet.getInt("libroID");
                LocalDate fechaPrestamo = resultSet.getDate("fechaPrestamo").toLocalDate();
                LocalDate fechaDevolucion = resultSet.getDate("fechaDevolucion").toLocalDate();
                Prestamo prestamo = new Prestamo(id, libroID, usuarioID, fechaPrestamo, fechaDevolucion);
                listPrestamo.add(prestamo);
            }
            
        }
        catch(SQLException e){
            System.out.println("Error al mostrar los registros" + e.getMessage());
        }
        
        return listPrestamo;
    }
    
}
