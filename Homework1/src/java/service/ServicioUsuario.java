package service;

import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import authn.Secured;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import model.entities.Alquiler;
import model.entities.Usuario;

@Stateless
@Path("/user")
public class ServicioUsuario extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    public ServicioUsuario() {
        super(Usuario.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @PUT
    @Secured
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") int id, Usuario entity) {
        super.edit(entity);
    }

    @GET
    @Path("{id}")
    @Produces(value = {"application/json", "text/json", "*/*"})
    public Response find(@PathParam("id") int id) {
        return Response.ok().entity(super.find(id)).build();
    }

    @GET
    @Override
    @Produces(value = {"application/json", "text/json", "*/*"})
    public List<Usuario> findAll() {
        return super.findAll();
    }
  
    @GET
    @Path("/auth")
    @Produces(MediaType.APPLICATION_JSON)
    public Response auth(@QueryParam("nombre") String nombre, @QueryParam("clave") String clave){
        Usuario usuario = (Usuario) em.createNamedQuery("validarUsuario")
                .setParameter("nombre", nombre)
                .setParameter("clave", clave)
                .getSingleResult();
        if(usuario==null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else{
            return Response.ok().entity(usuario).build();
        }

    }
    

}
