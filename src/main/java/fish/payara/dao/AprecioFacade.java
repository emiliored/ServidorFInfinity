package fish.payara.dao;

import fish.payara.model.Aprecio;
import fish.payara.model.AprecioPK;
import fish.payara.model.Recurso;
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
public class AprecioFacade extends AbstractFacade<Aprecio> {
    
    @Inject
    private EntityManager entityManager;
    
    @Resource
    private UserTransaction userTransaction;
    
    private final static Logger LOGGER = Logger.getLogger(RecursoFacade.class.getName());

    public AprecioFacade() {
        super(Aprecio.class);
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
    
    public List<Aprecio> findUserResouces(AprecioPK id) {
    	LOGGER.info("En findUserResouces()");
        
    	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Aprecio> criteriaQuery = criteriaBuilder.createQuery(Aprecio.class);
        Root<Aprecio> root = criteriaQuery.from(Aprecio.class);
        criteriaQuery.select(root);
        Predicate predicate = criteriaBuilder.and(criteriaBuilder.not(criteriaBuilder.isNull(root.get("aprecio"))), criteriaBuilder.equal(root.get("aprecio"), entityManager.find(Aprecio.class, id)));
        criteriaQuery = criteriaQuery.where(predicate);
        TypedQuery<Aprecio> typeQuery = entityManager.createQuery(criteriaQuery);
        
        return typeQuery.getResultList();
    }
}
