package character;

public class Ability {
	
	private String name;
	private String description;
	private CharacterStats.Stats stat;
	private CharacterStats.Stats additionalStat;
	
	public Ability(String name, String description, CharacterStats.Stats stat){
		this.name = name;
		this.description = description;
		this.stat = stat;
	}
	
	public Ability(String name, String description, CharacterStats.Stats stat, CharacterStats.Stats additionalStat){
		this.name = name;
		this.description = description;
		this.stat = stat;
		this.additionalStat = additionalStat;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public CharacterStats.Stats getStat(){
		return this.stat;
	}
	
	public CharacterStats.Stats getAdditionalStat() throws NullPointerException{
		if(this.additionalStat != null){
			return this.additionalStat;
		} else {
			throw new NullPointerException();
		}
	}
	
	public String toString(){
		String s = String.format("%s: %s.\nStat:%s\n", this.name, this.description, this.stat.toString());
		
		if(this.additionalStat != null){
			s += String.format("Additional Stat:%s\n", this.additionalStat.toString());
		}
		
		return s;
	}
}
