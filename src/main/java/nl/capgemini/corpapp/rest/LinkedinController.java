package nl.capgemini.corpapp.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.capgemini.corpapp.documents.Linkedin;
import nl.capgemini.corpapp.documents.User;
import nl.capgemini.corpapp.security.MongoUserDetails;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

	@GET
	@Path("/me")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getMe(@PathParam("corpkey") String corpkey) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MongoUserDetails user = (MongoUserDetails) auth.getPrincipal();

		Query query = new Query();
		query.addCriteria(Criteria.where("corpkey").is(user.getUser().getCorpKey()));

		Linkedin Linkedin = mongoOperation.findOne(query, Linkedin.class);

		return Linkedin;

	}

	@GET
	@Path("/{corpkey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getCorpkey(@PathParam("corpkey") String corpkey) {

		Query query = new Query();
		query.addCriteria(Criteria.where("corpkey").is(corpkey));

		Linkedin Linkedin = mongoOperation.findOne(query, Linkedin.class);

		return Linkedin;

	}
}
