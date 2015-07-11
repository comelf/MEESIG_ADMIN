package test.util;

import org.junit.runners.model.InitializationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jndi.JndiTemplate;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

public class JndiSpringJUnit4ClassRunner extends SpringJUnit4ClassRunner {
	private static final Logger log = LoggerFactory
			.getLogger(JndiSpringJUnit4ClassRunner.class);

	public JndiSpringJUnit4ClassRunner(Class<?> clazz)
			throws InitializationError {
		super(clazz);
		try {
			bindJndi();
		} catch (Exception e) {
			log.error("JNDI bind Error : {}", e.getMessage());
		}
	}

	private void bindJndi() throws Exception {
		SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
		builder.activate();
		JndiTemplate jt = new JndiTemplate();
		
		MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
		
		ds.setUrl("jdbc:mysql://10.14.57.130:3306/MEESIG");
        ds.setUser("meesig");
        ds.setPassword("M@eS1g$2*Wow!");
		
		jt.bind( "jdbc/database", ds );
	}
}
