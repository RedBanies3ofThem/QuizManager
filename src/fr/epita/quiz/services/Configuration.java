package fr.epita.quiz.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

	private Properties properties;

	private static Configuration instance;

	private Configuration() {
		this.properties = new Properties();
		try (InputStream is = new FileInputStream(new File("conf.properties"))) {
			this.properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Configuration getInstance() {
		if (instance == null) {
			instance = new Configuration();
		}
		return instance;

	}

	public String getConfigurationValue(String configurationKey) {
		return this.properties.getProperty(configurationKey);
	}

}
