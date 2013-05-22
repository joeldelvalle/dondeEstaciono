package coop.tecso.donde.estaciono.logger.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class LoggerConfiguration implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			PropertiesFactoryBean loader = new PropertiesFactoryBean();
			loader.setLocation(new ClassPathResource("config/log4j.properties"));
			loader.afterPropertiesSet();
			Properties loaderProps;
			loaderProps = (Properties) loader.getObject();
			PropertyConfigurator.configure(loaderProps);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
