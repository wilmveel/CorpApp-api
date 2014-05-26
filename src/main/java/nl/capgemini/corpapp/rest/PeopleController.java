package nl.capgemini.corpapp.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import nl.capgemini.corpapp.documents.Linkedin;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
@Path("/people")
public class PeopleController {

	@Resource
	MongoOperations mongoOperation;
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Object search(@QueryParam("search") String search) {

		Query query = new Query();
		if(search != null){
			query.addCriteria(Criteria.where("lastName").regex(search, "i"));
		}
		List<Linkedin> peopleList = mongoOperation.find(query, Linkedin.class);
		return peopleList;

	}
}
