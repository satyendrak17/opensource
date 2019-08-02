package com.gofynd.assesment.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "warehouse")
public class WarehouseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "warehouse_id")
	private long id;
	
	@Column(name = "slot_number")
	private int slotNumber;
	
	@Column(name = "is_available")
	private boolean isAvailable;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "warehouse")
    private List<ProductReceiptEntity> receipt;

	public List<ProductReceiptEntity> getReceipt() {
		return receipt;
	}

	public void setReceipt(List<ProductReceiptEntity> receipt) {
		this.receipt = receipt;
	}

	public int getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	
	
	
	
	
}
