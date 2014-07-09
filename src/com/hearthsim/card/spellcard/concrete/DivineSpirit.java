package com.hearthsim.card.spellcard.concrete;

import com.hearthsim.card.Deck;
import com.hearthsim.card.minion.Minion;
import com.hearthsim.card.minion.concrete.Wolfrider;
import com.hearthsim.card.spellcard.SpellCard;
import com.hearthsim.exception.HSInvalidPlayerIndexException;
import com.hearthsim.util.BoardState;
import com.hearthsim.util.HearthTreeNode;

public class DivineSpirit extends SpellCard {


	/**
	 * Constructor
	 * 
	 * @param hasBeenUsed Whether the card has already been used or not
	 */
	public DivineSpirit(boolean hasBeenUsed) {
		super("Divine Spirit", (byte)1, hasBeenUsed);
	}

	/**
	 * Constructor
	 * 
	 * Defaults to hasBeenUsed = false
	 */
	public DivineSpirit() {
		this(false);
	}
	
	@Override
	public Object deepCopy() {
		return new DivineSpirit(this.hasBeenUsed_);
	}
	
	/**
	 * 
	 * Use the card on the given target
	 * 
	 * This card heals the target minion up to full health and gives it taunt.  Cannot be used on heroes.
	 * 
	 * @param thisCardIndex The index (position) of the card in the hand
	 * @param playerIndex The index of the target player.  0 if targeting yourself or your own minions, 1 if targeting the enemy
	 * @param minionIndex The index of the target minion.
	 * @param boardState The BoardState before this card has performed its action.  It will be manipulated and returned.
	 * 
	 * @return The boardState is manipulated and returned
	 */
	@Override
	protected HearthTreeNode<BoardState> use_core(
			int thisCardIndex,
			int playerIndex,
			int minionIndex,
			HearthTreeNode<BoardState> boardState,
			Deck deck)
		throws HSInvalidPlayerIndexException
	{
		if (minionIndex == 0) {
			//cant't use it on the heroes
			return null;
		}
		
		Minion targetMinion = boardState.data_.getMinion(playerIndex, minionIndex-1);			
		targetMinion.setHealth((byte)(targetMinion.getHealth() * 2));
		if (targetMinion.getMaxHealth() < targetMinion.getHealth())
			targetMinion.setMaxHealth(targetMinion.getHealth());
		return super.use_core(thisCardIndex, playerIndex, minionIndex, boardState, deck);
	}
}