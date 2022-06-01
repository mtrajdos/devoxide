package com.fdmgroup.demo.model;

public enum Position {
	TOP("Top Lane"),
	MID("Mid Lane"),
	JUNGLE("Jungle"),
	BOTTOM("Bottom"),
	SUPPORT("Support");
	
	private String name;
	
	private Position(String position){
		name = position;
	}

	public String getName() {
		return name;
	}

	public static String getPosition(int index){
		return Position.values()[index].toString();
	}
}
