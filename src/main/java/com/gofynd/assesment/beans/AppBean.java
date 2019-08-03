package com.gofynd.assesment.beans;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gofynd.assesment.entity.ProductReceiptEntity;
import com.gofynd.assesment.impl.WarehouseImpl;

@Component
public class AppBean {
	Logger logger = LoggerFactory.getLogger(AppBean.class);
	@Autowired
	private WarehouseImpl warehouseImpl;
	public void run(String... args) throws Exception {
		
		System.out.println("==================== User Manual =============================");
		System.out.println("Input Instructions, program will take the input in below format only");
		System.out.println("==============");
		System.out.println("Step 1: To create warehouse - warehouse 2(can be any number)");
		System.out.println("Step 2: To store any product - store 12333 color");
		System.out.println("Step 3: To Sell - Sell 2 (2 is Slot allocated Number)");
		System.out.println("Step 4: To view the status - status");
		System.out.println("Step 5: To fetch product code by colour - product_codes_for_products_with_colour colourname" );
		System.out.println("Step 6: To fetch product slots by colour - slot_numbers_for_products_with_colour colourname");
		System.out.println("Step 7: To fetch slots by product code - slot_number_for_product_code productcode");
		System.out.println("Enter 'done' to exit anytime from program");
		System.out.println("===================================================================");
		String userInput = "";
		String format = "%1$-10s %2$-20s %3$-20s \n";
		try (Scanner scanner = new Scanner(System.in)) {			
			while (!userInput.equals("done")) {
				try {
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
							System.out.println("Warehouse is full");
						}
						break;
						
					case "sell":
						int slotNumberSold = Integer.parseInt(userInput.split(" ")[1]);
						boolean isSold = warehouseImpl.freeUpSlot(slotNumberSold);
						System.out.println("Output:");
						if (isSold) {
							System.out.println("Slot number "+slotNumberSold+" is free");
						} else {
							System.out.println("Something went wrong, or nothing stored to be sold.");
						}
						break;
					case "product_codes_for_products_with_colour":
					case "slot_numbers_for_products_with_colour":
						String prdColor = userInput.split(" ")[1];
						List<ProductReceiptEntity> prodReceipts = warehouseImpl.getProdCodesByColor(prdColor);
						System.out.println("Output:");
						if (prodReceipts != null && prodReceipts.size() > 0) {
							for (ProductReceiptEntity prod: prodReceipts) {
								if (firstWord.equalsIgnoreCase("slot_numbers_for_products_with_colour")) {
									System.out.print(prod.getWarehouse().getSlotNumber());
								} else {
									System.out.print(prod.getProductId());
								}
								
								System.out.print("  ");
								System.out.println("\n");
							}
						} else {
							System.out.println("No products found!");
						}
						break;
						
					case "slot_number_for_product_code":
						long prdCode = Long.parseLong(userInput.split(" ")[1]);
						List<ProductReceiptEntity> prodByProdCode = warehouseImpl.getProdByProdCode(prdCode);
						System.out.println("Output:");
						if (prodByProdCode != null && prodByProdCode.size() > 0) {
							for (ProductReceiptEntity prod: prodByProdCode) {
								System.out.print(prod.getWarehouse().getSlotNumber());
								System.out.print("  ");
							}
						} else {
							System.out.println("No products found!");
						}
						System.out.println("\n");
						break;
					case "status":
						List<ProductReceiptEntity> allProducts = warehouseImpl.getAllProducts();
						System.out.println("Output:");
						System.out.format(format, "Slot No.", "Product Code", "Colour");
						if (allProducts != null && allProducts.size() > 0) {
							for (ProductReceiptEntity prod: allProducts) {
								System.out.format(format, prod.getWarehouse().getSlotNumber(),
										prod.getProductId(), prod.getColor());
							}
						} else {
							System.out.println("No products found!");
						}
						System.out.println("\n");
						break;
						
					case "done":
						System.out.println("Exiting !!");
						System.exit(-1);
					default:
						System.out.println("Invalid Input!!, please refer the instructions above and try again");
						break;
					}
				}
				} catch (Exception e) {
					System.out.println("e " + e.getMessage());
					System.out.println("Something unexpected happened, please eneter a valid input.");
				}
			}		
			System.out.println("Finished the task!!");
			System.exit(-1);
		}
		
	}

}
