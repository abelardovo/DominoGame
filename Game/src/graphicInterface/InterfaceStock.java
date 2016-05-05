package graphicInterface;

import softwareGame.Domino;

public interface InterfaceStock<T> {
	
	public boolean isEmpty();
	
	public Domino<T> draw();
	
	public String toString();

}
