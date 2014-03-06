package nl.capgemini.corpapp.jobs;

import javax.annotation.Resource;

import nl.capgemini.corpapp.config.TestApplicatoinConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicatoinConfig.class)
public class LinkedinJobTest {

	@Resource
	LinkedinJob linkedinJob;
	
	
	@Test
	public void testSyncJob(){
		linkedinJob.sync();
	}

}
