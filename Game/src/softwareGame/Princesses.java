package softwareGame;

public class Princesses {
	
	private String name;

	public Princesses(int i){
		this.name = ObtainPrincess(i);
	}
	
	public static String ObtainPrincess(int i){
		switch (i)
		{
		case 0:
			return "Jasmine";
		case 1:
			return "Aurora";
		case 2:
			return "Cinderella";
		case 3:
			return "Ariel";
		case 4:
			return "Belle";
		case 5:
			return "SnowWhite";
		case 6:
			return "Kairi";
		}
		return "ERROR";
	}
	
	@Override
	public boolean equals(Object p){
		Princesses paux = (Princesses) p;
		return this.name == paux.name;
		
	}
	
	public String toString(){
		return this.name;
	}
}
