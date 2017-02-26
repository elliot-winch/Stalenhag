package character;

public class Attribute {
	
	private String name;
	private String description;
	//private Alignment alignment;
	
	/*public static enum Alignment{
		POSITIVE,
		NEUTRAL,
		NEGATIVE
	}*/
	
	public Attribute(String name, String description/*, Alignment alignment*/){
		this.name = name;
		this.description = description;
		//this.alignment = alignment;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	/*
	public Alignment getAlignment(){
		return this.alignment;
	}
	
	public static Alignment charToAlignment(char c) throws IllegalArgumentException{
		
		switch(c){
			case 'p': 
				return Alignment.POSITIVE;
			case 'e':
				return Alignment.NEUTRAL;
			case 'n':
				return Alignment.NEGATIVE;
			default:
				throw new IllegalArgumentException();
		}
	}*/
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Attribute)){
			return false;
		}
		
		Attribute a = (Attribute) o;
		
		return a.getName().equals(this.name);
			
	}

	public String toString(){
		return String.format("%s: %s.", this.name, this.description/*, this.alignment.toString()*/);
	}
}
