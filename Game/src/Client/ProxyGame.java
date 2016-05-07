package Client;

import java.io.IOException;
import java.util.*;
import graphicInterface.GGame;
import graphicInterface.InterfaceGame;
import softwareGame.Game;

public class ProxyGame implements InterfaceGame {

	@SuppressWarnings("rawtypes")
	protected GGame gGame;
	
	private static List<String> validUsers;
	private boolean correctUser = false;
	private static int userIndex;
	
	private static List<String> passwd;
	private boolean gameStarted = false;
	Game<Integer> game = null;
	
	/**
	 * Contructor for the Proxy Game class. It initializes a list of valid users to be able to play the game and their corresponding passwords.
	 * Create a graphical interface with a welcoming message to introduce a valid user.
	 */
	@SuppressWarnings("rawtypes")
	public ProxyGame()
	{
		/*
		 * Initialization of the list of valid users.
		 */
		ProxyGame.validUsers = new ArrayList<String>();
		ProxyGame.validUsers.add("Andres");
		ProxyGame.validUsers.add("Abe");
		ProxyGame.validUsers.add("Paul");
		ProxyGame.userIndex=-1;

		/*
		 * Initialization of the list of valid users' passwords.
		 */
		ProxyGame.passwd = new ArrayList<String>();
		ProxyGame.passwd.add("a");
		ProxyGame.passwd.add("b");
		ProxyGame.passwd.add("c");
		
		/*
		 * Starting the game with a message.
		 */
		this.gGame = new GGame(this);
		gGame.setVisible(true);
		this.gGame.setMessage("To start our amazing domino game, please enter your user: ");

	}
	
	/**
	 * This method is called when an event is produced in the graphical interface. 
	 * If the player has already entered correctly both a user and password, the proxy just forwards the information to the actual game.
	 * If not, the method will first ask the player to enter a valid user. It will keep asking until one is supplied.
	 * After a valid user has been introduced, it will ask for it's password. It wikk keep asking until the correct one is supplied.
	 * @param type The state of the game.
	 */
	@Override
	public void receivedMessage(int type) 
	{	
		if(gameStarted)
		{
			game.receivedMessage(type);
			return;
		}
		
		String s = this.gGame.getPlayerName();
		
		if(correctUser)
		{
			if(checkPasswd(s))
			{
				try 
				{
					game = new Game<Integer>(s, this.gGame);
					this.gameStarted=true;
				}catch (IOException e){}
				gGame.stopInput();
			}
			else
			{
				this.gGame.setMessage("Wrong password! Try again: ");
			}
			return;
		}
				
		if(checkUser(s))
		{
			this.gGame.setMessage("Please enter your password: ");
		}
		else
		{
			this.gGame.setMessage("No such user :( !\nPlease enter a VALID user: ");
			
		}
	}

	/**
	 * Verifies if the password provided matches the one with the user.
	 * @param s Password provied by the a valid user.
	 * @return true if password supplied matches, false otherwise.
	 */

	private boolean checkPasswd(String s)
	{	
		if(s.equals(ProxyGame.passwd.get(ProxyGame.userIndex)))
		{
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * Verifies if the user name provided is a valid one.
	 * @param s User name provided by the player 
	 * @return true if user name supplied matches, false otherwise.
	 */
	private boolean checkUser(String s)
	{
		
		for(String temp : ProxyGame.validUsers)
		{
			ProxyGame.userIndex++;
		
			if(s.equals(temp))
			{
				this.gGame.setMessage("Welcome "+temp+"!!! Please enter your password to start playing.\n");
				this.correctUser = true;
				return true;
			}
		}
		
		ProxyGame.userIndex = -1;
		System.out.println("Wrong user. "+s );
		return false;
	}

}
