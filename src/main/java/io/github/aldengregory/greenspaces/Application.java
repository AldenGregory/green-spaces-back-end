 package io.github.aldengregory.greenspaces;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class starts the Spring application.
 */
@SpringBootApplication
public class Application {

	/**
	 * This is an entry point from which this application is started.
	 * 
	 * @param args Program arguments that are currently unused.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
