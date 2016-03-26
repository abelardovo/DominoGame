package softwareGame;
import graphicInterface.InterfaceDomino;

/**
 * CLASS DOMINO
 * 
 * @author      Abelardo Valino <abelardo.valino_ovalle@telecom-sudparis.eu>
 * @author		Andres Gonzalez <andres.gonzalez_arria@telecom-sudparis.eu>
 * @version     1.0                 (current version number of program)
 * @since       2016-03-26          (the version of the package this class was first added to)
 */
public class Domino implements InterfaceDomino{
	
	int right;
	int left;
	
	/**
	 * The constructor to be call by the Stock constructor to create all the Dominos.
	 * @param r The right value of the Domino.
	 * @param l The left value of the Domino. 
	 */		
	public Domino(int r, int l){
		this.right = r;
		this.left = l;
	}
	
	/**
	 * Method to obtain the right value of a Domino.
	 * @return the right value of the Domino.
	 */		
	public int getRightValue(){
		return this.right;
	}
	
	/**
	 * Method to obtain the left value of a Domino.
	 * @return Returns the left value of the Domino.
	 */	
	public int getLeftValue(){
		return this.left;
	}	
	
	/**
	 * Method to obtain true or false if the Domino is a double or not.
	 * @return true if the left and right value of a Domino are equal, otherwise false.
	 */		
	public boolean isThereDouble(){
		if(this.getLeftValue() == this.getRightValue())
			return true;
		return false;
	}
	
	/**
	 * Method to obtain the String representation of a Domino.
	 * @return String representation of the Domino.
	 */
	public String toString(){
		return Integer.toString(this.left)+":"+Integer.toString(this.right);
	}
}
