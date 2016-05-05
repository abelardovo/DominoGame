package Client;

import java.io.IOException;
import java.util.Scanner;

import graphicInterface.GGame;
import graphicInterface.InterfaceGame;
import softwareGame.Game;

public class ProxyGame implements InterfaceGame {

	protected GGame gGame;
	private InterfaceGame pg;
	private String passwd;
	private boolean b = false;
	Game<Integer> game = null;
	
	public ProxyGame(){
		
		this.passwd = "a";
		this.gGame = new GGame(this);
		gGame.setVisible(true);
		this.gGame.setMessage("Please enter your user: ");

	}
	
	@Override
	public void receivedMessage(int type) {
		
		if(b){
			game.receivedMessage(type);
			return;
		}

		String s = this.gGame.getPlayerName();
		
		if(s.equals(this.passwd)){
			this.gGame.setMessage("Welcome!!!\n");
			System.out.print("Welcome: "+s);
			try {
				game = new Game<Integer>(s, this.gGame);
				b=true;
			} catch (IOException e){}
			
			return;
		}

		System.out.print("\n----"+s+", and password is: "+this.passwd);
		this.gGame.setMessage("Wrong password :(\n");
	}
	
//	private checkP
}
