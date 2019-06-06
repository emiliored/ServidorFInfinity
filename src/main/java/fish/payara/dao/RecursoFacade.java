package fish.payara.dao;

import fish.payara.model.Recurso;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

@ApplicationScoped
public class RecursoFacade extends AbstractFacade<Recurso> {

    @Inject
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    private final static Logger LOGGER = Logger.getLogger(RecursoFacade.class.getName());

    public RecursoFacade() {
        super(Recurso.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    protected UserTransaction getUserTransaction() {
        return userTransaction;
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

    public List<Recurso> findUserResouces(int idUsuario) {
        LOGGER.info("En findUserResouces()");
        TypedQuery<Recurso> query = entityManager.createQuery("SELECT R FROM Recurso r WHERE r.idUsuario=:idUsuario ORDER BY r.nombre",
                 Recurso.class);
        query.setParameter("idUsuario", idUsuario);
        return query.getResultList();
    }

    //Obtener los nombres de los recursos públicos que no tienen etiquetas de todos los usuarios.	SIN ETIQUETAR (Ordenado alfabéticamente por nombre)
    public List<Recurso> recursosSinEtiquetar() throws Exception {
        //Sentencia JQL
        TypedQuery<Recurso> query = entityManager.createQuery("SELECT R FROM Recurso r WHERE r.visibilidad=true AND R NOT IN(SELECT v.recurso FROM Visibilidad v) ORDER BY r.nombre",
                 Recurso.class);
                //.setMaxResults(20); //Número máximo de resultados.
        return query.getResultList();
    }

    //Obtener los recursos a partir de una etiqueta.
    public List<Recurso> recursosEtiqueta(String nombre) throws Exception {
        //Sentencia JQL
        TypedQuery<Recurso> query = entityManager.createQuery("SELECT v.recurso FROM Visibilidad v WHERE v.recurso.visibilidad=true AND v.etiqueta.etiquetaPK.nombre=:nombre",
                 Recurso.class)
                .setMaxResults(20); //Número máximo de resultados.
        query.setParameter("nombre", nombre);
        return query.getResultList();
    }

    //Obtener los recursos a partir de una etiqueta.
    public List<Recurso> recursosBuscarPorEtiqueta(String nombre) throws Exception {
        //Sentencia JQL
        TypedQuery<Recurso> query = entityManager.createQuery("SELECT v.recurso FROM Visibilidad v WHERE v.recurso.visibilidad=true AND v.etiqueta.etiquetaPK.nombre LIKE :nombre",
                 Recurso.class)
                .setMaxResults(20); //Número máximo de resultados.
        query.setParameter("nombre", "%"+nombre+"%");
        return query.getResultList();
    }
}
