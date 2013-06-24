package coop.tecso.donde.estaciono.json;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

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

		jgen.writeObjectField(this.createClassName(value), value);

		jgen.writeEndObject();

	}

	@SuppressWarnings("rawtypes")
	private String createClassName(Object value) {

		if (Collection.class.isAssignableFrom(value.getClass())) {

			List list = (List) value;
			if (list.size() > 0) {
				return list.get(0).getClass().getSimpleName().toLowerCase() + "list";
			}
			
			return "emptylist";

		}

		return value.getClass().getSimpleName().toLowerCase();
	}

}
