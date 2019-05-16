package fish.payara.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;


public abstract class AbstractFacade<T> {

    private final Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();
    protected abstract UserTransaction getUserTransaction();
    protected abstract Logger getLogger();

    public void create(T entity) {
        getLogger().info("En create()");
        try {
            getUserTransaction().begin();
            getEntityManager().persist(entity);
            getUserTransaction().commit();
        } catch (javax.transaction.NotSupportedException | SystemException | javax.transaction.RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(AbstractFacade.class.getName()).log(Level.SEVERE, null, ex);
            getLogger().info("<<< ERROR >>> en create()");
        }
        

    }

    public void edit(T entity) {
        getLogger().info("En edit()");
        try {
            getUserTransaction().begin();
            getEntityManager().merge(entity);
            getUserTransaction().commit();
        } catch (javax.transaction.NotSupportedException | SystemException | javax.transaction.RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(AbstractFacade.class.getName()).log(Level.SEVERE, null, ex);
            getLogger().info("<<< ERROR >>> en edit()");
        }
    }

    public void remove(Object id) {
        getLogger().info("En remove()");
        try {
            getUserTransaction().begin();
            T entity = find(id);
            getEntityManager().remove(entity);
            getUserTransaction().commit();
        } catch (javax.transaction.NotSupportedException | SystemException | javax.transaction.RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(AbstractFacade.class.getName()).log(Level.SEVERE, null, ex);
            getLogger().info("<<< ERROR >>> en remove()");
        }
    }
    
    public void remove(int id) {
        getLogger().info("En remove()");
    	try {
            getUserTransaction().begin();
    		T entity = find(id);
	    	getEntityManager().remove(entity);
	    	getUserTransaction().commit();
        } catch (javax.transaction.NotSupportedException | SystemException | javax.transaction.RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(AbstractFacade.class.getName()).log(Level.SEVERE, null, ex);
            getLogger().info("<<< ERROR >>> en remove()");
        }
     }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }
    
    public T find(int id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        CriteriaQuery<T> criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    public List<T> findRange(int start, int end) {
    	int[] rango = new int[] {start, end};
    	return findRange(rango);
    }
    
    public List<T> findRange(int[] range) {
        CriteriaQuery<T> criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);
        TypedQuery<T> typeQuery = getEntityManager().createQuery(criteriaQuery);
        typeQuery.setMaxResults(range[1] - range[0] + 1);
        typeQuery.setFirstResult(range[0]);
        return typeQuery.getResultList();
    }

    public long count() {
        CriteriaQuery<Long> criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery(Long.class);
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(getEntityManager().getCriteriaBuilder().count(root));
        TypedQuery<Long> typedQuery = getEntityManager().createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }

}
