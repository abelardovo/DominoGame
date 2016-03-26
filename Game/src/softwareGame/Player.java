package softwareGame;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	String name;
	List<Domino> hand;
	
	public Player(String n){
		
		this.name = n;
		this.hand = new ArrayList<Domino>();
		
	}

	public Player(){
		
		this.name = "Computer";
		this.hand = new ArrayList<Domino>();
	}
	
	public String getName(){
		return this.name;
	}

	public Domino getDomino(int n){
		return this.hand.get(n);
	}
	
	public void addDomino(Domino d){
		this.hand.add(d);
	}	
	
	public void removeDomino(int n){
		this.hand.remove(n);
	}
	
	public void removeDomino(Domino d){
		
		for(int n=0; n<this.hand.size(); n++){
			if(this.getDomino(n) == d){
				this.removeDomino(n);
			}		
		}
	}
	
	public boolean noMoreDominos(){
		if(this.hand.isEmpty()){
			return true;
		}
		return false;
	}
	
	public boolean searchDomino(int left,int right){
		
		for(int n=0; n<this.hand.size(); n++){
			
			if( (right == this.getDomino(n).getLeftValue()) || (right == this.getDomino(n).getRightValue()) ||
				(left == this.getDomino(n).getLeftValue())  || (left == this.getDomino(n).getRightValue())){
					
				return true;	
			}
		}
		
		
		return false;
	}
	
	public int searchForDouble(int i){
		
		for(int n=0; n<this.hand.size(); n++){
			if(this.getDomino(n).isThereDouble()){
				if(this.getDomino(n).getLeftValue() == i)
					return n;
			}
			
		}
		return -1;
	}

	public String toString(){
		String s = this.name + " hand: ";
		for (int i=0; i<this.hand.size();i++){
			s = s + this.hand.get(i).toString() + " / ";
		}
		
		s = s + "\n";

		return s;
	}
	
}
