package fish.payara.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Comentario.class)
public abstract class Comentario_ {

	public static volatile SingularAttribute<Comentario, String> texto;
	public static volatile SingularAttribute<Comentario, Recurso> recurso;
	public static volatile SingularAttribute<Comentario, Usuario> usuario;
	public static volatile SingularAttribute<Comentario, ComentarioPK> comentarioPK;

}

