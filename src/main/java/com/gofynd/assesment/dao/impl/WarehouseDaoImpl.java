package com.gofynd.assesment.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gofynd.assesment.dao.WarehouseDaoService;
import com.gofynd.assesment.entity.ProductReceiptEntity;
import com.gofynd.assesment.entity.WarehouseEntity;
import com.gofynd.assesment.repository.ReceiptRepository;
import com.gofynd.assesment.repository.WarehouseRepository;

@Service
public class WarehouseDaoImpl implements WarehouseDaoService {
	
	@Autowired
	private WarehouseRepository warehouseRepo;
	
	@Autowired
	private ReceiptRepository receiptRepository;

	@Override
	public WarehouseEntity persistWareHouse(WarehouseEntity warehouseEntity) {
		return warehouseRepo.save(warehouseEntity);
	}

	@Override
	public List<WarehouseEntity> getAllAvlSlots(boolean isAvailable) {
		return warehouseRepo.findByIsAvailable(isAvailable);
	}

	@Override
	public WarehouseEntity getBySlotNumber(int slotNumber) {
		return warehouseRepo.findBySlotNumber(slotNumber);
	}

	@Override
	public boolean updateSlotAvailability(WarehouseEntity warehouse) {
		WarehouseEntity warehouseEntity =  warehouseRepo.save(warehouse);
		if (warehouseEntity != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateProductAvailability(ProductReceiptEntity product) {
		ProductReceiptEntity productReceiptEntity =  receiptRepository.save(product);
		if (productReceiptEntity != null) {
			return true;
		}
		return false;
	}

	@Override
	public ProductReceiptEntity getProductBySlotNumber(WarehouseEntity warehouse) {
		return receiptRepository.findByWarehouse(warehouse);
	}

	@Override
	public List<ProductReceiptEntity> getProdCodesByColor(String prdColor) {
		return receiptRepository.findByColorAndIsSold(prdColor, false);
	}

	@Override
	public List<ProductReceiptEntity> getProdByProdCode(long prodCode) {
		return receiptRepository.findByProductIdAndIsSold(prodCode, false);
	}

	@Override
	public List<ProductReceiptEntity> getAllProducts() {
		return receiptRepository.findByIsSold(false);
	}

}
