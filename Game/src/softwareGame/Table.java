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
	
	public void setDoubleValue(int n){
		this.right = n;
		this.left = n;
	}
	
	public boolean canPlay(Domino d){
		
		if( (this.right == d.getLeftValue()) || (this.right == d.getRightValue()) ||
			(this.left == d.getLeftValue())  || (this.left == d.getRightValue())){
			return true;
		}
		
		return false;	
	}
	
	public void initialPlay(Domino d){
		
		this.board.add(d);
		this.left = d.getLeftValue();
		this.right = d.getRightValue();
	
	}
	
	public void play(Domino d){
		
		if(this.right == d.getLeftValue()){
			this.board.add(d);
			this.right = d.getRightValue();
			return;
		}
		
		if(this.left == d.getLeftValue()){
			this.board.add(0,d);
			this.left = d.getRightValue();
			return;
		}
		
		if(this.right == d.getRightValue()){
			this.board.add(d);
			this.right = d.getLeftValue();
			return;
		}
		
		if(this.left == d.getRightValue()){
			this.board.add(0,d);
			this.left = d.getLeftValue();
			return;
		}
		
	}
	
	public String printState(){
		return "STATE TABLE: "+this.getLeftValue()+" : "+this.getRightValue()+"\n";
	}
	
	public String toString(){
		return this.board.toString();
	}
		
}
