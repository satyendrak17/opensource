package com.gofynd.assesment;

import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.gofynd.assesment.impl.WarehouseImpl;

@SpringBootApplication
@ComponentScan("com.gofynd")
public class AssesmentApplication {
	
	@Autowired
	private WarehouseImpl warehouseImpl;
	
	public static void main(String[] args) {
		SpringApplication.run(AssesmentApplication.class, args);
		// WarehouseImpl warehouseImpl = new WarehouseImpl();
		// Take user input for warehouse
		// store warehouse information in database
		// show the success message to the user
		/*
		String userInput = "";
		try (Scanner scanner = new Scanner(System.in)) {
			while (!userInput.equals("done")) {
				System.out.println("Input:");
				System.out.println();
				String input = scanner.nextLine().trim();
				String firstWord = "";
				if (input != null && input != "") {
					userInput = input.toLowerCase();
					firstWord = userInput.split(" ")[0];
					switch (firstWord) {
					case "warehouse":
							int slot = Integer.parseInt(userInput.split(" ")[1]);
							boolean isCreated = warehouseImpl.createWarehouse("warehouse", slot);
							System.out.println("Output:");
							if (isCreated) {
								System.out.println("Created a warehouse with "+slot+" slots ");
							} else {
								System.out.println("Warehouse could not be created!!");
							}
							
							
						break;

					default:
						System.out.println("Default case!");
						break;
					}
				}
			}		
			System.out.println("Finished the task!!");
			System.exit(-1);
		} */
		
		
		
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
	
	@PostConstruct
	public void init() {
		String userInput = "";
		try (Scanner scanner = new Scanner(System.in)) {
			
			while (!userInput.equals("done")) {
				System.out.println("Input:");
				String input = scanner.nextLine().trim();
				String firstWord = "";
				if (input != null && input != "") {
					userInput = input.toLowerCase();
					firstWord = userInput.split(" ")[0];
					switch (firstWord) {
					case "warehouse":
							int slot = Integer.parseInt(userInput.split(" ")[1]);
							boolean isWarehouseCreated = warehouseImpl.createWarehouse("warehouse", slot);
							System.out.println("Output:");
							if (isWarehouseCreated) {
								System.out.println("Created a warehouse with "+slot+" slots ");
							} else {
								System.out.println("Warehouse could not be created!!");
							}
						break;
					
					case "store":
							// Store the product
						long productId = Long.parseLong(userInput.split(" ")[1]);
						String color = userInput.split(" ")[2];
						int slotGiven = warehouseImpl.storeProduct(productId, color);
						System.out.println("Output:");
						if (slotGiven > 0) {
							System.out.println("Allocated slot number "+slotGiven);
						} else {
							System.out.println("Product receipt could not be created, try again !!");
						}
						break;
						
					case "sell":
						int slotNumberSold = Integer.parseInt(userInput.split(" ")[1]);
						boolean isSold = warehouseImpl.freeUpSlot(slotNumberSold);
						System.out.println("Output:");
						if (isSold) {
							System.out.println("Slot number "+slotNumberSold+" is free");
						} else {
							System.out.println("Something went wrong!!");
						}
						break;

					default:
						System.out.println("Invalid Input!!, please refer the instructions above");
						break;
					}
				}
			}		
			System.out.println("Finished the task!!");
			System.exit(-1);
		}
	}

}
