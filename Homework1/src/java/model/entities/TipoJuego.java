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
public class TipoJuego implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="TipoJuego_Gen", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TipoJuego_Gen") 
    
    private int id;
    private String tipo;

    @JsonbTransient
    @OneToMany(mappedBy="tipo")
    private Collection<Videojuego> videojuegos;
    
    public TipoJuego() {
        videojuegos = new ArrayList<>();
    }
    public int getId() {
        return id;
    }
    public String getTipo() {
        return tipo;
    }

    public Collection<Videojuego> getVideojuegos() {
        return videojuegos;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setVideojuegos(Collection<Videojuego> videojuegos) {
        this.videojuegos = videojuegos;
    }
       
}
