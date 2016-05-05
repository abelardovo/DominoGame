package Client;

import java.io.IOException;
import java.util.Scanner;

import graphicInterface.GGame;
import graphicInterface.InterfaceGame;
import softwareGame.Game;

public class ProxyGame implements InterfaceGame {

	protected GGame gGame;
	private InterfaceGame pg;
	private static String passwd;
	private boolean gameStarted = false;
	Game<Integer> game = null;
	
	public ProxyGame(){
		
		this.passwd = "a";
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
		
		if(checkPasswd(s, this)){
			try {
				game = new Game<Integer>(s, this.gGame);
				this.gameStarted=true;
			} catch (IOException e){}
			gGame.stopInput();
		}
		else{
			this.gGame.setMessage("Wrong password :( !\nPlease enter a VALID user: ");

		}
		}
	
	private boolean checkPasswd(String s, ProxyGame pg){
		
		if(s.equals(ProxyGame.passwd)){
			
			this.gGame.setMessage("Welcome!!! Please click on Enter, to start playing.\n");
			return true;
		}
		
		System.out.print("\n----"+s+", and password is: "+this.passwd);
		return false;
		
	}
}