package com.mongodb.m101j;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class SparkFormHandling {

	public static void main(String[] args) {
		final Configuration config = new Configuration();
		config.setClassForTemplateLoading(SparkFormHandling.class, "/");

		Spark.get("/", new Route() {

			@Override
			public Object handle(Request request, Response response) throws Exception {
				StringWriter writer = new StringWriter();
				
				try {
					Template template = config.getTemplate("fruitPicker.ftl");
					Map<String, Object> fruitMap = new HashMap<>();
					fruitMap.put("fruits", Arrays.asList("apple", "oranges", "banana", "watermelon"));

					template.process(fruitMap, writer);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return writer;
			}
		});
		
		Spark.post("/favorite_fruit", new Route() {
			
			@Override
			public Object handle(Request request, Response response) throws Exception {
				final String fruit = request.queryParams("fruit");
				if (fruit == null) {
					return "Why don't you pick one?";
				} else {
					return "Your favorite fruit is " + fruit;
				}
			}
		});
	}

}
