package nl.capgemini.corpapp.jobs;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LinkedinJob {
	
	private static final Logger logger = Logger.getLogger(LinkedinJob.class);

	private static final String LINKEDIN_URL = "https://api.linkedin.com/v1/people";
	
	public static void pull(String token) {
		
		StringBuilder url = new StringBuilder(LINKEDIN_URL);
		url.append("~:(picture-url,skills,certifications,educations)");
		url.append("?oauth2_access_token=" + token);
		
		
		try {
			
			logger.debug("Connect to linkedin: " + url.toString());
			
			// Connect to linkedin
			URL linkedin = new URL(url.toString());
			Reader inputStream = new InputStreamReader(linkedin.openStream());

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(inputStream);

			JSONObject jsonObject = (JSONObject) obj;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Scheduled(cron="*/5 * * * * MON-FRI")
	public void sync(){
		
	}
	
	
	
}
