package deim.urv.cat.homework2.service;
import deim.urv.cat.homework2.model.User;
import deim.urv.cat.homework2.model.Game;
import jakarta.ws.rs.core.Response;
import java.util.List;


    public interface UserService {

    public Response getAlquiler(int alquilerId);

    public String crearAlquiler(int usuarioId, RespuestaAlquiler jsonAlquiler);

    public Response crearVideojuego(RespuestaVideojuego videojuego);

    public List<RespuestaUsuario> obtenerListaUsuarios();

    public Response obtenerUsuario(int usuarioId);

    public List<Game> obtenerVideojuegos(String tipo, String videoconsola);

    public String autorizacionUsuario(String id, String clave);

    public Response modificarUsuario(int usuarioId, String nombre, String nuevaClave);
    
    public User getUserByName(String nombre, String clave); 
    
    public Response actualizarDisponibilidad(String titulo);
}


