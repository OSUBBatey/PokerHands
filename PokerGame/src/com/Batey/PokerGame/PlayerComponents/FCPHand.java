/**
 * 
 */
package com.Batey.PokerGame.PlayerComponents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a Five Card Poker Hand. Stores card objects and return methods.
 * @author Brian Batey
 */
public class FCPHand {
	
	//Class variables
	private ArrayList<Card> handArray;
	private final int MAX_HAND_SIZE = 5;
	
	/**
	 * Constructor
	 * @param playerHand
	 * 		- a well formed array of CardID tokens with a length of 5
	 * @Note 
	 * 		- cardID is a two character string representing a card using {2-9,T,J,Q,K} for rank 
	 * 		and {C, D, H, S} for suit.	 
	 */
	public FCPHand(String[] playerHand) {
		assert(playerHand.length == MAX_HAND_SIZE): ("ERROR: Player hand must contain exactly 5 entries!!!!");
		this.handArray = new ArrayList<Card>();
		this.initialize(playerHand);				
	}
	
	/**
	 * Generates Card objects with the given array and stores them in @this.handArray
	 * 
	 * @param playerHand
	 * 		- a well formed array of CardID tokens with a length of 5
	 * @Note 
	 * 		- cardID is a two character string representing a card using {2-9,T,J,Q,K} for rank 
	 * 		and {C, D, H, S} for suit.
	 */
	private void initialize(String[] playerHand) {
		for(int i=0; i<playerHand.length; i++) {
			this.handArray.add(new Card(playerHand[i].charAt(0),playerHand[i].charAt(1)));			
		}
		/*
		 * NOTE: Array is sorted here by card rank from least to greatest 
		 * to aid in score determination.
		 */
		this.handArray.sort(getComparator());
	}
	
	/**
	 * Returns the highest ranked card in @this player's hand.
	 * @return
	 * 		- a Card object
	 */	
	public Card getHighCard() {
		return this.handArray.get(this.handArray.size()-1); //Last entry is high card since array is sorted
	}
	
	/**
	 * Returns the card at the specified position in @this player's hand.
	 * @param pos
	 * 		- an integer representing the desired card index
	 * @return
	 * 		- a Card object
	 */
	public Card getCard(int pos) {
		return this.handArray.get(pos);
	}	
	
	/**
	 * Returns the hand array stored in @this.
	 * @return
	 * 		- a sorted List<Card> object representing the player's hand
	 */
	public List<Card> getCardArray() {
		return Collections.unmodifiableList(this.handArray);
	}
	
	/**
	 * Creates a custom comparator for sorting hand array.
	 * @return
	 * 		- an initialized Comparator<Card> object
	 */
	private Comparator<Card> getComparator(){
		return new Comparator<Card>() {
			@Override
			public int compare(Card card1, Card card2) {				
				return card1.compareTo(card2);
			}			
		};
	}
}
