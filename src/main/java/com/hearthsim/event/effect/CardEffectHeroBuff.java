package com.hearthsim.event.effect;

import com.hearthsim.card.Card;
import com.hearthsim.exception.HSException;
import com.hearthsim.model.PlayerSide;
import com.hearthsim.util.tree.HearthTreeNode;

public class CardEffectHeroBuff extends CardEffectHero {
    private byte attackDelta;
    private byte armorDelta;

    public CardEffectHeroBuff(int attackDelta, int armorDelta) {
        this.attackDelta = (byte) attackDelta;
        this.armorDelta = (byte) armorDelta;
    }

    public HearthTreeNode applyEffect(PlayerSide originSide, Card origin, PlayerSide targetSide, HearthTreeNode boardState) throws HSException {
        if (this.attackDelta > 0) {
            boardState.data_.modelForSide(targetSide).getHero().addExtraAttackUntilTurnEnd(this.attackDelta);
        }

        if (this.armorDelta > 0) {
            boardState.data_.modelForSide(targetSide).getHero().addArmor(this.armorDelta);
        }
        return boardState;
    }
}
