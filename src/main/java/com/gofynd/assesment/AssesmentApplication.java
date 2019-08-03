package com.gofynd.assesment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.gofynd.assesment.beans.AppBean;

@SpringBootApplication
@ComponentScan("com.gofynd")
public class AssesmentApplication {
	public static void main(String[] args) {
		final ConfigurableApplicationContext context = SpringApplication.run(AssesmentApplication.class, args);
		final AppBean app = context.getBean(AppBean.class);
        try {
			app.run(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// WarehouseImpl warehouseImpl = new WarehouseImpl();
		// Take user input for warehouse
		// store warehouse information in database
		// show the success message to the user
		
		
		// Take user input to store the product receipt and allocate the racks
		// Store the details in the database
		// show the success message to the user
		// Repeat until user presses -1
		
		// When user enters 'sell' and slot number
		// make that slot free and mark it sold
		// show the success message to the user
		
		
		// When user enters 'stats' display tab delimited output
		
		// when slots are full show slots are full
		
		
		// when user enters product codes and color
		// show the allocated slots
		// if only product code is entered show the slots allocated
		
	}
}
