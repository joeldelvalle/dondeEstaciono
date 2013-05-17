package coop.tecso.donde.estaciono.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class CustomJsonSerializer extends JsonSerializer<Object> {

	public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {

 		jgen.writeStartObject();

		jgen.writeObjectField(value.getClass().getSimpleName().toLowerCase(), value);

		jgen.writeEndObject();

	}
	
}
