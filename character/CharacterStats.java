package character;

import characterCreator.CharacterCreator;

public class CharacterStats {
	
	public static final int healthCalculator = 13;
	
	private int melee;
	private int ballistics;
	private int perception;
	private int charisma;
	private int endurance;
	private int robotics;
	private int healing;
	
	public static enum Stats{
		MELEE,
		BALLISTICS,
		PERCEPTION,
		CHARISMA,
		ENDURANCE,
		ROBOTICS,
		HEALING,
		MAXHEALTH
	}
	
	private int maxHealth;

	public CharacterStats(int melee, int ballistics, int perception, int charisma, int endurance, int robotics, int healing){
		int baseStatNumber = melee + ballistics + perception + charisma + endurance + robotics + healing;
		
		if(baseStatNumber > CharacterCreator.startingStatPoints || baseStatNumber < CharacterCreator.minStatValue){
			System.err.printf("Warning: sum of stats should be between %d and %d.", CharacterCreator.minStatValue, CharacterCreator.startingStatPoints);
		}
		
		this.melee = melee;
		this.ballistics = ballistics;
		this.perception = perception;
		this.charisma = charisma;
		this.endurance = endurance;
		this.robotics = robotics;	
		this.healing = healing;
		this.maxHealth = calculateMaxHealth();
	}
	
	public CharacterStats(int[] stats) throws IllegalArgumentException{
		for(int i = 0; i < stats.length; i++){
			if(stats[i] > CharacterCreator.startingStatPoints || stats[i] < CharacterCreator.minStatValue){
				throw new IllegalArgumentException(String.format("Stat must be between %d and %d", CharacterCreator.startingStatPoints, CharacterCreator.startingStatPoints));
			}
		}
		
		this.melee = stats[0];
		this.ballistics = stats[1];
		this.perception = stats[2];
		this.charisma = stats[3];
		this.endurance = stats[4];
		this.robotics = stats[5];	
		this.healing = stats[6];
		this.maxHealth = calculateMaxHealth();
	}
		
	private int calculateMaxHealth(){
		return healthCalculator - this.endurance;
	}
	
	public int getStat(Stats stat){
		
		switch(stat){
		case MELEE: 
			return this.melee;
		case BALLISTICS:
			return this.ballistics;
		case PERCEPTION:
			return this.perception;
		case CHARISMA:
			return this.charisma;
		case ENDURANCE:
			return this.endurance;
		case ROBOTICS:
			return this.robotics;
		case HEALING:
			return this.healing;
		case MAXHEALTH:
			return this.maxHealth;
		default:
			System.err.println("Not a valid type: returning 0.");
			return 0;
		}
	}
	
	public static Stats charToStat(char c) throws IllegalArgumentException {
		
		switch(c){
		case 'm': 
			return Stats.MELEE;
		case 'b': 
			return Stats.BALLISTICS;
		case 'p': 
			return Stats.PERCEPTION;
		case 'e': 
			return Stats.ENDURANCE;
		case 'c': 
			return Stats.CHARISMA;
		case 'r': 
			return Stats.ROBOTICS;
		case 'h': 
			return Stats.HEALING;			
		default:
			throw new IllegalArgumentException(String.format("%c is not allowed", c));
		}
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("\nThis character has the following stats:\n");
		
		for(Stats stat : Stats.values()){	
			sb.append(String.format("%s: %d\n", stat, getStat(stat)));
		}
		
		sb.append("\n");
		
		return sb.toString();
		
	}
}
