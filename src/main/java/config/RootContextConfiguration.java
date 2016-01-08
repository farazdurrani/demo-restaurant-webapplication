package config;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.velocity.VelocityEngineFactory;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import CSR.DefaultOrderRepository;
import CSR.OrderRepository;
import CSR.ENTITY.Admin;
import CSR.ENTITY.Customer;
import CSR.ENTITY.Menu;
import CSR.ENTITY.MenuForShow;
import CSR.ENTITY.Order;
import CSR.ENTITY.SingleOrder;
import CSR.ENTITY.User;


@Configuration
@EnableTransactionManagement

@ComponentScan(
        basePackages = {"CSR", "sendemail"},
        excludeFilters = @ComponentScan.Filter(Controller.class)
)
/*@ComponentScan(
        basePackages = "CSR",
        excludeFilters = @ComponentScan.Filter(Controller.class)
)*/
public class RootContextConfiguration 
{
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
    	BasicDataSource dataSource = new BasicDataSource();
    	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    	String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
    	String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
    	String name = "demo";
    	String url = "jdbc:mysql://" + host + ":" + port + "/" + name;
    	//dataSource.setUrl("jdbc:mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/demo");
    	dataSource.setUrl(url);
    	dataSource.setUsername("adminFnhZzhv");
    	dataSource.setPassword("l1uIxPhgrE4u");
    	
    	return dataSource;
    }
    
    
    private Properties getHibernateProperties() {
    	Properties properties = new Properties();
    	properties.put("hibernate.show_sql", "true");
    	properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    	return properties;
    }
    
    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
    	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
    	sessionBuilder.addProperties(getHibernateProperties());
    	sessionBuilder.addAnnotatedClasses(Order.class);
    	sessionBuilder.addAnnotatedClasses(SingleOrder.class);
    	sessionBuilder.addAnnotatedClasses(User.class);
    	sessionBuilder.addAnnotatedClasses(Customer.class);
    	sessionBuilder.addAnnotatedClasses(Menu.class);
    	sessionBuilder.addAnnotatedClasses(MenuForShow.class);
    	sessionBuilder.addAnnotatedClasses(Admin.class);
    	return sessionBuilder.buildSessionFactory();
    }
    
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
			SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(
				sessionFactory);

		return transactionManager;
	}
    
    @Autowired
    @Bean(name = "orderRepository")
    public OrderRepository getOrderRepository(SessionFactory sessionFactory) {
    	return new DefaultOrderRepository(sessionFactory);
    }
    
    @Bean
	public JavaMailSenderImpl javaMailSenderImpl(){
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		//Set gmail email id
		mailSender.setUsername("studentmanagementportal@gmail.com");
		//Set gmail email password
		mailSender.setPassword("bilalbilal");
		Properties prop = mailSender.getJavaMailProperties();
		prop.put("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.debug", "true");
		return mailSender;
	}
    
    @Bean
    public VelocityEngine getVelocityEngine() throws VelocityException, IOException{
      VelocityEngineFactory factory = new VelocityEngineFactory();
      Properties props = new Properties();
      props.put("resource.loader", "class");
      props.put("class.resource.loader.class", 
                "org.apache.velocity.runtime.resource.loader." + 
                "ClasspathResourceLoader");
      factory.setVelocityProperties(props);
      return factory.createVelocityEngine();
    }
	
}


      




