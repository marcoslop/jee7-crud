package org.javaee.javaeemysql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class PersonIntegrationTest {

	@Deployment
    public static Archive createTestArchive() {
		return  ShrinkWrap.create(JavaArchive.class, "test.jar")
        		//.addPackages(true, "com.tulotero")
        		.addClasses(AbstractDao.class, Person.class, PersonService.class)
                .addAsResource("persistence.xml", "META-INF/persistence.xml")
                //.addAsWebInfResource(new FileAsset(new File("src/main/webapp/WEB-INF/web.xml")), "web.xml")
                //.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
	
	@PersistenceContext
    EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    @Inject
    PersonService personService;
    
    Person person1;
    Person person2;
	
	@Before
	public void setup() throws Exception {
		utx.begin();
	    em.joinTransaction();
		
		person1 = new Person();
		person1.setFirstName("Name1");
		person1.setLastName("Lastname1");
		person1.setMoney(50D);
		personService.create(person1);
		
		person2 = new Person();
		person2.setFirstName("Name2");
		person2.setLastName("Lastname2");
		person2.setMoney(50D);
	}
	
	@After
    public void rollbackTransaction() throws Exception {
        utx.rollback();
    }

	@Test
	public void testInjectionWorks() {
		assertNotNull(personService);
	}
	
	@Test
	public void testCreate (){
		personService.create(person2);
		
		assertNotNull(person2.getId());
	}
	
	@Test
	public void testFind (){
		personService.create(person2);
		Person personLoaded = personService.find(person2.getId());
		
		assertEquals(person2.getFirstName(), personLoaded.getFirstName());
	}

}
