package nl.capgemini.corpapp.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.capgemini.corpapp.documents.Carpool;
import nl.capgemini.corpapp.documents.PeopleDoc;
import nl.capgemini.corpapp.documents.User;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

@Component
@Path("/init")
public class InitController {

	@Resource
	MongoOperations mongoOperation;

	@GET
	@Path("/user")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getInitUser() {

		
		mongoOperation.dropCollection(User.class);
		// Init users
		mongoOperation.save(new User("wveelent", "12345678", "WVEELENT"));
		mongoOperation.save(new User("baltena", "12345678", "BALTENA"));
		mongoOperation.save(new User("wslager", "12345678", "WSLAGER"));
		mongoOperation.save(new User("prispen", "12345678", "PRISPEN"));

		List<User> User = mongoOperation.findAll(User.class);

		return User;

	}
	
	@GET
	@Path("/carpool")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getInitCarpool() {

		
		mongoOperation.dropCollection(Carpool.class);
		// Init users
		mongoOperation.save(new Carpool("Amsterdam", "Utrecht", "WVEELENT"));
		mongoOperation.save(new Carpool("Gouda", "Den Haag", "BALTENA"));

		List<Carpool> User = mongoOperation.findAll(Carpool.class);

		return User;

	}

}
