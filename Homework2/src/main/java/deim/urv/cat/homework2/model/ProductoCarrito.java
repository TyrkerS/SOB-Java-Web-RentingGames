/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.model;

/**
 *
 * @author nicol
 */
public class ProductoCarrito {
    
    private Store tienda;
    private Game videojuego;
    private int cantidad;
    
    public ProductoCarrito(Store tienda, Game videojuego, int cantidad) {
        this.tienda = tienda;
        this.videojuego = videojuego;
        this.cantidad = cantidad;
    }

    public Store getTienda() {
        return tienda;
    }

    public void setTienda(Store tienda) {
        this.tienda = tienda;
    }

    public Game getVideojuego() {
        return videojuego;
    }

    public void setVideojuego(Game videojuego) {
        this.videojuego = videojuego;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
