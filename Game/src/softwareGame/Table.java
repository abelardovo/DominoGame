package softwareGame;

import graphicInterface.InterfaceTable;

import java.util.ArrayList;
import java.util.List;

/**
 * CLASS TABLE
 * 
 * @author      Abelardo Valino <abelardo.valino_ovalle@telecom-sudparis.eu>
 * @author		Andres Gonzalez <andres.gonzalez_arria@telecom-sudparis.eu>
 * @version     2.0                 (current version number of program)
 * @since       2016-03-26          (the version of the package this class was first added to)
 */

public class Table<T> implements InterfaceTable<T>
{
	
   	@SuppressWarnings("rawtypes")
	protected static Table tab = null;
	protected List<Domino<T>> board = null;
	private T right;
	private T left;

	/**
	 * The constructor to be called by the Singleton getUniqueInstance method, when creating the table in which to play.
	 */
	private Table(){
		
		this.right = null;
		this.left = null;
		this.board = new ArrayList<Domino<T>>();
	}
	
	/**
	 * The constructor to be called by the user application, when creating the singleton table in which to play.
	 * @return Singleton Table instance.
	 */
	@SuppressWarnings("rawtypes")
	public static Table getTable(){
		
		if(tab == null)
		{
			tab = new Table();
		}	
		return tab;		
	}
	
	/**
	 * Method to obtain the right value of the table, which is one of the two possible values a Domino must have in order to be played.
	 * @return Right value of the table.
	 */
	public T getRightValue(){
		
		return this.right;
	}
	
	/**
	 * Method to obtain the left value of the table, which is one of the two possible values a Domino must have in order to be played.
	 * @return Left value of the table.
	 */
	public T getLeftValue(){
		
		return this.left;
	}
	
	/**
	 * Method to set initial values to the right and left variables of the table.
	 * @param d Domino that is going to be added to the table, which values are extracted so it will be an acceptable Domino.
	 */
	public void setValue(Domino<T> d){
		
		this.setRight(d.getRightValue());
		this.left = d.getLeftValue();
	}
	
	public boolean canPlayRight(Domino<T> d){
		
		if( (d.getLeftValue().equals(this.getRight())) || (d.getRightValue().equals(this.getRight()))){
			return true;	
		}		
		
		return false;	
	}
	
	/**
	 * Method that checks if a given Domino can be played in the Table. One of its values match with the left or the right value of the table.
	 * @param d Domino that is being verified to confirm it can be played.
	 * @return true if the Domino can be played, otherwise false.
	 */
	public boolean canPlay(Domino<T> d){
		
		if( (d.getLeftValue().equals(this.getRight())) || (d.getRightValue().equals(this.getRight())) ||
			(d.getLeftValue().equals(this.getLeft()))  || (d.getRightValue().equals(this.getLeft()))){
			return true;
		}	
		
		return false;	
	}
	
	/**
	 * Method to set the initial Domino in the table.
	 * @param d Domino to be setted in the table.
	 */
	public void initialPlay(Domino<T> d){
		
		this.board.add(d);
		this.left = d.getLeftValue();
		this.setRight(d.getRightValue());
	}
	
	public void playRight(Domino<T> d){
		
		// Checking if values of Domino match the right value of the table. 
		if(this.getRight().equals(d.getRightValue()))
		{
			this.board.add(d);
			this.setRight(d.getLeftValue());
			return;
		}
		
		if(this.getRight().equals(d.getLeftValue()))
		{
			this.board.add(d);
			this.setRight(d.getRightValue());
			return;
		}		
		
	}

	
	/**
	 * Method that adds a given Domino to the table. By default, the given Domino will be added in the left side of the table.
	 * @param d Domino to be added in the table.
	 */
	public void play(Domino<T> d){
		// Checking if values of Domino match the left value of the table. 
		
		if(this.getLeft().equals(d.getRightValue()))
		{
			this.board.add(0,d);
			this.setLeft(d.getLeftValue());
			return;
		}
		
		if(this.getLeft().equals(d.getLeftValue()))
		{
			this.board.add(0,d);
			this.setLeft(d.getRightValue());
			return;
		}

		// Checking if values of Domino match the right value of the table. 
		if(this.getRight().equals(d.getRightValue()))
		{
			this.board.add(d);
			this.setRight(d.getLeftValue());
			return;
		}
		
		if(this.getRight().equals(d.getLeftValue()))
		{
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
		
		if(this.right == null || this.left == null)
			return "STATE TABLE: -1 : -1\n";
		return "STATE TABLE: "+this.getLeftValue().toString()+" : "+this.getRightValue().toString()+"\n";
	}
	
	/**
	 * Method to obtain the String representation of Dominos in the table.
	 * @return String representation of the Dominos in the table.
	 */
	public String toString(){
		
		return this.board.toString();
	}

	/**
	 * Returns the table's right value.
	 * @return the right
	 */
	public T getRight(){
		
		return this.right;
	}

	/**
	 * Sets a value to the right attribute of the table.
	 * @param t the right to set
	 */
	public void setRight(T t){
		
		this.right = t;
	}
	
	/**
	 * Returns the table's left value.
	 * @return the right
	 */
	public T getLeft(){
		
		return this.left;
	}

	/**
	 * Sets a value to the right attribute of the table.
	 * @param right the right to set
	 */
	public void setLeft(T left) {
		this.left = left;
	}
	
}
