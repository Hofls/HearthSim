package com.hearthsim.card.minion.concrete;

import com.hearthsim.card.Card;
import com.hearthsim.card.minion.Minion;
import com.hearthsim.card.minion.MinionTargetableBattlecry;
import com.hearthsim.event.EffectMinionAction;
import com.hearthsim.event.battlecry.BattlecryActionTargetable;
import com.hearthsim.exception.HSException;
import com.hearthsim.model.BoardModel;
import com.hearthsim.model.PlayerSide;
import com.hearthsim.util.tree.HearthTreeNode;

public class AldorPeacekeeper extends Minion implements MinionTargetableBattlecry {

    /**
     * Battlecry: Change an enemy minion's attack to 1
     */
    private final static BattlecryActionTargetable filter = new BattlecryActionTargetable() {
        protected boolean canTargetEnemyMinions() { return true; }
    };

    private final static EffectMinionAction<Minion> battlecryAction = new EffectMinionAction<Minion>() {
        @Override
        public HearthTreeNode applyEffect(PlayerSide originSide, Minion origin, PlayerSide targetSide, int targetCharacterIndex, HearthTreeNode boardState) throws HSException {
            Minion targetMinion = boardState.data_.modelForSide(targetSide).getCharacter(targetCharacterIndex);
            targetMinion.setAttack((byte) 1);
            return boardState;
        }
    };

    public AldorPeacekeeper() {
        super();
    }

    @Override
    public boolean canTargetWithBattlecry(PlayerSide originSide, Card origin, PlayerSide targetSide, int targetCharacterIndex, BoardModel board) {
        return AldorPeacekeeper.filter.canTargetWithBattlecry(originSide, origin, targetSide, targetCharacterIndex, board);
    }

    @Override
    public HearthTreeNode useTargetableBattlecry_core(PlayerSide originSide, Minion origin, PlayerSide targetSide, int targetCharacterIndex, HearthTreeNode boardState) throws HSException {
        return AldorPeacekeeper.battlecryAction.applyEffect(originSide, origin, targetSide, targetCharacterIndex, boardState);
    }
}
