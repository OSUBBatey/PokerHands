/**
 * 
 */
package com.Batey.PokerGame.Enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Card Rank Enumerations
 * @author Brian Batey
 */
public enum CardRank {
	TWO('2'),THREE('3'),FOUR('4'),FIVE('5'),
	SIX('6'),SEVEN('7'),EIGHT('8'),NINE('9'),
	TEN('T'),JACK('J'),QUEEN('Q'),KING('K'),
	ACE('A');
	
	//Used for getting rank by value.
	private static Map<Character, CardRank> rankMap  = new HashMap<Character, CardRank>();
	static {
		for(CardRank rank: values()) {
			rankMap.put(rank.getRankChar(), rank);
		}		
	}
	
	private char val; //Used for returning values
	
	//Private constructor for returning values
	private CardRank(char val) {
		this.val = val;
	}
	
	/**
	 * Returns the character associated with the rank of @this
	 * @return
	 * 		- a char object 
	 */		
	public char getRankChar() {
		return this.val;
	}
	
	/**
	 * Returns the enumeration associated with the given character value.
	 * @param val
	 * 		- a valid char object in the range of {'2'-'9', 'T', 'J', 'Q', 'K', 'A'}
	 * @return
	 * 		a CardRank object, null if not in proper range
	 */
	public static CardRank getRankByVal(char val){		
		return rankMap.get(val);
	}
}
