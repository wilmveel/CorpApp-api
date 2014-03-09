package nl.capgemini.corpapp.jobs;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;

import nl.capgemini.corpapp.documents.Linkedin;
import nl.capgemini.corpapp.service.LinkedinService;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LinkedinJob {

	private static final Logger LOG = Logger.getLogger(LinkedinJob.class);

	@Resource
	MongoOperations mongoOperation;

	@Resource
	LinkedinService linkedinService;

	@Scheduled(cron = "0 * * * * *")
	public void test() {
		LOG.debug("Test schedule " + new Date());
	}

	@Scheduled(cron = "0 0 * * * *")
	public void sync() {

		LOG.debug("Sync linkedin");

		Collection<Linkedin> linkedinList = mongoOperation.findAll(Linkedin.class);

		for (Linkedin linkedinDoc : linkedinList) {
			LOG.debug("Sync: " + linkedinDoc.getCorpkey());
			LOG.debug("Token: " + linkedinDoc.getAccesToken());

			try {
				Linkedin l = linkedinService.pull(linkedinDoc.getAccesToken());
				l.setCorpkey(linkedinDoc.getCorpkey());
				l.setId(linkedinDoc.getId());
				l.setSync(new Date());
				mongoOperation.save(l);
			} catch (IOException e) {
				LOG.debug("Unable to sync", e);
			}

		}
	}
}
