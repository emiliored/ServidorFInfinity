package fish.payara.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class JPAProducer {
    
	@PersistenceContext
	@Produces
	private EntityManager entityManager;
}

