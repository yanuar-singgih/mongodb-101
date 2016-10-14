package com.mongodb.m101j;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloWorldFreemarkerStyle {

	public static void main(String[] args) {
		Configuration config = new Configuration();
		config.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");
		
		try {
			Template template = config.getTemplate("hello.ftl");
			StringWriter writer = new StringWriter();
			Map<String, Object> helloMap = new HashMap<>();
			helloMap.put("name", "Yanuar");
			
			template.process(helloMap, writer);	
			System.out.println(writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
