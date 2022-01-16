package com.theatre.converters;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import com.theatre.models.PerformanceDTO;


public class PerformanceConverter implements Formatter<PerformanceDTO>{

	@Override
	public String print(PerformanceDTO object, Locale locale) {
		
		return object.getName();
	}

	@Override
	public PerformanceDTO parse(String text, Locale locale) throws ParseException {
		
		return null;
	}

}
