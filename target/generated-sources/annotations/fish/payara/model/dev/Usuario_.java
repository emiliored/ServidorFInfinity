package fish.payara.model.dev;

import fish.payara.model.Aprecio;
import fish.payara.model.Comentario;
import fish.payara.model.Etiqueta;
import fish.payara.model.Recurso;
import fish.payara.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-16T20:29:14")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> apodo;
    public static volatile SingularAttribute<Usuario, String> apellidos;
    public static volatile ListAttribute<Usuario, Recurso> recursoList;
    public static volatile ListAttribute<Usuario, Etiqueta> etiquetaList;
    public static volatile ListAttribute<Usuario, Comentario> comentarioList;
    public static volatile SingularAttribute<Usuario, Integer> idUsuario;
    public static volatile SingularAttribute<Usuario, String> contrasena;
    public static volatile SingularAttribute<Usuario, String> usersalt;
    public static volatile ListAttribute<Usuario, Aprecio> aprecioList;
    public static volatile SingularAttribute<Usuario, String> nombre;

}