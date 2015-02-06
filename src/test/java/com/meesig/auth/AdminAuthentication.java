package com.meesig.auth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.bind.annotation.ExceptionHandler;

import test.util.JndiSpringJUnit4ClassRunner;

import com.meesig.model.User;
import com.meesig.service.UserManager;

import core.config.ApplicationConfig;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.hamcrest.core.IsNull.notNullValue;


@RunWith(JndiSpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class, loader = AnnotationConfigContextLoader.class)
@DirtiesContext
public class AdminAuthentication {

	private static final Logger log = LoggerFactory
			.getLogger(AdminAuthentication.class);

	@Autowired
	UserManager userManager;

	User newUser;
	
	@Before
	public void setUp() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String user_login_id = "tester1525";
		String user_password = "password!";
		String user_name = "name";
		String user_email = "test@test.com";
		Date user_b_date = sdf.parse("1999-01-01");
		String user_sex = "남";
		String user_location = "서울시 용산구";

		newUser = new User(user_login_id, user_password, user_name,
				user_email, user_b_date, user_sex, user_location);
		userManager.createUser(newUser, "USERS");
	}

	@Test
	public void userMatchFalseTest() {
		String user_login_id = "loginId";
		String user_password = "pass";
		
		User user = userManager.selectUserByLoginIdAndCrc(new User(user_login_id));
		Boolean isMatch = userManager.isMatchPassword(user, user_password);
		assertThat(isMatch, is(false));
	}

	@Test
	public void userMatchTrueTest() {
		String user_login_id = "tester1525";
		String user_password = "password!";
		
		User user = userManager.selectUserByLoginIdAndCrc(new User(user_login_id));
		Boolean isMatch = userManager.isMatchPassword(user, user_password);
		assertThat(isMatch, is(true));
	}
	
	@Test
	public void userSelectTest() {
		User loginUser = new User("tester1525", "password!");
		User DBUser = userManager.selectUserByLoginIdAndCrc(loginUser);
		assertThat(loginUser.getUser_login_id(), is(DBUser.getUser_login_id()));
	}
	
//	@Test
//	public void dassf() throws ParseException {
//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//	String user_login_id = "admin";
//	String user_password = "test";
//	String user_name = "name";
//	String user_email = "test@test.com";
//	Date user_b_date = sdf.parse("1999-01-01");
//	String user_sex = "m";
//	String user_location = "서울시 용산구";
//
//	User test = new User(user_login_id, user_password, user_name,
//			user_email, user_b_date, user_sex, user_location);
//	userManager.createUser(test, "ADMIN");
//	}
	
	
	@After
	public void delete() {
		userManager.deleteUserById(newUser);
	}
	
}
