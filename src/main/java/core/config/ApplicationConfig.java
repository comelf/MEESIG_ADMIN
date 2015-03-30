package core.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.transaction.aspectj.AnnotationTransactionAspect;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import core.util.auth.Crc32;
import core.util.auth.PhPass;

@Configuration
@ComponentScan(basePackages = {"com.meesig.controller","com.meesig.service"} )
public class ApplicationConfig {

//	@Resource
//	private Environment env;
//
//	@Bean
//	public RestTemplate restTemplate(){
//		return new RestTemplate();
//	}
//	
//	@Bean
//	public CacheManager cacheManager(){
//		return CacheManager.create();
//	}
//	
//	@Bean
//	public Cache cache(){
//		return cacheManager().getCache("naverApiCache");
//	}
	
	@Bean
	public DataSource dataSource() {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        DataSource dataSource = dsLookup.getDataSource("jdbc/database");
        return dataSource;
    } 
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		//factoryBean.setTransactionFactory(new ManagedTransactionFactory());
		factoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		return factoryBean.getObject();
	}

	@Bean
	public SqlSession sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}	
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	

	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setDefaultEncoding("utf-8");
	    multipartResolver.setMaxUploadSize(10000000);
	    return multipartResolver;
	}
	
	@Bean
	public PhPass phPass() {
		return new PhPass(8);
	}

	@Bean
	public Crc32 crc32 () {
		return new Crc32();
	}

}
