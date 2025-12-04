/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;
import deim.urv.cat.homework2.model.Game;
import deim.urv.cat.homework2.model.ProductoCarrito;
import deim.urv.cat.homework2.model.Store;
import deim.urv.cat.homework2.model.User;
import deim.urv.cat.homework2.service.RespuestaAlquiler;
import deim.urv.cat.homework2.service.RespuestaUsuario;
import deim.urv.cat.homework2.service.RespuestaVideojuego;
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
import java.util.Collection;
import java.util.List;

/**
 *
 * @author nicol
 */
@Controller
@Path("purchased")
public class CompraRealizadaController {
    
    @Inject
    UserServiceImpl service;
    @Context
    HttpServletRequest request;
    @Inject
    private HttpSession session;
    private List<ProductoCarrito> carrito;
    
    
    @POST
    @UriRef("addAlquiler")
    public String addAlquiler() {
        User user = (User) session.getAttribute("user");
        String fecha = getFechaRetorno();
        
        carrito = (List<ProductoCarrito>) session.getAttribute("carrito");

        Collection<RespuestaVideojuego> listaVideojuego = new ArrayList<>();
        double precioTotal = 0;
        for(ProductoCarrito producto : carrito) {
            Game currVideo = producto.getVideojuego();
            Collection<RespuestaVideojuego.Tienda> tienda = new ArrayList<>();
            for(Store store: currVideo.getTienda()) {
                tienda.add(new RespuestaVideojuego.Tienda(store.getId(), store.getDireccion(), null));
            }

            listaVideojuego.add(new RespuestaVideojuego(currVideo.getTitulo(), currVideo.getImagen(), currVideo.getDisponibilidad(),
            currVideo.getDescripcion(), new RespuestaVideojuego.TipoJuego(currVideo.getTipo().getId(), currVideo.getTipo().getTipo(), null), null, tienda, null, currVideo.getPrecio()));
            precioTotal += producto.getVideojuego().getPrecio() * producto.getCantidad();

        }

        String numPedido = service.crearAlquiler(user.getId(), new RespuestaAlquiler(getFechaRetorno(), (int) precioTotal, listaVideojuego, new RespuestaUsuario(user.getId(), user.getNombre(), user.getClave())));


        if (numPedido != null && !numPedido.isEmpty()) {
            // Actualizar la disponibilidad de cada producto en el carrito y luego vaciar el carrito
            int i=0;
            for (ProductoCarrito producto : carrito) {
                i=0;
                while (i<producto.getCantidad()){
                    service.actualizarDisponibilidad(producto.getVideojuego().getTitulo());
                    i++;
                }
            }

            carrito.clear(); // Vaciar el carrito
            session.setAttribute("carrito", new ArrayList<ProductoCarrito>()); // Actualizar la sesión
        }

        request.setAttribute("numPedido", numPedido);
        request.setAttribute("fecha", fecha);

        return "compra_realizada.jsp";
    }

    
        private String getFechaRetorno() {
        LocalDate fecha = LocalDate.now();
        LocalDate res = fecha.plusWeeks(1);
        
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaRes = res.format(formatter);
        
        return fechaRes;
    }
}
