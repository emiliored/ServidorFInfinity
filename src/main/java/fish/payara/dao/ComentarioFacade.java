package fish.payara.dao;

import fish.payara.model.Comentario;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

@ApplicationScoped
public class ComentarioFacade extends AbstractFacade<Comentario> {
    
    @Inject
    private EntityManager entityManager;
    
    @Resource
    private UserTransaction userTransaction;
    
    private final static Logger LOGGER = Logger.getLogger(RecursoFacade.class.getName());

    public ComentarioFacade() {
        super(Comentario.class);
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
