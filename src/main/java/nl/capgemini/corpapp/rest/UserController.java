package nl.capgemini.corpapp.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.capgemini.corpapp.documents.User;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

@Component
@Path("/user")
public class UserController {

	private static final Logger LOG = Logger.getLogger(InitController.class);

	@Autowired
	private MongoOperations mongoOperation;

	@GET
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object getRegister() {
		return "Hallo";
	}
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object postRegister(User user) {
		
		LOG.debug("New user" + user.getCorpKey());
		user.setUsername(user.getCorpKey().toLowerCase());
		user.setPassword("test1234");
		
		mongoOperation.save(user);
		
		return "OK";

		

	}

}
