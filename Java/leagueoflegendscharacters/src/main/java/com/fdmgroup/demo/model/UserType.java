package com.fdmgroup.demo.model;

public enum UserType {
	ADMIN("Administrator"),
	SUMMONER("Summoner");
	
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
