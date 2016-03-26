package softwareGame;
import java.util.*;

/**
 * CLASS STOCK
 * 
 * @author      Abelardo Valino <abelardo.valino_ovalle@telecom-sudparis.eu>
 * @author		Andres Gonzalez <andres.gonzalez_arria@telecom-sudparis.eu>
 * @version     1.0                 (current version number of program)
 * @since       2016-03-26          (the version of the package this class was first added to)
 */
public class Stock {

	List<Domino> pile;
	
	/**
	 * The constructor to be call by the user application. 
	 * It creates all the Dominos of the game and add them to the Stock.  
	 */			
	public Stock(){
		
		this.pile = new ArrayList<Domino>();
		
		//Creates a total of 28 Dominos. SUM(x)from 1 to 7 
		for (int i=6; i>=0; i--){
			for (int j=i;j>=0;j--){
				this.addDomino(new Domino(i,j));
			}
		}	
	}
	
	/**
	 * Method to add the Domino d to the pile which is represented by a List of Dominos.
	 * @param d The Domino to be added.
	 */
	private void addDomino(Domino d){
		this.pile.add(d);
	}
	
	/**
	 * Method to remove from the Stock the Domino that is in the List "pile" at the position n.
	 * @param n The index number of the Domino to be removed.
	 */
	private void removeDomino(int n){
		this.pile.remove(n);
	}
	
	/**
	 * Method to obtain true or false if the Stock is empty or not.
	 * @return True if the pile is empty, otherwise false.
	 */
	public boolean isEmpty(){
		if(this.pile.isEmpty()){
			return true;
		}
		return false;
	}
	
	/**
	 * Method to obtain a random Domino from the Stock.
	 * @return A random Domino from the Stock.
	 */
	public Domino draw(){
		Random rand = new Random();
		int  n = rand.nextInt(this.pile.size());
		
		Domino d = this.pile.get(n);
		this.removeDomino(n);
		
		return d;
	}
	
	/**
	 * Method to obtain the String representation of the Stock.
	 * @return String representation of the Stock.
	 */	
	public String toString(){
		return this.pile.toString();
	}
}
