package nl.capgemini.corpapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoConfig extends AbstractMongoConfiguration {

	private String MONGO_URI = "mongodb://admin:4dwFYkdd@SG-corpapp-2049.servers.mongodirector.com:27017/admin";

	@Override
	public String getDatabaseName() {
		return "capapp";
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		MongoClientURI mongoClientURI = new MongoClientURI(MONGO_URI);
		MongoClient mongoClient = new MongoClient(mongoClientURI);
		return mongoClient;
	}
}
