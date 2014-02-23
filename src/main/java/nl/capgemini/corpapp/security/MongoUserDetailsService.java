package nl.capgemini.corpapp.security;

import javax.annotation.Resource;

import nl.capgemini.corpapp.documents.User;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MongoUserDetailsService implements UserDetailsService {

	@Resource
	MongoOperations mongoOperation;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(username));

		User user = mongoOperation.findOne(query, User.class);
		
		System.out.println("Found user: " + user);
		
		if (user != null) {
			MongoUserDetails details = new MongoUserDetails(user);
			return details;
		} else {
			throw new UsernameNotFoundException("User not found in mongoDb");
		}
	}
}
