package softwareGame;

/**
 * CLASS PRINCESS
 * 
 * @author      Abelardo Valino <abelardo.valino_ovalle@telecom-sudparis.eu>
 * @author		Andres Gonzalez <andres.gonzalez_arria@telecom-sudparis.eu>
 * @version     1.0                 (current version number of program)
 * @since       2016-05-08          (the version of the package this class was first added to)
 */
public class Princess {
	
	private String name;

	/**
	 * The constructor to be call by the Stock constructor to create all the Dominos<Princesses>.
	 * @param i the value of the domino.
	 */		
	public Princess(int i){
		
		this.name = ObtainPrincess(i);
	}
	
	/**
	 * Method to obtain the name of a princess.
	 * @param i the value of the domino.
	 */	
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
	
	/**
	 * Method to compare two princesses.
	 * @param p a princess.
	 */
	@Override
	public boolean equals(Object p){
		
		Princess paux = (Princess) p;
		return this.name == paux.name;
	}
	
	/**
	 * Method to obtain the String representation of a Princess.
	 * @return String representation of the Princess.
	 */
	public String toString(){
		
		return this.name;
	}
}
