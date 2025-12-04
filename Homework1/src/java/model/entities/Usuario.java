/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Column;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author nicol
 */

@NamedQuery(name="validarUsuario",
        query="SELECT u FROM Usuario u WHERE u.nombre = :nombre AND u.clave = :clave")


@Entity
@XmlRootElement
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "Usuario_Gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Usuario_Gen")
    private int id;
    
    @Column(unique=true)
    private String nombre;

    
    private String clave;

    @JsonbTransient
    @OneToMany(mappedBy = "usuario")
    private Collection<Alquiler> alquiler;

    public Usuario() {
        alquiler = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }

    public String getClave() {
        return clave;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Collection<Alquiler> getAlquiler() {
        return alquiler;
    }

    public void addAlquiler(Alquiler alquiler) {
        this.alquiler.add(alquiler);
    }

    public void setAlquiler(Collection<Alquiler> alquiler) {
        this.alquiler = alquiler;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}
