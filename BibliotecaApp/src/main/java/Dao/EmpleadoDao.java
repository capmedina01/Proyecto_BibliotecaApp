/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Config.Conexion;
import Models.Empleado;
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
public class EmpleadoDao {
    
    private Connection connection;
    private Conexion conexion;
    
    public EmpleadoDao (){
        conexion = new Conexion();
        connection = conexion.getConexion();
    
    }
    public void crearEmpleado(Empleado empleado){
        String sql = "INSERT INTO empleado (nombre, direccion, telefono, email, clave, rolID) VALUES (?, ?, ?, ?, ?, ?)" ;
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, empleado.getNombre());
            statement.setString(2, empleado.getDireccion());
            statement.setString(3, empleado.getTelefono());
            statement.setString(4, empleado.getEmail());
            statement.setString(5, empleado.getClave());
            statement.setInt(6, empleado.getRolID());
            statement.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("Error al crear el empleado" + e.getMessage());            
        }
    }
    
    public void actualizarEmpleado(Empleado empleado){
        String sql = "UPDATE empleado SET nombre=?, direccion=?, telefono=?, email=?, clave=?, rolID=? WHERE categoriaID = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, empleado.getNombre());
            statement.setString(2, empleado.getDireccion());
            statement.setString(3, empleado.getTelefono());
            statement.setString(4, empleado.getEmail());
            statement.setString(5, empleado.getClave());
            statement.setInt(6, empleado.getRolID());
            statement.setInt(7, empleado.getEmpleadoID());
            statement.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("Error al actulizar los datos" + e.getMessage());
            
        }
    }
    
    public void eliminarEmpleado(int id){
        String sql = "DELETE FROM empleado WHERE categoriaID = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            statement.executeUpdate();
            
        }
        catch(SQLException e){
            System.out.println("Error al eliminar el registro" + e.getMessage());            
        }
    }
    
    public List<Empleado> mostrarEmpleado(){
        List<Empleado> listEmpleado = new ArrayList<>();        
        String sql = "SELECT * FROM empleado";
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
            while(resultSet.next()){
                int id = resultSet.getInt("empleadoID");
                String nombre = resultSet.getString("nombre");
                String direccion = resultSet.getString("direccion");
                String telefono = resultSet.getString("telefono");
                String email = resultSet.getString("email");
                String clave = resultSet.getString("clave");
                int rol = resultSet.getInt("rolID");
                Empleado empleado = new Empleado(id, nombre, direccion, telefono, email, clave, rol);
                listEmpleado.add(empleado);                
            }           
        }
        catch(SQLException e){
            System.out.println("Error al mostrar la lista de empleados" + e.getMessage());            
        }
        return listEmpleado;
    }
    
    public boolean validarUsuario(Empleado empleado){
        String sql = "SELECT COUNT(*) FROM empleado WHERE email = ? AND clave = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, empleado.getEmail());
            statement.setString(2, empleado.getClave());
            try(ResultSet result = statement.executeQuery()){
                if(result.next()){
                    int cont = result.getInt(1);
                    return cont>0;
                }
                
            }
            
        }
        catch(SQLException e){
            System.out.println("Error registro no encontrado" + e.getMessage());
        }
        return false;
    }
    
    public void cerrarConexion(){
        conexion.closeConnection(connection);  
    
    }
    
}
