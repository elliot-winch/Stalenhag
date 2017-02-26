package loaders;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import character.Attribute;

public class AttributeLoader {
	
	private static ArrayList<Attribute> attributesList;
	
	public static ArrayList<Attribute> getAttributes(){
		return attributesList;
	}

	public static void load(){
		attributesList = new ArrayList<Attribute>();
		
		try {
			File attributeF = new File("src/textFiles/attributes");
			Scanner fileScanner = new Scanner(attributeF);
				
			while(fileScanner.hasNextLine()){
				String line = fileScanner.nextLine();
				String[] splitLine = line.split("-");
				
				String name = splitLine[0];
				String description = splitLine[1];
				//Attribute.Alignment alignment = Attribute.charToAlignment(splitLine[2].toCharArray()[0]);
				
				Attribute attribute = new Attribute(name, description/*, alignment*/);
				attributesList.add(attribute);
			}	
			
			fileScanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("Attributes file not found.");
		}
	}
}
