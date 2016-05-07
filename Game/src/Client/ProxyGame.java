package Client;

import java.io.IOException;
import java.util.*;
import graphicInterface.GGame;
import graphicInterface.InterfaceGame;
import softwareGame.Game;

public class ProxyGame implements InterfaceGame {

	protected GGame gGame;
	private InterfaceGame pg;
	private static List<String> validUsers;
	private static String passwd;
	private boolean gameStarted = false;
	Game<Integer> game = null;
	
	private boolean correctUser = false;
	
	public ProxyGame(){
		
		ProxyGame.validUsers = new ArrayList<String>();
		ProxyGame.validUsers.add("Andres");
		ProxyGame.validUsers.add("Abe");
		ProxyGame.validUsers.add("Paul");
		ProxyGame.passwd = "a";
		this.gGame = new GGame(this);
		gGame.setVisible(true);
		this.gGame.setMessage("Please enter your user: ");

	}
	
	@Override
	public void receivedMessage(int type) {
		
		if(gameStarted){
			game.receivedMessage(type);
			return;
		}
		

		String s = this.gGame.getPlayerName();
		
		if (correctUser)
		{
			if(checkPasswd(s, this))
			{
				try 
				{
					game = new Game<Integer>(s, this.gGame);
					this.gameStarted=true;
				} catch (IOException e){}
				gGame.stopInput();
			}
			else
			{
				this.gGame.setMessage("Wrong password! Try again: ");
			}
			return;
		}
		
		
		if(checkUser(s,this))
		{
			this.gGame.setMessage("Please enter your password: ");
			System.out.println("Good User. S="+s);
		}
		else
		{
			this.gGame.setMessage("No such user :( !\nPlease enter a VALID user: ");
			
		}
	
	
	}
	
	private boolean checkPasswd(String s, ProxyGame pg){
		
		if(s.equals(ProxyGame.passwd)){
			
			this.gGame.setMessage("Welcome!!! Please click on Enter, to start playing.\n");
			return true;
		}
		
		System.out.print("\n----"+s+", and password is: "+ProxyGame.passwd+"\n");
		return false;
		
	}
	
	private boolean checkUser(String s, ProxyGame pg){
		
		for(String temp : ProxyGame.validUsers){
			if(s.equals(temp)){
				
				this.gGame.setMessage("Welcome "+temp+"!!! Please enter your password to start playing.\n");
				System.out.println("Welcome "+temp+"!!! Please enter your password to start playing.\n");
				this.correctUser = true;
				return true;
			}
		}
		System.out.println("Wrong user. "+s );
		return false;

	}

}
