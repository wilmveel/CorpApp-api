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
@Path("/linkedin")
public class LinkedinController {

	@Resource
	MongoOperations mongoOperation;

	@Resource
	LinkedinService linkedinService;

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

	@GET
	@Path("/connect")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getConnect(@QueryParam("code") String code, @Context HttpServletRequest httpRequest) {

		String requestUrl = httpRequest.getRequestURL().toString();
		linkedinService.setRequestURL(requestUrl);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MongoUserDetails user = (MongoUserDetails) auth.getPrincipal();

		Query query = new Query();
		query.addCriteria(Criteria.where("corpkey").is(user.getUser().getCorpKey()));

		String accessToken = linkedinService.accessToken(code);
		
		try {
			Linkedin linkedin = linkedinService.pull(accessToken);
			linkedin.setAccesToken(accessToken);
			linkedin.setCorpkey(user.getUser().getCorpKey());

			Linkedin linkedinMongo = mongoOperation.findOne(query, Linkedin.class);
			if (linkedinMongo != null) {
				linkedin.setId(linkedinMongo.getId());
			}

			linkedin.setSync(new Date());
			mongoOperation.save(linkedin);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return "OK";

	}
}
