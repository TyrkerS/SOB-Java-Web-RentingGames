/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;
import deim.urv.cat.homework2.model.Console;
import deim.urv.cat.homework2.model.Game;
import deim.urv.cat.homework2.model.ProductoCarrito;
import deim.urv.cat.homework2.model.Store;
import deim.urv.cat.homework2.service.UserServiceImpl;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.UriRef;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicol
 */
@Controller
@Path("details")
public class DetallesController {

    @Inject
    UserServiceImpl service;
    @Context
    HttpServletRequest request;  // Injectar el objeto HttpServletRequest
    @Inject
    private HttpSession session; //la sessió
    private Game game;
    private Store tiendaRef;
    private Console consola;

    @GET
    public String getDetalles() {

        String titulo = request.getParameter("titulo");
        if (titulo == null) {
            titulo = "Título no disponible";
        }

        titulo = titulo.replaceAll(" ", "%20");
        session.setAttribute("lastRedirect", "details?titulo=" + titulo);
        game = service.getVideojuego(titulo);
        consola = service.getConsoleByGame(titulo);
        request.setAttribute("videojuego", game);
        request.setAttribute("consola", consola);
        return "detalles_videojuego.jsp"; // Injects CRSF token 
    }

    @POST
    @UriRef("addCart")
    public String addCart(@FormParam("juego") String juego, @FormParam("tienda") String tienda, @FormParam("cantidad") int cantidad) {
        game = service.getVideojuego(juego);

        // Verificar si el usuario está logueado
        if (session.getAttribute("user") == null) return "redirect:SignUp";

        tiendaRef = service.getTienda(tienda);
        request.setAttribute("videojuego", game);

        if (cantidad > 0) {
            List<ProductoCarrito> carrito = (List<ProductoCarrito>) session.getAttribute("carrito");

            if (carrito == null) {
                carrito = new ArrayList<>();
            }

            // Calcular la cantidad total del mismo juego en el carrito
            int cantidadTotal = carrito.stream()
                    .filter(prod -> prod.getVideojuego().getTitulo().equals(juego))
                    .mapToInt(ProductoCarrito::getCantidad)
                    .sum();

            // Verificar si la cantidad total excede la disponibilidad
            if (cantidadTotal + cantidad > game.getDisponibilidad()) {
                request.setAttribute("error", "Agregar este juego excede la disponibilidad.");
            } else {
                carrito.add(new ProductoCarrito(tiendaRef, game, cantidad));
                session.setAttribute("carrito", carrito);
            }
        }

        return "detalles_videojuego.jsp";
    }


}
