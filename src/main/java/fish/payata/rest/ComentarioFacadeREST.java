package fish.payata.rest;

import fish.payara.dao.ComentarioFacade;
import fish.payara.model.AprecioPK;
import fish.payara.model.Comentario;
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
import javax.ws.rs.core.Context;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
@Path("comentario")
public class ComentarioFacadeREST {

    @Inject
    private ComentarioFacade comentarioFacade;

    private final static Logger LOGGER = Logger.getLogger(ComentarioFacadeREST.class.getName());

    @GET
    @Produces(APPLICATION_JSON)
    public List<Comentario> findAll() {
        LOGGER.info("En enfonfAll()");
        return comentarioFacade.findAll();
    }
    
    /*
    @GET
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Response getComentario(AprecioPK aprecioPK) {
        LOGGER.info("En getUsuario()");
        Comentario comentario = comentarioFacade.find(aprecioPK);
        return Response.ok(comentario).build();
    }
    */

    @DELETE
    @Consumes(APPLICATION_JSON)
    public Response deleteComentario(AprecioPK id) {
        LOGGER.info("En deleteUsuario()");
        comentarioFacade.remove(id);
        return Response.ok().build();
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    public Response putComentario(@Context UriInfo uriInfo, Comentario comentario) {
        LOGGER.info("En putAprecio()");
        comentarioFacade.create(comentario);
        UriBuilder uriBuilder = uriInfo.getRequestUriBuilder();
        URI uri = uriBuilder.path(Integer.toString(comentario.getComentarioPK().getIdRecurso(), comentario.getComentarioPK().getIdUsuario())).build();
        return Response.created(uri).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response postComentario(Comentario comentario) {
        LOGGER.info("En postUsuario()");
        comentarioFacade.edit(comentario);
        return Response.ok().build();
    }
}
