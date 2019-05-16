package fish.payara.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

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

