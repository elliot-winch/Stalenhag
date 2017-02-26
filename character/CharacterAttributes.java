package character;

import java.util.ArrayList;

public class CharacterAttributes {
	
	private ArrayList<Attribute> attributes;

	public ArrayList<Attribute> getAttributes(){
		return this.attributes;
	}
	
	public CharacterAttributes(){
		this.attributes = new ArrayList<Attribute>();
	}
	
	public CharacterAttributes(ArrayList<Attribute> attributes){
		this.attributes = attributes;
	}
	
	public void addAttribute(Attribute a) throws IllegalArgumentException{
		for(Attribute e : attributes){
			if(e.equals(a)){
				throw new IllegalArgumentException("Already in attributes list");
			}
		}
		this.attributes.add(a);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		sb.append("\nAttributes:\n\n");
		
		for(Attribute a : attributes){
			sb.append(a.toString() + "\n");
		}
		
		return sb.toString();
	}
}
