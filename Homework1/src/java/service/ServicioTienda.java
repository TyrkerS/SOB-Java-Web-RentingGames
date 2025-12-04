package service;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import model.entities.Tienda;




@Stateless
@Path("/tienda")
public class ServicioTienda extends AbstractFacade<Tienda> {

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;
    public ServicioTienda() {
        super(Tienda.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @GET
    @Path("{idTienda}")
    @Produces(value = {"application/json", "text/json", "*/*"})
    public Response findTienda(@PathParam("idTienda") String idTienda) {
         Tienda tienda =  em.createQuery("SELECT v FROM Tienda v WHERE v.id = " + idTienda, Tienda.class)
                .getSingleResult();

         return Response.ok().entity(tienda).build();
    }
}
