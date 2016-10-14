package com.mongodb.m101j;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldSparkFreemarkerStyle {

	public static void main(String[] args) {
		final Configuration config = new Configuration();
		config.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");

		Spark.get("/", new Route() {

			@Override
			public Object handle(Request request, Response response) throws Exception {
				StringWriter writer = new StringWriter();
				
				try {
					Template template = config.getTemplate("hello.ftl");
					Map<String, Object> helloMap = new HashMap<>();
					helloMap.put("name", "Yanuar");

					template.process(helloMap, writer);
					System.out.println(writer);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return writer;
			}
		});
	}

}
