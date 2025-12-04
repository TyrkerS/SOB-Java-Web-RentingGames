/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Collection;
import model.entities.Alquiler;
import model.entities.Usuario;

/**
 *
 * @author nicol
 */
@Stateless
@Path("/rentals")
public class ServicioListaAlquiler extends AbstractFacade<Alquiler> {
    
    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;
    public ServicioListaAlquiler() {
        super(Alquiler.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @GET
    @Path("/alquileres")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlquileresUsuario(@QueryParam("nombre") String nombre, @QueryParam("clave") String clave){
        Usuario usuario = (Usuario) em.createNamedQuery("validarUsuario")
                .setParameter("nombre", nombre)
                .setParameter("clave", clave)
                .getSingleResult();
        if(usuario==null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else{
            
            Collection<Alquiler> alquileres = (Collection<Alquiler>) em.createQuery("SELECT a FROM Alquiler a WHERE a.usuario.id = " + usuario.getId(), Alquiler.class)
                .getResultList();
            
            
            return Response.ok().entity(alquileres).build();
        }

    }
}
