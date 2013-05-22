package coop.tecso.donde.estaciono.logger;

import org.apache.log4j.Logger;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class CustomLogger {

	private Logger log = null;

	public CustomLogger(String className) {
		log = Logger.getLogger(className);
	}

	public void logStartMethod(String methodName) {
		log.info(this.buildMessage(methodName, "Start"));
	}

	public void logEndMethod(String methodName) {
		log.info(this.buildMessage(methodName, "End"));
	}

	public void logInfo(String methodName, String message) {
		log.info(this.buildMessage(methodName, message));
	}

	public void logDebug(String methodName, String message) {
		log.debug(this.buildMessage(methodName, message));
	}

	public void logError(String methodName, String message) {
		log.error(this.buildMessage(methodName, message));
	}

	public void logError(String methodName, String message, Throwable throwable) {
		log.error(this.buildMessage(methodName, message), throwable);
	}

	private String buildMessage(String methodName, String message) {
		StringBuilder sb = new StringBuilder();
		sb.append(methodName);
		sb.append(" - ");
		sb.append(message);
		return sb.toString();
	}

}