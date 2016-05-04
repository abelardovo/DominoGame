package softwareGame;

import java.util.ArrayList;
import java.util.List;


/**
 * CLASS TABLE
 * 
 * @author      Abelardo Valino <abelardo.valino_ovalle@telecom-sudparis.eu>
 * @author		Andres Gonzalez <andres.gonzalez_arria@telecom-sudparis.eu>
 * @version     1.0                 (current version number of program)
 * @since       2016-03-26          (the version of the package this class was first added to)
 */

public class Table {
	
   	protected static Table tab = null;
	public List<Domino> board = null;
	private int right;
	private int left;

	/**
	 * The constructor to be called by the user application when creating the table in which to play.
	 */
	protected Table(){
		this.right = -1;
		this.left = -1;
		this.board = new ArrayList<Domino>();
	}
	
	/**
	 * 
	 * @return
	 */
	public static Table getTable(){
		if(tab == null){
			tab = new Table();
		}	
		return tab;

			
	}
	/**
	 * Method to obtain the right value of the table, which is one of the two possible values a Domino must have in order to be played.
	 * @return Right value of the table.
	 */
	public int getRightValue(){
		return this.getRight();
	}
	
	/**
	 * Method to obtain the left value of the table, which is one of the two possible values a Domino must have in order to be played.
	 * @return Left value of the table.
	 */
	public int getLeftValue(){
		return this.left;
	}
	
	/**
	 * Method to set initial values to the right and left variables of the table.
	 * @param d Domino that is going to be added to the table, which values are extracted so it will be an acceptable Domino.
	 */
	public void setValue(Domino d){
		this.setRight(d.getRightValue());
		this.left = d.getLeftValue();
	}
	
	/**
	 * Method that checks if a given Domino can be played in the Table. One of its values match with the left or the right value of the table.
	 * @param d Domino that is being verified to confirm it can be played.
	 * @return true if the Domino can be played, otherwise false.
	 */
	public boolean canPlay(Domino d){
		
		if( (this.getRight() == d.getLeftValue()) || (this.getRight() == d.getRightValue()) ||
			(this.left == d.getLeftValue())  || (this.left == d.getRightValue())){
			return true;
		}
		
		return false;	
	}
	
	/**
	 * Method to set the initial Domino in the table.
	 * @param d Domino to be setted in the table.
	 */
	public void initialPlay(Domino d){
		
		this.board.add(d);
		this.left = d.getLeftValue();
		this.setRight(d.getRightValue());
	
	}
	
	/**
	 * Method that adds a given Domino to the table. By default, the given Domino will be added in the left side of the table.
	 * @param d Domino to be added in the table.
	 */
	public void play(Domino d){
		// Checking if values of Domino match the left value of the table. 
		
		if(this.left == d.getRightValue()){
			this.board.add(0,d);
			this.left = d.getLeftValue();
			return;
		}
		
		if(this.left == d.getLeftValue()){
			this.board.add(0,d);
			this.left = d.getRightValue();
			return;
		}

		// Checking if values of Domino match the right value of the table. 
		if(this.getRight() == d.getRightValue()){
			this.board.add(d);
			this.setRight(d.getLeftValue());
			return;
		}
		
		if(this.getRight() == d.getLeftValue()){
			this.board.add(d);
			this.setRight(d.getRightValue());
			return;
		}		
		
		
	}
	
	/**
	 * Method to obtain the String representation of table's state (left and right values).
	 * @return String representation of the table's state.
	 */
	public String printState(){
		return "STATE TABLE: "+this.getLeftValue()+" : "+this.getRightValue()+"\n";
	}
	
	/**
	 * Method to obtain the String representation of Dominos in the table.
	 * @return String representation of the Dominos in the table.
	 */
	public String toString(){
		return this.board.toString();
	}

	/**
	 * @return the right
	 */
	public int getRight() {
		return this.right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(int right) {
		this.right = right;
	}
	
	/**
	 * @return the right
	 */
	public int getLeft() {
		return this.left;
	}

	/**
	 * @param right the right to set
	 */
	public void setLeft(int left) {
		this.left = left;
	}
	
}
