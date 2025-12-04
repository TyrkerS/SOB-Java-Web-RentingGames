/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.Game;
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
import java.util.List;

/**
 *
 * @author nicolle
 */
@Controller
@Path("store")
public class PagPrincipalController {

    @Inject
    UserServiceImpl service;
    @Context
    HttpServletRequest request;  // Injectar el objeto HttpServletRequest
    @Inject
    private HttpSession session; //la sessió
    private List<Game> games;
   
    @GET
    public String getVideojuegos() {

        session.setAttribute("lastRedirect", "store");

        games = service.obtenerVideojuegos("null", "null");

        request.setAttribute("videojuegos", games);
        
        for(Game game : games){
            game.setVideoconsola(service.getConsoleByGame(game.getTitulo()));
        }

        return "pp_videojuegos.jsp";
    }

    @POST
    @UriRef("filter")
    public String FilterGame(@FormParam("busqueda") String busqueda, @FormParam("filtro") int filtro) {

        if (busqueda.equals("")) {
            games = service.obtenerVideojuegos("null", "null");
        } else if (filtro == 0) {
            games = service.obtenerVideojuegos(busqueda, "null");
        } else if (filtro == 1) {
            games = service.obtenerVideojuegos("null", busqueda);
        }
        request.setAttribute("videojuegos", games);
        return "pp_videojuegos.jsp";
    }

}
