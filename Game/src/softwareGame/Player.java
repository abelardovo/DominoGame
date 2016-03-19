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
}
