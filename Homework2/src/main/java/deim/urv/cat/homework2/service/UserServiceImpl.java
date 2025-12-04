package deim.urv.cat.homework2.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.*;
import deim.urv.cat.homework2.model.Console;
import deim.urv.cat.homework2.model.Game;
import deim.urv.cat.homework2.model.Rental;
import deim.urv.cat.homework2.model.Store;
import deim.urv.cat.homework2.model.User;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.GenericType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private final WebTarget webTargetVideojuego;
    private final WebTarget webTargetRental;
    private final WebTarget webTargetUser;
    private final WebTarget webTargetTienda;
    private final Client client;
    private static final String BASE_URI = "http://localhost:8080/Homework1/webresources";

    public UserServiceImpl() {
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
        webTargetVideojuego = client.target(BASE_URI).path("videojuegos");
        webTargetRental = client.target(BASE_URI).path("rental");
        webTargetUser = client.target(BASE_URI).path("user");
        webTargetTienda = client.target(BASE_URI).path("tienda");
    }

    public void close() {
        client.close();
    }

    @Override
    public Response getAlquiler(int alquilerId) {
        // Realizar una solicitud GET al servicio ServicioAlquiler para obtener un alquiler por ID
        Response respuesta = null;

        respuesta = webTargetRental
                .path(String.valueOf(alquilerId))
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic " + autorizacionUsuario("sob", "sob"))
                .get();

        if (respuesta.getStatus() == 401) {
            return null;
        } else {
            return respuesta;
        }
    }

    public String crearAlquiler(int usuarioId, RespuestaAlquiler jsonAlquiler) {
        // Obtener el usuario por su ID
        // Agregar la información del usuario al JSON del alquiler

        Response responseUsuario = obtenerUsuario(usuarioId);
        Gson g = new Gson();
        String jsonUsuario = responseUsuario.readEntity(String.class);

        RespuestaUsuario user = g.fromJson(jsonUsuario, RespuestaUsuario.class);
        jsonAlquiler.setUsuario(user);

        Response response = webTargetRental
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic " + autorizacionUsuario(user.getNombre(), user.getClave()))
                .post(Entity.entity(g.toJson(jsonAlquiler), MediaType.APPLICATION_JSON), Response.class);

        if (response.getStatus() == 401) {
            return null;
        }

        String responseBody = response.readEntity(String.class);

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
        return jsonObject.get("id").toString();

    }

    @Override
    public Response crearVideojuego(RespuestaVideojuego videojuego) {

        Gson g = new Gson();

        // Realizar una solicitud POST al servicio ServicioAlquiler para crear un nuevo alquiler
        Response res = webTargetVideojuego
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic " + autorizacionUsuario("sob", "sob"))
                .post(Entity.entity(g.toJson(videojuego), MediaType.APPLICATION_JSON), Response.class);

        if (res.getStatus() == 401) {
            return null;
        } else {
            return res;
        }
    }

    @Override
    public List<RespuestaUsuario> obtenerListaUsuarios() {

        Response response = webTargetUser
                .request(MediaType.APPLICATION_JSON)
                .get();

        String usuarios = response.readEntity(String.class);

        Gson g = new Gson();
        //Conversion a tipo deseado
        Type typeUsuarios = new TypeToken<List<RespuestaUsuario>>() {
        }.getType();

        return g.fromJson(usuarios, typeUsuarios);

    }

    public Response obtenerUsuario(int usuarioId) {
        // Realizar una solicitud GET al servicio ServicioUsuario para obtener un usuario por ID
        return webTargetUser
                .path(String.valueOf(usuarioId))
                .request(MediaType.APPLICATION_JSON)
                .get();
    }

    @Override
    public List<Game> obtenerVideojuegos(String tipo, String videoconsola) {

        WebTarget resource = client.target(BASE_URI + "/videojuegos?tipo={arg0}&videoconsola={arg1}").resolveTemplates(Map.of("arg0", tipo, "arg1", videoconsola));

        Response petiVideojuegos = resource.request().get();

        return petiVideojuegos.readEntity(new GenericType<List<Game>>() {
        });

    }

    public Game getVideojuego(String titulo) {

        Response respuesta = webTargetVideojuego
                .path(titulo)
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .get();

        return respuesta.readEntity(Game.class);
    }

    public Store getTienda(String idTienda) {

        Response respuesta = webTargetTienda
                .path(idTienda)
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .get();

        return respuesta.readEntity(Store.class);
    }

    public String autorizacionUsuario(String id, String clave) {
        String credenciales = id + ":" + clave;

        return Base64.getEncoder().encodeToString(credenciales.getBytes());
    }

    public Response modificarUsuario(int usuarioId, String nombre, String nuevaClave) {
        // Realizar una solicitud PUT al servicio ServicioUsuario para editar un usuario por ID
        RespuestaUsuario usuario = new RespuestaUsuario(usuarioId, nombre, nuevaClave);

        Response res = webTargetUser
                .path(String.valueOf(usuarioId))
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic " + autorizacionUsuario("sob", "sob"))
                .put(Entity.entity(usuario, MediaType.APPLICATION_JSON), Response.class);

        if (res.getStatus() == 401) {
            return null;
        }
        return res;
    }

    public User getUserByName(String nombre, String clave) {
        WebTarget webTarget = client.target(BASE_URI).path("/user/auth");
        User user = null;
        Response response = webTarget
                .queryParam("nombre", nombre)
                .queryParam("clave", clave)
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic " + autorizacionUsuario(nombre, clave))
                .get();
        if (response.getStatus() == 200) {
            user = response.readEntity(User.class);

        } else {
            System.out.println(response.getStatus());
        }
        return user;
    }

    public List<Rental> getRentalsByUser(String nombre, String clave) {
        WebTarget webTarget = client.target(BASE_URI).path("/rentals/alquileres");
        List<Rental> rentals = null;
        Response response = webTarget
                .queryParam("nombre", nombre)
                .queryParam("clave", clave)
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic " + autorizacionUsuario(nombre, clave))
                .get();

        if (response.getStatus() == 200) {

            GenericEntity<List<Rental>> genRentals = new GenericEntity<List<Rental>>(new ArrayList<>()) {
            };

            rentals = (List<Rental>) response.readEntity(genRentals.getRawType());

        } else {
            System.out.println(response.getStatus());
        }
        return rentals;
    }

    public Console getConsoleByGame(String nombre) {
        WebTarget webTarget = client.target(BASE_URI).path("/consoles/getVideoconsola");
        Console consola = null;
        Response response = webTarget
                .queryParam("nombre", nombre)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (response.getStatus() == 200) {

            consola = (Console) response.readEntity(Console.class);

        } else {
            System.out.println(response.getStatus());
        }
        return consola;
    }
    
    @Transactional
    @Override
    public Response actualizarDisponibilidad(String titulo) {
        WebTarget webTarget = client.target(BASE_URI).path("/videojuegos/actualizarDisponibilidad");

        Response response = webTarget
                .queryParam("titulo", titulo)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json("")); // Entity.json("") se utiliza para representar el cuerpo vacío para una solicitud POST
        return null;

    }
}
