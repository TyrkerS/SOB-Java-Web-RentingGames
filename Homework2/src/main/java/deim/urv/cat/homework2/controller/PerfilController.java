/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;
import deim.urv.cat.homework2.model.Rental;
import deim.urv.cat.homework2.model.User;
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
import java.util.List;

/**
 *
 * @author nicol
 */
@Controller
@Path("profile")
public class PerfilController {
    
    @Inject UserServiceImpl service;
    @Context HttpServletRequest request;
    @Inject
    private HttpSession session;

    @GET
    public String getPerfil(){
        
        User user = (User) session.getAttribute("user");
        List<Rental> rentals = service.getRentalsByUser(user.getNombre(), user.getClave());
        
        request.setAttribute("rentals", rentals);

        return "perfil.jsp";
    }
    
    @POST
    @UriRef("cerrarSesion")
    public String cerrarSesion() {

        session.setAttribute("user", null);

        return "redirect:store";

    }
}
