package com.fdmgroup.festivalBookingSystem.model;

public enum Genre {
	ROCK("Rock"),
	METAL("Metal"),
	POP("Pop"),
	INDIE("Indie"),
	EDM("Electronic dance music"),
	INSTRUMENTAL("Instrumental"),
	COUNTRY("Country"),
	MIXED("Mixed");
	
	private String name;
	
	private Genre(String genre){
		name = genre;
	}
	
	public String getName() {
		return name;
	}

	public static String getGenre(int index){
		return Genre.values()[index].toString();
	}
}
