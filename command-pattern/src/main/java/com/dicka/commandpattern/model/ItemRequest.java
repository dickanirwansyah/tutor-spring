package com.dicka.commandpattern.model;

import com.dicka.commandpattern.commandPattern.ServiceRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest implements ServiceRequest{

	private String itemName;
	private double itemPrice;
	
}
