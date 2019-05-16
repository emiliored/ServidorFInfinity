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
import javax.ws.rs.core.Context;
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

    @DELETE
    @Consumes(APPLICATION_JSON)
    public Response deleteAprecio(AprecioPK aprecioPK) {
        LOGGER.info("En deleteUsuario()");
        aprecioFacade.remove(aprecioPK);
        return Response.ok().build();
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    public Response putAprecio(@Context UriInfo uriInfo, Aprecio aprecio) {
        LOGGER.info("En putAprecio()");
        aprecioFacade.create(aprecio);
        UriBuilder uriBuilder = uriInfo.getRequestUriBuilder();
        URI uri = uriBuilder.path(Integer.toString(aprecio.getAprecioPK().getIdRecurso(), aprecio.getAprecioPK().getIdUsuario())).build();
        return Response.created(uri).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response postAprecio(Aprecio aprecio) {
        LOGGER.info("En postUsuario()");
        aprecioFacade.edit(aprecio);
        return Response.ok().build();
    }
}
