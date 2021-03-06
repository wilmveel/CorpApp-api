package nl.capgemini.corpapp.rest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.capgemini.corpapp.documents.Carpool;
import nl.capgemini.corpapp.documents.CarpoolLocation;
import nl.capgemini.corpapp.documents.Escape;
import nl.capgemini.corpapp.documents.Linkedin;
import nl.capgemini.corpapp.documents.Training;
import nl.capgemini.corpapp.documents.User;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

@Component
@Path("/init")
public class InitController {

	private static final Logger LOG = Logger.getLogger(InitController.class);

	@Resource
	MongoOperations mongoOperation;

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getInit() {

		LOG.debug("getInit");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("users", this.getInitUser());
		map.put("carpool", this.getInitCarpool());
		map.put("linkedin", this.getInitLinkedin());
		map.put("escape", this.getInitEscape());

		return map;
	}

	@GET
	@Path("/user")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getInitUser() {

		LOG.debug("getInitUser");

		mongoOperation.dropCollection(User.class);
		// Init users
		
		mongoOperation.save(new User("test", "test", "test", "test@capgemini.com"));
		
		mongoOperation.save(new User("baltena", "12345678", "BALTENA", "bart.altena@capgemini.com"));
		mongoOperation.save(new User("wslager", "12345678", "WSLAGER", "wouter.slager@capgemini.com"));
		mongoOperation.save(new User("prispen", "12345678", "PRISPEN", "paul.rispens@capgemini.com"));
		mongoOperation.save(new User("cveelent", "12345678", "CVEELENT", "christien.veelenturf@capgemini.com"));
		mongoOperation.save(new User("rebbers", "12345678", "REBBERS", "richard.ebbers@capgemini.com"));

		mongoOperation.save(new User("wveelent", "12345678", "WVEELENT", "willem.veelenturf@capgemini.com"));
		mongoOperation.save(new User("ehermans", "12345678", "EHERMANS", "bert.hermans@capgemini.com"));
		mongoOperation.save(new User("wkersten", "12345678", "WKERSTEN", "willem.kersten@capgemini.com"));
		mongoOperation.save(new User("tdelisse", "12345678", "TDELISSE", "thomas.delissen@capgemini.com"));
		mongoOperation.save(new User("ssewgobi", "12345678", "SSEWGOBI", "soeradj.sewgobind@capgemini.com"));

		
		List<User> User = mongoOperation.findAll(User.class);

		return User;

	}

	@GET
	@Path("/carpool")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getInitCarpool() {

		mongoOperation.dropCollection(Carpool.class);
		// Init users

		CarpoolLocation carpoolLocation1 = new CarpoolLocation("Reykjavikplein 1, Utrecht, Netherlands", 52.308226, 4.944984999999974);
		CarpoolLocation carpoolLocation2 = new CarpoolLocation("Haarlemmerweg, 1055 Amsterdam, Nederland", 52.3848374, 4.851391799999988);

		mongoOperation.save(new Carpool(carpoolLocation1, carpoolLocation2, "WVEELENT"));
		mongoOperation.save(new Carpool(carpoolLocation2, carpoolLocation1, "BALTENA"));

		List<Carpool> carpool = mongoOperation.findAll(Carpool.class);

		return carpool;

	}

	@GET
	@Path("/linkedin")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getInitLinkedin() {

		mongoOperation.dropCollection(Linkedin.class);

		// Init users
		mongoOperation
				.save(new Linkedin(
						"CAUKEMA",
						"AQVVCKNvIcuukse2nmLKvcHbqBvFWsKpCdBIe1WAFoatBNGRO2-FWfIs6ifeS4zsMzuzJXvvhoPx2bS2ccbYKHN7JABBGS06P3Ridgw8uFfnnNNzMvr0SFnf4sGzubVN4dIu7Y0pmgviQP8kkWIGdYGzhu__4UiYdD09TAnllrfp450zYpQ"));
		mongoOperation
				.save(new Linkedin(
						"WVEELENT",
						"AQXv5GOcGNGoR4Xuy4PSNMg6b2evkqKOmfdNk5ed3i1XPp_E5KpjFl3FAEmekVg5YfjLSFa3D9E0i3LPHBRSheaPU1F8WKbWsyjuVB9D66108UKXSMFX7UdrXStQEcVuWP-yF8oRXNM1J0ZZ0x7QVv4fATkiagIp0rn_ntO3bfFQMc54RHU"));
		mongoOperation
				.save(new Linkedin(
						"WSLAGER",
						"AQUjNB-yqO3Q_il972SGyl6a-DCCBznVEgtF63-Tx30jWbPJFonVh0Hg49lEMFy_x8qWwPSofMgMAt5uzYs103gyw7jogfl0pAohkih1-PZhD0T5b61oyb7lAef-GrU0wX1foXkFKYG7zaArx-xHaRkBPO-EtwAQMXMnpaooSxUPpBADwD4"));
		mongoOperation
				.save(new Linkedin(
						"PCHAUBEY",
						"AQVLGF1TJh_yQr5R6i4AC_Ho5bPtvAzG7Et7jM88xTGbSCxs5GZvAKpO4xv9beA-ZuV-S2lnxhrTG-nCNlKoMlNao0gZrCtjtA-MBFI9xe4UkPEzh6ULF3eRbdVKPle6nIq1QevUmfEfqDQp7WeXUdBoKtj9adpBJ9goUTBeFZ3HcXxdxtI"));
		mongoOperation
				.save(new Linkedin(
						"IREKOK",
						"AQVAk-ZDW1Wkb9mnhIr0cJMTxlkw_rgYiJYMebklHZAUs3x_fPYOabu0LZ3Fw_n1oSXc6mVfUJdq9hyYYZ9Tpdj-db3itybFAM6108uQR3NsWWn_iEKKkARDjtYADZIuW3gstaxeo4wRQtS4tcvGp8ARnAbGrY6ox0cwQKXM-sh9hghPHhU"));

		List<Linkedin> linkedin = mongoOperation.findAll(Linkedin.class);

		return linkedin;

	}

	@GET
	@Path("/escape")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getInitEscape() {

		mongoOperation.dropCollection(Escape.class);

		// Init escape
		mongoOperation.save(new Escape(new Date(), "http://van.zelf.nl/wp-content/uploads/2010/02/wintersport2.jpg", "Wintersport", "Fijne vakantie"));
		mongoOperation.save(new Escape(new Date(), "http://upload.wikimedia.org/wikipedia/commons/0/0b/Rijksmuseum_Amsterdam.jpg", "Rijksmuseum Amsterdam",
				"We gaan naar Amsterdam!!"));

		List<Escape> escape = mongoOperation.findAll(Escape.class);

		return escape;

	}

	@GET
	@Path("/training")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getAdvantageYou() throws IOException {

		String filename = "trainiglist.txt";
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		String[] locationArray = new String[5];
		locationArray[0] = "Amsterdam";
		locationArray[1] = "Utrecht";
		locationArray[2] = "Heerlen";
		locationArray[3] = "Amersfort";
		locationArray[4] = "Amsterdam";
		
		String[] corpKeyArray = new String[5];
		corpKeyArray[0] = "WVEELENT";
		corpKeyArray[1] = "BALTENA";
		corpKeyArray[2] = "WSLAGER";
		corpKeyArray[3] = "PCHAUBEY";
		corpKeyArray[4] = "IREKOK";
		
		String line;
		Random rand = new Random();
        while ((line = bufferedReader.readLine()) != null) {
            Training training = new Training();
            training.setName(line);
            training.setDate(new Date());
            int  randomLoaction = rand.nextInt(5) + 1;
            training.setLocation(locationArray[randomLoaction]);
            int  randomCorpKey = rand.nextInt(5) + 1;
            training.setInstructorCorpKey(corpKeyArray[randomCorpKey]);
        }
        
        List<Training> trainingList = mongoOperation.findAll(Training.class);
		return trainingList;

	}

}
