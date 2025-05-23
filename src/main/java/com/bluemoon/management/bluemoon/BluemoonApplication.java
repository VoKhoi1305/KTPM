package com.bluemoon.management.bluemoon;

import com.bluemoon.management.bluemoon.controller.ApartmentController;
import com.bluemoon.management.bluemoon.service.ApartmentService;
import com.bluemoon.management.bluemoon.service.ApartmentTypeService;
import com.bluemoon.management.bluemoon.service.impl.ApartmentServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BluemoonApplication {

	public static void main(String[] args) {
		SpringApplication.run(BluemoonApplication.class, args);
	}
}
