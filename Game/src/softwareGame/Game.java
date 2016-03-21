
package softwareGame;

import graphicInterface.GGame;
import graphicInterface.InterfaceGame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
* Run the game between a player and the computer.
* @version march 2016
*
*/
public class Game implements InterfaceGame
{
	/**
	 * The graphical interface.
	 */
	GGame gGame;
	
	/**
	 * The stock
	 */
	 private Stock stock;
   
	 /**
	  * The board where dominos are put.
	  */
	 private Table table;
   
   /**
    * Player 1
    */
	 private Player player1;
   
   /**
    * Computer
    */
	 private Player pc;
   
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
	
   /**
    * Constructor for 2 players.Create a graphical interface and send it a message to enter the player's name.
    * @param name1 The name of the first player.
    * @param name2 The name of the second player.
    * @throws IOException 
    */
   public Game() throws IOException
   {	   
	   this.gGame = new GGame(this);
       gGame.setVisible(true);
       this.gGame.setMessage("Please enter your name(and return): ");
    }
   
   /**
    * This method is called when an event is produced in the graphical interface.
    * @param val The state of the game.
    */
   public void receivedMessage(int val)
   {
	   System.out.println( "\ntype received message  "+ val +" for state "+indState);
	   switch (val)
	   {
	   case GGame.DATA_NAME: //TO DO 
		   this.initialize(this.gGame.getPlayerName());
		   break;
	   case GGame.PLAY:

		   switch (indState)
		   {
		   case 6:
			   
			   break;
		   }
		   break;

	   case GGame.JUMP:	
		   switch (indState)
		   {
			   //TO DO 
		   }
		   break;
		   
	   case GGame.DRAW:
		   switch (indState)
		   {
			   //TO DO 
		   }
		   break;		   
	   }
   }
   
   /**
    * Create a stock, a board, two players (player and computer), initialise the 
    * graphical interface : hand, button and send it the first message.
    * @param name The name of the player
    */
   public void initialize(String name)
   {
	   Domino d;
	   this.stock = new Stock();
	   this.table = new Table();
	   this.player1 = new Player(name);
	   this.pc = new Player();
	   
	   //Each player takes there 6 chips
	   for(int i=0;i<6;i++ ){
		   d = this.stock.draw();
		   this.player1.addDomino(d);
		   this.gGame.addDominoInHand(d);
		   this.pc.addDomino(this.stock.draw());
	   }
	   
	   System.out.print(this.table.printState());	   
	   System.out.print("name of the player: "+ this.player1.getName()+"\n");
	   System.out.print(this.pc.toString());
	   
	   this.gGame.setEnabledJump(true);
	   this.gGame.setMessage("Hello "+this.player1.getName()+" good luck.  Please click on double 6 or jump");

		
   }
   
   /**
    * 
    * @param side The side to be considered : 1 or 2 (1: left, 2: right)
    * @return The extremity value of the domino on the considered side the table
    */
   public int getEnd(int side)
   {
  	 //TO DO 
	   return 0;
   }
   
   /**
    * Take the selected domino, verify if it is the right one.
    * If it is ok, call treatAnswer method otherwise send a message.
    * @param d The selected domino.
    */
   public void treatDoubleAnswer(Domino d)
   {
  		//TO DO 	  	
   }
 
   
   /**
    * When a player plays, we verify that the selected domino d may be
    * put on the table. If it is the case, we remove d from the hand of the player
    * and from the graphical hand. Then put d on the board and on the graphic board.
    * @param d The domino selected by the player.
    */
    public void treatAnswer(Domino d)
    {
    	if(!this.table.canPlay(d)){
			 return;
		 }
    	
		this.player1.removeDomino(d);
		this.gGame.removeDominoFromHand(d);
		this.table.play(d);
		this.gGame.putDominoOnTable(d);
		
    }
	 
	/**
	* The player draw. If the stock is empty, the computer plays (state 9) 
	* otherwise the drawn domino is added to the hand and the graphic hand
        * and, then, the computer plays (state 8).
	*
	*/
	public void playerDraw()
	{ 
		if(this.stock.isEmpty()){
			this.indState = 9;
			this.computerPlay();
		}
		else{
			this.player1.addDomino(this.stock.draw());
			this.gGame.addDominoInHand(this.stock.draw());
			this.indState = 8;
			this.computerPlay();
		}
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
	   System.out.println("state:"+indState+ ". computer plays");
  	 Domino d=null;
  	 switch (indState)
  	 {
  	 //we look for a double n in the computer's hand
  	 //If yes, the computer plays else the player is asked to play the double domino (n-1)
  	 case 6: case 5: case 4:case 3: case 2: case 1: 
  		 	int i = this.pc.searchForDouble(indState); 
  		 	if(i != -1){
  		
  		 		this.table.initialPlay(this.pc.getDomino(i));
  		 		this.gGame.addDominoInHand(this.pc.getDomino(i));
  		 		this.pc.removeDomino(i);
  	  		 		
  		 	}
  		 	else{
  		 		
  		 		//Player plays
  		 	}
  		 	break;
  	 //If n=0 we look for a double 0 in the computer's hand.
  	 //If yes, the computer plays, otherwise the player is asked to play any other domino.
  	 case 0:
  	 	i = this.pc.searchForDouble(indState); 
	 	if(i != -1){
	 		
	 		this.table.initialPlay(this.pc.getDomino(i));
	 		this.gGame.addDominoInHand(this.pc.getDomino(i));
	 		this.pc.removeDomino(i);
  		 		
	 	}
	 	else{
	 		//Player plays
	 	}
  		break;
  	 //If n=8 or 9  normal game managing the stock and the empty stock.
  	 case 8:case 9:
  		for(i=0; i<this.pc.hand.size(); i++){
  			
  			if(this.table.canPlay(this.pc.getDomino(i))){
  				this.table.play(this.pc.getDomino(i));
  		 		this.gGame.addDominoInHand(this.pc.getDomino(i));
  				this.pc.removeDomino(i);
  				break;
  			}
  		}
  		
  		while(!this.table.canPlay( this.pc.getDomino(i) )){
  			this.pc.addDomino( this.stock.draw() );
  			i++;
  		}
  		
		this.table.play(this.pc.getDomino(i));
		this.gGame.addDominoInHand(this.pc.getDomino(i));
		this.pc.removeDomino(i);
		break;
  	
  	 //If n=11 blocked game.	 
  	 case 11: 
  		 
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
	   System.out.println("State:"+indState + ". Into jump player's process"); 
  	 switch (indState)
  	 {
  	 case 6:
  		 if (this.player1.searchForDouble(6) == -1){
  			 
  		 }
  	 		
  	 }
   }	
   
   public static void main(String [] args)
   {
	   
   	try {
		Game game = new Game();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}       
   }
}
