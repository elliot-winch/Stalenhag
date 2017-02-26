package characterCreator;

import java.util.ArrayList;
import java.util.Scanner;

import character.*;
import character.Character;
import equpiment.*;
import loaders.AbilityLoader;
import loaders.AttributeLoader;
import loaders.EquipmentLoader;

public class CharacterCreator {
	
	//private static CharacterStats.Stats lowestStat;
	private static String[] statDescriptions = {"Close quaters and hand-to-hand combat",
										"Firearms handling and accuracy",
										"Noticing physical details and searching",
										"Persausion and emotional empathy",
										"Stamina and resistance to damage",
										"Hacking and engineering knowledge",
										"Medicine and survival"};
	
	public static final int startingStatPoints = 21;
	public static final int startingStatValue = 9;
	public static final int minStatValue = 3;
	
	public static final int startingDollars = 1000;
	
	public static final int numberOfAttributes = 2;
	public static final int numberOfAbilities = 3;
	
	private static final long sleepTime = 4000;
	
	public static void main(String[] args){
		
		EquipmentLoader.load();
		AttributeLoader.load();
		AbilityLoader.load();
				
		Scanner in = new Scanner(System.in);
		
		System.out.println("Welcome to the Stalenhag Character Creator!\n");
		
		try{
			CharacterStats cs = makeCharacterStats(in);
			
			Thread.sleep(sleepTime);
			
			CharacterEquipment ce = makeCharacterEquipment(in);
			
			Thread.sleep(sleepTime);
			
			CharacterAttributes catt = makeCharacterAttributes(in);
			
			Thread.sleep(sleepTime);
	
			CharacterAbilities cab = makeCharacterAbilities(in);
			
			Thread.sleep(sleepTime);
	
			System.out.print("\n\nPlease enter a name for your character: ");
			
			String name = in.next();
			
			Character c = new Character(name, cs, ce, catt, cab);
			
			System.out.println(c.toString());
		
		}catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	private static CharacterStats makeCharacterStats(Scanner in) throws InterruptedException{
		System.out.println("First, we will work on your character's stats.\n\n"
				+ "The stats for a player character in Stalenhag are:\n");
		
		CharacterStats.Stats[] values = CharacterStats.Stats.values();
		for(int i = 0; i < values.length - 1; i++){
			System.out.println(values[i] + " - " + statDescriptions[i]);
		}
		
		System.out.println(String.format("\nThe lower a stat's value, the better. All stats start at %d\n"
				+ "and you have %d points to allocate; points allocated reduce\nthe stat's value "
				+ "(i.e. improve it). The minimum value for a stat is %d.\n", startingStatValue, startingStatPoints, minStatValue));
		
		Thread.sleep(sleepTime);
		
		int points = startingStatPoints;
		int[] statsArray = new int[7];
		
		for(int i = 0; i < statsArray.length; i++){
			statsArray[i] = startingStatValue;
		}
		
		int i = 0;
		
		outerloop:
		do{
			while(i < statsArray.length){
				if(points == 0){
					System.out.println("No points remaining.");
					System.out.println(String.format("You %s stat is now %d.\n", values[i], statsArray[i]));
					i++;
					continue;
				}
				
				System.out.println(String.format("From 0 to %d, how many points would you\nlike to assign to %s? You have %d remaining.", startingStatValue - minStatValue, values[i], points));
	
				int userInt = in.nextInt();		
				
				if(userInt < 0){
					System.err.println("Value cannot be less than 0");
					continue;
				}
				
				if(userInt > startingStatValue - minStatValue){
					System.err.println(String.format("Stat cannot be less than %d", minStatValue));
					continue;
				}
				
				if(points - userInt < 0){
					System.err.println("Not enough points remaining! Assigning all remaining points to this stat");
					statsArray[i] -= points;
					points = 0;
					System.out.println(String.format("Your %s stat is now %d.\n", values[i], statsArray[i]));
					continue;
				}
				
				statsArray[i] -= userInt;
				points -= userInt;
				
				System.out.println(String.format("Your %s stat is now %d.\n", values[i], statsArray[i]));
				
				i++;
				
				if(i == 7 && points > 0){
					System.out.println("Not all points allocated. Would you like to reallocate?: Y/N");
					
					char input = 0;
					try{
						input = in.nextLine().charAt(0);
					} catch (StringIndexOutOfBoundsException e){}

					while(input != 'Y' && input != 'y' && input != 'N' && input != 'n'){
						System.out.println("Invalid character entered. Not all points allocated. Would you like to reallocate?: Y/N");
						input = in.next().charAt(0);
					}
					
					if(input == 'N' || input == 'n'){
						System.out.println("Okay! N.B the game might be significantly harder.");
						break outerloop;
					} 	
					
					if(input == 'Y' || input == 'y'){
						i = 0;
					}
				}
			}
		} while (points != 0);
		
		/*
		//Determine lowest stat for specialism
		int min = 0;
		int dupes = 0;
		for(int j = 0; j < statsArray.length; j++){
			if(statsArray[j] < min){
				min = j;
				dupes = 0;
			}
			
			if(statsArray[j] == min){
				dupes++;
			}
		}
		
		if(dupes == 0){
			//lowestStat = (CharacterStats.Stats) i;
		} else {
			//int rand = 
			//lowestStat = 
		}*/
		
		return new CharacterStats(statsArray);
	}
	
	private static CharacterEquipment makeCharacterEquipment(Scanner in) throws InterruptedException{
		
		CharacterEquipment ce = new CharacterEquipment(startingDollars, 2);
		
		System.out.println(String.format("\nNext we will give your character some gear.\nYou have $%.2f to spend on "
				+ "firearms, melee weapons,\n armour, robotic companions, food and miscellaneous equipment.\n", ce.getDollars()));
		
		Thread.sleep(sleepTime);
		
		ce = buyEquipmentType(ce, in, EquipmentLoader.getFirearmsList());
		ce = buyEquipmentType(ce, in, EquipmentLoader.getMeleeList());
		ce = buyEquipmentType(ce, in, EquipmentLoader.getArmourList());
		ce = buyEquipmentType(ce, in, EquipmentLoader.getRobotsList());
		
		return ce;
	}
	
	private static CharacterAttributes makeCharacterAttributes(Scanner in) throws InterruptedException{
		System.out.println("Next we will pick attributes. Attributes are descriptors that both inform\n"
				+ "you character's personality and give you small advantages/disadvantages.\n"
				+ "Choose two of the following attributes by typing the number associated with them:\n");
		
		Thread.sleep(sleepTime);
		
		CharacterAttributes catt = new CharacterAttributes();
		
		ArrayList<Attribute> attributesList = AttributeLoader.getAttributes();
		
		printArrayList(attributesList);

		for(int i = 0; i < numberOfAttributes; i++){			
			int userInt = in.nextInt();			
			
			if(userInt <= -1 || userInt >= attributesList.size()){
				System.err.println("Not a vaild attribute number.");
				i--;
				continue;
			}
			
			try{
				catt.addAttribute(attributesList.get(userInt));
				System.out.println(String.format("Added %s to your attributes.", attributesList.get(userInt).getName()));
			}catch(IllegalArgumentException e){
				System.err.println(e.getMessage());
				i--;
			}
		}
		
		System.out.println("Done assigning attributes.");
		
		return catt;
	}
	
	public static CharacterAbilities makeCharacterAbilities(Scanner in) throws InterruptedException{
		System.out.println(String.format("\n\nFinally, we will pick your character's unique abilities.\n"
				+ "Choose %d from the list. You should choose abilities that use your better stats.\n"
				+ "For example, if a stat uses ballistics, and your ballistics is 4, then your will\n"
				+ "be able to use that ability more often and more effectively.\n"
				+ "Choose %d from the list by typing the number associated with them.\n", numberOfAbilities, numberOfAbilities));
		
		Thread.sleep(sleepTime);
		
		CharacterAbilities cab = new CharacterAbilities();
		
		ArrayList<Ability> abilityList = AbilityLoader.getAbilities();
		
		printArrayList(abilityList);

		for(int i = 0; i < numberOfAbilities; i++){			
			int userInt = in.nextInt();			
			
			if(userInt <= -1 || userInt >= abilityList.size()){
				System.err.println("Not a vaild ability number.");
				i--;
				continue;
			}
			
			try{
				cab.addAbility(abilityList.get(userInt));
				System.out.println(String.format("Added %s to your abilities.", abilityList.get(userInt).getName()));
			}catch(IllegalArgumentException e){
				System.err.println(e.getMessage());
				i--;
			}
		}	
		
		System.out.println("Done assigning abilities.");
		
		return cab;
	}
	
	private static <E extends Equipment> CharacterEquipment buyEquipmentType(CharacterEquipment ce, Scanner in, ArrayList<E> list) throws InterruptedException{
		System.out.printf("%s: \n", list.get(0).getClass().toString());
		printArrayList(list);
		System.out.println("Buy as many as you want, but you can only use one at a time.\n\n");
		
		int userInt = 0;
		
		while(ce.hasDollars()){
			System.out.println("What would you like to buy? Enter the number of the item \nand hit enter to buy. Type '-1' to finish buying.");
			
			userInt = in.nextInt();			
			
			if(userInt == -1){
				break;
			}
			
			if(userInt <= -1 || userInt >= list.size()){
				System.err.println("Not a vaild number.");
				continue;
			}
			
			try{
				E f = list.get(userInt);
				ce.addEquipment(f);
				ce.spendDollars(f.getCost());
				System.out.println(String.format("%s added to your inventory", f.getName()));
			}catch(IllegalArgumentException e){
				System.err.println(e.getMessage());
			}
		}	
		
		System.out.printf("Done buying %s.\n\n", list.get(0).getClass().toString());
		Thread.sleep(sleepTime);
		
		return ce;
	}
	
	private static <E> void printArrayList(ArrayList<E> list){
		
		for(int i = 0; i < list.size(); i++){
			System.out.println(i+ ": " + list.get(i).toString());
		}
		
		System.out.println("\n");
	}
}
