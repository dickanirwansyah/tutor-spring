package com.dicka.demojpainventory.util;

import java.util.UUID;

public class UtilsId {

	public static String getUniqueID(){
		return UUID.randomUUID().toString();
	}
	
}
