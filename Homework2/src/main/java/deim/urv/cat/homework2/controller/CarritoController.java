/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;
import deim.urv.cat.homework2.model.ProductoCarrito;
import deim.urv.cat.homework2.service.UserServiceImpl;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.UriRef;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicol
 */

@Controller
@Path("cart")
public class CarritoController {
    
    @Context
    HttpServletRequest request;  // Injectar el objeto HttpServletRequest
    @Inject
    private HttpSession session;
    private double precioTotal;
    private List<ProductoCarrito> carrito;

     
    @GET
    @UriRef("getCart")
    public String getCarrito() {
        
        session.setAttribute("lastRedirect", "cart");
        
        carrito = (List<ProductoCarrito>) session.getAttribute("carrito");
        
        if(carrito == null) {
            carrito = new ArrayList<>();
        }
        
        precioTotal = 0;
        for(ProductoCarrito producto : carrito) {
            precioTotal += producto.getVideojuego().getPrecio() * producto.getCantidad();
        }
        
        request.setAttribute("precioTotal", precioTotal);
        request.setAttribute("fechaRetorno", getFechaRetorno());
        
        return "carrito.jsp";
    }
    
    private String getFechaRetorno() {
        LocalDate fecha = LocalDate.now();
        LocalDate res = fecha.plusWeeks(1);
        
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaRes = res.format(formatter);
        
        return fechaRes;
    }
     
    
}
