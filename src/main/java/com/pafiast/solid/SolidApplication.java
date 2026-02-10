package com.pafiast.solid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the SOLID principles demonstration.
 * This Spring Boot application initializes the context and starts the web server.
 */
@SpringBootApplication
public class SolidApplication {

    /**
     * Main entry point of the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(SolidApplication.class, args);
    }
}

