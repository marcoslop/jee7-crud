package org.javaee.javaeemysql;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/persons")
public class PersonResource {

	private PersonService personService;
	
	@GET
	@Produces("application/json")
	public List<Person> getAllPersons (){
		return personService.findAll();
	}
	
	@GET
	@Produces("application/json")
    @Path("/{id}")
    public Person getPersonById(@PathParam("id") Integer id)
    {
        return personService.find(id);
    }

	@Inject
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	
}
