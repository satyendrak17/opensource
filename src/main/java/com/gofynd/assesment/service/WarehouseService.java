package com.gofynd.assesment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gofynd.assesment.entity.ProductReceiptEntity;

@Service
public interface WarehouseService {

	// main impl interface
	boolean createWarehouse(String name, int slot);
	int storeProduct(long productId, String color);
	int getNearestAvlSlot();
	boolean freeUpSlot(int slotNumber);
	List<ProductReceiptEntity> getProdCodesByColor(String prdColor);
	List<ProductReceiptEntity> getProdByProdCode(long prodCode);
	List<ProductReceiptEntity> getAllProducts();
}
