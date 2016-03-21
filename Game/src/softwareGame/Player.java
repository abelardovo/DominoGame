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
		
		this.name = "PC";
		this.hand = new ArrayList<Domino>();
	}
	
	public String getName(){
		return this.name;
	}
	
	public Domino getDomino(int n){
		return this.hand.get(n);
	}
	
	public void removeDomino(int n){
		this.hand.remove(n);
	}
	
	public void addDomino(Domino d){
		this.hand.add(d);
	}
	
	public String toString(){
		String s = this.name + " hand: ";
		
		for (int i=0; i<this.hand.size();i++){
			s = s + this.hand.get(i).toString() + " / ";
		}
		
		return s;
	}
}
