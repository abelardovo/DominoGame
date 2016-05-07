package softwareGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * CLASS PLAYER
 * 
 * @author      Abelardo Valino <abelardo.valino_ovalle@telecom-sudparis.eu>
 * @author		Andres Gonzalez <andres.gonzalez_arria@telecom-sudparis.eu>
 * @version     2.0                 (current version number of program)
 * @since       2016-03-26          (the version of the package this class was first added to)
 */

public class Player<T> 
{	
	private String name;
	private List<Domino<T>> hand;
	
	/**
	 * The constructor to be called by the user application when creating the user's player.
	 * @param n Name of the user's player.
	 */
	public Player(String n)
	{
		this.name = n;
		this.hand = new ArrayList<Domino<T>>();
	}
	
	/**
	 * The constructor to be called by the user application when creating the PC.
	 */
	public Player()
	{	
		this.name = "Computer";
		this.hand = new ArrayList<Domino<T>>();
	}
	
	/**
	 * Method to obtain name of the player. 
	 * @return The name of the player.
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Method to obtain the size of the List "hand".
	 * @return The number of Dominos in the player's hand
	 */
	public int getHandSize()
	{
		return this.hand.size();
	}

	/**
	 * Method to obtain a certain Domino from the hand of the player. 
	 * @param n Index number that refers to the wanted Domino in the player's hand.
	 * @return The Domino in the player's hand, which index is input.
	 */
	public Domino<T> getDomino(int n)
	{
		return this.hand.get(n);
	}
	
	/**
	 * Method to add a given Domino to the player's hand.
	 * @param d Domino to add to the the hand of the player.
	 */
	public void addDomino(Domino<T> d)
	{
		this.hand.add(d);
	}	
	
	/**
	 * Method to remove a Domino from the player's hand, using an index number.
	 * @param n Index number that refers to the wanted Domino in the player's hand.
	 */
	public void removeDomino(int n)
	{
		this.hand.remove(n);
	}
	
	/**
	 * Method to remove a Domino from the player's hand, using the Domino the caller want to remove.
	 * @param d Domino that is to be removed from the player's hand.
	 */
	public void removeDomino(Domino<T> d)
	{	
		for(int n=0; n<this.hand.size(); n++)
		{
			if(this.getDomino(n) == d)
			{
				this.removeDomino(n);
			}		
		}
	}
	
	/**
	 * Method to determine if the last Domino chip has been played.
	 * @return true if the player's hand has no Dominos left, otherwise false.
	 */
	public boolean noMoreDominos()
	{	
		if(this.hand.isEmpty())
		{
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
	public boolean searchDomino(T left,T right)
	{		
		// Iterator for the player's hand.
		Iterator<Domino<T>> handIterator = this.hand.iterator();
		Domino<T> d; 
		
		while (handIterator.hasNext())
		{	
			d = handIterator.next();
			if( (d.getLeftValue().equals(right)) && (d.getRightValue().equals(left)) || 
				(d.getRightValue().equals(right)) && (d.getLeftValue().equals(left)) )
			{			
					return true;		
			}			
		}
		
		return false;
	}
	
	/**
	 * Method that searches the player's hand, to determine if a given Domino, that has double value, is in the player's hand.	 
	 * @param indState Value of the Domino being searched.
	 * @return If it is found, returns the index number "n" in the player's hand, of the Domino being searched, otherwise -1.
	 */
	public int searchForDouble(int indState)
	{	
		// Iterator for the player's hand.
		Iterator<Domino<T>> handIterator = this.hand.iterator();
		Domino<T> d; 
		int n = 0;
		
		while (handIterator.hasNext())
		{	
			d = handIterator.next();

			if(d.isThereDouble())
			{
				if( d.getLeftValue() instanceof Princess)
				{
					if(d.getLeftValue().toString().equals(Princess.ObtainPrincess(indState)))
						return n;
				}else if (d.getLeftValue() instanceof Integer)
					if(d.getLeftValue().equals(indState))
					return n;
			}
			n++;
		}
		
		return -1;
	}

	/**
	 * Method to obtain the String representation of a player's hand.
	 * @return String representation of the player's hand.
	 */
	public String toString()
	{
		String s = this.name + " hand: ";
		
		// Iterator for the player's hand.
		Iterator<Domino<T>> handIterator = this.hand.iterator();
		
		while (handIterator.hasNext())
		{
			s = s + handIterator.next().toString() + " / ";
		}
		
		s = s + "\n";

		return s;
	}
	
}
