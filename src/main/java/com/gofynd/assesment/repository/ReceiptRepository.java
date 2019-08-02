package com.gofynd.assesment.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gofynd.assesment.entity.ProductReceiptEntity;
import com.gofynd.assesment.entity.WarehouseEntity;

@Repository
public interface ReceiptRepository extends CrudRepository<ProductReceiptEntity, String> {
	ProductReceiptEntity findByWarehouse(WarehouseEntity warehouse);
	List<ProductReceiptEntity> findByColorAndIsSold(String color, boolean isSold);
	List<ProductReceiptEntity> findByProductIdAndIsSold(long productId, boolean isSold);
	List<ProductReceiptEntity> findByIsSold(boolean isSold);
}
