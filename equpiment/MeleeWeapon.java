package equpiment;

import character.CharacterStats.Stats;

public class MeleeWeapon extends Weapon {
	
	public MeleeWeapon(String name, String description, int level, Stats stat, float cost, float weight, int damage) {
		super(name, description, level, stat, cost, weight);
		this.damage = damage;
	}

	public MeleeWeapon(String name, String description, int level, Stats stat, float cost, float weight, int damage, Stats additionalStat) {
		super(name, description, level, stat, cost, weight, additionalStat);
		this.damage = damage;
	}
}
