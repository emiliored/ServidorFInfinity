package fish.payara.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Aprecio.class)
public abstract class Aprecio_ {

	public static volatile SingularAttribute<Aprecio, Recurso> recurso;
	public static volatile SingularAttribute<Aprecio, Date> fecha;
	public static volatile SingularAttribute<Aprecio, AprecioPK> aprecioPK;
	public static volatile SingularAttribute<Aprecio, Usuario> usuario;

}

