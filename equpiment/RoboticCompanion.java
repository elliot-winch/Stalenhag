package equpiment;

import character.CharacterStats;

public class RoboticCompanion extends Equipment{
	
	private int currentHealth;
	private boolean damaged;
	
	private int maxHealth;
	private int damage;
	private int protection;
	private int deployTime;
	private int coolTime;

	public RoboticCompanion(String name, String description, int level, CharacterStats.Stats stat, float cost, float weight, 
			int health, int damage, int protection, int deployTime, int coolTime) {
		super(name, description, level, stat, cost, weight);
		this.maxHealth = health;
		this.damage = damage;
		this.protection = protection;
		this.deployTime = deployTime;
		this.coolTime = coolTime;
		
		this.currentHealth = this.maxHealth;
		this.damaged = false;
	}
	
	public RoboticCompanion(String name, String description, int level, CharacterStats.Stats stat, float cost, float weight, 
			int health, int damage, int protection, int deployTime, int coolTime, CharacterStats.Stats additionalStat) {
		super(name, description, level, stat, cost, weight, additionalStat);
		this.maxHealth = health;
		this.damage = damage;
		this.protection = protection;
		this.deployTime = deployTime;
		this.coolTime = coolTime;
		
		this.currentHealth = this.maxHealth;
		this.damaged = false;
	}
	
	public int getMaxHealth(){
		return this.maxHealth;
	}
	
	public int getCurrentHealth(){
		return this.currentHealth;
	}
	
	public int getDamage(){
		return this.damage;
	}
	
	public int getProtection(){
		return this.protection;
	}
	
	public int getDeployTime(){
		return this.deployTime;
	}
	
	public int getCoolTime(){
		return this.coolTime;
	}
	
	public void heal(int health){
		this.currentHealth += health;
		
		if(this.currentHealth > this.maxHealth){
			this.currentHealth = this.maxHealth;
		}
	}
	
	public void takeDamage(int damage){
		damage -= this.protection;
		
		this.currentHealth -= damage;
		
		if(this.currentHealth <= 0){
			this.damaged = true;
		}
	}
	
	public void repair(int amount){
		if(this.damaged){
			this.currentHealth = 1;
		} else {
			this.currentHealth += amount;
		}		
	}
}
