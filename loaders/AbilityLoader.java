package loaders;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import character.Ability;
import character.CharacterStats;

public class AbilityLoader {

	private static ArrayList<Ability> abilityList;
	
	public static ArrayList<Ability> getAbilities(){
		return abilityList;
	}

	public static void load(){
		abilityList = new ArrayList<Ability>();
		
		try {
			File abilityF = new File("src/textFiles/abilities");
			Scanner fileScanner = new Scanner(abilityF);
				
			while(fileScanner.hasNextLine()){
				String line = fileScanner.nextLine();
				String[] splitLine = line.split("-");
				
				String name = splitLine[0];
				String description = splitLine[1];
				CharacterStats.Stats stat = CharacterStats.charToStat(splitLine[2].toCharArray()[0]);
				
				try{
					CharacterStats.Stats additionalStat = CharacterStats.charToStat(splitLine[3].toCharArray()[0]);
					if(additionalStat != null){
						Ability ability = new Ability(name, description, stat, additionalStat);
						abilityList.add(ability);
						continue;
					}
				} catch (IllegalArgumentException i){
					System.err.println("Invalid char.");
				} catch (ArrayIndexOutOfBoundsException e){}				
				
				Ability ability = new Ability(name, description, stat);
				abilityList.add(ability);
			}
			
			fileScanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("Attributes file not found.");
		}
	}
}
