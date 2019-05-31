package fish.payata.rest;

import fish.payara.clases.cliente.UsuarioCliente;
import fish.payara.clases.BadPasswordException;
import fish.payara.clases.Cifrado;
import fish.payara.dao.UsuarioFacade;
import fish.payara.model.Usuario;
import java.util.List;
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
import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
        LOGGER.info("En count()");
        return usuarioFacade.count();
    }

    @GET
    @Produces(APPLICATION_JSON)
    public List<Usuario> findAll() {
        LOGGER.info("En findAll()");
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
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("apodo")
    public Response getApodoUsuario(@QueryParam("idUsuario") int idUsuario) {
        LOGGER.info("En getApodoUsuario()");
        try{
            return Response.ok(usuarioFacade.getApodoUsuario(idUsuario)).build();
        } catch(Exception e){
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    //TODO Hacer borrado de usuarios y recursos.
    @DELETE
    @Path("{idUsuario : \\d+}")
    public Response deleteUsuario(@PathParam("idUsuario") int id_usuario) {
        LOGGER.info("En deleteUsuario()");
        usuarioFacade.remove(id_usuario);
        return Response.ok().build();
    }

    //Login de un usuario:
    @Path("login")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUsuario(@QueryParam("apodo")String apodo,@QueryParam("contrasena")String contrasena) {
        LOGGER.info("En loginUsuario()");
        Usuario u;
        try {
            u=usuarioFacade.getUsuario(apodo);
            if(!u.getContrasena().equals(Cifrado.getEncryptedPassword(contrasena, u.getUsersalt())))
                throw new BadPasswordException("Contraseña inválida.");
            UsuarioCliente uc=usuarioFacade.getUsuarioCliente(u.getIdUsuario(),u.getApodo());
            System.out.println(uc.toString());
            return Response.status(Status.ACCEPTED).entity(uc).build();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Status.NOT_ACCEPTABLE).build();
        }
    }
    
    //Registro de un usuario:
    @Path("registro")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUsuario(Usuario usuario) {
        LOGGER.info("En createUsuario()");
        try {
            usuario.setUsersalt(Cifrado.getNewSalt());
            usuario.setContrasena(Cifrado.getEncryptedPassword(usuario.getContrasena(),usuario.getUsersalt()));
            usuarioFacade.createUsuario(usuario);
            return Response.status(Status.CREATED).entity(new UsuarioCliente(usuario.getIdUsuario(),usuario.getApodo())).build();
        }catch (Exception ex) {
            //Logger.getLogger(UsuarioFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Status.NOT_ACCEPTABLE).build();
        }
    }
    
    //Modificar la contraseña de un usuario:
    @POST
    public Response postUsuario(@QueryParam("apodo")String apodo,@QueryParam("contrasena")String contrasena) {
        LOGGER.info("En postUsuario()");
        try{
            Usuario u=usuarioFacade.getUsuario(apodo);
            u.setContrasena(Cifrado.getEncryptedPassword(contrasena,u.getUsersalt()));
            usuarioFacade.edit(u);
        } catch (Exception e){
            Logger.getLogger(UsuarioFacadeREST.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(Status.NOT_MODIFIED).build();
        }
        return Response.ok().build();
    }

}
