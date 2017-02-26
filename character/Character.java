package character;

import equpiment.Armour;
import equpiment.Firearm;

public class Character {
	
	private String name;
	private CharacterStats cs;
	private CharacterAttributes catt;
	private CharacterAbilities cab;
	private CharacterEquipment ce;
	private int currentHealth;
	private int currentStrain;
	private boolean canAct;
	
	public String getName(){
		return this.name;
	}
	
	public int getCurrentHealth(){
		return this.currentHealth;
	}
	
	public int getCurrentStrain(){
		return this.currentStrain;
	}

	public Character(String name, CharacterStats cs, CharacterEquipment ce, CharacterAttributes catt, CharacterAbilities cab){
		this.name = name;
		this.cs = cs;
		this.catt = catt;
		this.cab = cab;
		this.ce = ce;
		this.currentHealth = this.cs.getStat(CharacterStats.Stats.MAXHEALTH);
		this.currentStrain = 0;
		this.canAct = true;
	}
	
	public void heal(int health){
		this.currentHealth += health;
		
		this.checkAlive();
		
		if(this.currentHealth > this.cs.getStat(CharacterStats.Stats.MAXHEALTH)){
			this.currentHealth = this.cs.getStat(CharacterStats.Stats.MAXHEALTH);
		}
	}
	
	public void takeDamage(int damage){
		if(this.ce.wearingArmour()){
			damage -= this.ce.getCurrentArmour().getProtection();
		}
		currentHealth -= damage;
		this.checkAlive();
	}
	
	public void recuperate(int strain){
		currentStrain -= strain;
		
		if(this.currentStrain < 0){
			this.currentStrain = 0;
		}
	}
	
	public void takeStrain(int strain){
		currentStrain += strain;
		this.checkExhausted();
	}
	
	private void checkAlive(){
		if(this.currentHealth <= 0){
			System.out.printf("\nKnocked out! Health is: %d\n", this.currentHealth);
			this.canAct = false;
			if(currentHealth <= -10){
				System.out.println("Dead!!");
			}
		} else {
			this.canAct = true;
		}
	}
	
	private void checkExhausted(){
		if(this.currentStrain >= this.currentHealth){
			System.out.printf("\nExhausted! Strain is: %d\n", this.currentStrain);
		}
	}
	
	public void performAction(CharacterStats.Stats stat){
		if(canAct){
			System.out.printf("This action requires %d action points.", cs.getStat(stat));
		}
	}
	
	public void performAction(CharacterStats.Stats stat, int strain){
		performAction(stat);
		
		takeStrain(strain);
	}
	
	public void equipArmour(Armour armour){
		//is in inventory?
		ce.setCurrentArmour(armour);
	}
	
	public void equipWeapon(Firearm weapon){
		//is in inventory?
		ce.setCurrentWeapon(weapon);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("\n\nThis character is named %s.\n" , this.name));
		
		sb.append(String.format("\nCurrent status:\nHealth: %d/%d\nStrain:%d/%d\n", this.currentHealth, this.cs.getStat(CharacterStats.Stats.MAXHEALTH), this.currentStrain, this.currentHealth));
		
		sb.append(this.cs.toString());
		
		sb.append(this.ce.toString());
		
		sb.append(this.catt.toString());
		
		sb.append(this.cab.toString());
		
		return sb.toString();
	}
}
