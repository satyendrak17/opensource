package com.gofynd.assesment.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gofynd.assesment.dao.impl.WarehouseDaoImpl;
import com.gofynd.assesment.entity.ProductReceiptEntity;
import com.gofynd.assesment.entity.WarehouseEntity;
import com.gofynd.assesment.repository.ReceiptRepository;
import com.gofynd.assesment.service.WarehouseService;

@Service
@Transactional
public class WarehouseImpl implements WarehouseService {
	
	@Autowired
	private WarehouseDaoImpl warehouseDao;
	
	@Autowired
	private ReceiptRepository receiptRepo;
	
	@Override
	public boolean createWarehouse(String name, int slot) {
		// Create the entity and pass it to dao to be  persisted in db
		WarehouseEntity warehouseEntity = null;
		for (int indx = 1; indx <= slot; indx++) {
			warehouseEntity = new WarehouseEntity();
			warehouseEntity.setSlotNumber(indx);
			warehouseEntity.setAvailable(true);
			WarehouseEntity persistedEntity = warehouseDao.persistWareHouse(warehouseEntity);
			if (persistedEntity == null) {
				// Throw new not created exception
				System.out.println("Warehouse could not be created");
				return false;
			}
		}
		
		return true;
	}

	@Override
	public int storeProduct(long productId, String color) {
		int avlSlot = getNearestAvlSlot();
		if (avlSlot <= 0) {
			return 0;
		}
		ProductReceiptEntity prodEntity = new ProductReceiptEntity();
		prodEntity.setSold(false);
		prodEntity.setColor(color);
		WarehouseEntity warehouseEntity = warehouseDao.getBySlotNumber(avlSlot);
		warehouseEntity.setAvailable(false);
		prodEntity.setWarehouse(warehouseEntity);
		prodEntity.setProductId(productId);
		
		ProductReceiptEntity productReceiptEntity = receiptRepo.save(prodEntity);
		if (productReceiptEntity != null) {
			return avlSlot;
		} else {
			return -1;
		}
		
	}

	@Override
	public int getNearestAvlSlot() {
		List<Integer> slots = new ArrayList<>();
		List<WarehouseEntity> allItems = warehouseDao.getAllAvlSlots(true);
		if (allItems.size() == 0) {
			return 0;
		}
		for (WarehouseEntity warehouseSlot: allItems) {
			slots.add(warehouseSlot.getSlotNumber());
		}
		Collections.sort(slots);
		return slots.get(0);
	}

	@Override
	public boolean freeUpSlot(int slotNumber) {
		// Get warehouse by slot number
		WarehouseEntity warehouse = warehouseDao.getBySlotNumber(slotNumber);
		warehouse.setAvailable(true);
		// Get Product receipt by slot number
		ProductReceiptEntity product = warehouseDao.getProductBySlotNumber(warehouse);
		product.setSold(true);
		product.setWarehouse(warehouse);
		boolean isSold = warehouseDao.updateProductAvailability(product);
		
		if (isSold) {
			return true;
		}
		return false;
	}

	@Override
	public List<ProductReceiptEntity> getProdCodesByColor(String prdColor) {
		List<ProductReceiptEntity> productList= warehouseDao.getProdCodesByColor(prdColor);
		return productList;
	}

	@Override
	public List<ProductReceiptEntity> getProdByProdCode(long prodCode) {
		List<ProductReceiptEntity> productList= warehouseDao.getProdByProdCode(prodCode);
		return productList;
	}

	@Override
	public List<ProductReceiptEntity> getAllProducts() {
		List<ProductReceiptEntity> productList= warehouseDao.getAllProducts();
		return productList;
	}

}
