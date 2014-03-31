package nl.capgemini.corpapp.rest;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import nl.capgemini.corpapp.config.TestApplicatoinConfig;
import nl.capgemini.corpapp.documents.User;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicatoinConfig.class)
public class InitControllerTest {

	@Resource
	InitController initController;
	
	@Test
	public void getUserTest(){
		List<User> userList = (List<User>) initController.getInitUser();
	}
	
	@Test
	public void getInitTest(){
		//Map<String, Object> map = (Map<String, Object>) initController.getInit();
		//List<User> userList = (List<User>) map.get("users");
		//assertEquals("WVEELENT", userList.get(0).getCorpKey());
	}
	
}
