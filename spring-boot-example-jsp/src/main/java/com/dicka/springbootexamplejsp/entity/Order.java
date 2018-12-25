package com.dicka.springbootexamplejsp.entity;

import java.io.Serializable;

public class Order implements Serializable{

	private String orderId;
	private String productName;
	private int quantity;
	private OrderStatus orderStatus;
	
	public Order(){
		
	}
	
	public String getOrderId(){
		return orderId;
	}
	
	public void setOrderId(String orderId){
		this.orderId = orderId;
	}
	
	public String getProductName(){
		return productName;
	}
	
	public void setProductName(String productName){
		this.productName = productName;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	
	public OrderStatus getOrderStatus(){
		return orderStatus;
	}
	
	public void setOrderStatus(OrderStatus orderStatus){
		this.orderStatus = orderStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result
				+ ((orderStatus == null) ? 0 : orderStatus.hashCode());
		result = prime * result
				+ ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + quantity;
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
		Order other = (Order) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (orderStatus != other.orderStatus)
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", productName=" + productName
				+ ", quantity=" + quantity + ", orderStatus=" + orderStatus
				+ "]";
	}
	
	
}
