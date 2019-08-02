package com.gofynd.assesment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gofynd.assesment.entity.ProductReceiptEntity;
import com.gofynd.assesment.entity.WarehouseEntity;

@Repository
public interface ReceiptRepository extends CrudRepository<ProductReceiptEntity, String> {
	ProductReceiptEntity findByWarehouse(WarehouseEntity warehouse);
}
