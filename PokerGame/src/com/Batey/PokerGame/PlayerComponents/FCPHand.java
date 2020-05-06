/**
 * 
 */
package com.Batey.PokerGame.PlayerComponents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Brian Batey
 *
 */
public class FCPHand extends Hand{
	private final int MAX_HAND_SIZE = 5;
	public FCPHand(String[] playerHand) {
		assert(playerHand.length == MAX_HAND_SIZE): ("ERROR: Player hand must contain exactly 5 entries!!!!");
		super.handArray = new ArrayList<Card>();
		this.initialize(playerHand);				
	}
	
	private void initialize(String[] playerHand) {		

		for(int i=0; i<playerHand.length; i++) {
			super.handArray.add(new Card(playerHand[i].charAt(0),playerHand[i].charAt(1)));			
		}
		
		super.handArray.sort(getComparator());
	}
	
	public Card getHighCard() {
		return super.handArray.get(super.handArray.size()-1);
	}
	
	public Card getLowCard() {
		return super.handArray.get(0);
	}

	@Override
	public Card getCard(int pos) {
		return super.handArray.get(pos);
	}
	
	@Override
	public List<Card> getCardArray() {
		return Collections.unmodifiableList(super.handArray);
	}
	
	private Comparator<Card> getComparator(){
		return new Comparator<Card>() {

			@Override
			public int compare(Card card1, Card card2) {				
				return card1.compareTo(card2);
			}			
		};
	}
}
