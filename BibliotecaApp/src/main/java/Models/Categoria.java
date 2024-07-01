/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author kalic
 */
public class Categoria {
    
    private int categoriaID;
    private String nombre;

    public Categoria(int categoriaID, String nombre) {
        this.categoriaID = categoriaID;
        this.nombre = nombre;
    }

    public Categoria(String nombre) {
        this.nombre = nombre;
        
    }
    
    @Override
    public String toString() {
        return nombre;
    }

    public int getCategoriaID() {
        return categoriaID;
    }

    public void setCategoriaID(int categoriaID) {
        this.categoriaID = categoriaID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
