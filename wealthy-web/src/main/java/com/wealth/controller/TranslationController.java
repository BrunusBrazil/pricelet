package com.wealth.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="translation")
public class TranslationController {
		
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value="/labels", method = RequestMethod.GET)
	public Map<String,String> getLabels(@RequestParam("lang") String lang) throws Exception{
		Map<String, String> labels = new HashMap<String, String>();
		ResourceBundle  resourceBundle = ResourceBundle.getBundle("i18n/messages");
		Locale locale = new Locale(lang); 
		Enumeration<String> keys =  resourceBundle.getKeys();
		while(keys.hasMoreElements()){
			String key = keys.nextElement();
			String value  = messageSource.getMessage(key, null, locale);
			labels.put(key, value);
		}
		return labels;
	}	

}
