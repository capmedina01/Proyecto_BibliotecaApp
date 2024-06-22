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
import java.sql.SQLException;
import java.time.LocalDate;

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
       }
       catch(SQLException e){
           System.out.println("error al crear el registro de prestamo" + e.getMessage());
                    
           
       }
        
    }
    
    
}
