package equpiment;

import character.CharacterStats.Stats;

public class Weapon extends Equipment{
	
	protected int damage;
	
	public Weapon(String name, String description, int level, Stats stat, float cost, float weight) {
		super(name, description, level, stat, cost, weight);
	}	
		
	public Weapon(String name, String description, int level, Stats stat, float cost, float weight, Stats additionalStat) {
		super(name, description, level, stat, cost, weight, additionalStat);
	}

	public int getDamage(){
		return this.damage;
	}
}
