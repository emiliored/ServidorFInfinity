package fish.payata.rest;

import fish.payara.dao.UsuarioFacade;
import fish.payara.model.Usuario;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
@Path("usuario")
public class UsuarioFacadeREST {

    @Inject
    private UsuarioFacade usuarioFacade;

    private final static Logger LOGGER = Logger.getLogger(UsuarioFacadeREST.class.getName());

    @GET
    @Path("count")
    @Produces(TEXT_PLAIN)
    public long countREST() {
        return usuarioFacade.count();
    }

    @GET
    @Produces(APPLICATION_JSON)
    public List<Usuario> findAll() {
        LOGGER.info("En enfonfAll()");
        return usuarioFacade.findAll();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("{idUsuario : \\d+}")
    public Response getUsuario(@PathParam("idUsuario") int id_usuario) {
        LOGGER.info("En getUsuario()");
        Usuario usuario = usuarioFacade.find(id_usuario);
        return Response.ok(usuario).build();
    }

    @DELETE
    @Path("{idUsuario : \\d+}")
    public Response deleteUsuario(@PathParam("idUsuario") int id_usuario) {
        LOGGER.info("En deleteUsuario()");
        usuarioFacade.remove(id_usuario);
        return Response.ok().build();
    }

    /*@PUT
    @Consumes(APPLICATION_JSON)
    public Response putUsuario(@Context UriInfo uriInfo, Usuario usuario) {
        LOGGER.info("En putUsuario()");
        //usuario.setIdUsuario(0);
        usuarioFacade.create(usuario);
        UriBuilder uriBuilder = uriInfo.getRequestUriBuilder();
        URI uri = uriBuilder.path(Integer.toString(usuario.getIdUsuario())).build();
        return Response.created(uri).build();
    }*/

    //Login de un usuario:
    @Path("login")
    @GET
    public Response loginUsuario(@QueryParam("apodo")String apodo,@QueryParam("contrasena")String contrasena) {
        LOGGER.info("En loginUsuario()");
        try {
            Usuario u=usuarioFacade.getUsuario(apodo);
            if(!u.getContrasena().equals(Cifrado.getEncryptedPassword(contrasena, u.getUsersalt())))
                throw new BadPasswordException("Contraseña inválida.");
        } catch(BadPasswordException bpe){
            return Response.status(Status.NO_CONTENT).build();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Status.NOT_ACCEPTABLE).build();
        }
        return Response.status(Status.ACCEPTED).build();
    }
    
    //Registro de un usuario:
    @Path("registro")
    @PUT
    @Consumes(APPLICATION_JSON)
    public Response createUsuario(Usuario usuario) {
        LOGGER.info("En createUsuario()");
        try {
            usuario.setUsersalt(Cifrado.getNewSalt());
            usuario.setContrasena(Cifrado.getEncryptedPassword(usuario.getContrasena(),usuario.getUsersalt()));
            usuarioFacade.create(usuario);
        }catch(NoResultException nre) {
            //El usuario a introducido un apodo incorrecto.
            return Response.status(Status.NOT_ACCEPTABLE).build();
        }catch (Exception ex) {
            Logger.getLogger(UsuarioFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Status.NOT_ACCEPTABLE).build();
        }
        return Response.status(Status.CREATED).build();
    }
    
    
    @POST
    @Consumes(APPLICATION_JSON)
    public Response postUsuario(Usuario usuario) {
        LOGGER.info("En postUsuario()");
        usuarioFacade.edit(usuario);
        return Response.ok().build();
    }

    /*@GET
    @Produces(APPLICATION_JSON)
    @Path("{apodo : \\d+}/{contrasena : \\d+}")
    public Usuario getLogin(@PathParam("apodo") String apodo, @PathParam("contrasena") String contrasena) {
        LOGGER.info("En ()");
        Usuario usuario = usuarioFacade.login(apodo, contrasena);
        return usuario;
    }*/
}
