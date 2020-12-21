package com.buscador.buscadorontology;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BuscadorOntologyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuscadorOntologyApplication.class, args);
		Ontologia onto = new Ontologia();
	}

}
