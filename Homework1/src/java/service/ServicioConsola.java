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
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import model.entities.VideoConsola;

/**
 *
 * @author nicol
 */
@Stateless
@Path("/consoles")
public class ServicioConsola extends AbstractFacade<VideoConsola> {
    
    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;
    public ServicioConsola() {
        super(VideoConsola.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @GET
    @Path("/getVideoconsola")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVideoConsolas(@QueryParam("nombre") String nombre){
        VideoConsola videoConsola = (VideoConsola) em.createQuery("SELECT v FROM VideoConsola v WHERE v.id = (SELECT j.videoconsola.id FROM Videojuego j WHERE j.titulo = '" + nombre + "')", VideoConsola.class)
                .getSingleResult();
        if(videoConsola==null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else{  
            return Response.ok().entity(videoConsola).build();
        }
    }
    
}