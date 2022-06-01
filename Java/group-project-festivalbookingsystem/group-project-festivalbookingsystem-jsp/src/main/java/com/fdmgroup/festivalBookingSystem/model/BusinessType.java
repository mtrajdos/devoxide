package com.fdmgroup.festivalBookingSystem.model;

public enum BusinessType {
	ACCOMMODATION("Accommodation"),
	RESTAURANT("Restaurant"),
	MERCHANDISE("Merchandise");
	
	private String name;
	
	private BusinessType(String type){
		name = type;
	}
	
	public String getName() {
		return name;
	}

	public static String getBusinessType(int index){
		return BusinessType.values()[index].toString();
	}

}
