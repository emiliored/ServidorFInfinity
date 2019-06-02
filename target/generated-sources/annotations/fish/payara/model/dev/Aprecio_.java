package fish.payara.model.dev;

import fish.payara.model.Aprecio;
import fish.payara.model.AprecioPK;
import fish.payara.model.Recurso;
import fish.payara.model.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-31T19:34:23")
@StaticMetamodel(Aprecio.class)
public class Aprecio_ { 

    public static volatile SingularAttribute<Aprecio, Recurso> recurso;
    public static volatile SingularAttribute<Aprecio, Date> fecha;
    public static volatile SingularAttribute<Aprecio, AprecioPK> aprecioPK;
    public static volatile SingularAttribute<Aprecio, Usuario> usuario;

}