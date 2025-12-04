/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deim.urv.cat.homework2.model;

import jakarta.json.bind.annotation.JsonbTransient;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

@XmlRootElement
@Entity
public class Console implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="VideoConsola_Gen", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VideoConsola_Gen") 
    private int id;
    private String nombre;

    @JsonbTransient
    @OneToMany(mappedBy="videoconsola")
    private Collection<Game> videojuegos;
    
    public Console() {
        videojuegos= new ArrayList<>();
    }
    public int getId() {
        return id;
    }

    public Collection<Game> getVideojuegos() {
        return videojuegos;
    }
 
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Collection<Game> getVideojuego() {
        return videojuegos;
    }

    public void addVideojuego(Game videojuego) {
        this.videojuegos.add(videojuego);
    }

    public void setVideojuegos(Collection<Game> videojuegos) {
        this.videojuegos = videojuegos;
    }

    @Override
    public String toString() {
        return "VideoConsola{" + "id=" + id + ", nombre=" + nombre + ", videojuegos=" + videojuegos + '}';
    }
    
    
         
}
