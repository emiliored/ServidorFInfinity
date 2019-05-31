package fish.payara.model.dev;

import fish.payara.model.Etiqueta;
import fish.payara.model.Recurso;
import fish.payara.model.Visibilidad;
import fish.payara.model.VisibilidadPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-31T18:08:31")
@StaticMetamodel(Visibilidad.class)
public class Visibilidad_ { 

    public static volatile SingularAttribute<Visibilidad, Recurso> recurso;
    public static volatile SingularAttribute<Visibilidad, Etiqueta> etiqueta;
    public static volatile SingularAttribute<Visibilidad, VisibilidadPK> visibilidadPK;
    public static volatile SingularAttribute<Visibilidad, Boolean> visibilidad;

}