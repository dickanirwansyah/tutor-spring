package com.dicka.demojpainventory.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.dicka.demojpainventory.service.ServiceRequest;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInventoryRequest implements ServiceRequest{
	
	//@NotBlank(message = "kode inventory masih kosong.")
	private String inventoryId;
	
	//@Size(max = 100, min = 1, message = "max 100 dan min 1")
	//@NotNull(message = "stock masih kosong.")
	private Integer stock;
	
	@NotNull(message = "price masih kosong.")
	private double price;

	private int suppliersId;
}
