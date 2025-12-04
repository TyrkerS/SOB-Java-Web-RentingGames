package service;
import jakarta.ejb.Stateless;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import authn.Secured;
import jakarta.ws.rs.core.Response;
import java.io.StringReader;
import model.entities.Alquiler;
import model.entities.Usuario;
import model.entities.Videojuego;


@Stateless
@Path("/rental")
public class ServicioAlquiler extends AbstractFacade<Alquiler> {

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;
    public ServicioAlquiler() {
        super(Alquiler.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @POST
    @Secured
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(String jsonString){
        //El string con forma json se convierte a objeto Json y podemos manejarlo en fragmentos
            StringReader stringReader = new StringReader(jsonString);
            JsonReader jsonReader = Json.createReader(stringReader);
            JsonObject jsonObject = jsonReader.readObject();
            
            Alquiler alquiler = new Alquiler();
            
            JsonObject clienteObject = jsonObject.getJsonObject("usuario");
            int cliente = clienteObject.getInt("id");

            Usuario usuario = getEntityManager().find(Usuario.class, cliente);
            
            if(usuario!=null){
                alquiler.setUsuario(usuario);
            }

            alquiler.setFechaRetorno(jsonObject.getString("fechaRetorno"));

            JsonArray arrVideojuego = jsonObject.getJsonArray("videojuego");
            for(JsonValue value : arrVideojuego){
                
                JsonObject objectValue = (JsonObject) value;

                String nombreJuego = objectValue.getString("titulo", "");

                Videojuego videojuego = getEntityManager().find(Videojuego.class, nombreJuego);
                if(videojuego!=null){
                    alquiler.addVideojuego(videojuego);
                    videojuego.addAlquiler(alquiler);
                }
            }
           
            alquiler.calcularPrecioTotal();
            usuario.addAlquiler(alquiler);

            super.create(alquiler);
            
            return Response.status(Response.Status.CREATED)
            .entity("{\"id\": " + alquiler.getId() + "}")
            .build();
            
    }
            
    @GET
    @Secured
    @Path("{id}")
    @Produces(value = {"application/json", "text/json", "*/*"})
    public Response find(@PathParam("id") int id) {
        
        Alquiler alquiler = super.find(id);
        
        return Response.ok().entity(alquiler).build();
    }
   
}
