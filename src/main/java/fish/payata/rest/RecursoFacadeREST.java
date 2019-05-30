package fish.payata.rest;

import fish.payara.clases.Cifrado;
import fish.payara.dao.RecursoFacade;
import fish.payara.model.Etiqueta;
import fish.payara.model.EtiquetaPK;
import fish.payara.model.Recurso;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("recurso")
public class RecursoFacadeREST {

    //Carpeta donde se guardarán los archivos en el servidor.
    private static final String CARPETASERVIDOR = "/home/usuario/Escritorio/ArchivosServidor/";

    @Inject
    private RecursoFacade recursoFacade;

    private final static Logger LOGGER = Logger.getLogger(RecursoFacadeREST.class.getName());

    //Obtener los nombres de los recursos públicos de todos los usuarios.	SIN ETIQUETAR (Ordenado alfabéticamente por nombre)
    @GET
    @Path("sinetiquetar")
    @Produces(APPLICATION_JSON)
    public Response findRecursos() {
        LOGGER.info("En findRecursos()");
        try {
            return Response.ok(recursoFacade.recursosSinEtiquetar()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    
    @GET
    @Produces(APPLICATION_JSON)
    public Response getRecursos(@DefaultValue("-1") @QueryParam("idUsuario") int idUsuario) {
        LOGGER.info("En getRecursos()");

        List<Recurso> listRecursos;

        if (idUsuario != -1) {
            listRecursos = recursoFacade.findUserResouces(idUsuario);
        } else {
            listRecursos = recursoFacade.findAll();
        }
        return Response.ok(listRecursos).build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("{id_recurso : \\d+}")
    public Response getRecurso(@PathParam("id_recurso") int id_recurso) {
        LOGGER.info("En getRecurso()");
        Recurso recurso = recursoFacade.find(id_recurso);
        return Response.ok(recurso).build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("{desde : \\d+}/{hasta : \\d+}")
    public Response getRecursosDesdeHasta(@PathParam("desde") int from, @PathParam("hasta") int to) {
        LOGGER.info("En getRecursosDesdeHasta()");
        List<Recurso> listRecursos = recursoFacade.findRange(from, to);
        return Response.ok(listRecursos).build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("etiqueta")
    public Response getRecursosEtiqueta(@QueryParam("nombre") String nombre) {
        LOGGER.info("En getRecursosEtiqueta()");
        try {
            return Response.ok(recursoFacade.recursosEtiqueta(nombre)).build();
        } catch (Exception ex) {
            Logger.getLogger(RecursoFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    
    @GET
    @Produces(APPLICATION_JSON)
    @Path("buscar")
    public Response getRecursosBuscarPorEtiqueta(@QueryParam("nombre") String nombre) {
        LOGGER.info("En getRecursosBuscarPorEtiqueta()");
        try {
            return Response.ok(recursoFacade.recursosBuscarPorEtiqueta(nombre)).build();
        } catch (Exception ex) {
            Logger.getLogger(RecursoFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    /*
	@DELETE
	@Path("{idUsuario : \\d+}")
	public Response deleteRecurso(@PathParam("idUsuario") int id_recurso) {
		LOGGER.info("-En deleteRecurso()");
		recursoFacade.remove(id_recurso);
		return Response.ok().build();
	}

	@PUT
	@Consumes(APPLICATION_JSON)
	public Response putRecurso(@Context UriInfo uriInfo, Recurso recurso) {
		LOGGER.info("En putRecurso()");
		recurso.setIdRecurso(0);
		recursoFacade.create(recurso);
		UriBuilder uriBuilder = uriInfo.getRequestUriBuilder();
		URI uri = uriBuilder.path(Integer.toString(recurso.getIdRecurso())).build();
		return Response.created(uri).build();
	}

	@POST
	@Consumes(APPLICATION_JSON)
	public Response postRecurso(Recurso recurso) {
		LOGGER.info("En postRecurso()");
		recursoFacade.edit(recurso);
		return Response.ok().build();
	}
     */
    //Descargar fichero binario.
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("descargar")
    public Response descargarFichero(@QueryParam("idRecurso") int idRecurso) {
        LOGGER.info("En descargarFichero()");
        try {
            Recurso r = recursoFacade.find(idRecurso);
            InputStream is = new FileInputStream(new File(r.getRuta()));
            return Response.ok(is, MediaType.APPLICATION_OCTET_STREAM)
                    .build();
        } catch (Exception e) {
            //Error
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    //Subir fichero binario - Simple. Máximo 512 MBytes.
    @POST
    @Path("subir")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public Response subirFichero(
            //Campos necesarios para la creación de un objeto Recurso.
            @QueryParam("idUsuario") int idUsuario,
            @QueryParam("nombre") String nombre,
            @QueryParam("descripcion") String descripcion,
            @QueryParam("visibilidad") boolean visibilidad,
            InputStream is) {
        //Fichero a guardar.
        File fichero = new File(CARPETASERVIDOR, nombre);
        try {
            //Guardar el fichero en disco.
            if (!guardar(is, fichero)) {
                throw new IOException("No se ha podido guardar el fichero.");
            }
            //Guardar el recurso en la base de datos.
            Recurso r = new Recurso(idUsuario, null, nombre, descripcion, Cifrado.createSha1(fichero), fichero.toString(), visibilidad);
            recursoFacade.create(r);
            LOGGER.info("En subirFichero() " + fichero.toString());
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).build();
        }

    }

//    //Subir fichero binario 
//    @POST
//    @Path("subirAvanzado")
//    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
//    public Response subirFicheroAvanzado(
//            //Campos necesarios para la creación de un objeto Recurso.
//            @QueryParam("idUsuario") int idUsuario,
//            @QueryParam("nombre") String nombre,
//            @QueryParam("descripcion") String descripcion,
//            @QueryParam("visibilidad") boolean visibilidad,
//            InputStream is) {
//        //Fichero a guardar.
//        File fichero = new File(CARPETASERVIDOR, nombre);
//        try {
//            //Guardar el fichero en disco.
//            if (!guardar(is, fichero)) {
//                throw new IOException("No se ha podido guardar el fichero.");
//            }
//            //Guardar el recurso en la base de datos.
//            Recurso r = new Recurso(idUsuario, null, nombre, descripcion, Cifrado.createSha1(fichero), fichero.toString(), visibilidad);
//            recursoFacade.create(r);
//            LOGGER.info("En subirFichero() " + fichero.toString());
//            return Response.status(Response.Status.CREATED).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.CONFLICT).build();
//        }
//
//    }

    /**
     * Método que intentará borrar el recurso y su fichero binario asociado, si
     * existe en el servidor; si no borrará sólo el recurso y saltará una
     * excepción.
     *
     * @param idRecurso
     * @return La respuesta HTTP.
     */
    @DELETE
    @Path("borrar")
    public Response borrarFichero(@QueryParam("idRecurso") int idRecurso) {
        try {
            Recurso r = recursoFacade.find(idRecurso);
            recursoFacade.remove(idRecurso);
            Files.delete(Paths.get(r.getRuta()));
        } catch (IOException ioe) {
            //El fichero no existe.
        } catch (Exception ex) {
            Logger.getLogger(RecursoFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
        return Response.ok().build();
    }

    //Guardar el fichero en disco. (Servidor)
    private static boolean guardar(InputStream is, File fichero) {
        boolean boo = true;
        try (OutputStream os = new FileOutputStream(fichero)) {
            int longitud;
            byte[] buffer = new byte[1024];
            while ((longitud = is.read(buffer)) >= 0) {
                os.write(buffer, 0, longitud);
            }
            os.flush();
        } catch (Exception e) {
            boo = false;
        }
        return boo;
    }

}
