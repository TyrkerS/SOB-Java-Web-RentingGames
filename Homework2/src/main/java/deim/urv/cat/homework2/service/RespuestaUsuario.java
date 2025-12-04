/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.service;

import com.google.gson.annotations.SerializedName;
import java.util.Collection;

/**
 *
 * @author nicol
 */
public class RespuestaUsuario {
   
    
    @SerializedName("id")
    private int id;
    
    @SerializedName("nombre")
    private String nombre;
    
    @SerializedName("clave")
    private String clave;
    
    @SerializedName("alquiler")
    private Collection<RespuestaAlquiler> alquiler;

    public RespuestaUsuario() {
    }
    
    public RespuestaUsuario(int id, String nombre, String clave, Collection<RespuestaAlquiler> alquiler) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.alquiler = alquiler;
    }
    
    public RespuestaUsuario(int id, String nombre, String clave) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
    }

    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getClave() {
        return clave;
    }

    public Collection<RespuestaAlquiler> getAlquiler() {
        return alquiler;
    }

    @Override
    public String toString() {
        return "RespuestaUsuario{" + "id=" + id + ", clave=" + clave + ", alquiler=" + alquiler + '}';
    }
    
    
}
