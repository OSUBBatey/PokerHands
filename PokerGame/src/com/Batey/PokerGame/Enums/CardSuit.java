package com.Batey.PokerGame.Enums;

import java.util.HashMap;
import java.util.Map;
/**
 * Card Suit Enumerations
 * @author Brian Batey
 */
public enum CardSuit {
	
	CLUBS('C'),DIAMONDS('D'),HEARTS('H'),SPADES('S');
	
	private char val;//Used for returning values
	
	//Used for getting rank by value.
	private static Map<Character, CardSuit> suitMap = new HashMap<Character, CardSuit>();
	static {
		for(CardSuit suit: values()) {
			suitMap.put(suit.getSuitChar(), suit);
		}		
	}
	
	//Private constructor for returning values
	private CardSuit(char val) {
		this.val = val;
	}
	
	/**
	 * Returns the character associated with the rank of @this
	 * @return
	 * 		- a char object 
	 */	
	public char getSuitChar() {
		return this.val;
	}
	
	/**
	 * Returns the enumeration associated with the given character value.
	 * @param val
	 * 		- a valid char object in the range of {'C', 'D', 'H', 'S'}
	 * @return
	 * 		a CardSuit object, null if not in proper range
	 */
	public static CardSuit getSuitByVal(char val){				
		return suitMap.get(val);
	}
	
}
