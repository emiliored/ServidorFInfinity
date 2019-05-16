package fish.payata.rest;

import fish.payara.dao.RecursoFacade;
import fish.payara.model.Recurso;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;


@ApplicationScoped
@Path("recurso")
public class RecursoFacadeREST {

    //Carpeta donde se guardarán los archivos en el servidor.
    //private static final String CARPETASERVIDOR="/home/usuario/Escritorio/ArchivosServidor/";
    
    @Inject
    private RecursoFacade recursoFacade;
    
    private final static Logger LOGGER = Logger.getLogger(RecursoFacadeREST.class.getName());

	/*@GET
	@Produces(APPLICATION_JSON)
	public Response getRecursos(@DefaultValue("-1") @QueryParam("id_usuario") int id_usuario) {
		LOGGER.info("En getRecursos()");
                
		List<Recurso> listRecursos;
                
		if (id_usuario != -1)
			listRecursos = recursoFacade.findUserResouces(id_usuario);
		else
			listRecursos = recursoFacade.findAll();
		return Response.ok(listRecursos).build();	
	}*/
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<Recurso> getRecursos(@DefaultValue("-1") @QueryParam("id_usuario") int id_usuario){
            
            LOGGER.info("En getRecursosModificado()");
            
            List<Recurso> listRecursos;
            
            if(id_usuario !=-1)
                listRecursos=recursoFacade.findUserResouces(id_usuario);
            else
                listRecursos=recursoFacade.findAll();
            return listRecursos;
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
        
        //Modificaciones
        //Descargar fichero binario.
        @GET
        @Produces(MediaType.APPLICATION_OCTET_STREAM)
        @Path("descargar")
        public Response descargarFichero(@QueryParam("idRecurso")int idRecurso) {
            
            try{
                Recurso r=recursoFacade.find(idRecurso);
                InputStream is=new FileInputStream(new File(r.getRuta()));
                return Response.ok(is,MediaType.APPLICATION_OCTET_STREAM)
                    .build();
            } catch(Exception e){
                //Error
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        
        //Subir fichero binario - Simple. Máximo 512 MBytes.
        @POST
        @Path("subida")
        @Consumes(MediaType.APPLICATION_OCTET_STREAM)
        public Response subirFichero(
                //Campos necesarios para la creación de un objeto Recurso.
                @QueryParam("idUsuario")int idUsuario,
                @QueryParam("nombre")String nombre,
                @QueryParam("descripcion")String descripcion,
                @QueryParam("visibilidad")boolean visibilidad,
                InputStream is){
            //Carpeta del servidor.
            File fichero=new File("/home/usuario/Escritorio/ArchivosServidor/",nombre);
            //Guardar el fichero en disco.
            if(guardar(is,fichero)){
                try{
                    Recurso r=new Recurso(idUsuario,null,nombre,descripcion,Cifrado.createSha1(fichero),fichero.toString(),visibilidad);
                    System.out.println(fichero.toString());
                    recursoFacade.create(r);
                }catch(Exception e){
                    return Response.status(Response.Status.CONFLICT).build();
                }
                return Response.status(Response.Status.CREATED).build();
            }else{
                    if(fichero.exists())
                        fichero.delete();
                return Response.status(Response.Status.CONFLICT).build();
            }
        }
        
        /*
        //Subir fichero binario.
        @POST
        @Path("subida")
        @Consumes(MediaType.MULTIPART_FORM_DATA)
        public Response subirFichero(
            //Campos necesarios para la creación de un objeto Recurso.
            @QueryParam("idUsuario")int idUsuario,
            @QueryParam("descripcion")String descripcion,
            @QueryParam("visibilidad")boolean visibilidad,
            //Campos que pasan los datos binarios.
            @FormDataParam("file") InputStream is,
            @FormDataParam("file") FormDataContentDisposition fileDetail){
            //Carpeta del servidor.
            File fichero=new File("/home/usuario/Escritorio/ArchivosServidor/",fileDetail.getFileName());
            //Guardar el fichero en disco.
            if(guardar(is,fichero)){
                try{
                  //  Recurso r=new Recurso(idUsuario,null,fileDetail.getFileName(),descripcion,Cifrado.createSha1(fichero.toFile()),fichero.toString(),visibilidad);
                  //  recursoFacade.create(r);
                }catch(Exception e){
                    return Response.status(Response.Status.CONFLICT).build();
                }
                return Response.status(Response.Status.CREATED).build();
            }else{
                    if(fichero.exists())
                        fichero.delete();
                return Response.status(Response.Status.CONFLICT).build();
            }
            
        }*/
        
        //Guardar el fichero en disco. (Servidor)
        private static boolean guardar(InputStream is,File fichero){
            boolean boo=true;
            try(OutputStream os=new FileOutputStream(fichero)){
                int longitud;
                byte[] buffer=new byte[1024];
                while((longitud=is.read(buffer))>=0)
                    os.write(buffer, 0, longitud);
                os.flush();
            }catch (Exception e){
                boo=false;
            }
            return boo;
        }
        
}
