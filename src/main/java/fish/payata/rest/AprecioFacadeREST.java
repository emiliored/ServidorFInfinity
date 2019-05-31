package fish.payata.rest;

import fish.payara.dao.AprecioFacade;
import fish.payara.model.Aprecio;
import fish.payara.model.AprecioPK;
import java.net.URI;
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
import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
@Path("aprecio")
public class AprecioFacadeREST {

    @Inject
    private AprecioFacade aprecioFacade;

    private final static Logger LOGGER = Logger.getLogger(AprecioFacadeREST.class.getName());

    @GET
    @Produces(APPLICATION_JSON)
    public List<Aprecio> findAll() {
        LOGGER.info("En enfonfAll()");
        return aprecioFacade.findAll();
    }
    /*
    @GET
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Response getAprecio(AprecioPK aprecioPK) {
        LOGGER.info("En getUsuario()");
        Aprecio aprecio = aprecioFacade.find(aprecioPK);
        return Response.ok(aprecio).build();
    }
    */
    @GET
    @Path("likes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLikes(@QueryParam("idUsuario") int idUsuario,@QueryParam("idRecurso") int idRecurso) {
        LOGGER.info("En getLikes()");
        try{
            return Response.ok(aprecioFacade.obtenerLikes(idUsuario, idRecurso)).build();
        } catch(Exception e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putAprecio(Aprecio a) {
        LOGGER.info("En putAprecio()");
        try{
            aprecioFacade.create(a);
            return Response.status(Response.Status.CREATED).build();
        } catch(Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response postAprecio(Aprecio aprecio) {
        LOGGER.info("En postAprecio()");
        aprecioFacade.edit(aprecio);
        return Response.ok().build();
    }
    
    @DELETE
    public Response deleteAprecio(@QueryParam("idUsuario") int idUsuario, @QueryParam("idRecurso") int idRecurso) {
        LOGGER.info("En deleteAprecio()");
        try{
            aprecioFacade.remove(new AprecioPK(idUsuario,idRecurso));
            return Response.ok().build();
        } catch(Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
