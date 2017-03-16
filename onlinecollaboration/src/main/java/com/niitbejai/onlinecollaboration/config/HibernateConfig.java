package com.niitbejai.onlinecollaboration.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages={"com.niitbejai.onlinecollaboration.dto"})
@EnableTransactionManagement 
public class HibernateConfig 
{

	// The below values are database specific and will change if we change the DBMS
	
	private final static String DATABASE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final static String DATABASE_DRIVER ="oracle.jdbc.driver.OracleDriver";
	private final static String DATABASE_DIALECT = 	"org.hibernate.dialect.Oracle10gDialect";
	private final static String DATABASE_USERNAME = "hr";
	private final static String DATABASE_PASSWORD = "hr";
	
	// Creating datasource bean
	@Bean("dataSource")
	public DataSource getDataSource()
	{
		BasicDataSource dataSource = new BasicDataSource();
		
		dataSource.setDriverClassName(DATABASE_DRIVER);
		dataSource.setUrl(DATABASE_URL);
		dataSource.setUsername(DATABASE_USERNAME);
		dataSource.setPassword(DATABASE_PASSWORD);
		
		return dataSource;
	}
	
	// Creating sessionFactory bean
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
		
		builder.addProperties(getHibernateProperties());
		builder.scanPackages("com.niitbejai.onlinecollaboration");
		
		SessionFactory tmpSF = builder.buildSessionFactory(); 
		return tmpSF;
	}

	private Properties getHibernateProperties() 
	{
		Properties properties = new Properties();
		
		properties.put("hibernate.dialect", DATABASE_DIALECT);
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		
		return properties;
	}
	
	//Creating Transaction Manager Bean
		@Bean
		public HibernateTransactionManager getTransactionManager(SessionFactory sesionFactory)
		{
			HibernateTransactionManager transactionManager = new HibernateTransactionManager(sesionFactory);
			
			return transactionManager;
		}
}
