package com.dicka.demojpainventory.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.dicka.demojpainventory.service.ServiceRequest;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddNewInventoryRequest implements ServiceRequest{

	//@NotBlank(message = "please enter inventory Id.")
	private String inventoryId;
	
	@NotNull(message = "please enter stock.")
	private int stock;
	
	@NotNull(message = "please enter price.")
	private double price;
	
	//@NotNull(message = "please enter suppliersId.")
	private int suppliersId;
}
