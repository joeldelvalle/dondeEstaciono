package coop.tecso.donde.estaciono.json;

import java.io.IOException;
import java.util.Iterator;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import coop.tecso.donde.estaciono.cache.ClassNameCache;
import coop.tecso.donde.estaciono.utils.DESUtils;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class CustomJsonDeserialize extends JsonDeserializer<Object> {

	@Override
	public Object deserialize(JsonParser jsonParser, DeserializationContext arg1) throws IOException, JsonProcessingException {

		ObjectCodec objectCodec = jsonParser.getCodec();
		JsonNode node = objectCodec.readTree(jsonParser);

		String classNameIdentification = "";
		for (Iterator<String> fieldNamesIterator = node.getFieldNames(); fieldNamesIterator.hasNext();) {
			classNameIdentification = fieldNamesIterator.next();
		}

		JsonNode jsonPayloadObject = node.get(classNameIdentification);

		try {

			Class<? extends Object> clazz = ClassNameCache.getInstance().getClassFromCache(classNameIdentification);

			Object payloadObject = DESUtils.convertJsonToObject(jsonPayloadObject.toString(), clazz);

			return payloadObject;

		} catch (Exception e) {
			throw new IOException("error in CustomJsonDeserialize  -  " + e);
		}
	}

}
