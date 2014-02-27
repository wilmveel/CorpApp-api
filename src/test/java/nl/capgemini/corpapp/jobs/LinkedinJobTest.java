package nl.capgemini.corpapp.jobs;

import javax.annotation.Resource;

import nl.capgemini.corpapp.config.TestApplicatoinConfig;
import nl.capgemini.corpapp.documents.Linkedin;

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
public class LinkedinJobTest  {
	
	private static final Logger LOG = Logger.getLogger(LinkedinJobTest.class);
	
	@Resource
	LinkedinJob linkedinJob;
	
	@Resource
	MongoOperations mongoOperation;
	
	@Test
	public void testLinkedinPull(){
		
		linkedinJob.pull("123");
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testLinkedinPullTestWillem(){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("corpkey").is("WVEELENT"));
		
		Linkedin linkedin = mongoOperation.findOne(query, Linkedin.class);
		
		LOG.debug("Found Willem: " + linkedin.getCorpkey());
		
		linkedin = linkedinJob.pull(linkedin.getAccesToken());
		
		LOG.debug("Found Willem: " + linkedin.getCorpkey());

	}

}
