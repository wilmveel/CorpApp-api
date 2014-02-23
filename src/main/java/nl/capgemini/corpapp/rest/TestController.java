package nl.capgemini.corpapp.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.capgemini.corpapp.documents.PeopleDto;
import nl.capgemini.corpapp.documents.User;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

@Component
@Path("/test")
public class TestController {

	@Resource
	MongoOperations mongoOperation;

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Object get() {

		List<PeopleDto> peopleList = mongoOperation.findAll(PeopleDto.class);
		String result = "Hello World: " + peopleList.get(0).getFirstName();

		return peopleList;

	}

}
