/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deim.urv.cat.homework2.model;
import java.util.ArrayList;
import java.util.Collection;

public class TypeGame { 
    
    private int id;
    private String tipo;
    private Collection<Game> videojuegos;
    
    public TypeGame() {
        videojuegos = new ArrayList<>();
    }
    public int getId() {
        return id;
    }
    public String getTipo() {
        return tipo;
    }

    public Collection<Game> getVideojuegos() {
        return videojuegos;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setVideojuegos(Collection<Game> videojuegos) {
        this.videojuegos = videojuegos;
    }
       
}
