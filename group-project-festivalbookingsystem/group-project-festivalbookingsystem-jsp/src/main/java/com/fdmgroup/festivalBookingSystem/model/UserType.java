package com.fdmgroup.festivalBookingSystem.model;

public enum UserType {
	ADMIN("Admin"),
	ATTENDEE("Attendee");
	
	private String name;
	
	private UserType(String type){
		name = type;
	}
	
	public String getName() {
		return name;
	}

	public static String getUserType(int index){
		return UserType.values()[index].toString();
	}
}
