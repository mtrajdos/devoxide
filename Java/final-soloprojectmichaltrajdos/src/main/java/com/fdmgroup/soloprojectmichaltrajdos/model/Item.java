package com.fdmgroup.soloprojectmichaltrajdos.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "Items")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_gen")
	@SequenceGenerator(name = "item_gen", sequenceName = "ITEM_SEQ", allocationSize = 1)
	private long item_id;

	@Column(nullable = false)
	private String itemName;
	
	@Column(nullable = false)
	private String itemDescription;
	
	@Column(nullable = false)
	private BigDecimal itemPrice;
	
	@Column(nullable = false)
	private long itemStock;
	
	public Item() {
	}

	public Item(String itemName, String itemDescription, BigDecimal itemPrice, long itemStock) {
		super();
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemPrice = itemPrice;
		this.itemStock = itemStock;
	}

	public long getItem_id() {
		return item_id;
	}

	public void setItem_id(long item_id) {
		this.item_id = item_id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public long getItemStock() {
		return itemStock;
	}

	public void setItemStock(long itemStock) {
		this.itemStock = itemStock;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemDescription == null) ? 0 : itemDescription.hashCode());
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((itemPrice == null) ? 0 : itemPrice.hashCode());
		result = prime * result + (int) (itemStock ^ (itemStock >>> 32));
		result = prime * result + (int) (item_id ^ (item_id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (itemDescription == null) {
			if (other.itemDescription != null)
				return false;
		} else if (!itemDescription.equals(other.itemDescription))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (itemPrice == null) {
			if (other.itemPrice != null)
				return false;
		} else if (!itemPrice.equals(other.itemPrice))
			return false;
		if (itemStock != other.itemStock)
			return false;
		if (item_id != other.item_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [item_id=" + item_id + ", itemName=" + itemName + ", itemDescription=" + itemDescription
				+ ", itemPrice=" + itemPrice + ", itemStock=" + itemStock + "]";
	}
	
}
