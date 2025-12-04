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
public class RespuestaVideojuego {

    @SerializedName("titulo")
    private String titulo;
    @SerializedName("imagen")
    private String imagen;
    @SerializedName("disponibilidad")
    private int disponibilidad;
    @SerializedName("descripcion")
    private String descripcion;
    @SerializedName("precio")
    private double precio;

    @SerializedName("tipo")
    private RespuestaVideojuego.TipoJuego tipo;

    @SerializedName("videoconsola")
    private RespuestaVideojuego.VideoConsola videoconsola;

    @SerializedName("tienda")
    private Collection<RespuestaVideojuego.Tienda> tienda;

    @SerializedName("alquiler")
    private Collection<RespuestaAlquiler> alquiler;

    public RespuestaVideojuego() {

    }

    public RespuestaVideojuego(String titulo, String imagen, int disponibilidad, String descripcion,
            RespuestaVideojuego.TipoJuego tipo, RespuestaVideojuego.VideoConsola videoconsola,
            Collection<RespuestaVideojuego.Tienda> tienda, Collection<RespuestaAlquiler> alquiler, double precio) {
        this.titulo = titulo;
        this.imagen = imagen;
        this.disponibilidad = disponibilidad;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.videoconsola = videoconsola;
        this.tienda = tienda;
        this.alquiler = alquiler;
        this.precio = precio;
    }

    public String getTitulo() {
        return titulo;
    }
    
    public String getImagen() {
        return imagen;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public String getDescripcion() {
        return descripcion;
    }
  
    public RespuestaVideojuego.TipoJuego getTipo() {
        return tipo;
    }

    public VideoConsola getVideoconsola() {
        return videoconsola;
    }

    public Collection<Tienda> getTienda() {
        return tienda;
    }

    public Collection<RespuestaAlquiler> getAlquiler() {
        return alquiler;
    }

    public double getPrecio() {
        return precio;
    }
   
    
    public static class TipoJuego {

        @SerializedName("id")
        private int id;
        @SerializedName("tipo")
        private String tipo;
        @SerializedName("videojuego")
        private Collection<RespuestaVideojuego> videojuego;

        public TipoJuego() {
        }

        public TipoJuego(int id, String tipo, Collection<RespuestaVideojuego> videojuego) {
            this.id = id;
            this.tipo = tipo;
            this.videojuego = videojuego;
        }

        public int getId() {
            return id;
        }

        public String getTipo() {
            return tipo;
        }

        public Collection<RespuestaVideojuego> getVideojuego() {
            return videojuego;
        }

        @Override
        public String toString() {
            return "TipoJuego{" + "id=" + id + ", tipo=" + tipo + ", videojuego=" + videojuego + '}';
        }
        
        

    }

    public static class Tienda {

        @SerializedName("id")
        private int id;
        @SerializedName("direccion")
        private String direccion;
        @SerializedName("videojuego")
        private Collection<RespuestaVideojuego> videojuego;

        public Tienda() {
        }

        public Tienda(int id, String direccion, Collection<RespuestaVideojuego> videojuego) {
            this.id = id;
            this.direccion = direccion;
            this.videojuego = videojuego;
        }

        public int getId() {
            return id;
        }

        public String getDireccion() {
            return direccion;
        }

        public Collection<RespuestaVideojuego> getVideojuego() {
            return videojuego;
        }

        @Override
        public String toString() {
            return "Tienda{" + "id=" + id + ", direccion=" + direccion + ", videojuego=" + videojuego + '}';
        }
        
        

    }

    public static class VideoConsola {

        @SerializedName("id")
        private int id;
        @SerializedName("videoconsola")
        private String videoconsola;
        @SerializedName("videojuego")
        private Collection<RespuestaVideojuego> videojuego;

        public VideoConsola() {
        }

        public VideoConsola(int id, String videoconsola, Collection<RespuestaVideojuego> videojuego) {
            this.id = id;
            this.videoconsola = videoconsola;
            this.videojuego = videojuego;
        }

        public int getId() {
            return id;
        }

        public String getVideoconsola() {
            return videoconsola;
        }

        public Collection<RespuestaVideojuego> getVideojuego() {
            return videojuego;
        }

        @Override
        public String toString() {
            return "VideoConsola{" + "id=" + id + ", videoconsola=" + videoconsola + ", videojuego=" + videojuego + '}';
        }
        
        

    }

    @Override
    public String toString() {
        return "RespuestaVideojuego{" + "titulo=" + titulo + ", disponibilidad=" + disponibilidad + ", descripcion=" + descripcion + ", precio=" + precio + ", tipo=" + tipo + ", videoconsola=" + videoconsola + ", tienda=" + tienda + ", alquiler=" + alquiler + '}';
    }

    
}
