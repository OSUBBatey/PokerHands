/**
 * 
 */
package com.Batey.PokerGame.PlayerComponents;
import com.Batey.PokerGame.Enums.CardRank;
import com.Batey.PokerGame.Enums.CardSuit;
/**
 * @author Brian Batey
 *
 */
public class Card implements Comparable<Card> {
	
	private CardRank rank;
	private CardSuit suit;
	
	public Card(char rank, char suit) {
		this.rank = CardRank.getRankByVal(rank);
		this.suit = CardSuit.getSuitByVal(suit);
	}
	
	public CardRank getRank() {
		return this.rank;
	}
	
	public CardSuit getSuit() {
		return this.suit;
	}
	
	public int rankToInt(char rank) {
		int val;
		if(Character.isDigit(rank)) {
			val = Character.getNumericValue(rank);
		}else if(rank == 'T') {
			val = 10;
		}else if(rank == 'J') {
			val = 11;
		}else if(rank == 'Q') {
			val = 12;
		}else if(rank == 'K') {
			val = 13;
		}else{
			val = 14;
		}
		return val;
	}

	@Override
	public int compareTo(Card o) {
		if(this.rank == o.rank) {return 0;}
		else if(rankToInt(this.rank.getRankChar()) < rankToInt(o.rank.getRankChar())) {return -1;}
		else {return 1;}
	}
	
	@Override
	public String toString() {
		return this.rank.toString() + " of " + this.suit.toString();	
	}
}
