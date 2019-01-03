package com.dicka.demojpainventory.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response<T> {

	private Integer code;
	private String status;
	private T data;
	private Map<String, List<String>> errors;
	
	public void addError(String key, String error){
		initializeIfErrorsIfNull();
		initializeListIfNull(key);
		errors.get(key).add(error);
	}
	
	private void initializeIfErrorsIfNull(){
		if (errors == null)
			errors = new HashMap();
	}
	
	private void initializeListIfNull(String key){
		errors.computeIfAbsent(key, k -> new ArrayList<>());
	}
	
	/** pemanggilan class dalam sebuah class **/
	
	/** response data ok **/
	public static<T> Response<T> ok(T data){
		Assert.notNull(data, "data tidak boleh kosong");
		return Response.status(HttpStatus.OK, data);
	}
	
	/** response data tidak ada tapi tidak error **/
	public static<T> Response<T> okOrNotFound(@Nullable T data){
		if(Objects.isNull(data)){
			return Response.status(HttpStatus.NOT_FOUND, null);
		}else{
			return Response.ok(data);
		}
	}
	
	public static<T> Response<T> status(HttpStatus status, @Nullable T data){
		return Response.<T>builder()
				.code(status.value())
				.status(status.getReasonPhrase())
				.data(data)
				.build();
	}
}
