/**
 * 
 */
package com.Batey.PokerGame.Enums;

/**
 * FCP Score Rank Enumerations
 * @author Brian Batey
 */
public enum FCPScoreRank {
	S_FLUSH("full house"), FOUR_OF_A_KIND("four of a kind"), FULL_HOUSE("full house"),
	FLUSH("flush"), STRAIGHT("straight"), THREE_OF_A_KIND("three of a kind"),
	TWO_PAIR("two pair"), PAIR("pair"), HIGH_CARD("high card"), TIE("Tie.");
	
	private String val;//Used for returning values
	
	//Private constructor for returning values	
	private FCPScoreRank(String val) {
		this.val = val;
	}	
	
	@Override
	public String toString() {
		return this.val;
	}
	
	/**
	 * Returns the score value associated with a specific win state. The value of the rank is
	 * determined by the rules of the game Poker using a hand of 5 cards.
	 *  
	 * @param rank
	 * 		- an FCPScoreRank object to find the value of
	 * @return
	 * 		-an integer value representing the given score rank
	 */		
	static public int scoreRankVal(FCPScoreRank rank) {
		switch(rank) {
			case S_FLUSH:
				return 9;
			case FOUR_OF_A_KIND:
				return 8;
			case FULL_HOUSE:
				return 7;
			case FLUSH:
				return 6;
			case STRAIGHT:
				return 5;
			case THREE_OF_A_KIND:
				return 4;
			case TWO_PAIR:
				return 3;
			case PAIR:
				return 2;
			case HIGH_CARD:
				return 1;
			case TIE:
				return 0;
			default:
				//TODO: Throw Exception an error has occurred.
				return -1;
		}
	}
}
