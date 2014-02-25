package nl.capgemini.corpapp.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.capgemini.corpapp.documents.Linkedin;
import nl.capgemini.corpapp.documents.PeopleDto;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

@Component
@Path("/linkedin")
public class LinkedinController {

	@Resource
	MongoOperations mongoOperation;
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Object get() {

		List<Linkedin> LinkedinList = mongoOperation.findAll(Linkedin.class);

		return LinkedinList;

	}
}
