package com.gofynd.assesment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "product_receipt")
public class ProductReceiptEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "receipt_id")
	private long id;
	
	@Column(name = "product_id")
	private long productId;
	
	@Column(name = "product_color")
	private String color;
	
	// @Column(name = "slot_number")
	// private int slotNumber;	
	
	@Column(name = "is_sold")
	private boolean isSold;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "slot_number")
	private WarehouseEntity warehouse;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}


	public WarehouseEntity getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WarehouseEntity warehouse) {
		this.warehouse = warehouse;
	}

	public boolean isSold() {
		return isSold;
	}

	public void setSold(boolean isSold) {
		this.isSold = isSold;
	}
	
	
	
	
	
}
