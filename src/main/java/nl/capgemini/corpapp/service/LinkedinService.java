package nl.capgemini.corpapp.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import nl.capgemini.corpapp.documents.Linkedin;
import nl.capgemini.corpapp.jobs.LinkedinJob;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
public class LinkedinService {

	private static final Logger LOG = Logger.getLogger(LinkedinService.class);

	private static final String LINKEDIN_URL = "https://api.linkedin.com/v1/people";

	public static Linkedin pull(String token) {

		StringBuilder url = new StringBuilder(LINKEDIN_URL);
		url.append("/~:(picture-url,skills,certifications,educations)");
		url.append("?oauth2_access_token=" + token);

		LOG.debug("Linkedin URL: " + url);

		try {

			LOG.debug("Connect to linkedin: " + url.toString());

			// Connect to linkedin
			URL linkedin = new URL(url.toString());

			URLConnection connection = linkedin.openConnection();
			connection.setRequestProperty("x-li-format", "json");

			Reader inputStream = new InputStreamReader(connection.getInputStream());

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(inputStream);

			JSONObject jsonObject = (JSONObject) obj;

			LOG.debug("Linkedin Object: " + jsonObject);

			JSONObject skillsJson = (JSONObject) jsonObject.get("skills");
			JSONArray skillsValeJson = (JSONArray) skillsJson.get("values");

			LOG.debug("skillsValeJson: " + skillsValeJson);

			List<String> linkedinSkillList = new ArrayList<String>();
			for (Object skillObject : skillsValeJson) {
				JSONObject skillJson = (JSONObject) skillObject;
				JSONObject skill = (JSONObject) skillJson.get("skill");
				linkedinSkillList.add(skill.get("name").toString());
			}

			Linkedin linkedinDoc = new Linkedin();
			linkedinDoc.setCorpkey("WVEELENT");
			linkedinDoc.setAccesToken(token);
			linkedinDoc.setPictureUrl(jsonObject.get("pictureUrl").toString());
			linkedinDoc.setSkils(linkedinSkillList);

			return linkedinDoc;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
