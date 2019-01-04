package com.dicka.demojpainventory.request;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.dicka.demojpainventory.service.ServiceRequest;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSuppliersRequest implements ServiceRequest{

	private int id;
	
	@NotBlank(message = "please enter name.")
	private String name;
	
	@NotBlank(message = "please enter phone.")
	private String phone;
	
	@NotBlank(message = "please enter address.")
	private String address;
	
	@Email(message = "please enter valid email.")
	@NotBlank(message = "please enter email.")
	private String email;
}
