package equpiment;

import character.CharacterStats;

public class Armour extends Equipment{
	 
	private int protection;
	
	public Armour(String name, String description, int level, CharacterStats.Stats stat, float cost, float weight,  int protection){
		super(name, description, level, stat, cost, weight);
		this.protection = protection;
	}
	
	public Armour(String name, String description, int level, CharacterStats.Stats stat, float cost, float weight,  int protection, CharacterStats.Stats additionalStat){
		super(name, description, level, stat, cost, weight, additionalStat);
		this.protection = protection;
	}
	
	public int getProtection(){
		return this.protection;
	}
	
	public String toString(){
		String toReturn = super.toString() + String.format("Protection: %d\n", this.protection);
		
		if(this.additionalStat != null){
			toReturn += String.format("Additional Stat: %s\n", this.additionalStat.toString());
		}
		
		return toReturn + "\n";
	}
}
