package character;

import java.util.ArrayList;
import equpiment.*;

public class CharacterEquipment {
	
	private ArrayList<Equipment> equipmentList;
	private float dollars;
	private float daysOfSupplies;
	
	private Armour currentArmour;
	private Weapon currentWeapon;
	
	public CharacterEquipment(float dollars, float daysOfSupplies){
		equipmentList = new ArrayList<Equipment>();
		this.dollars = dollars;
		this.daysOfSupplies = daysOfSupplies;
	}
	
	public CharacterEquipment(){
		equipmentList = new ArrayList<Equipment>();
		this.dollars = 0;
		this.daysOfSupplies = 0;
	}
	
	public ArrayList<Equipment> getEquipment(){
		return this.equipmentList;
	}
	
	public void addEquipment(Equipment e){
		this.equipmentList.add(e);
	}
	
	public float getDollars(){
		return this.dollars;
	}
	
	public boolean hasDollars(){
		return this.dollars > 0;
	}
	
	public void spendDollars(float amount) throws IllegalArgumentException{
		if(this.dollars - amount < 0){
			throw new IllegalArgumentException("Not enough cash.");
		}
		
		this.dollars -= amount;
		System.out.println(String.format("You now have $%f.", this.dollars));
	}
	
	public void earnDollars(float amount) throws IllegalArgumentException{
		if(amount < 0){
			throw new IllegalArgumentException("Cannot add negative dollars.");
		}
		
		this.dollars += amount;
		System.out.println(String.format("You now have $%d.", this.dollars));
	}
	
	
	public float getDaysOfFood(){
		return this.daysOfSupplies;
	}
	
	public boolean wearingArmour(){
		return this.currentArmour != null;
	}
	
	public Armour getCurrentArmour(){
		return this.currentArmour;
	}
	
	public boolean weaponEquiped(){
		return this.currentWeapon != null;
	}
	
	public Weapon getCurrentWeapon(){
		return this.currentWeapon;
	}
	
	public void setCurrentArmour(Armour armour){
		this.currentArmour = armour;
	}
	
	public void setCurrentWeapon(Weapon weapon){
		this.currentWeapon = weapon;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		sb.append("Invetory:\n\n");
		
		if(this.currentWeapon != null){
			sb.append(String.format("Current Weapon: %s\n", this.currentWeapon.toString()));
		}
		
		if(this.currentArmour != null){
			sb.append(String.format("Current Armour:%s\n", this.currentArmour.toString()));
		}
		
		sb.append(String.format("Dollars: $%.2f.\nSupplies remaining: %.2f days\n\n", this.dollars, this.daysOfSupplies));
		
		sb.append("Other equipment:\n");
		
		for(Equipment e : equipmentList){
			sb.append(e.toString() + "\n");
		}
		
		sb.append("\n");
		
		return sb.toString();
	}
}
