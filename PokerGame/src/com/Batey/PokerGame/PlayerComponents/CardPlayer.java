package com.Batey.PokerGame.PlayerComponents;

/**
 *  Player Class. Contains player name, hand, and methods for retrieving player details.
 */

public class CardPlayer {	
	//Class variables
	private String player_name;	
	private FCPHand player_hand;	
    
	/**
	 * CardPlayer Constructor
	 * @param playerName
	 * 		- a string representing the player name
	 * @param playerHand
	 * 		- a Hand object representing the player's hand
	 * @updates
	 * <pre>
	 * -this.player_name
	 * -this.player_hand
	 * </pre>
	 */
	public CardPlayer(String playerName, FCPHand playerHand) {
		assert playerName != null : "ERROR: Player Name input was Null!";		
		assert playerHand != null : "ERROR: Player Hand input was Null!";
		
		this.player_name = playerName;		
		this.player_hand = playerHand;	
	}	
	
	/**
	 * Returns the player name of @this
	 * @return
	 * 		- a string object
	 */
	public String getPlayerName() {
		return this.player_name;
	}
	
	/**
	 * Prints the player hand of @this to console
	 */
	public void printPlayerHand() {		
		for(int i=0; i<this.player_hand.getCardArray().size();i++) {
			System.out.print(this.player_hand.getCard(i).getRank());
			System.out.println(" OF " + this.player_hand.getCard(i).getSuit());
		}
	}
	
	/**
	 * Returns the player Hand object of @this
	 * @return	
	 * 		- a Hand object
	 */
	public FCPHand getPlayerHand() {
		return this.player_hand;
	}
	
	/**
	 * Return the highest ranked card in @this player's hand.
	 * @return
	 * 	- a Card object
	 */
	public Card getHighCard() {
		return (this.player_hand).getHighCard();
	}
}
