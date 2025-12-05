package service;

import authn.Secured;
import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.StringReader;
import java.util.Collections;
import java.util.Comparator;
import model.entities.Tienda;
import model.entities.TipoJuego;
import model.entities.VideoConsola;
import model.entities.Videojuego;

@Stateless
@Path("/videojuegos")
public class ServicioVideojuego extends AbstractFacade<Videojuego> {

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    public ServicioVideojuego() {
        super(Videojuego.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @POST
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(String jsonString) {

        try {

            StringReader stringReader = new StringReader(jsonString);
            JsonReader jsonReader = Json.createReader(stringReader);
            JsonObject jsonObject = jsonReader.readObject();

            String titulo = jsonObject.getString("titulo");

            if (em.find(Videojuego.class, titulo) != null) {
                return Response.status(Response.Status.CONFLICT).entity("El videojuego ya existe").build();
            }

            Videojuego videojuego = new Videojuego();

            videojuego.setTitulo(titulo);
            videojuego.setImagen(jsonObject.getString("imagen"));
            videojuego.setDisponibilidad(jsonObject.getInt("disponibilidad"));
            videojuego.setDescripcion(jsonObject.getString("descripcion"));

            Object precio = jsonObject.get("precio");

            try {
                videojuego.setPrecio(Double.parseDouble(precio.toString().trim()));
            } catch (NumberFormatException e) {
            }

            JsonObject tipoObject = jsonObject.getJsonObject("tipo");
            int tipoId = tipoObject.getInt("id");
            TipoJuego tipo = getEntityManager().find(TipoJuego.class, tipoId);
            videojuego.setTipo(tipo);

            JsonObject videoconsolaObject = jsonObject.getJsonObject("videoconsola");
            int videoconsolaId = videoconsolaObject.getInt("id");
            VideoConsola videoconsola = getEntityManager().find(VideoConsola.class, videoconsolaId);
            videoconsola.addVideojuego(videojuego);
            videojuego.setVideoconsola(videoconsola);

            JsonArray tiendas = jsonObject.getJsonArray("tienda");

            for (JsonValue value : tiendas) {

                JsonObject objectValue = (JsonObject) value;
                int idTienda = objectValue.getInt("id");
                Tienda tienda = em.find(Tienda.class, idTienda);

                videojuego.addTienda(tienda);
                tienda.addVideojuego(videojuego);

            }

            super.create(videojuego);
            return Response.status(Response.Status.CREATED).entity("Se ha procesado la solicitud correctamente").build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al procesar la solicitud").build();
        }
    }

    @GET
    @Path("{titulo}")
    @Produces(value = {"application/json", "text/json", "*/*"})
    public Response findVideojuego(@PathParam("titulo") String titulo) {
        Videojuego videojuego = em.createQuery("SELECT v FROM Videojuego v WHERE v.titulo = :titulo", Videojuego.class)
                .setParameter("titulo", titulo)
                .getSingleResult();

        return Response.ok().entity(videojuego).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(
            @QueryParam("tipo") String type,
            @QueryParam("videoconsola") String console
    ) {

        /*Aqui se verifica si cada variable contiene la cadena de caracteres "null", si es asi, se asigna el valor null.
Esto lo hacemos para tratar los casos en los que solo queramos listar un tipo de juego, un tipo de consola o
también funcionará si queremos usar ambos valores como opcionales y listar todos los videojuegos*/
        type = ("null".equals(type)) ? null : type;
        console = ("null".equals(console)) ? null : console;

        Response existen = existenParams(type, console);
        if (existen != null) {
            return existen;
        }
        List<Videojuego> videojuegos;

        if (type != null && console != null) {
            // Ambos parámetros están presentes
            videojuegos = em.createQuery("SELECT v FROM Videojuego v WHERE v.tipo.tipo = :tipo AND v.videoconsola.nombre = :VideoConsola ORDER BY v.titulo", Videojuego.class)
                    .setParameter("tipo", type)
                    .setParameter("VideoConsola", console)
                    .getResultList();
        } else if (type != null) {
            // Solo se proporcionó el parámetro 'type'
            videojuegos = em.createQuery("SELECT v FROM Videojuego v WHERE v.tipo.tipo = :tipo ORDER BY v.titulo", Videojuego.class)
                    .setParameter("tipo", type)
                    .getResultList();
        } else if (console != null) {
            // Solo se proporcionó el parámetro 'console'
            videojuegos = em.createQuery("SELECT v FROM Videojuego v WHERE v.videoconsola.nombre = :VideoConsola ORDER BY v.titulo", Videojuego.class)
                    .setParameter("VideoConsola", console)
                    .getResultList();
        } else {
            // No se proporcionaron parámetros, obtener todos los videojuegos
            videojuegos = super.findAll();
            // Ordenar la lista de videojuegos por título
            Collections.sort(videojuegos, Comparator.comparing(Videojuego::getTitulo));
        }
        

        return Response.ok().entity(videojuegos).build();
    }

    /*
    Con este método comprobamos si existen los parámetros pasados por parámetro. Retornamos null si se ha encontrado
    coincidencias en la base de datos.
     */
    public Response existenParams(String tipo, String consola) {

        if (tipo != null && consola != null) {

            TypedQuery<Long> query = em.createQuery("SELECT COUNT(t) FROM TipoJuego t WHERE t.tipo = :tipo", Long.class);
            query.setParameter("tipo", tipo);
            Long count = query.getSingleResult();

            if (count == 0) {
                return Response.status(Response.Status.BAD_REQUEST).entity("El tipo indicado no existe.").build();
            }

            TypedQuery<Long> query1 = em.createQuery("SELECT COUNT(v) FROM VideoConsola v WHERE v.nombre = :nombre", Long.class);
            query1.setParameter("nombre", consola);
            Long count1 = query1.getSingleResult();

            if (count1 == 0) {
                return Response.status(Response.Status.BAD_REQUEST).entity("La consola indicada no existe.").build();
            }
        }

        return null;
    }

    @POST
    @Path("/actualizarDisponibilidad")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarDisponibilidad(@QueryParam("titulo") String titulo) {
        try {
            if (titulo == null || titulo.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST).entity("El parámetro 'titulo' es requerido.").build();
            }

            Videojuego videojuego = em.find(Videojuego.class, titulo);

            if (videojuego == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("No se encontró el videojuego con el título proporcionado.").build();
            }

            // Actualizar disponibilidad
            int dispo = videojuego.getDisponibilidad();
            dispo = dispo-1;
            videojuego.setDisponibilidad(dispo);
            em.merge(videojuego);
         
            return Response.ok().entity("La disponibilidad del videojuego ha sido actualizada.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al procesar la solicitud").build();
        }
    }
}
