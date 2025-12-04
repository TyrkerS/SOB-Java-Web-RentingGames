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
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collection;

@XmlRootElement
@Entity
public class Tienda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="Tienda_Gen", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Tienda_Gen") 
    
    private int id;
    private String direccion;

    @JsonbTransient
    @ManyToMany(mappedBy="tienda")
    private Collection<Videojuego> videojuegos;
    
    public Tienda() {
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

    public Collection<Videojuego> getVideojuegos() {
        return videojuegos;
    }

    public void addVideojuego(Videojuego videojuego) {
        this.videojuegos.add(videojuego);
    }

    public void setVideojuegos(Collection<Videojuego> videojuegos) {
        this.videojuegos = videojuegos;
    }
         
}
