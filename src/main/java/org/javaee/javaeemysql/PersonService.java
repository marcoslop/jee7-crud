package org.javaee.javaeemysql;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marcoslop
 */
@Stateless
public class PersonService extends AbstractDao<Person> {
    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonService() {
        super(Person.class);
    }
    
}
