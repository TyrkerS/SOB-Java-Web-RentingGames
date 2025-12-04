/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.service;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author nicol
 */
public class RespuestaAlquiler {

    @SerializedName("id")
    private int id;
    @SerializedName("fechaRetorno")
    private String fechaRetorno;
    @SerializedName("precioTotal")
    private int precioTotal;

    @SerializedName("videojuego")
    private Collection<RespuestaVideojuego> videojuegos;

    @SerializedName("usuario")
    private RespuestaUsuario usuario;

    public RespuestaAlquiler() {
         videojuegos = new ArrayList<>();
    }

    public RespuestaAlquiler(String fechaRetorno, int precioTotal, Collection<RespuestaVideojuego> videojuego,
            RespuestaUsuario usuario) {
       // this.id = id;
        this.fechaRetorno = fechaRetorno;
        this.precioTotal = precioTotal;
        this.videojuegos = videojuego;
        this.usuario = usuario;
    }
    
        public RespuestaAlquiler(String fechaRetorno, Collection<RespuestaVideojuego> videojuego,
            RespuestaUsuario usuario) {
       // this.id = id;
        this.fechaRetorno = fechaRetorno;
        this.videojuegos = videojuego;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public String getFechaRetorno() {
        return fechaRetorno;
    }

    public int getPrecioTotal() {
        return precioTotal;
    }

    public Collection<RespuestaVideojuego> getVideojuego() {
        return videojuegos;
    }

    public RespuestaUsuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(RespuestaUsuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "RespuestaAlquiler{" + "id=" + id + ", fechaRetorno=" + fechaRetorno + ", precioTotal=" + precioTotal + ", videojuegos=" + videojuegos + ", usuario=" + usuario + '}';
    }
    
    
}
