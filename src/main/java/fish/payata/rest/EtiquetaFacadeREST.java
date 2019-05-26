package fish.payata.rest;

import fish.payara.dao.EtiquetaFacade;
import fish.payara.dao.VisibilidadFacade;
import fish.payara.model.Etiqueta;
import fish.payara.model.EtiquetaPK;
import fish.payara.model.Visibilidad;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
@Path("etiqueta")
public class EtiquetaFacadeREST {

    @Inject
    private EtiquetaFacade etiquetaFacade;

    private final static Logger LOGGER = Logger.getLogger(EtiquetaFacadeREST.class.getName());

    @GET
    @Produces(APPLICATION_JSON)
    public List<Etiqueta> findAll() {
        LOGGER.info("En enfonfAll()");
        return etiquetaFacade.findAll();
    }

    //Obtener las etiquetas públicas del usuario:   PÚBLICAS (Ordenado alfabéticamente por nombre)   
    @GET
    @Path("publicas")
    @Produces(APPLICATION_JSON)
    public Response findPublicasUsuario(@QueryParam("idUsuario")int idUsuario) {
        LOGGER.info("En findPublicasUsuario()");
        try{
            return Response.ok(etiquetaFacade.etiquetasPublicasUsuario(idUsuario)).build();
        } catch (Exception e){
            //e.printStackTrace();
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    //Obtener las etiquetas privadas del usuario:   PRIVADAS (Ordenado alfabéticamente por nombre)
    @GET
    @Path("privadas")
    @Produces(APPLICATION_JSON)
    public Response findPrivadasUsuario(@QueryParam("idUsuario")int idUsuario) {
        LOGGER.info("En findPrivadasUsuario()");
        try{
            return Response.ok(etiquetaFacade.etiquetasPrivadasUsuario(idUsuario)).build();
        } catch (Exception e){
            //e.printStackTrace();
            return Response.status(Status.BAD_REQUEST).build();
        }
    }
    
    //Obtener las etiquetas publicas de todos los usuarios:	NOVEDADES
    @GET
    @Path("novedades")
    @Produces(APPLICATION_JSON)
    public Response findNovedades() {
        LOGGER.info("En findNovedades()");
        try{
            return Response.ok(etiquetaFacade.etiquetasNovedades()).build();
        } catch (Exception e){
            e.printStackTrace();
            return Response.status(Status.BAD_REQUEST).build();
        }
    }
    
    //Obtener las etiquetas publicas de todos los usuarios:	GENERALES (Ordenado alfabéticamente por nombre)
    @GET
    @Path("generales")
    @Produces(APPLICATION_JSON)
    public Response findGenerales() {
        LOGGER.info("En findPublicasGenerales()");
        try{
            return Response.ok(etiquetaFacade.etiquetasGenerales()).build();
        } catch (Exception e){
            //e.printStackTrace();
            return Response.status(Status.BAD_REQUEST).build();
        }
    }
    
    //Obtener las etiquetas publicas de todos los usuarios:	GENERALES (Ordenado alfabéticamente por nombre)
    @GET
    @Path("valoradas")
    @Produces(APPLICATION_JSON)
    public Response findValoradas() {
        LOGGER.info("En findValoradas()");
        try{
            return Response.ok(etiquetaFacade.etiquetasValoradas()).build();
        } catch (Exception e){
            e.printStackTrace();
            return Response.status(Status.BAD_REQUEST).build();
        }
    }
    
    @DELETE
    @Consumes(APPLICATION_JSON)
    public Response deleteEtiqueta(EtiquetaPK etiquetaPk) {
        LOGGER.info("En deleteUsuario()");
        etiquetaFacade.remove(etiquetaPk);
        return Response.ok().build();
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    public Response putEtiqueta(@Context UriInfo uriInfo, Etiqueta etiqueta) {
        LOGGER.info("En putAprecio()");
        etiquetaFacade.create(etiqueta);
        UriBuilder uriBuilder = uriInfo.getRequestUriBuilder();
        URI uri = uriBuilder.path(Integer.toString(etiqueta.getEtiquetaPK().getIdUsuario()).concat(etiqueta.getEtiquetaPK().getNombre())).build();
        return Response.created(uri).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response postEtiqueta(Etiqueta etiqueta) {
        LOGGER.info("En postUsuario()");
        etiquetaFacade.edit(etiqueta);
        return Response.ok().build();
    }
}
