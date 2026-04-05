package com.api.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager2 {
	// WAP to read properties file from src/test/resources/config/config.properties

	private static Properties prop = new Properties(); // create object of properties class
	private static String path = "config/config.properties";
	private static String env;

	private ConfigManager2() {

	}

	// static block will be executed only once at the time of class loading.
	// no matter how many objects you create
	static {

		env = System.getProperty("env", "qa");
		env = env.toLowerCase().trim();

		switch (env) {
		case "dev" -> path = "config/config.dev.properties";
		
		case "qa" -> path = "config/config.qa.properties";

		case "uat" -> path = "config/config.uat.properties";

		default -> path = "config/config.qa.properties";
		}

		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

		if (input == null) {
			throw new RuntimeException("cannot find file at the path" + path);
		}

		try {

			prop.load(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getProperty(String key) throws IOException {

		return prop.getProperty(key);

	}

}