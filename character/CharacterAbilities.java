package character;

import java.util.ArrayList;

public class CharacterAbilities {
		
	private ArrayList<Ability> abilitiesList;

	public ArrayList<Ability> getAbilites(){
		return this.abilitiesList;
	}
	
	public CharacterAbilities(){
		this.abilitiesList = new ArrayList<Ability>();
	}
	
	public CharacterAbilities(ArrayList<Ability> abilities){
		this.abilitiesList = abilities;
	}
	
	public void addAbility(Ability a){
		this.abilitiesList.add(a);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		
		
		sb.append("\n\nSpecial Abilities:\n\n");
				
		for(Ability a : abilitiesList){
			sb.append(a.toString() + "\n");
		}
		
		return sb.toString();
	}
}