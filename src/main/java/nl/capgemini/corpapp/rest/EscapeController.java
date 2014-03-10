package nl.capgemini.corpapp.rest;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import nl.capgemini.corpapp.documents.Escape;
import nl.capgemini.corpapp.documents.Linkedin;
import nl.capgemini.corpapp.security.MongoUserDetails;
import nl.capgemini.corpapp.service.LinkedinService;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Path("/escape")
public class EscapeController {

	@Resource
	MongoOperations mongoOperation;

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Object get() {
		List<Escape> escapeList = mongoOperation.findAll(Escape.class);
		return escapeList;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getMe(@PathParam("id") String id) {

		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));

		Escape escape = mongoOperation.findOne(query, Escape.class);

		return escape;

	}

}
