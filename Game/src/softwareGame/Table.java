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
		
		if( (this.right == d.getLeftValue()) || (this.right == d.getRightValue()) ||
			(this.left == d.getLeftValue())  || (this.left == d.getRightValue())){
			return true;
		}
		
		return false;	
	}
	
	public void play(Domino d){
		
		if(this.right == d.getLeftValue()){
			this.board.add(0,d);
			this.left = d.getLeftValue();
		}
		
		if(this.right == d.getRightValue()){
			this.board.add(d);
			this.right = d.getLeftValue();
		}
		
		if(this.left == d.getLeftValue()){
			this.board.add(0,d);
			this.left = d.getRightValue();
		}
		
		if(this.left == d.getRightValue()){
			this.board.add(d);
			this.right = d.getRightValue();
		}
		
	}
	
	public String toString(){
		return this.board.toString();
	}
		
}
