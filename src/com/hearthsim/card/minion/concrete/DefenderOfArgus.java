package com.hearthsim.card.minion.concrete;

import com.hearthsim.card.Deck;
import com.hearthsim.card.minion.Minion;
import com.hearthsim.event.attack.AttackAction;
import com.hearthsim.event.deathrattle.DeathrattleAction;
import com.hearthsim.exception.HSException;
import com.hearthsim.util.tree.HearthTreeNode;

public class DefenderOfArgus extends Minion {

	private static final String NAME = "Defender of Argus";
	private static final byte MANA_COST = 4;
	private static final byte ATTACK = 2;
	private static final byte HEALTH = 3;
	
	private static final boolean TAUNT = false;
	private static final boolean DIVINE_SHIELD = false;
	private static final boolean WINDFURY = false;
	private static final boolean CHARGE = false;
	
	private static final boolean SUMMONED = false;
	private static final boolean TRANSFORMED = false;
	private static final byte SPELL_DAMAGE = 0;
	
	public DefenderOfArgus() {
		this(
				MANA_COST,
				ATTACK,
				HEALTH,
				ATTACK,
				(byte)0,
				(byte)0,
				HEALTH,
				HEALTH,
				(byte)0,
				SPELL_DAMAGE,
				TAUNT,
				DIVINE_SHIELD,
				WINDFURY,
				CHARGE,
				false,
				false,
				false,
				false,
				SUMMONED,
				TRANSFORMED,
				false,
				false,
				null,
				null,
				true,
				false
			);
	}
	
	public DefenderOfArgus(	
			byte mana,
			byte attack,
			byte health,
			byte baseAttack,
			byte extraAttackUntilTurnEnd,
			byte auraAttack,
			byte baseHealth,
			byte maxHealth,
			byte auraHealth,
			byte spellDamage,
			boolean taunt,
			boolean divineShield,
			boolean windFury,
			boolean charge,
			boolean hasAttacked,
			boolean hasWindFuryAttacked,
			boolean frozen,
			boolean silenced,
			boolean summoned,
			boolean transformed,
			boolean destroyOnTurnStart,
			boolean destroyOnTurnEnd,
			DeathrattleAction deathrattleAction,
			AttackAction attackAction,
			boolean isInHand,
			boolean hasBeenUsed) {
		
		super(
			NAME,
			mana,
			attack,
			health,
			baseAttack,
			extraAttackUntilTurnEnd,
			auraAttack,
			baseHealth,
			maxHealth,
			auraHealth,
			spellDamage,
			taunt,
			divineShield,
			windFury,
			charge,
			hasAttacked,
			hasWindFuryAttacked,
			frozen,
			silenced,
			summoned,
			transformed,
			destroyOnTurnStart,
			destroyOnTurnEnd,
			deathrattleAction,
			attackAction,
			isInHand,
			hasBeenUsed);
	}
	
	@Override
	public Object deepCopy() {
		return new DefenderOfArgus(
				this.mana_,
				this.attack_,
				this.health_,
				this.baseAttack_,
				this.extraAttackUntilTurnEnd_,
				this.auraAttack_,
				this.baseHealth_,
				this.maxHealth_,
				this.auraHealth_,
				this.spellDamage_,
				this.taunt_,
				this.divineShield_,
				this.windFury_,
				this.charge_,
				this.hasAttacked_,
				this.hasWindFuryAttacked_,
				this.frozen_,
				this.silenced_,
				this.summoned_,
				this.transformed_,
				this.destroyOnTurnStart_,
				this.destroyOnTurnEnd_,
				this.deathrattleAction_,
				this.attackAction_,
				this.isInHand_,
				this.hasBeenUsed_);
	}
	

	/**
	 * 
	 * Override for battlecry
	 * 
	 * Battlecry: Give adjacent minions +1/+1 and Taunt
	 * 
	 * @param thisCardIndex The index (position) of the card in the hand
	 * @param playerIndex The index of the target player.  0 if targeting yourself or your own minions, 1 if targeting the enemy
	 * @param minionIndex The index of the target minion.
	 * @param boardState The BoardState before this card has performed its action.  It will be manipulated and returned.
	 * 
	 * @return The boardState is manipulated and returned
	 */
	@Override
	public HearthTreeNode use_core(
			int targetPlayerIndex,
			Minion targetMinion,
			HearthTreeNode boardState,
			Deck deckPlayer0,
			Deck deckPlayer1)
		throws HSException
	{		
		HearthTreeNode toRet = super.use_core(targetPlayerIndex, targetMinion, boardState, deckPlayer0, deckPlayer1);
		if (toRet != null) {
			int thisMinionIndex = toRet.data_.getMinions(targetPlayerIndex).indexOf(this);
			if (thisMinionIndex == 0) {
				Minion minionToBuff = toRet.data_.getMinion(targetPlayerIndex, 1);
				minionToBuff.setAttack((byte)(minionToBuff.getAttack() + 1));
				minionToBuff.setHealth((byte)(minionToBuff.getHealth() + 1));
				minionToBuff.setTaunt(true);
			} else if (thisMinionIndex == 6) {
				Minion minionToBuff = toRet.data_.getMinion(targetPlayerIndex, 5);
				minionToBuff.setAttack((byte)(minionToBuff.getAttack() + 1));
				minionToBuff.setHealth((byte)(minionToBuff.getHealth() + 1));
				minionToBuff.setTaunt(true);				
			} else {
				Minion minionToBuff0 = toRet.data_.getMinion(targetPlayerIndex, thisMinionIndex - 1);
				minionToBuff0.setAttack((byte)(minionToBuff0.getAttack() + 1));
				minionToBuff0.setHealth((byte)(minionToBuff0.getHealth() + 1));
				minionToBuff0.setTaunt(true);				
				Minion minionToBuff1 = toRet.data_.getMinion(targetPlayerIndex, thisMinionIndex + 1);
				minionToBuff1.setAttack((byte)(minionToBuff1.getAttack() + 1));
				minionToBuff1.setHealth((byte)(minionToBuff1.getHealth() + 1));
				minionToBuff1.setTaunt(true);				
			}
		}
		return toRet;
	}
}
