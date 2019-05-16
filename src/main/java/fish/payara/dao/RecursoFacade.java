package fish.payara.dao;

import fish.payara.model.Recurso;
import fish.payara.model.Usuario;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
        
    	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Recurso> criteriaQuery = criteriaBuilder.createQuery(Recurso.class);
        Root<Recurso> root = criteriaQuery.from(Recurso.class);
        criteriaQuery.select(root);
        Predicate predicate = criteriaBuilder.and(criteriaBuilder.not(criteriaBuilder.isNull(root.get("usuario"))), criteriaBuilder.equal(root.get("usuario"), entityManager.find(Usuario.class, idUsuario)));
        criteriaQuery = criteriaQuery.where(predicate);
        TypedQuery<Recurso> typeQuery = entityManager.createQuery(criteriaQuery);
        
        return typeQuery.getResultList();
    }
    /* 
    //Obtener el recurso a partir de su hash.
    public Recurso getRecurso(String hash) throws Exception{
       
        LOGGER.info("En getRecurso()");

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Recurso> criteriaQuery = criteriaBuilder.createQuery(Recurso.class);
        Root<Recurso> root = criteriaQuery.from(Recurso.class);
        
        criteriaQuery.select(root);
        criteriaQuery.where(
                criteriaBuilder.equal(root.get("filehash"), hash)
        );
        
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }*/
}
