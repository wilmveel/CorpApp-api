package nl.capgemini.corpapp.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.capgemini.corpapp.documents.Carpool;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Path("/carpool")
public class CarpoolController {

	private static final Logger LOG = Logger.getLogger(CarpoolController.class);
	
	@Resource
	MongoOperations mongoOperation;

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getCarpool() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username

		System.out.println("Name: " + name);

		List<Carpool> carpool = mongoOperation.findAll(Carpool.class);
		return carpool;

	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postCarpool(Carpool carpool) {
		
		LOG.debug("Carpool: " + carpool);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username
		
		carpool.setCorpkey(name);
		
		mongoOperation.save(carpool);
		
		return Response.ok().build();
	}
}
