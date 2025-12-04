/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deim.urv.cat.homework2.model;
import java.util.ArrayList;
import java.util.Collection;


public class Store {
   
    private int id;
    private String direccion;
    private Collection<Game> videojuegos;
    
    public Store() {
        videojuegos = new ArrayList<>();
    }
    public int getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }
   
    public void setId(int id) {
        this.id = id;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Collection<Game> getVideojuegos() {
        return videojuegos;
    }

    public void addVideojuego(Game videojuego) {
        this.videojuegos.add(videojuego);
    }

    public void setVideojuegos(Collection<Game> videojuegos) {
        this.videojuegos = videojuegos;
    }
         
}
