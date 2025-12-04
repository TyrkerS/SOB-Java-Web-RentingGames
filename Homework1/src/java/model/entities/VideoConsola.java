/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

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
public class VideoConsola implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="VideoConsola_Gen", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VideoConsola_Gen") 
    private int id;
    private String nombre;

    @JsonbTransient
    @OneToMany(mappedBy="videoconsola")
    private Collection<Videojuego> videojuegos;
    
    public VideoConsola() {
        videojuegos= new ArrayList<>();
    }
    public int getId() {
        return id;
    }

    public Collection<Videojuego> getVideojuegos() {
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

    public Collection<Videojuego> getVideojuego() {
        return videojuegos;
    }

    public void addVideojuego(Videojuego videojuego) {
        this.videojuegos.add(videojuego);
    }

    public void setVideojuegos(Collection<Videojuego> videojuegos) {
        this.videojuegos = videojuegos;
    }

    @Override
    public String toString() {
        return "VideoConsola{" + "id=" + id + ", nombre=" + nombre + ", videojuegos=" + videojuegos + '}';
    }
    
    
         
}
