package com.gofynd.assesment.service;

import org.springframework.stereotype.Service;

import com.gofynd.assesment.entity.WarehouseEntity;

@Service
public interface WarehouseService {

	// main impl interface
	boolean createWarehouse(String name, int slot);
	int storeProduct(long productId, String color);
	int getNearestAvlSlot();
	boolean freeUpSlot(int slotNumber);
}
