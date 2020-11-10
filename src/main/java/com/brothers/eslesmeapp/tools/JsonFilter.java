package com.brothers.eslesmeapp.tools;

import java.util.List;

import com.brothers.eslesmeapp.model.Testler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class JsonFilter {
	public String getFilteredString (List<Testler> testler,String filterName,String jsonField) {
		ObjectMapper mapper = new ObjectMapper();
	    FilterProvider filters = new SimpleFilterProvider()  
	      .addFilter(filterName, SimpleBeanPropertyFilter.serializeAllExcept(jsonField));  
	    ObjectWriter writer = mapper.writer(filters);  
	    try {
	        return writer.writeValueAsString(testler);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}
