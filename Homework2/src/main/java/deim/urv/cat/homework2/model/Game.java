/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deim.urv.cat.homework2.model;

import java.util.ArrayList;
import java.util.Collection;


public class Game{

    private String titulo;
    private String imagen;
    private int disponibilidad;
    private String descripcion;
    private double precio;
    private TypeGame tipo;
    private Console videoconsola;
    private Collection<Store> tienda;
    
    private Collection<Rental> alquiler;
    
    public Game() {
        tienda= new ArrayList<>();
        alquiler= new ArrayList<>();
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


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }     

    public TypeGame getTipo() {
        return tipo;
    }

    public Console getVideoconsola() {
        return videoconsola;
    }

    public Collection<Store> getTienda() {
        return tienda;
    }

    public Collection<Rental> getAlquiler() {
        return alquiler;
    }

    public void setTipo(TypeGame tipo) {
        this.tipo = tipo;
    }

    public void setVideoconsola(Console videoconsola) {
        this.videoconsola = videoconsola;
    }

    public void setTienda(Collection<Store> tienda) {
        this.tienda = tienda;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public void addAlquiler(Rental nuevoAlquiler) {
        alquiler.add(nuevoAlquiler);
    }
    
    public void addTienda(Store nuevaTienda) {
        tienda.add(nuevaTienda);
    }

    @Override
    public String toString() {
        return "Videojuego{" + "titulo=" + titulo + ", precio= " + precio + ", disponibilidad=" + disponibilidad + ", descripcion=" + descripcion + ", tipo=" + tipo + ", videoconsola=" + videoconsola + ", tienda=" + tienda + ", alquiler=" + alquiler + '}';
    }

    public void setAlquiler(Collection<Rental> alquiler) {
        this.alquiler = alquiler;
    }
  
}
