package fish.payara.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Recurso.class)
public abstract class Recurso_ {

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

