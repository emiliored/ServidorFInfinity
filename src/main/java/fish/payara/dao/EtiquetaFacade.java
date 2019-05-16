package fish.payara.dao;

import fish.payara.model.Etiqueta;
import fish.payara.model.Usuario;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

@ApplicationScoped
public class EtiquetaFacade extends AbstractFacade<Etiqueta> {
    
    @Inject
    private EntityManager entityManager;
    
    @Resource
    private UserTransaction userTransaction;
    
    private final static Logger LOGGER = Logger.getLogger(RecursoFacade.class.getName());

    public EtiquetaFacade() {
        super(Etiqueta.class);
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
    
    
    
}
