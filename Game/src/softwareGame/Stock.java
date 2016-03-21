package softwareGame;

import java.util.*;

public class Stock {

	List<Domino> pile;
	
	public Stock(){
		this.pile = new ArrayList<Domino>();
		
		for (int i=6; i>=0; i--){
			for (int j=i;j>=0;j--){
				this.addDomino(new Domino(i,j));
			}
		}
		
	}
	
	public void addDomino(Domino d){
		this.pile.add(d);
	}
	
	public void removeDomino(int n){
		this.pile.remove(n);
	}
	
	public boolean isEmpty(){
		if(this.pile.isEmpty()){
			return true;
		}
		return false;
	}
	
	public Domino draw(){
		Random rand = new Random();
		int  n = rand.nextInt(this.pile.size());
		
		Domino d = this.pile.get(n);
		this.removeDomino(n);
		
		return d;
	}
	
	public String toString(){
		return this.pile.toString();
	}
}
