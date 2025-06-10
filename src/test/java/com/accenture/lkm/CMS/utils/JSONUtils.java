package com.accenture.lkm.CMS.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {
	
	public static Object convertJsonToObject(String json,Class var) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		return mapper.readValue(json, var);
	}
	
	public static <T> T convertJsonToObject2(String json,Class<T> var) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		return mapper.readValue(json, var);
	}
	
	public static String convertObjectToJson(Object var) throws JsonProcessingException {
		ObjectMapper mapper=new ObjectMapper();
		return mapper.writeValueAsString(var);
	}

}
