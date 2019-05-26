package fish.payara.model.dev;

import fish.payara.model.Aprecio;
import fish.payara.model.Comentario;
import fish.payara.model.Recurso;
import fish.payara.model.Usuario;
import fish.payara.model.Visibilidad;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-23T17:16:55")
@StaticMetamodel(Recurso.class)
public class Recurso_ { 

    public static volatile SingularAttribute<Recurso, String> descripcion;
    public static volatile SingularAttribute<Recurso, String> ruta;
    public static volatile ListAttribute<Recurso, Comentario> comentarioList;
    public static volatile SingularAttribute<Recurso, Integer> idUsuario;
    public static volatile SingularAttribute<Recurso, Integer> idRecurso;
    public static volatile ListAttribute<Recurso, Aprecio> aprecioList;
    public static volatile SingularAttribute<Recurso, Usuario> usuario;
    public static volatile SingularAttribute<Recurso, String> filehash;
    public static volatile ListAttribute<Recurso, Visibilidad> visibilidadList;
    public static volatile SingularAttribute<Recurso, String> nombre;
    public static volatile SingularAttribute<Recurso, Boolean> visibilidad;

}