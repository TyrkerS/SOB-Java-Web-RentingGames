package model.entities;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@XmlRootElement
public class Alquiler implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="Alquiler_Gen", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Alquiler_Gen") 
    private int id;
    private String fechaRetorno;
    private int precioTotal;

    //@JsonbTransient
    @ManyToMany(mappedBy="alquiler")
    private Collection<Videojuego> videojuegos;
    
    
    //@JsonbTransient
    @ManyToOne
    private Usuario usuario;

    public Collection<Videojuego> getVideojuegos() {
        return videojuegos;
    }
    
    public Alquiler() {
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

    public Collection<Videojuego> getVideojuego() {
        return videojuegos;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void addVideojuego(Videojuego nuevoVideojuego) {
        videojuegos.add(nuevoVideojuego);
    }
    
    public void calcularPrecioTotal() {
        for(Videojuego videojuego : videojuegos) {
            precioTotal+=videojuego.getPrecio();
        }
    }

    public void setVideojuegos(Collection<Videojuego> videojuegos) {
        this.videojuegos = videojuegos;
    }

    @Override
    public String toString() {
        return "Alquiler{" + "id=" + id + ", fechaRetorno=" + fechaRetorno + ", precioTotal=" + precioTotal + ", videojuegos=" + videojuegos + ", usuario=" + usuario + '}';
    }
    
}
