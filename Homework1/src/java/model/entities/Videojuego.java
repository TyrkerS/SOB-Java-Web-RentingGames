/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collection;

@XmlRootElement
@Entity
public class Videojuego implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String titulo;
    private String imagen;
    private int disponibilidad;
    private String descripcion;
    private double precio;

    @ManyToOne
    private TipoJuego tipo;

    @JsonbTransient
    @ManyToOne
    private VideoConsola videoconsola;

    @ManyToMany
    private Collection<Tienda> tienda;

    @JsonbTransient
    @ManyToMany
    private Collection<Alquiler> alquiler;

    public Videojuego() {
        tienda = new ArrayList<>();
        alquiler = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoJuego getTipo() {
        return tipo;
    }

    public String getImagen() {
        return imagen;
    }

    public VideoConsola getVideoconsola() {
        return videoconsola;
    }

    public Collection<Tienda> getTienda() {
        return tienda;
    }

    public Collection<Alquiler> getAlquiler() {
        return alquiler;
    }

    public void setTipo(TipoJuego tipo) {
        this.tipo = tipo;
    }

    public void setVideoconsola(VideoConsola videoconsola) {
        this.videoconsola = videoconsola;
    }

    public void setTienda(Collection<Tienda> tienda) {
        this.tienda = tienda;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void addAlquiler(Alquiler nuevoAlquiler) {
        alquiler.add(nuevoAlquiler);
    }

    public void addTienda(Tienda nuevaTienda) {
        tienda.add(nuevaTienda);
    }

    @Override
    public String toString() {
        return "Videojuego{" + "titulo=" + titulo + ", precio= " + precio + ", disponibilidad=" + disponibilidad + ", descripcion=" + descripcion + ", tipo=" + tipo + ", videoconsola=" + videoconsola + ", tienda=" + tienda + ", alquiler=" + alquiler + '}';
    }

    public void setAlquiler(Collection<Alquiler> alquiler) {
        this.alquiler = alquiler;
    }

}
