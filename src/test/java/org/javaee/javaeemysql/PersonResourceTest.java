package org.javaee.javaeemysql;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PersonResourceTest {

	PersonResource personResource;
	
	@Mock private PersonService personService;
	
	private List<Person> allPersons;
	private Person person1;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		personResource = new PersonResource();
		personResource.setPersonService(personService);
		
		allPersons = new ArrayList<Person>();
		person1 = new Person();
		allPersons.add(person1);
		when(personService.findAll()).thenReturn(allPersons);
		when(personService.find(new Integer(1))).thenReturn(person1);
	}

	@Test
	public void testGetAllPersons() {
		assertEquals (allPersons, personResource.getAllPersons());
	}

	@Test
	public void testGetPersonById() {
		assertEquals (person1, personResource.getPersonById(new Integer(1)));
	}
	
	@Test
	public void testGetPersonById_NotFound() {
		assertNull (personResource.getPersonById(new Integer(2)));
	}

}
