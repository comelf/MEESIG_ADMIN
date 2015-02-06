package com.meesig.user;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import test.util.JndiSpringJUnit4ClassRunner;

import com.meesig.model.User;
import com.meesig.service.UserManager;
import com.meesig.util.Paging;

import core.config.ApplicationConfig;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(JndiSpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class, loader = AnnotationConfigContextLoader.class)
public class AdminUserList {

	@Autowired
	UserManager userManager;
	
	@Test
	public void userCountTest() {
		int total = userManager.countTotalUser();
		assertThat(total, is(102));
		
		Paging paging = new Paging(51, 2022, 20);
		
		System.out.println(paging.toString());
		
	}
	


}
