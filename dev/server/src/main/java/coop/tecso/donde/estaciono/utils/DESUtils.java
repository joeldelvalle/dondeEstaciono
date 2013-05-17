package coop.tecso.donde.estaciono.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class DESUtils {

	public static String convertObjectToJson(Object object) {

		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {

			json = mapper.writeValueAsString(object);

		} catch (JsonGenerationException e) {
			json = e.getMessage();
		} catch (JsonMappingException e) {
			json = e.getMessage();
		} catch (IOException e) {
			json = e.getMessage();
		}

		return json;
	}

	public static <T> T convertJsonToObject(String json, Class<T> clazz) throws DondeEstacionoServerException {

		ObjectMapper mapper = new ObjectMapper();
		T object;
		try {

			object = mapper.readValue(json, clazz);

		} catch (Exception e) {
			throw new DondeEstacionoServerException(e);
		}

		return object;
	}

	public static <T> List<T> convertCollectionToList(Collection<T> collection, Class<T> clazz) {

		List<T> list = new ArrayList<T>();

		for (T value : collection) {
			list.add(value);
		}

		return list;
	}

	public static boolean isNull(Object object) {
		return object == null;
	}

}
