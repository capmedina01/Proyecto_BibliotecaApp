/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Config.Conexion;
import Models.Rol;
import Models.Usuario;
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
public class UsuarioDao {
    
    private Connection connection;
    private Conexion conexion;

    public UsuarioDao (){
        
        conexion = new Conexion();
        connection = conexion.getConexion();    
    }
    
    public void crearLRol(Usuario usuario){
        
        String sql = "INSERT INTO usuario (nombre, telefono, email, direccion) VALUES (?, ?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1, usuario.getNombre());
                statement.setString(2, usuario.getTelefono());
                statement.setString(3, usuario.getEmail());
                statement.setString(4, usuario.getDireccion());
                statement.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("Error al crear el registro" + e.getMessage());            
        }
        
    }
    public void actualizarRol(Usuario usuario){
        
        String sql = "UPDATE usuario SET nombre = ?, telefono=?, email=?; direccion=?  WHERE usuarioID = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, usuario.getNombre());
                statement.setString(2, usuario.getTelefono());
                statement.setString(3, usuario.getEmail());
                statement.setString(4, usuario.getDireccion());
                statement.setInt(5, usuario.getUsuarioID());
                statement.executeUpdate();
            
        }
        catch(SQLException e){
            System.out.println("Error al actualizar los datos" + e.getMessage());
        }
        
    }
    
    public void eliminarLUsuario(int id){
        
        String sql = "DELETE FROM usuario WHERE usuarioID = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);            
            statement.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("Error al eliminar el registro" + e.getMessage());
        }
    }
    
    public List<Usuario> leerUsuario(){
        List<Usuario> listUsuario = new ArrayList<>();
        String sql = "SELECT * FROM usuario ";
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
                    while(resultSet.next()){
                        int id = resultSet.getInt("usuarioID");
                        String nombre = resultSet.getString("nombre");
                        String telefono = resultSet.getString("telefono");
                        String email = resultSet.getString("email");
                        String direccion = resultSet.getString("direccion");
                        Usuario usuario = new Usuario(id, nombre, telefono, email, direccion);
                        listUsuario.add(usuario);
                    }
            
        }
        catch(SQLException e){
            System.out.println("Error al leer los datos" + e.getMessage());
            
        }
        return listUsuario;
    }
    
    public void cerrarConexion(){
        conexion.closeConnection(connection);  
    
    }
    
}
