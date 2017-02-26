package loaders;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import character.CharacterStats;
import equpiment.*;

public class EquipmentLoader {
	
	private static ArrayList<Equipment> allEquipmentList;
	private static ArrayList<Firearm> firearmsList;
	private static ArrayList<Armour> armourList;
	private static ArrayList<MeleeWeapon> meleeList;
	private static ArrayList<RoboticCompanion> robotsList;
	
	public static ArrayList<Armour> getArmourList() throws NullPointerException{
		if(armourList != null){
			return armourList;
		} else {
			throw new NullPointerException();
		}
	}
	
	//getByLevel(int level) method.
	
	public static ArrayList<Firearm> getFirearmsList() throws NullPointerException{
		if(firearmsList != null){
			return firearmsList;
		} else {
			throw new NullPointerException();
		}
	}
	
	public static ArrayList<MeleeWeapon> getMeleeList() throws NullPointerException{
		if(meleeList != null){
			return meleeList;
		} else {
			throw new NullPointerException();
		}
	}
	
	public static ArrayList<RoboticCompanion> getRobotsList() throws NullPointerException{
		if(robotsList != null){
			return robotsList;
		} else {
			throw new NullPointerException();
		}
	}

	public static void load(){
		allEquipmentList = new ArrayList<Equipment>();
		
		armourList = new ArrayList<Armour>();
		
		try {
			File armourFile = new File("src/textFiles/armour");
			Scanner fileScanner = new Scanner(armourFile);
				
			while(fileScanner.hasNextLine()){
				String line = fileScanner.nextLine();
				String[] splitLine = line.split("-");
				
				String name = splitLine[0];
				String description = splitLine[1];
				int level = Integer.parseInt(splitLine[2]);
				CharacterStats.Stats stat = CharacterStats.charToStat(splitLine[3].toCharArray()[0]);
				float cost = Float.parseFloat(splitLine[4]);
				float weight = Float.parseFloat(splitLine[5]);
				int protection = Integer.parseInt(splitLine[6]);
				
				try{
					CharacterStats.Stats additionalStat = CharacterStats.charToStat(splitLine[7].toCharArray()[0]);
					if(additionalStat != null){
						Armour armour = new Armour(name, description, level, stat, cost, weight, protection, additionalStat);
						armourList.add(armour);
						allEquipmentList.add(armour);
						continue;
					}
				} catch (IllegalArgumentException i){
					System.err.println("Invalid char.");
				} catch (ArrayIndexOutOfBoundsException e){}
				
				Armour armour = new Armour(name, description, level, stat, cost, weight, protection);
				armourList.add(armour);
				allEquipmentList.add(armour);
			}	
			
			fileScanner.close();

		} catch (FileNotFoundException e) {
			System.err.println("Armour file not found.");
		}
		
		firearmsList = new ArrayList<Firearm>();

		try {
			File firearmsF = new File("src/textFiles/firearms");
			Scanner fileScanner = new Scanner(firearmsF);
				
			while(fileScanner.hasNextLine()){
				String line = fileScanner.nextLine();
				String[] splitLine = line.split("-");
				
				String name = splitLine[0];
				String description = splitLine[1];
				int level = Integer.parseInt(splitLine[2]);
				CharacterStats.Stats stat = CharacterStats.charToStat(splitLine[3].toCharArray()[0]);
				float cost = Float.parseFloat(splitLine[4]);
				float weight = Float.parseFloat(splitLine[5]);
				int maxRounds = Integer.parseInt(splitLine[6]);
				int damage = Integer.parseInt(splitLine[7]);
				
				try{
					CharacterStats.Stats additionalStat = CharacterStats.charToStat(splitLine[8].toCharArray()[0]);
					if(additionalStat != null){
						Firearm weapon = new Firearm(name, description, level, stat, cost, weight, maxRounds, damage, additionalStat);
						firearmsList.add(weapon);
						allEquipmentList.add(weapon);
						continue;
					}
				} catch (IllegalArgumentException i){
					System.err.println("Invalid char.");
				} catch (ArrayIndexOutOfBoundsException e){}
				
				Firearm weapon = new Firearm(name, description, level, stat, cost, weight, maxRounds, damage);
				firearmsList.add(weapon);
				allEquipmentList.add(weapon);
			}	
						
			fileScanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("Weapons file not found.");
		}
		
		meleeList = new ArrayList<MeleeWeapon>();
		
		try {
			File meleeF = new File("src/textFiles/meleeWeapons");
			Scanner fileScanner = new Scanner(meleeF);
				
			while(fileScanner.hasNextLine()){
				String line = fileScanner.nextLine();
				String[] splitLine = line.split("-");
				
				String name = splitLine[0];
				String description = splitLine[1];
				int level = Integer.parseInt(splitLine[2]);
				CharacterStats.Stats stat = CharacterStats.charToStat(splitLine[3].toCharArray()[0]);
				float cost = Float.parseFloat(splitLine[4]);
				float weight = Float.parseFloat(splitLine[5]);
				int damage = Integer.parseInt(splitLine[6]);
				
				try{
					CharacterStats.Stats additionalStat = CharacterStats.charToStat(splitLine[7].toCharArray()[0]);
					if(additionalStat != null){
						MeleeWeapon melee = new MeleeWeapon(name, description, level, stat, cost, weight, damage, additionalStat);
						meleeList.add(melee);
						allEquipmentList.add(melee);
						continue;
					}
				} catch (IllegalArgumentException i){
					System.err.println("Invalid char.");
				} catch (ArrayIndexOutOfBoundsException e){}
				
				MeleeWeapon melee = new MeleeWeapon(name, description, level, stat, cost, weight, damage);
				meleeList.add(melee);
				allEquipmentList.add(melee);
			}	
			
			fileScanner.close();

		} catch (FileNotFoundException e) {
			System.err.println("Melee file not found.");
		}
		
		robotsList = new ArrayList<RoboticCompanion>();
		
		try {
			File robotF = new File("src/textFiles/robotCompanions");
			Scanner fileScanner = new Scanner(robotF);
				
			while(fileScanner.hasNextLine()){
				String line = fileScanner.nextLine();
				String[] splitLine = line.split("-");
				
				String name = splitLine[0];
				String description = splitLine[1];
				int level = Integer.parseInt(splitLine[2]);
				CharacterStats.Stats stat = CharacterStats.charToStat(splitLine[3].toCharArray()[0]);
				float cost = Float.parseFloat(splitLine[4]);
				float weight = Float.parseFloat(splitLine[5]);
				int health = Integer.parseInt(splitLine[6]);
				int damage = Integer.parseInt(splitLine[7]);
				int protection = Integer.parseInt(splitLine[8]);
				int deployTime = Integer.parseInt(splitLine[9]);
				int coolTime = Integer.parseInt(splitLine[10]);
				
				try{
					CharacterStats.Stats additionalStat = CharacterStats.charToStat(splitLine[11].toCharArray()[0]);
					if(additionalStat != null){
						RoboticCompanion robot = new RoboticCompanion(name, description, level, stat, cost, weight, 
								health, damage, protection, deployTime, coolTime, additionalStat);
						robotsList.add(robot);
						allEquipmentList.add(robot);
						continue;
					}
				} catch (IllegalArgumentException i){
					System.err.println("Invalid char.");
				} catch (ArrayIndexOutOfBoundsException e){}
				
				RoboticCompanion robot = new RoboticCompanion(name, description, level, stat, cost, weight, 
						health, damage, protection, deployTime, coolTime);				
				robotsList.add(robot);
				allEquipmentList.add(robot);
			}	
			
			fileScanner.close();

		} catch (FileNotFoundException e) {
			System.err.println("Robots file not found.");
		}
	}
}
