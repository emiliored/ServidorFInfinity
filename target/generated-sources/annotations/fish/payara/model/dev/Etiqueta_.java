package fish.payara.model.dev;

import fish.payara.model.Etiqueta;
import fish.payara.model.EtiquetaPK;
import fish.payara.model.Usuario;
import fish.payara.model.Visibilidad;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-31T18:08:31")
@StaticMetamodel(Etiqueta.class)
public class Etiqueta_ { 

    public static volatile SingularAttribute<Etiqueta, Usuario> usuario;
    public static volatile SingularAttribute<Etiqueta, EtiquetaPK> etiquetaPK;
    public static volatile ListAttribute<Etiqueta, Visibilidad> visibilidadList;

}