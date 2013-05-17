package coop.tecso.donde.estaciono.errors;

import coop.tecso.donde.estaciono.logger.LoggerFactory;
import coop.tecso.donde.estaciono.model.Error;
import coop.tecso.donde.estaciono.utils.DESUtils;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class ErrorBuilder {

	private LoggerFactory log = LoggerFactory.getInstance(this.getClass());

	private ErrorMessageCache errorMessageCache;

	private static ErrorBuilder instance;

	public static ErrorBuilder getInstance() {

		if (DESUtils.isNull(instance)) {
			instance = new ErrorBuilder();
		}

		return instance;
	}

	private ErrorBuilder() {
		this.errorMessageCache = ErrorMessageCache.getInstance();
	}

	public Error buildError(String messageErrorKey) {
		String method = "buildError";
		log.logStartMethod(method);
		log.logInfo(method, "messageErrorKey: " + messageErrorKey);

		String[] errorKey = messageErrorKey.split("\\|");

		String messageKey = errorKey[0];

		String errorMessage = this.findMessage(messageKey);

		errorMessage = this.reemplazaValoresEnMensaje(errorKey, errorMessage);

		String[] errorMessageSplited = errorMessage.split("\\|");

		Error error;
		if (errorMessageSplited.length > 1) {
			error = new Error(errorMessageSplited[0], errorMessageSplited[1]);
		} else {
			error = new Error("999", errorMessageSplited[0]);
		}

		log.logInfo(method, "error: " + error.toString());
		log.logEndMethod(method);
		return error;
	}

	private String findMessage(String messageKey) {
		return this.errorMessageCache.getMessage(messageKey);
	}

	private String reemplazaValoresEnMensaje(String[] errorKey, String errorMessage) {

		int cantidadDeParametrosExtraDelMensaje = errorKey.length - 1;

		if (cantidadDeParametrosExtraDelMensaje > 0) {

			for (int indice = 1; indice <= cantidadDeParametrosExtraDelMensaje; indice++) {
				errorMessage = errorMessage.replaceAll("\\{" + (indice) + "\\}", errorKey[indice]);
			}

		}

		return errorMessage;

	}

}
