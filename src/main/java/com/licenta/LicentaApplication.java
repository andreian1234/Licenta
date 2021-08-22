package com.licenta;

import com.licenta.controllers.RecipeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LicentaApplication {


	public static void main(String[] args) {
		SpringApplication.run(LicentaApplication.class, args);
	}

	RecipeController controller = new RecipeController();

}
