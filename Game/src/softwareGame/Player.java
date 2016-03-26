package softwareGame;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	String name;
	List<Domino> hand;
	
	/**
	 * The constructor to be called by the user application when creating the user's player.
	 * @param n Name of the user's player.
	 */
	public Player(String n){
		
		this.name = n;
		this.hand = new ArrayList<Domino>();
		
	}
	
	/**
	 * The constructor to be called by the user application when creating the PC.
	 */
	public Player(){
		
		this.name = "Computer";
		this.hand = new ArrayList<Domino>();
	}
	
	/**
	 * Method to obtain name of the player. 
	 * @return The name of the player.
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Method to obtain a certain Domino from the hand of the player. 
	 * @param n Index number that refers to the wanted Domino in the player's hand.
	 * @return The Domino in the player's hand, which index is input.
	 */
	public Domino getDomino(int n){
		return this.hand.get(n);
	}
	
	/**
	 * Method to add a given Domino to the player's hand.
	 * @param d Domino to add to the the hand of the player.
	 */
	public void addDomino(Domino d){
		this.hand.add(d);
	}	
	
	/**
	 * Method to remove a Domino from the player's hand, using an index number.
	 * @param n Index number that refers to the wanted Domino in the player's hand.
	 */
	public void removeDomino(int n){
		this.hand.remove(n);
	}
	
	/**
	 * Method to remove a Domino from the player's hand, using the Domino the caller want to remove.
	 * @param d Domino that is to be removed from the player's hand.
	 */
	public void removeDomino(Domino d){
		
		for(int n=0; n<this.hand.size(); n++){
			if(this.getDomino(n) == d){
				this.removeDomino(n);
			}		
		}
	}
	
	/**
	 * Method to determine if the last Domino chip has been played.
	 * @return true if the player's hand has no Dominos left, otherwise false.
	 */
	public boolean noMoreDominos(){
		
		if(this.hand.isEmpty()){
			return true;
		}
		return false;
	}
	
	/**
	 * Method that searches the player's hand, to determine if a given Domino is in the player's hand.
	 * @param left Left value of the Domino that is being searched.
	 * @param right Right value of the Domino being searched.
	 * @return true if the Domino given in the input is in the player's hand, otherwise false.
	 */
	public boolean searchDomino(int left,int right){
		
		for(int n=0; n<this.hand.size(); n++){
			
			if( (right == this.getDomino(n).getLeftValue()) || (right == this.getDomino(n).getRightValue()) ||
				(left == this.getDomino(n).getLeftValue())  || (left == this.getDomino(n).getRightValue())){
					
				return true;	
			}
		}
		return false;
	}
	
	/**
	 * Method that searches the player's hand, to determine if a given Domino, that has double value, is in the player's hand.	 
	 * @param i Value of the Domino being searched.
	 * @return If it is found, returns the index number "n" in the player's hand, of the Domino being searched, otherwise -1.
	 */
	public int searchForDouble(int i){
		
		for(int n=0; n<this.hand.size(); n++){
			
			if(this.getDomino(n).isThereDouble()){
	
				if(this.getDomino(n).getLeftValue() == i)
					return n;
			}
			
		}
		return -1;
	}

	/**
	 * Method to obtain the String representation of a player's hand.
	 * @return String representation of the player's hand.
	 */
	public String toString(){
		String s = this.name + " hand: ";
		for (int i=0; i<this.hand.size();i++){
			s = s + this.hand.get(i).toString() + " / ";
		}
		
		s = s + "\n";

		return s;
	}
	
}
