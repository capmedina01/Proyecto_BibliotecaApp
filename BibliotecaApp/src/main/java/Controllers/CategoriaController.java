/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Dao.CategoriaDao;
import Models.Categoria;
import java.util.List;

/**
 *
 * @author kalic
 */
public class CategoriaController {
    
    private CategoriaDao catDao;

    public CategoriaController() {
        this.catDao = new CategoriaDao();
    }
    
    
    public List<Categoria> obtenerCat(){
        
        return catDao.leerCategoria();
    } 
    
    public void nuevaCategoria(String nombre){
        Categoria cat = new Categoria(nombre);
        catDao.crearCategoria(cat);
    }
}
