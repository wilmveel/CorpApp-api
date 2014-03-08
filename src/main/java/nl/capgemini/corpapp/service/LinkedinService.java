package nl.capgemini.corpapp.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import nl.capgemini.corpapp.documents.Linkedin;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
public class LinkedinService {

	public static final String CLIENT_ID = "7714w3q3mqlrro";
	public static final String CLIENT_SECRET = "4VAVCHKeiMsMUrDZ";

	private static final Logger LOG = Logger.getLogger(LinkedinService.class);

	public static final String LINKEDIN_URL = "https://api.linkedin.com/v1/people";
	public static final String REDIRECT_URL = "http://localhost:8080/corpapi/rest/linkedin/connect";

	public String accessToken(String code) {
		StringBuilder request = new StringBuilder();
		request.append("https://www.linkedin.com/uas/oauth2/accessToken?grant_type=authorization_code");
		request.append("&code=" + code);
		request.append("&redirect_uri=" + REDIRECT_URL);
		request.append("&client_id=" + CLIENT_ID);
		request.append("&client_secret=" + CLIENT_SECRET);

		try {
			URL url = new URL(request.toString());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "text/plain");
			connection.setRequestProperty("charset", "utf-8");
			connection.connect();
			
			InputStream stream = connection.getInputStream();
			
			String jsonString = IOUtils.toString(stream, "UTF-8");
			
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
						
			LOG.debug("JSON: " + jsonObject);
			
			return jsonObject.get("access_token").toString();
			
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	public Linkedin pull(String token) {

		StringBuilder url = new StringBuilder(LINKEDIN_URL);
		url.append("/~:(first-name,last-name,headline,picture-url,skills,certifications,educations,)");
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

			// SKills
			JSONObject skillsJson = (JSONObject) jsonObject.get("skills");
			JSONArray skillsValeJson = (JSONArray) skillsJson.get("values");
			List<String> linkedinSkillList = new ArrayList<String>();
			for (Object skillObject : skillsValeJson) {
				JSONObject skillJson = (JSONObject) skillObject;
				JSONObject skill = (JSONObject) skillJson.get("skill");
				linkedinSkillList.add(skill.get("name").toString());
			}

			// Picture url
			String pictureUrl = jsonObject.get("pictureUrl").toString();
			String lastName = jsonObject.get("lastName").toString();
			String firstName = jsonObject.get("firstName").toString();
			String headline = jsonObject.get("headline").toString();

			Linkedin linkedinDoc = new Linkedin();
			linkedinDoc.setAccesToken(token);
			linkedinDoc.setSkills(linkedinSkillList);

			linkedinDoc.setPictureUrl(pictureUrl);
			linkedinDoc.setLastName(lastName);
			linkedinDoc.setFirstName(firstName);
			linkedinDoc.setHeadline(headline);

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
