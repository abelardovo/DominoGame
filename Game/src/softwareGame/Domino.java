package softwareGame;
import graphicInterface.InterfaceDomino;


public class Domino implements InterfaceDomino{
	
	int right;
	int left;
	
	public Domino(int r, int l){
		this.right = r;
		this.left = l;
	}
	
	public int getRightValue(){
		return this.right;
	}

	public int getLeftValue(){
		return this.left;
	}	
	
	public boolean isThereDouble(){
		if(this.getLeftValue() == this.getRightValue())
			return true;
		return false;
	}
	
	
//	public boolean equals(Domino d){
//		
//		if(this.right == d.right){
//			if (this.left == d.left){
//				return true;
//			}
//		}
//		return false;
//	}
	
	public String toString(){
		return "("+Integer.toString(this.left)+","+Integer.toString(this.right)+")";
	}
}
