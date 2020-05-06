/**
 * 
 */
package com.Batey.PokerGame.PlayerComponents;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Batey
 *
 */
public abstract class Hand {
	
	//Private Members
	protected Card highCard;
	protected ArrayList<Card> handArray;
	
	//Member Getter
	public abstract Card getCard(int pos);
	public abstract List<Card> getCardArray();
}
