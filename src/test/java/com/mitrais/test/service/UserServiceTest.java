package com.mitrais.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mitrais.test.MitraisApplication;
import com.mitrais.test.model.User;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MitraisApplication.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@Transactional
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void saveUser_success() {
		User user = new User();
		user.setFirstName("David");
		user.setLastName("Vincent Kristanto");
		user.setEmail("david@gmail.com");
		user.setMobile("085777400200");
		user.setGender("Male");
		userService.saveUser(user);
		
		User userTest = userService.getUserByEmail("david@gmail.com");
		assertThat(userTest.getFirstName(), is("David"));
		assertThat(userTest.getLastName(), is("Vincent Kristanto"));
		assertThat(userTest.getEmail(), is("david@gmail.com"));
		assertThat(userTest.getMobile(), is("085777400200"));
		assertThat(userTest.getGender(), is("Male"));		
	}
	
	@Test
	public void getUserByEmail_success() {
		User user = new User();
		user.setFirstName("David");
		user.setLastName("Vincent Kristanto");
		user.setEmail("david@gmail.com");
		user.setMobile("085777400200");
		user.setGender("Male");
		userService.saveUser(user);
		
		User user2 = new User();
		user2.setFirstName("Shelly");
		user2.setLastName("Ariantika");
		user2.setEmail("shelly@gmail.com");
		user2.setMobile("085777400100");
		user2.setGender("Female");
		userService.saveUser(user2);
		
		User userTest = userService.getUserByEmail("david@gmail.com");
		assertThat(userTest.getFirstName(), is("David"));
		assertThat(userTest.getLastName(), is("Vincent Kristanto"));
		assertThat(userTest.getEmail(), is("david@gmail.com"));
		assertThat(userTest.getMobile(), is("085777400200"));
		assertThat(userTest.getGender(), is("Male"));		
		
		User userTest2 = userService.getUserByEmail("shelly@gmail.com");
		assertThat(userTest2.getFirstName(), is("Shelly"));
		assertThat(userTest2.getLastName(), is("Ariantika"));
		assertThat(userTest2.getEmail(), is("shelly@gmail.com"));
		assertThat(userTest2.getMobile(), is("085777400100"));
		assertThat(userTest2.getGender(), is("Female"));
	}
	
	@Test
	public void getUserByMobile_success() {
		User user = new User();
		user.setFirstName("David");
		user.setLastName("Vincent Kristanto");
		user.setEmail("david@gmail.com");
		user.setMobile("085777400200");
		user.setGender("Male");
		userService.saveUser(user);
		
		User user2 = new User();
		user2.setFirstName("Shelly");
		user2.setLastName("Ariantika");
		user2.setEmail("shelly@gmail.com");
		user2.setMobile("085777400100");
		user2.setGender("Female");
		userService.saveUser(user2);
		
		User userTest = userService.getUserByMobile("085777400200");
		assertThat(userTest.getFirstName(), is("David"));
		assertThat(userTest.getLastName(), is("Vincent Kristanto"));
		assertThat(userTest.getEmail(), is("david@gmail.com"));
		assertThat(userTest.getMobile(), is("085777400200"));
		assertThat(userTest.getGender(), is("Male"));		
		
		User userTest2 = userService.getUserByMobile("085777400100");
		assertThat(userTest2.getFirstName(), is("Shelly"));
		assertThat(userTest2.getLastName(), is("Ariantika"));
		assertThat(userTest2.getEmail(), is("shelly@gmail.com"));
		assertThat(userTest2.getMobile(), is("085777400100"));
		assertThat(userTest2.getGender(), is("Female"));
	}
}
