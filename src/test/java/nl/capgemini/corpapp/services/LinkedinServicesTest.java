package nl.capgemini.corpapp.services;

import java.io.IOException;

import javax.annotation.Resource;

import nl.capgemini.corpapp.config.TestApplicatoinConfig;
import nl.capgemini.corpapp.documents.Linkedin;
import nl.capgemini.corpapp.jobs.LinkedinJob;
import nl.capgemini.corpapp.service.LinkedinService;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicatoinConfig.class)
public class LinkedinServicesTest  {
	
	private static final Logger LOG = Logger.getLogger(LinkedinServicesTest.class);
	
	@Resource
	LinkedinService linkedinService;
	
	@Resource
	MongoOperations mongoOperation;
	
	
	@Test
	public void testLinkedinPullTestWillem(){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("corpkey").is("WVEELENT"));
		
		Linkedin linkedin = mongoOperation.findOne(query, Linkedin.class);
		
		LOG.debug("Found Willem: " + linkedin.getCorpkey());
		
		try {
			linkedin = linkedinService.pull(linkedin.getAccesToken());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LOG.debug("Picture Url: " +linkedin.getPictureUrl());
		
		LOG.debug("Found Willem: " + linkedin.getCorpkey());

	}

}
