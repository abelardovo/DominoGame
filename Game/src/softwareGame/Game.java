
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
	   System.out.println( "\nAn event has been produced. Event: "+ val +", for state: "+indState);
	   switch (val)
	   {
	   case GGame.DATA_NAME:
		   this.initialize(this.gGame.getPlayerName());
		   break;
		   		   
	   case GGame.PLAY:

		   switch (indState)
		   {
		   case 0: case 1: case 2: case 3: case 4: case 5: case 6:
			   this.treatDoubleAnswer((Domino) this.gGame.getDomino());
			   break;
			   
		   case 7: case 8:
			   
			   this.treatAnswer((Domino) this.gGame.getDomino());
			   break; 
			   
			  
		   case 12:
			   
			   this.table.setValue((Domino) this.gGame.getDomino());
			   this.treatAnswer((Domino) this.gGame.getDomino());
			   break;
		   }
		   
		   break;
		   
		   

	   case GGame.JUMP:	
		   switch (indState)
		   {
		   
		   case 0: case 1: case 2: case 3: case 4: case 5: case 6:
			   
			   this.treatJumpAnswer();
			   
			   break;
			   
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
		   
		   Domino d;
		   
		   switch (indState)
		   {

		   case 8: 
			   
			   if (this.stock.isEmpty()){
				   
				   this.gGame.setMessage("The Stock is empty. Please choose a domino or jump. ");
				   
				   this.gGame.setEnabledJump(true);
				   this.gGame.setHandEnable(true);
				   this.gGame.setEnabledDraw(false);
				   this.gGame.setEnabledPlayPC(false);
				   break;
			   }
			   
			   d = this.stock.draw();
			   this.player1.addDomino(d);
			   this.gGame.addDominoInHand(d);
			   
			   break;
		   }
		   break;		   

	   case GGame.VALIDPCPLAY:
		   this.computerPlay();
		   break;

	   }
	   
	   
	   System.out.print(this.table.printState());	   
	   System.out.print(this.pc.toString());
	   System.out.print("TABLE: "+this.table.toString()+"\n");	   
   }
   
   /**
    * Create a stock, a board, two players (player and computer), initialise the 
    * graphical interface : hand, button and send it the first message.
    * @param name The name of the player
    */
   public void initialize(String name)
   {
	   this.stock = new Stock();
	   this.table = new Table();
	   this.player1 = new Player(name);
	   this.pc = new Player();
	   Domino d;

	   //Each player takes there 6 chips
	   for(int i=0;i<6;i++ ){
		   d = this.stock.draw();
		   this.player1.addDomino(d);
		   this.gGame.addDominoInHand(d);
		   this.pc.addDomino(this.stock.draw());
	   }
	   
	   System.out.print("name of the player: "+ this.player1.getName()+"\n");
	   
	   this.gGame.setEnabledJump(true);
	   this.gGame.setHandEnable(true);
	   this.gGame.setEnabledDraw(false);
	   this.gGame.setEnabledPlayPC(false);
	   
	   this.gGame.setMessage("Hello "+this.player1.getName()+" good luck.  Please click on double "+this.indState+" or jump");
	
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

	   if ((d.getLeftValue() != this.indState) || (d.getRightValue() != this.indState)){
		   this.gGame.setMessage("This it is not the double "+this.indState);
		   return;			   
	   }
	   
	   this.indState = 8;
	   
	   this.table.setValue(d);
	   this.treatAnswer(d);
	   
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
    		this.gGame.setMessage("Can not play this Domino! ");
			return;
		 }
    	
    	System.out.println("The player played: "+d.toString());
    	this.table.play(d);
		this.gGame.putDominoOnTable(d);
		this.player1.removeDomino(d);
		this.gGame.removeDominoFromHand(d);
		
		if((this.indState == 12)||(this.indState == 7)){
		   this.indState = 8;
		}
		
		this.gGame.setEnabledJump(false);
		this.gGame.setEnabledDraw(false);
		this.gGame.setHandEnable(false);
		
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
			
		}else{
			
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
   		int i;
   		System.out.println("Computer tries to play, with state: "+indState);
  	 	Domino d=null;
  	 switch (indState)
  	 {
  	 //we look for a double n in the computer's hand
  	 //If yes, the computer plays else the player is asked to play the double domino (n-1)
  	 case 1: case 2: case 3: case 4: case 5: case 6: 
  		 
  		 	i = this.pc.searchForDouble(indState); 
  		 	if(i != -1){
  		
  		 		this.table.initialPlay(this.pc.getDomino(i));
  		 		this.gGame.putDominoOnTable(this.pc.getDomino(i));
  		 		this.pc.removeDomino(i);
  		 		
  		 		this.indState = 8;
  		 		
  		 		this.gGame.setEnabledJump(false); 
  		 		this.gGame.setEnabledPlayPC(false);
  		 		this.gGame.setHandEnable(true);
  		 		this.gGame.setEnabledDraw(true);
  	  		 		
  		 	}else{
  		 		
  		 		this.indState--;

  		 		this.gGame.setMessage( "The PC does not have the double. Please click on double "+this.indState+" or jump");
  		 		this.gGame.setEnabledPlayPC(false);
  		 		this.gGame.setEnabledDraw(false);
  		 		this.gGame.setHandEnable(true);
  		 		this.gGame.setEnabledJump(true);
  		 	}
  		 		
  		 	
  		 	break;
  	 //If n=0 we look for a double 0 in the computer's hand.
  	 //If yes, the computer plays, otherwise the player is asked to play any other domino.
  	 case 0:
  	 	i = this.pc.searchForDouble(indState); 
	 	if(i != -1){
	 		
	 		this.table.initialPlay(this.pc.getDomino(i));
	 		this.gGame.putDominoOnTable(this.pc.getDomino(i));
	 		this.pc.removeDomino(i);
	 		
		 	this.indState = 8;

		 	this.gGame.setEnabledJump(false); 
		 	this.gGame.setEnabledPlayPC(false);
		 	this.gGame.setHandEnable(true);
		 	this.gGame.setEnabledDraw(true);	 		
  		 		
	 	}else{
	 		
		 	this.indState = 12;
		 	
		 	this.gGame.setMessage( "Please click on any domino.");
		 	
		 	this.gGame.setEnabledPlayPC(false);
		 	this.gGame.setEnabledJump(false);
		 	this.gGame.setEnabledDraw(false);
		 	this.gGame.setHandEnable(true);

		}
	 	
  		break;
  		
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
  		while(!this.table.canPlay(this.pc.getDomino(i))){
  			i++;
  			if(i>=this.pc.hand.size()){
  				
  				if(this.stock.isEmpty()){
  					if(this.indState == 9){
  						this.indState = 11;
  	  					this.gGame.setMessage("The Stock is empty, the PC cannot play. It's a DRAW!"); 						
  						break;
  					}
  					this.indState = 7;
  					this.gGame.setMessage("The Stock is empty and the PC cannot play. Select a domino!");
  					break;
  				}
  				this.pc.addDomino( this.stock.draw() );
  	  		}
  		}
  		  		
  		if (this.indState == 11){
  			this.gGame.setHandEnable(false);
  		 	this.gGame.setEnabledDraw(false);
  		 	this.gGame.setEnabledPlayPC(false);
  		 	this.gGame.setEnabledJump(false); 
  		 	break;
  		}
  		
  		if (this.indState == 7){
  		 	this.gGame.setEnabledDraw(false);
  		 	this.gGame.setEnabledPlayPC(false);
  		 	this.gGame.setEnabledJump(false);
  			this.gGame.setHandEnable(true);
  		 	break;
  		}  		
		
  		d = this.pc.getDomino(i);
  		this.table.play(d);
  		System.out.println("The computer has played: "+ d.toString() );
		this.gGame.putDominoOnTable(d);
	 	this.pc.removeDomino(i);
	 		 	
	 	if(this.pc.noMoreDominos()){
			
			this.indState = 10;
			
		 	this.gGame.setEnabledPlayPC(false);
		 	this.gGame.setEnabledJump(false); 
	  		this.gGame.setHandEnable(false);
	  		this.gGame.setEnabledDraw(false);
	  		
			this.gGame.setMessage("PC wins!!!!");

		}else{
			
		 	this.gGame.setEnabledPlayPC(false);
	  		this.gGame.setHandEnable(true);
	  		
	  		if(this.stock.isEmpty()){

	  			this.indState = 8;

	  			this.gGame.setEnabledJump(true); 
		  		this.gGame.setEnabledDraw(false);
				this.gGame.setMessage("Stock empty. "+this.player1.getName()+" choose a domino or JUMP.");


	  		}else{
	  			this.gGame.setEnabledJump(false); 
		  		this.gGame.setEnabledDraw(true);
				this.gGame.setMessage("The PC played "+d.toString()+".\n"+this.player1.getName()+" choose a domino or DRAW from the Stock.");

	  		}
	  		

		 		
		}
	 	
		break;
  	
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
	   switch (indState)
  	 {
	   case 0: case 1: case 2: case 3: case 4: case 5: case 6:
  		 if (this.player1.searchForDouble(indState) == -1){
  			 
  			 this.gGame.setHandEnable(false);
  			 this.gGame.setEnabledJump(false);
  			 this.gGame.setEnabledDraw(false);
  			 this.gGame.setEnabledPlayPC(true);
  			 this.gGame.setMessage("Click on Play PC, for the PC to play.");
  			 
  		 }else{
  			 
  			 this.gGame.setMessage("Liar! You have the double "+ this.indState+" in your hand! You have to play it ");
  		 
  		 }
  		 
  		 break;
  	 		
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
