package graphicInterface;

import softwareGame.Domino;

public interface InterfaceTable<T> {

	public T getRightValue();
	
	public T getLeftValue();
	
	public void setValue(Domino<T> d);
	
	public boolean canPlay(Domino<T> d);
	
	public void initialPlay(Domino<T> d);
	
	public void play(Domino<T> d);
	
	public String printState();
	
	public String toString();
	
	public T getRight();
	
	public void setRight(T t);

	public T getLeft();
	
	public void setLeft(T left);



}
