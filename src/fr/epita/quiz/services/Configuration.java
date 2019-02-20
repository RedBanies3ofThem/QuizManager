package fr.epita.quiz.services;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/** Class to get application configurations from a config file
 * @author Jonathan Sadighian and Rhea Moubarak
 *
 */
public class Configuration {

	private Properties properties;
	private static Configuration instance;

	/** Default Constructor. Singleton, thus is private.
	 * 
	 */
	private Configuration() {
		this.properties = new Properties();
		try (InputStream is = new FileInputStream(new File("conf.properties"))) {
			this.properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** Get the singleton configuration object for accessing the config file 
	 * @return (Configuration) Configuration singlton instance
	 */
	public static Configuration getInstance() {
		if (instance == null) {
			instance = new Configuration();
		}
		return instance;
	}

	/** Get a value for a given configuration (key-value pattern)
	 * @param configurationKey (String) Key to look up a value
	 * @return (String) Value for the provided key
	 */
	public String getConfigurationValue(String configurationKey) {
		return this.properties.getProperty(configurationKey);
	}

}
