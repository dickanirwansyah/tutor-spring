package com.dicka.demojpainventory.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
public class AddNewSuppliersRequest implements ServiceRequest{

	private int id;
	
	@NotBlank(message = "please enter name.")
	private String name;
	
	@NotBlank(message = "please enter phone.")
	private String phone;
	
	@Size(max = 100, min = 10)
	@NotBlank(message = "please enter address.")
	private String address;
	
	@NotBlank(message = "please enter email.")
	@Email(message = "please enter valid email.")
	private String email;
}
