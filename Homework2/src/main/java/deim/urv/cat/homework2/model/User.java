package deim.urv.cat.homework2.model;

import java.util.ArrayList;
import java.util.Collection;

public class User {
    
    private int id;
    private String nombre;
    private String clave;
    private Collection<Rental> alquiler;
    
    public User() {
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

    public Collection<Rental> getAlquiler() {
        return alquiler;
    }

    public void addAlquiler(Rental alquiler) {
        this.alquiler.add(alquiler);
    }

    public void setAlquiler(Collection<Rental> alquiler) {
        this.alquiler = alquiler;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nombre=" + nombre + ", clave=" + clave + ", alquiler=" + alquiler + '}';
    }
    
}
