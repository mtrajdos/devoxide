package com.fdmgroup.demo.model;

public enum DamageSource {
	AP("Ability Power"),
	AD("Attack Damage"),
	MIXED("Ability Power and Attack Damage");
	
	private String name;
	
	private DamageSource(String damage){
		name = damage;
	}
	
	public String getName() {
		return name;
	}

	public static String getDamageSource(int index){
		return DamageSource.values()[index].toString();
	}
}
