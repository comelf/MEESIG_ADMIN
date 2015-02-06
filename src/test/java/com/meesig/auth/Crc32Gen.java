package com.meesig.auth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import test.util.JndiSpringJUnit4ClassRunner;
import core.config.ApplicationConfig;
import core.util.auth.Crc32;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(JndiSpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class, loader = AnnotationConfigContextLoader.class)
public class Crc32Gen {

	@Autowired
	Crc32 crc;

	@Test
	public void crcTest() {
		int a = crc.getCode("test");
		int b = crc.getCode("test");
		assertThat(a, is(b));
	}
	
	@Test
	public void crcTest2() {
		int a = crc.getCode("test1");
		int b = crc.getCode("test2");
		assertThat(a, not(is(b)));
	}

}
