package fish.payata.rest;

import fish.payara.dao.VisibilidadFacade;
import fish.payara.model.EtiquetaPK;
import fish.payara.model.Visibilidad;
import fish.payara.model.VisibilidadPK;
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
@Path("visibilidad")
public class VisibilidadFacadeREST {

    @Inject
    private VisibilidadFacade visibilidadFacade;

    private final static Logger LOGGER = Logger.getLogger(VisibilidadFacadeREST.class.getName());

    @GET
    @Produces(APPLICATION_JSON)
    public List<Visibilidad> findAll() {
        LOGGER.info("En findAll()");
        return visibilidadFacade.findAll();
    }
    /*
    @GET
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Response getVisibilidad(VisibilidadPK visibilidadPK) {
        LOGGER.info("En getUsuario()");
        Visibilidad visibilidad = visibilidadFacade.find(visibilidadPK);
        return Response.ok(visibilidad).build();
    }
    */

    @DELETE
    @Consumes(APPLICATION_JSON)
    public Response deleteVisibilidad(EtiquetaPK id) {
        LOGGER.info("En deleteVisibilidad()");
        visibilidadFacade.remove(id);
        return Response.ok().build();
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    public Response putVisibilidad(@Context UriInfo uriInfo, Visibilidad visibilidad) {
        LOGGER.info("En putVisibilidad()");
        visibilidadFacade.create(visibilidad);
        UriBuilder uriBuilder = uriInfo.getRequestUriBuilder();
        URI uri = uriBuilder.path(Integer.toString(visibilidad.getVisibilidadPK().getIdUsuario(), visibilidad.getVisibilidadPK().getIdRecurso())).build();
        return Response.created(uri).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response postVisibilidad(Visibilidad visibilidad) {
        LOGGER.info("En postVisibilidad()");
        visibilidadFacade.edit(visibilidad);
        return Response.ok().build();
    }
}
