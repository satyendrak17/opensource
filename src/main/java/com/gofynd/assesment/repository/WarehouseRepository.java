package com.gofynd.assesment.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gofynd.assesment.entity.WarehouseEntity;

@Repository
public interface WarehouseRepository extends CrudRepository<WarehouseEntity, String> {
	List<WarehouseEntity> findAll();
	WarehouseEntity findBySlotNumber(int slotNumber);
	List<WarehouseEntity> findByIsAvailable(boolean isAvailable);
}
