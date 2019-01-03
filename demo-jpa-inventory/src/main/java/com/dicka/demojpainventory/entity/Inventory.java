package com.dicka.demojpainventory.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventory")
public class Inventory implements Serializable{
	
	@Id
	private String inventoryId;
	private int stock;
	private double price;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "suppliers_id", nullable = false)
	private Suppliers suppliers;
}
