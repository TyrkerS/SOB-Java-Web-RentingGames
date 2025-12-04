package deim.urv.cat.homework2.model;

import java.util.ArrayList;
import java.util.Collection;
import java.io.Serializable;


public class Rental implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String fechaRetorno;
    private int precioTotal;
 
    private Collection<Game> videojuegos;
    
    private User usuario;

    public Collection<Game> getVideojuegos() {
        return videojuegos;
    }
    
    public Rental() {
        videojuegos =new ArrayList<>();
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

    
    public void setId(int id) {
        this.id = id;
    }

    public void setFechaRetorno(String fechaRetorno) {
        this.fechaRetorno = fechaRetorno;
    }

    public void setPrecioTotal(int precioTotal) {
        this.precioTotal = precioTotal;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
    
    public void addVideojuego(Game nuevoVideojuego) {
        videojuegos.add(nuevoVideojuego);
    }
    
    public void calcularPrecioTotal() {
        for(Game videojuego : videojuegos) {
            precioTotal+=videojuego.getPrecio();
        }
    }

    public void setVideojuegos(Collection<Game> videojuegos) {
        this.videojuegos = videojuegos;
    }

    @Override
    public String toString() {
        return "Alquiler{" + "id=" + id + ", fechaRetorno=" + fechaRetorno + ", precioTotal=" + precioTotal + ", videojuegos=" + videojuegos + ", usuario=" + usuario + '}';
    }
    
}
