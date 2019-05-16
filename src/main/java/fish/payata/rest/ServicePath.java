package fish.payata.rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

@ApplicationPath("servicios")
public class ServicePath extends Application{
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        //Clase para pasar ficheros binarios. (Subida)
        classes.add(MultiPartFeature.class);
        //Clases usadas.
        classes.add(AprecioFacadeREST.class);
        classes.add(ComentarioFacadeREST.class);
        classes.add(EtiquetaFacadeREST.class);
        classes.add(RecursoFacadeREST.class);
        classes.add(UsuarioFacadeREST.class);
        classes.add(VisibilidadFacadeREST.class);
        return classes;
    }
}
