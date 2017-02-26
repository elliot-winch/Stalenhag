package equpiment;

import character.CharacterStats;

public class Equipment {
	
	protected String name;
	protected String description;
	protected int level;
	protected CharacterStats.Stats statUsed;
	protected float cost;
	protected float weight;
	protected CharacterStats.Stats additionalStat;

	
	public Equipment(String name, String description, int level, CharacterStats.Stats stat, float cost, float weight){
		this.name = name;
		this.level = level;
		this.statUsed = stat;
		this.cost = cost;
		this.weight = weight;
		this.description = description;
	}
	
	public Equipment(String name, String description, int level, CharacterStats.Stats stat, float cost, float weight, CharacterStats.Stats additionalStat){
		this(name, description, level, stat, cost, weight);
		this.additionalStat = additionalStat;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public CharacterStats.Stats getStat(){
		return this.statUsed;
	}
	
	public float getCost(){
		return this.cost;
	}
	
	public float getWeight(){
		return this.weight;
	}
	
	public CharacterStats.Stats getAdditionalStat(){
		return this.additionalStat;
	}
	
	public String toString(){
		return String.format("%s\n%s.\nLevel: %d\nStat Used: %s\nCost: $%.2f\nWeight: %.2f\n", this.name, this.description, this.level, this.statUsed.toString(), this.cost, this.weight);
	}
}
