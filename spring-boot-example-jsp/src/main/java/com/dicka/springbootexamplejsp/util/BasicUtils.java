package com.dicka.springbootexamplejsp.util;

import java.util.UUID;

public class BasicUtils {

	/** UUID **/
	public static String getUniqueID(){
		return UUID.randomUUID().toString();
	}
	
}
