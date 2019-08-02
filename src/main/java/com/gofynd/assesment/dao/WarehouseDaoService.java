package com.gofynd.assesment.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gofynd.assesment.entity.ProductReceiptEntity;
import com.gofynd.assesment.entity.WarehouseEntity;

@Service
public interface WarehouseDaoService {

	// Dao Interface
	WarehouseEntity persistWareHouse(WarehouseEntity warehouseEntity);
	List<WarehouseEntity> getAllAvlSlots(boolean isAvailable);
	WarehouseEntity getBySlotNumber(int slotNumber);
	ProductReceiptEntity getProductBySlotNumber(WarehouseEntity warehouse);
	boolean updateSlotAvailability(WarehouseEntity warehouse);
	boolean updateProductAvailability(ProductReceiptEntity product);
	List<ProductReceiptEntity> getProdCodesByColor(String prdColor);
	List<ProductReceiptEntity> getProdByProdCode(long prodCode);
	List<ProductReceiptEntity> getAllProducts();
}
