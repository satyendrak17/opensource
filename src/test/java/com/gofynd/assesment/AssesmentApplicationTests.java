package com.gofynd.assesment;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gofynd.assesment.dao.impl.WarehouseDaoImpl;
import com.gofynd.assesment.entity.WarehouseEntity;
import com.gofynd.assesment.impl.WarehouseImpl;




@RunWith(SpringRunner.class)
@SpringBootTest
public class AssesmentApplicationTests {
	
	@Autowired
	private WarehouseImpl warehouseServiceImpl;
	
	@Test
	public void createWarehouse() {
		WarehouseDaoImpl warehouseDao = Mockito.mock(WarehouseDaoImpl.class);
		WarehouseEntity entity = Mockito.mock(WarehouseEntity.class);
		entity.setAvailable(false);
		Mockito.when(warehouseDao.persistWareHouse(entity)).thenReturn(entity);
		final boolean istrue = warehouseServiceImpl.createWarehouse("warehouse",1);
		assertTrue(istrue);
	}

}
