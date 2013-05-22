package coop.tecso.donde.estaciono.errors;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;

import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.utils.DESUtils;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class ErrorMessageCache {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	private static final String ERROR_MESSAGE_FILE_LOCATION = "error/errorMessage.properties";

	private static ErrorMessageCache instance;

	private Properties properties;

	public static ErrorMessageCache getInstance() {

		if (DESUtils.isNull(instance)) {
			instance = new ErrorMessageCache();
		}

		return instance;
	}

	private ErrorMessageCache() {
		this.loadErrorMessage();
	}

	private void loadErrorMessage() {
		String method = "loadErrorMessage";
		log.logStartMethod(method);

		try {

			PropertiesFactoryBean loader = new PropertiesFactoryBean();
			loader.setLocation(new ClassPathResource(ERROR_MESSAGE_FILE_LOCATION));
			loader.afterPropertiesSet();

			properties = (Properties) loader.getObject();

		} catch (IOException ioe) {
			log.logError(method, "no se pudo cargar el archivo 'errorMessage.properties'");
		}

		log.logEndMethod(method);
	}

	public String getMessage(String key) {
		String method = "getMessage";
		log.logStartMethod(method);

		String message = key;
		if (properties.containsKey(key)) {
			message = properties.getProperty(key);
		}

		log.logInfo(method, "errorMessage; " + message);
		log.logEndMethod(method);
		return message;

	}

}
