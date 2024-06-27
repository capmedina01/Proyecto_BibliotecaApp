/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Dao.EmpleadoDao;
import Models.Empleado;

/**
 *
 * @author kalic
 */
public class EmpleadoController {
    
    private EmpleadoDao empleadoDao;
    
    public EmpleadoController(){
        this.empleadoDao = new EmpleadoDao();
    }
    
    public boolean validarUsuario(String email, String clave){
        Empleado empLogin = new Empleado(email, clave);
        
        return empleadoDao.validarUsuario(empLogin);
        
    }
    
}
