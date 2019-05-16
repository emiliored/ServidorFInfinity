package fish.payara.dao;

import fish.payara.model.Usuario;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

@ApplicationScoped
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @Inject
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    private final static Logger LOGGER = Logger.getLogger(RecursoFacade.class.getName());

    public UsuarioFacade() {
        super(Usuario.class);
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

    /*
    public Usuario login(String apodo, String contraseña) {
        LOGGER.info("En findUserResouces()");

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
        Root<Usuario> root = criteriaQuery.from(Usuario.class);
        
        criteriaQuery.select(root);
        ParameterExpression<String> p1 = criteriaBuilder.parameter(String.class);
        ParameterExpression<String> p2 = criteriaBuilder.parameter(String.class);
        criteriaQuery.where(
                criteriaBuilder.equal(root.get("apodo"), p1),
                criteriaBuilder.equal(root.get("contrasena"), p2)
        );
        
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }*/
    //Obtener el usuario a partir de su apodo.
    public Usuario getUsuario(String apodo) throws Exception{
       
        LOGGER.info("En findUserResouces()");

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
        Root<Usuario> root = criteriaQuery.from(Usuario.class);
        
        criteriaQuery.select(root);
        criteriaQuery.where(
                criteriaBuilder.equal(root.get("apodo"), apodo)
        );
        
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
    
}
