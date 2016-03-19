package softwareGame;

import java.util.ArrayList;
import java.util.List;

public class Table {
	
	List<Domino> board;
	int right;
	int left;

	public Table(){
		this.board = new ArrayList<Domino>();
		this.right = -1;
		this.left = -1;
	}
	
	public int getRightValue(){
		return this.right;
	}
	
	public int getLeftValue(){
		return this.left;
	}
	
	public boolean canPlay(Domino d){
		return true;
	}
	
	public boolean play(Domino d){
		
		if(this.right == d.getLeftValue()){
			this.board.add(d);
			this.right = d.getRightValue();
			return true;
		}
		
		if(this.left == d.getRightValue()){
			this.board.add(0,d);
			this.left = d.getLeftValue();
			return true;
		}		
		
		
		return false;
	}
	
	public String toString(){
		return this.board.toString();
	}
		
}
