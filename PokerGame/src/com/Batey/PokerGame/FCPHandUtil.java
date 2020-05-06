/**
 * 
 */
package com.Batey.PokerGame;

import java.util.Arrays;
import java.util.HashMap;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.Batey.PokerGame.Enums.FCPScoreRank;
import com.Batey.PokerGame.PlayerComponents.Card;
import com.Batey.PokerGame.PlayerComponents.CardPlayer;
import com.Batey.PokerGame.PlayerComponents.FCPHand;


/**
 * A simple utility to compare hands in a game of 5 card poker. Given a set of hands pairs,
 *  prints the results of each pair to the console.
 *  
 * @author Brian Batey  
 */
public class FCPHandUtil {
	
	/*
	 *  Class variables. 
	 */
	private Queue<FCPGameInstance> instanceQueue; //Object instance queue
	private final int DECK_SIZE = 5;
	private final int PLAYER_COUNT = 2;
	private final char COLON = ':'; //Used for removing ":" from player name input
	private Boolean isInit = false; //Initialization Flag
		
	/**
	 * Initializes the game queue and creates player objects. 
	 * Must be executed before the utility "run" function.
	 * 
	 * @updates
	 * 		- this.instanceQueue, isInit
	 * 
	 * @param inputArray
	 * 		- a well formed array of string in the form [playerOneName,(cardID x 5), playerTwoName, (cardID x 5)]
	 * 
	 * @Note 
	 * 		- cardID is a two character string representing a card using {2-9,T,J,Q,K} for rank 
	 * 		and {C, D, H, S} for suit.
	 */
	public void initialize(String[] inputArray) {				
		this.instanceQueue = new LinkedList<>();	
		this.parseInputToInstanceQueue(inputArray);
		this.isInit = true;
	}
	
	/**
	 * Executes player hand comparison for each store game instance created during initialization and
	 * prints the results to the console.
	 * 
	 * @updates
	 * 		- this.instanceQueue - each executed instance is cleared from the queue
	 */
	public void run() {
		if(isInit) { //Ensure object has been initialized
			while(!this.instanceQueue.isEmpty()) { //Run until queue is empty
				this.instanceQueue.remove().getInstanceOutcome();
			}
		}
	}
	

	/**
	 * Parses a well formatted input and creates/stores a game instance for each Player and Hand object
	 * found in the input array. 
	 *  
	 * @param inputArray
	 * 		- a well formed array of string in the form [playerOneName,(cardID x 5), playerTwoName, (cardID x 5)]
	 * 
	 * @Note 
	 * 		- cardID is a two character string representing a card using {2-9,T,J,Q,K} for rank 
	 * 		and {C, D, H, S} for suit.
	 * 
	 * @updates
	 * 		- this.instanceQueue
	 */
	private void parseInputToInstanceQueue(String[] inputArray) {
		/*
		 *  For loop executes once for every "x" entries, where x = (DECK_SIZE * PLAYER_COUNT) + PLAYER_COUNT.
		 *  Each execution creates two Hand objects, two CardPlayer objects,
		 *  and a resultant game instance object which is then stored in the instance queue.
		 *  
		 *  Entry ranges for the objects in the array are described in the above JAVADOC comment.
		 */
		for(int i = 0; i< inputArray.length; i+=(DECK_SIZE*PLAYER_COUNT)+PLAYER_COUNT) {
			this.instanceQueue.add(new FCPGameInstance(new CardPlayer(delimit(inputArray[i],COLON),new FCPHand(Arrays.copyOfRange(inputArray, i+1,i+6))),
					new CardPlayer(delimit(inputArray[i+DECK_SIZE+1], COLON),
							new FCPHand(Arrays.copyOfRange(inputArray, i+DECK_SIZE+2, i+(DECK_SIZE*PLAYER_COUNT)+PLAYER_COUNT)))));			
		}
	}
	
	/**
	 * A simple function to remove the first occurrence of character in a string.
	 * 
	 * @param 
	 * 		str the input string
	 * @param 
	 * 		delimiter the character to remove from the string
	 * @return
	 * 		str - with the first occurrence of "delimiter" removed
	 */
	private String delimit(String str, char delimiter) {		
		return new StringBuilder(str).deleteCharAt(str.indexOf(delimiter)).toString();
	}
	
	
	/**
	 * An instance of a Five Card Poker game.  Stores and manipulates player objects and outputs results based on a Five Card Poker rule set.
	 * 
	 * @author Brian Batey
	 *
	 */	
	private class FCPGameInstance{
		private CardPlayer players[];
		private FCPResult p1Result;
		private FCPResult p2Result;
		
		/**
		 * Constructor
		 * @param P1
		 * 		- Player1 representation 
		 * @param P2
		 * 		- Player2 representation
		 * 
		 * @updates
		 * 		this.players 
		 */
		public FCPGameInstance(CardPlayer P1, CardPlayer P2) {			
			this.players = new CardPlayer[PLAYER_COUNT];			
			this.players[0] = P1;
			this.players[1] = P2;			
		}
		
		/**
		 * Returns the player object for player 1
		 * @return
		 * 		- CardPlayer object
		 */		
		private CardPlayer getPlayerOne() {			
			return this.players[0];
		}	
		
		/**
		 * Returns the player object for player 2
		 * @return
		 * 		- CardPlayer object
		 */	
		private CardPlayer getPlayerTwo() {			
			return this.players[1];
		}
		
		/**
		 *  Calculates the results of @this and prints the result to the console. 
		 *  Winner is calculated first on hand rank score then by each subsequent highest card
		 *  rank until a winner is found. If no winner is found a tie is declared.
		 *  
		 *  @updates
		 *  <pre>
		 *  - this.p1Result
		 *  
		 *  - this.p2Result
		 *  </pre>
		 */
		public void getInstanceOutcome() {
			
			// Set this.p1Result and this.P2Result
			this.setPlayerResults();
			
			//Store the score values of each result
			int p1sVal = this.p1Result.getFCPScoreVal();
			int	p2sVal = this.p2Result.getFCPScoreVal();
						
			if(p1sVal < p2sVal) { //P2 Wins print P2 results
				this.p2Result.printFCPResult(this.getPlayerTwo().getPlayerName());
			}else if (p1sVal > p2sVal){//P1 Wins print P1 results
				this.p1Result.printFCPResult(this.getPlayerOne().getPlayerName());
			}else if (this.p1Result.getHighCardRank() < this.p2Result.getHighCardRank()){ //Score ranks same / Check high card - P2 wins 
				this.p2Result.printFCPResult(this.getPlayerTwo().getPlayerName());
			}else if (this.p1Result.getHighCardRank() > this.p2Result.getHighCardRank()){ //Score ranks same / Check high card - P1 wins 
				this.p1Result.printFCPResult(this.getPlayerOne().getPlayerName());
			}else {//Tie must exist, break it				
				breakTie();				
			}			
		}
		
		/**
		 * Breaks a tie if P1 and P2 have the same score rank value.
		 */
		private void breakTie() {
		
			Boolean isBroken = false; // Tie break flag - set to "true" if tie is broken
			FCPResult result = null;  // Result variable for new result 
			
			//Retrieve player Hand objects
			FCPHand p1 = (FCPHand)this.getPlayerOne().getPlayerHand();
			FCPHand p2 = (FCPHand)this.getPlayerTwo().getPlayerHand();

			/*
			 * Since player hands are sorted, iterate down from the 
			 * max card rank value to the min card rank value until 
			 * a non-matching pair is found and a winner is determined
			 * or the end of the hand is reached. 
			 */
			for(int i = DECK_SIZE-1; i>=0 && !isBroken; i--) {
				Card currCardP1 = p1.getCard(i);
				Card currCardP2 = p2.getCard(i);
				
				if(currCardP1.compareTo(currCardP2) > 0){ //P1 wins, print results
					isBroken = true;
					result = new FCPResult(FCPScoreRank.HIGH_CARD, currCardP1);
					result.printFCPResult(this.getPlayerOne().getPlayerName());
				}else if(currCardP1.compareTo(currCardP2) < 0) { //P2 wins, print results
					isBroken = true;
					result = new FCPResult(FCPScoreRank.HIGH_CARD, currCardP2);
					result.printFCPResult(this.getPlayerTwo().getPlayerName());
				}
			}		
			
			if(!isBroken) { //Actual tie is found, print results.
				System.out.println(FCPScoreRank.TIE);
			}
		}
		
		/**
		 * Calculates and sets player results for @this .
		 * 
		 *  @updates
		 *  <pre>
		 *  - this.p1Result
		 *  
		 *  - this.p2Result
		 *  </pre>	
		 */
		private void setPlayerResults() {
			this.p1Result = getPlayerHandResult(this.getPlayerOne()); 
			this.p2Result = getPlayerHandResult(this.getPlayerTwo());				
		}
		/**
		 * Calculates a numeric score rank for the given player based on the rules of
		 * the game Poker. Stores the value of the highest rank card in the 
		 * winning configuration along with the calculated score. Stores lowest
		 * group rank card as well in the case of full house and 2-pair.
		 *  
		 * @param player
		 * 		- the player object for which to determine a hand rank
		 * @return
		 * 		- an FCPResult object 
		 */
		private FCPResult getPlayerHandResult(CardPlayer player){
			
			//Tracking Flags
			boolean isStraight = true; //If non-consecutive rank is found set to false
			boolean isFlush = true; //If any suit is different set to false
			
			Integer lastInt = null; //Track the value of the last card for straight tracking
			
			int highGroupCount = 1, lowGroupCount = 1; //Track (high/low) group size counts
			Character lowGroupRank = null, highGroupRank = null; //Track (high/low) group ranks
			Card lowGroupCard = null, highGroupCard = null; //Store (high/low) group Card object
			
			Character heldSuit = null; //Suit value for flush tracking			
			
			FCPResult output = null; //Function output			
			
			Map<Character,Integer> rankCounts = new HashMap<>();//Map for storing group counts
			
			for(Card card: player.getPlayerHand().getCardArray()) {
				char currentRank = card.getRank().getRankChar();
				if(isStraight) {
					if(lastInt == null) {
						lastInt = card.rankToInt(currentRank);
					}else if(lastInt+1 != card.rankToInt(currentRank)) {
							isStraight = false;
					}
				}
				
				if(isFlush) {
					if(heldSuit == null) {
						heldSuit = card.getSuit().getSuitChar();
					}else if(heldSuit != card.getSuit().getSuitChar()) {
							isFlush = false;
					}
				}			
				
				if(!rankCounts.containsKey(currentRank)) {
					rankCounts.put(currentRank, 1);
				}else {
					rankCounts.replace(currentRank, rankCounts.get(currentRank)+1);
					if(highGroupRank == null) {											
						highGroupRank = currentRank;
						lowGroupRank = currentRank;
						highGroupCount = rankCounts.get(currentRank);
						lowGroupCount = highGroupCount;
						highGroupCard = card; 
						lowGroupCard = card;
					}else if(rankCounts.get(currentRank)> rankCounts.get(highGroupRank)) {						
						lowGroupCard = highGroupCard;
						lowGroupCount = highGroupCount;
						highGroupRank = currentRank;
						highGroupCard = card;						
						highGroupCount = rankCounts.get(highGroupRank);
					}else if(currentRank != highGroupRank && rankCounts.get(currentRank)> rankCounts.get(lowGroupRank)) {
						lowGroupCard = card;
						lowGroupRank = currentRank;
						lowGroupCount = rankCounts.get(currentRank);
					}
				}				
			}
			
			if(isFlush && isStraight) {
				output = new FCPResult( FCPScoreRank.S_FLUSH, player.getHighCard());
			}else if(isStraight) {
				output = new FCPResult(FCPScoreRank.STRAIGHT, player.getHighCard());
			}else if(isFlush) {
				output = new FCPResult(FCPScoreRank.FLUSH, player.getHighCard());
			}else if(highGroupCount == 4) {
				output = new FCPResult(FCPScoreRank.FOUR_OF_A_KIND, player.getHighCard());
			}else if(highGroupCount == 3) {
				if(lowGroupCount == 2) {
					output = new FCPResult(FCPScoreRank.FULL_HOUSE, highGroupCard, lowGroupCard);
				}else {
					output = new FCPResult(FCPScoreRank.THREE_OF_A_KIND, highGroupCard);
				}
			}else if(highGroupCount == 2) {
				if(highGroupCount == lowGroupCount) {
					output = new FCPResult(FCPScoreRank.TWO_PAIR, highGroupCard, lowGroupCard);
				}else {
					//TODO: ERROR CHECKING FOR THIS , I DONT THINK A BRANCH EXISTS FOR SOMETHING
					output = new FCPResult(FCPScoreRank.PAIR, highGroupCard, lowGroupCard);
				}
			}else {
				output = new FCPResult(FCPScoreRank.HIGH_CARD, player.getHighCard());
			}
			return output;
		}
	}
	/**
	 * 
	 * @author Brian Batey
	 *
	 */
	private class FCPResult{		
		private Card hCard, lCard;
		private FCPScoreRank scoreRank;		
		
		public FCPResult(FCPScoreRank sRank, Card highCard) {			
			this.scoreRank = sRank;						
			this.hCard = highCard;	
		}
		
		public FCPResult(FCPScoreRank sRank, Card highCard, Card lowCard) {			
			this.scoreRank = sRank;				
			this.hCard = highCard;
			this.lCard = lowCard;
		}
		
		public int getHighCardRank() {
			return this.hCard.rankToInt(this.hCard.getRank().getRankChar());
		}
		
		public int getFCPScoreVal() {
			return FCPScoreRank.scoreRankVal(this.scoreRank);
		}
		
		public void printFCPResult(String playerName) {			
			if(this.scoreRank != FCPScoreRank.TIE) {
				System.out.print(playerName + " wins. - with ");
				System.out.print(this.scoreRank.toString() + ": ");
				System.out.print(this.hCard.toString());				
				if(this.scoreRank == FCPScoreRank.FULL_HOUSE || this.scoreRank == FCPScoreRank.TWO_PAIR) {
					System.out.print(" over " + lCard.toString());
				}				
				System.out.println();				
			}else {
				System.out.print(FCPScoreRank.TIE);
			}
		}
	}
}
