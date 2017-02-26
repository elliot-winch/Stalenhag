package equpiment;

import character.CharacterStats;

public class Firearm extends Weapon {
	
	private int maxRounds;	
	private int currentRounds;
	private boolean canAct;
	
	public Firearm(String name, String description, int level, CharacterStats.Stats stat, float cost, float weight, int maxRounds, int damage){
		super(name, description, level, stat, cost, weight); 
		this.damage = damage;
		this.maxRounds = maxRounds;
		this.currentRounds = this.maxRounds;
		this.canAct = true;
	}
	
	public Firearm(String name, String description, int level, CharacterStats.Stats stat, float cost, float weight, int maxRounds, int damage, CharacterStats.Stats additionalStat){
		super(name, description, level, stat, cost, weight, additionalStat); 
		this.damage = damage;
		this.maxRounds = maxRounds;
		this.currentRounds = this.maxRounds;
		this.canAct = true;
	}
	
	public int getMaxAmmo(){
		return this.maxRounds;
	}
	
	public void useAmmo(){
		if(this.canAct){
			this.currentRounds--;
		} else {
			System.err.println("Cannot fire. Must reload.");
		}
		
		if(this.currentRounds <= 0){
			System.out.println("Weapon out of ammo. Must reload.");
			this.canAct = false;
		}
	}
	
	public void reload(){
		this.currentRounds = this.maxRounds;		
		this.canAct = true;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		sb.append(super.toString());
		
		sb.append(String.format("Damage: %d\nMax Rounds: %d\n", this.damage, this.maxRounds));
		
		if(additionalStat != null){
			sb.append(String.format("Additional Stat: %s\n", this.additionalStat));
		}
		
		return sb.toString();
	}
}
