package br.com.leucotron.livre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import br.com.leucotron.livre.core.config.AppContext;

/**
 * Main class of Leucotron Interactive Voice Response Experience application.
 * 
 * @author Virtus
 */
@SpringBootApplication
public class LivreApplication {

	/**
	 * Main.
	 * 
	 * @param args
	 * 		Arguments.
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(LivreApplication.class, args);
		
		AppContext.loadApplicationContext(ctx);
	}
}
