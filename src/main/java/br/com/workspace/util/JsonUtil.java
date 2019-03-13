package br.com.workspace.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility Class for JSON Objects
 * 
 * @author andrerafaelmezzalira
 *
 */
public class JsonUtil {

	private JsonUtil() {
		// private contructor
	}
	
	/**
	 * Convert string json in a object json
	 * 
	 * @param json
	 * @param t
	 * @return T
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static final <T> T fromJson(final String json, final Class<T> t)
			throws IOException {
		return new ObjectMapper().readValue(json, t);
	}

	/**
	 * Convert object json in a string json
	 * 
	 * @param t
	 * @return
	 * @throws JsonProcessingException
	 */
	public static final <T> String toJson(final T t) throws JsonProcessingException {
		return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(t);
	}
}
