
package softwareGame;

import graphicInterface.BadMatchException;
import graphicInterface.GGame;
import graphicInterface.InterfaceGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
* Run the game between a player and the computer.
* @version march 2016
 * @param <T>
*
*/
public class Game<T> implements InterfaceGame
{
	/**
	 * The graphical interface.
	 */
	GGame<T> gGame;
	
	/**
	 * The stock
	 */
	 private Stock<T> stock;
   
	 /**
	  * The board where dominos are put.
	  */
	 private Table<T> table;
   
   /**
    * Player 1
    */
	 private Player<T> player1;
   
   /**
    * Computer
    */
	 private Player<T> pc;
   
   /**States:
    * 0-6 search if double domino
    * 8 player plays
    * 9 player plays with empty stock
    * 10 computer plays
    * 12 player forced play
    * 14 computer plays with drawing from stock
    * 15 computer blocked
    * 18 player plays while computer is blocked
    * 19 player plays with empty stock while computer is blocked
    * 
    */
	
	//Meaning of the states
	 String stateMeaning[] ={"double0","double1","double2","double3","double4",
			 "double5","double6","blockedComputer","play","blockedPlayer",
			 "win","blockedGame","NoDoubleFirstDomino"};
	 int indState = 6;
	 boolean playright = false;
	 int GameType = 0;
	
   /**
    * Constructor for 2 players.Create a graphical interface and send it a message to enter the player's name.
    * @param name1 The name of the first player.
    * @param name2 The name of the second player.
    * @throws IOException 
    */
   public Game(String name, GGame g, int GameType) throws IOException
   {	   
	   this.gGame = g;
//       gGame.setVisible(true);
	   this.player1 = new Player(name);
	   this.gGame.setName(name);
	   this.GameType = GameType;
    }
   
   /**
    * This method is called when an event is produced in the graphical interface.
    * @param val The state of the game.
    */
   public void receivedMessage(int val)
   {
	   System.out.println( "\nAn event has been produced. Event: "+ val +", for state: "+indState);
	   
	   switch (val)
	   {
	   case GGame.DATA_NAME:
		   this.initialize(this.gGame.getPlayerName());
		   break;
	   
	   case GGame.PLAYRIGHT:
		   playright = true;
		   
	   case GGame.PLAY: 
		   switch (indState)
		   {
		   //Treating the double cases
		   case 0: case 1: case 2: case 3: case 4: case 5: case 6:		   
			   this.treatDoubleAnswer((Domino) this.gGame.getDomino());
			   break;
			
		   //Treating the cases blockedComputer and play
		   case 7: case 8:			   
			   this.treatAnswer((Domino) this.gGame.getDomino());
			   break; 
		   
		   //Treating the case noDoubleFirstDomino
		   case 12:	   
			   this.table.setValue((Domino) this.gGame.getDomino());
			   this.treatAnswer((Domino) this.gGame.getDomino());
			   break;
		   }
		   
		   break;
		   
	   case GGame.JUMP:
		   
		   switch (indState)
		   {
		   //Treating the double cases		   
		   case 0: case 1: case 2: case 3: case 4: case 5: case 6:		   
			   this.treatJumpAnswer();		   
			   break;
	
		   //Treating the case play when the stock is empty and the player wants to jump.
		   case 8:		   
			   if(!this.player1.searchDomino(this.table.getLeftValue(), this.table.getRightValue())){
				   this.indState = 9;
			   }
			   
			   this.gGame.setEnabledJump(false);
			   this.gGame.setEnabledPlayPC(true);
			   break;
		   }
		   
		   break;
		   
	   case GGame.DRAW:
		   		   
		   switch (indState)
		   {
		   //Treating the case play when the player wants to DRAW from the Stock
		   case 8: 
			   this.playerDraw();
			   break;
		   }
		   break;		   

	   case GGame.VALIDPCPLAY:
		   this.computerPlay();
		   break;
	   }
	   
	   //Prints by console the State of the board, the PC's hand and the Dominos that are on the table.
	   System.out.print(this.table.printState());	   
	   System.out.print(this.pc.toString());
	   System.out.print("TABLE: "+this.table.toString()+"\n");	   
   }
   
   /**
    * Create a stock, a board, two players (player and computer), initialize the 
    * graphical interface : hand, button and send it the first message.
    * @param name The name of the player
    */
   public void initialize(String name)
   {
	   this.stock = Stock.getStock(this.GameType);
	   this.table = Table.getTable();
//	   this.player1 = new Player(name);
	   this.pc = new Player();
	   Domino<T> d;

	   //Each player takes there 6 chips
	   for(int i=0;i<6;i++ ){
		   d = this.stock.draw();
		   this.player1.addDomino(d);
		   this.gGame.addDominoInHand(d);
		   this.pc.addDomino(this.stock.draw());
	   }
	   
	   System.out.print("name of the player: "+ this.player1.getName()+"\n");
	   
	   //Enabled the Jump button and the player's hand.
	   this.gGame.setEnabledJump(true);
	   this.gGame.setHandEnable(true);
	   this.gGame.setEnabledDraw(false);
	   this.gGame.setEnabledPlayPC(false);
	   
	   if(this.GameType == 0)
		   this.gGame.setMessage("Hello "+this.player1.getName()+" good luck.  Please click on double "+this.indState+" or jump");
	   else if (this.GameType == 1)
		   this.gGame.setMessage("Hello "+this.player1.getName()+" good luck.  Please click on double "+Princesses.ObtainPrincess(this.indState)+" or jump");
   }
   
   /*
    * 
    * @param side The side to be considered : 1 or 2 (1: left, 2: right)
    * @return The extremity value of the domino on the considered side the table
    */
   //public int getEnd(int side)
   //{
  	 //TO DO 
	 //  return 0;
   //}
   
   /**
    * Take the selected domino, verify if it is the right one.
    * If it is ok, call treatAnswer method otherwise send a message.
    * @param d The selected domino.
    */
   public void treatDoubleAnswer(Domino<T> d)
   {
	   
	   //If the selected Domino is not the double needed.
	   if(this.GameType == 0){
		   if (!(d.getLeftValue().equals(this.indState)) || !(d.getRightValue().equals(this.indState))){
			   this.gGame.setMessage("This it is not the double "+this.indState);
			   return;			   
		   }
	   }
	   else if (this.GameType == 1)
		   if (!(d.getLeftValue().toString().equals(Princesses.ObtainPrincess(this.indState))) || !(d.getRightValue().toString().equals(Princesses.ObtainPrincess((this.indState))))){
			   this.gGame.setMessage("This it is not the double "+Princesses.ObtainPrincess(this.indState));
			   return;			   
		   }
	   
	   this.indState = 8;
	   
	   //Set the right and left value of the Table.
	   this.table.setValue(d);
	   this.treatAnswer(d);
	   
   }
 
   
   /**
    * When a player plays, we verify that the selected domino d may be
    * put on the table. If it is the case, we remove d from the hand of the player
    * and from the graphical hand. Then put d on the board and on the graphic board.
    * @param d The domino selected by the player.
    */
    public void treatAnswer(Domino<T> d)
    {
    	//Verify if the Domino cannot be played.
    	if(!this.table.canPlay(d)){
    		this.gGame.setMessage("Can not play this Domino! ");
			return;
		 }
    	
    	//Otherwise, we put the Domino on the table and remove it from the player's hand.
    	System.out.println("The player played: "+d.toString());
    	
    	if(playright && (this.table.canPlayRight(d))){
    		playright = false;
    		try {
				this.gGame.putDominoOnRightTable(d);
	    		this.table.playRight(d);
			} catch (BadMatchException e) {
				try {
					this.gGame.putDominoOnLeftTable(d);
				} catch (BadMatchException e1) {
					e1.printStackTrace();
				}
				this.table.play(d);
			};
    	    
    	}else{
    		try {
				this.gGame.putDominoOnLeftTable(d);
	    		this.table.play(d);

			} catch (BadMatchException e) {
				try {
					this.gGame.putDominoOnRightTable(d);
				} catch (BadMatchException e1) {
					e1.printStackTrace();
				}
	    		this.table.playRight(d);

			};

    	}
    	
		//this.gGame.putDominoOnTable(d);
		this.player1.removeDomino(d);
		this.gGame.removeDominoFromHand(d);
		
		//If the state of the game was NoDoubleFirstDomino or BlockedComputer
		//we change the state of the game to Play.
		if((this.indState == 12)||(this.indState == 7)){
		   this.indState = 8;
		}
		
		//We turn off the Jump, Draw buttons and disable the Graphical Hand
		this.gGame.setEnabledJump(false);
		this.gGame.setEnabledDraw(false);
		this.gGame.setHandEnable(false);
		
		//Verifying if the player wins the game.
		//It depends on if he has more Dominos in his hand or not.
		if(this.player1.noMoreDominos()){
			
			this.indState = 10;
			
			this.gGame.setEnabledPlayPC(false);
			this.gGame.setMessage("You win!!!!");

		}else{
			//Computer Play
			this.gGame.setEnabledPlayPC(true);
			this.gGame.setMessage("Click on Play PC, for the PC to play.");
		}
		
    }
	 
	/**
	* The player draw. If the stock is empty, the player has to choose a 
	* Domino from his hand or Jump. 
	* otherwise the player can draw a Domino from the Stock until he can play.
	*
	*/
	public void playerDraw()
	{ 
		//If the Stock is empty, the player has to choose a Domino from his hand or jump.
		if (this.stock.isEmpty()){
			   
			this.gGame.setMessage("The Stock is empty. Please choose a domino or jump. ");
			   
			this.gGame.setEnabledJump(true);
			this.gGame.setHandEnable(true);
			this.gGame.setEnabledDraw(false);
			this.gGame.setEnabledPlayPC(false);
			return;
		   }
		
		//Otherwise, the player can take a Domino from the Stock
		Domino d = this.stock.draw();
		this.player1.addDomino(d);
		this.gGame.addDominoInHand(d);
		
	}
	 
	/**
	 * The computer plays in relationship with the state of the game.
	 * If state = n (with n =1,2,3,4,5,6) we look for a double n in the computer's hand.
	 * If yes, the computer plays else the player is asked to play the double domino (n-1).
	 * If n=0 we look for a double 0 in the computer's hand.
	 * If yes, the computer plays, otherwise the player is asked to play any other domino.
	 * If n=8 or 9  normal game managing the stock and the empty stock.
	 * If n=11 blocked game.
	 */
   	public void computerPlay( )
   	{
   		int i;
   		System.out.println("Computer tries to play, with state: "+indState);
  	 	Domino<T> d=null;
  	 switch (indState)
  	 {
  	 //we look for a double n in the computer's hand
  	 //If yes, the computer plays else the player is asked to play the double domino (n-1)
  	 case 1: case 2: case 3: case 4: case 5: case 6: 
  		 
  		 	i = this.pc.searchForDouble(indState);
  		 	//If the computer has the Double requested, he plays it.
  		 	if(i != -1){
  		
  		 		this.table.initialPlay(this.pc.getDomino(i));
  		 		this.gGame.putDominoOnTable(this.pc.getDomino(i));
  		 		this.pc.removeDomino(i);
  		 		
  		 		this.indState = 8;
  		 		
  		 		this.gGame.setEnabledJump(false); 
  		 		this.gGame.setEnabledPlayPC(false);
  		 		this.gGame.setHandEnable(true);
  		 		this.gGame.setEnabledDraw(true);
  	  		 
  		 	//Otherwise, he changes the state of the game to look for the next double. 	
  		 	}else{
  		 		
  		 		this.indState--;

  		 		if (this.GameType == 0)
  		 			this.gGame.setMessage( "The PC does not have the double. Please click on double "+this.indState+" or jump");
  		 		else if (this.GameType == 1)
  		 			this.gGame.setMessage( "The PC does not have the double. Please click on double "+Princesses.ObtainPrincess(this.indState)+" or jump");  		 			
  		 		this.gGame.setEnabledPlayPC(false);
  		 		this.gGame.setEnabledDraw(false);
  		 		this.gGame.setHandEnable(true);
  		 		this.gGame.setEnabledJump(true);
  		 	}
  		 	
  		 	break;
 
  	 //If n=0 we look for a double 0 in the computer's hand.
  	 //If true, the computer plays, otherwise the player is asked to play any other domino.
  	 case 0:
  	 	i = this.pc.searchForDouble(indState); 
		 
  	 	//If the computer has the Double 0, he plays it.
	 	if(i != -1){
	 		
	 		this.table.initialPlay(this.pc.getDomino(i));
	 		this.gGame.putDominoOnTable(this.pc.getDomino(i));
	 		this.pc.removeDomino(i);
	 		
		 	this.indState = 8;

		 	this.gGame.setEnabledJump(false); 
		 	this.gGame.setEnabledPlayPC(false);
		 	this.gGame.setHandEnable(true);
		 	this.gGame.setEnabledDraw(true);	 		
  		 
		//Otherwise, the computer changes the state of the game to NoDoubleFirstDomino.
	 	}else{
	 		
		 	this.indState = 12;
		 	
		 	this.gGame.setMessage( "Please click on any domino.");
		 	
		 	this.gGame.setEnabledPlayPC(false);
		 	this.gGame.setEnabledJump(false);
		 	this.gGame.setEnabledDraw(false);
		 	this.gGame.setHandEnable(true);

		}
	 	
  		break;
  	
  	 //Case if the Computer is blocked.
  	 case 7:
  		 	this.gGame.setMessage("The Computer is blocked! Play again.");
		 	this.gGame.setEnabledPlayPC(false);
		 	this.gGame.setHandEnable(true);
		 	this.gGame.setEnabledJump(false); 
		 	this.gGame.setEnabledDraw(true);	  		 
  		 break;
  		
  	 //If n=8 or 9  normal game managing the stock and the empty stock.
  	 case 8:case 9:
  		 
  		i=0;
  		
  		//Verifying which Domino can play the computer.
  		while(!this.table.canPlay(this.pc.getDomino(i))){
  			i++;
  			
  			//If the verification reaches the last Domino in the Computer's hand
  			if(i>= this.pc.getHandSize()){
  				
  				//We check if the Stock is empty. If true, it means that the PC cannot Draw more Dominos.
  				if(this.stock.isEmpty()){
  					
  					//If the state of the game was BlockedPlayer and the PC cannot play any Domino, it's a DRAW!
  					if(this.indState == 9){
  						this.indState = 11;
  	  					this.gGame.setMessage("The Stock is empty, the PC cannot play. It's a DRAW!"); 						
  						break;
  					}
  					
  					//Otherwise, the state of the game changes to BlockedComputer
  					this.indState = 7;
  					this.gGame.setMessage("The Stock is empty and the PC cannot play. Select a domino!");
  					break;
  				}
  				
  				//Otherwise, the Computer can draw a Domino from the Stock.
  				this.pc.addDomino( this.stock.draw() );
  	  		}
  		}
  		
  		//If the state of the game changes to BlockedGame, the game is over so we disable all the
  		//buttons and the player's hand.
  		if (this.indState == 11){
  			this.gGame.setHandEnable(false);
  		 	this.gGame.setEnabledDraw(false);
  		 	this.gGame.setEnabledPlayPC(false);
  		 	this.gGame.setEnabledJump(false); 
  		 	break;
  		}
  		
  		//If the state of the game changes to BlockedComputer, 
  		//we disable all the buttons and enable the playe's hand.
  		if (this.indState == 7){
  		 	this.gGame.setEnabledDraw(false);
  		 	this.gGame.setEnabledPlayPC(false);
  		 	this.gGame.setEnabledJump(false);
  			this.gGame.setHandEnable(true);
  		 	break;
  		}  		
		
  		//If the state of the game remains the same, the Computer plays the next available Domino. 
  		d = this.pc.getDomino(i);
  		this.table.play(d);
  		System.out.println("The computer has played: "+ d.toString() );
		this.gGame.putDominoOnTable(d);
	 	this.pc.removeDomino(i);
	 	
	 	//If the Computer doesn't have any Domino left, it means that it wins.
	 	if(this.pc.noMoreDominos()){
			
			this.indState = 10;
			
		 	this.gGame.setEnabledPlayPC(false);
		 	this.gGame.setEnabledJump(false); 
	  		this.gGame.setHandEnable(false);
	  		this.gGame.setEnabledDraw(false);
	  		
			this.gGame.setMessage("PC wins!!!!");

		//Otherwise, the game continues.
		}else{
			
		 	this.gGame.setEnabledPlayPC(false);
	  		this.gGame.setHandEnable(true);
	  		
	  		//If the Stock is empty, we disable the Draw button.
	  		if(this.stock.isEmpty()){

	  			this.indState = 8;

	  			this.gGame.setEnabledJump(true); 
		  		this.gGame.setEnabledDraw(false);
				this.gGame.setMessage("Stock empty. "+this.player1.getName()+" choose a domino or JUMP.");

			//Otherwise, we activate the Draw button and the Player's hand.
	  		}else{
	  			this.gGame.setEnabledJump(false); 
		  		this.gGame.setEnabledDraw(true);
				this.gGame.setMessage("The PC played "+d.toString()+".\n"+this.player1.getName()+" choose a domino or DRAW from the Stock.");

	  		}
	  		

		 		
		}
	 	
		break;
  		 
	default: System.out.println("state no valid");
					
  	 } 	    	
   }
   	
	/**
	 * To verify that the player does not cheat with the double.
	 * 
	 */
   public void treatJumpAnswer()
   {
	   switch (indState)
  	 {
	   case 0: case 1: case 2: case 3: case 4: case 5: case 6:
		   
		 //If the Player does not have the double requested, it's the PC's turn.
  		 if (this.player1.searchForDouble(indState) == -1){
  			 
  			 this.gGame.setHandEnable(false);
  			 this.gGame.setEnabledJump(false);
  			 this.gGame.setEnabledDraw(false);
  			 this.gGame.setEnabledPlayPC(true);
  			 this.gGame.setMessage("Click on Play PC, for the PC to play.");
  			 
  		//Otherwise, the player must play the requested Domino.
  		 }else{
  			 if(this.GameType == 0)
  				 this.gGame.setMessage("Liar! You have the double "+ this.indState+" in your hand! You have to play it ");
  			 else if(this.GameType == 1)
  				 this.gGame.setMessage("Liar! You have the double "+ Princesses.ObtainPrincess(this.indState)+" in your hand! You have to play it ");
  		 }
  		 
  		 break;
  	 }
   }
   
	/**
	 * The main starts the game.
	 * 
	 */  
   /*
   public static void main(String [] args)
   {
	   
   		try {
   			Game<Integer> game = new Game<Integer>();
   		} catch (IOException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   			System.out.print("An exception occurred! ");
   		}       
   }*/
}
