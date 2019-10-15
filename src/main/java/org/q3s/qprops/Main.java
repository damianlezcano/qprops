package org.q3s.qprops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ImportResource({"classpath:spring/context.xml"})
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
