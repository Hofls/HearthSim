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

public class IronforgeRifleman extends Minion implements MinionTargetableBattlecry {

    private static final byte BATTLECRY_DAMAGE = 1;

    /**
     * Battlecry: Deal 1 damage to a chosen target
     */
    private final static BattlecryActionTargetable filter = new BattlecryActionTargetable() {
        protected boolean canTargetEnemyHero() { return true; }
        protected boolean canTargetEnemyMinions() { return true; }
        protected boolean canTargetOwnHero() { return true; }
        protected boolean canTargetOwnMinions() { return true; }
    };

    private final static EffectMinionAction<Minion> battlecryAction = new EffectMinionAction<Minion>() {
        @Override
        public HearthTreeNode applyEffect(PlayerSide originSide, Minion origin, PlayerSide targetSide, int targetCharacterIndex, HearthTreeNode boardState) throws HSException {
            Minion targetMinion = boardState.data_.modelForSide(targetSide).getCharacter(targetCharacterIndex);
            HearthTreeNode toRet = boardState;
            toRet = targetMinion.takeDamageAndNotify(BATTLECRY_DAMAGE, PlayerSide.CURRENT_PLAYER, targetSide, toRet, false, false);
            return toRet;
        }
    };

    public IronforgeRifleman() {
        super();
    }

    @Override
    public boolean canTargetWithBattlecry(PlayerSide originSide, Card origin, PlayerSide targetSide, int targetCharacterIndex, BoardModel board) {
        return IronforgeRifleman.filter.canTargetWithBattlecry(originSide, origin, targetSide, targetCharacterIndex, board);
    }

    @Override
    public HearthTreeNode useTargetableBattlecry_core(PlayerSide originSide, Minion origin, PlayerSide targetSide, int targetCharacterIndex, HearthTreeNode boardState) throws HSException {
        return IronforgeRifleman.battlecryAction.applyEffect(originSide, origin, targetSide, targetCharacterIndex, boardState);
    }
}
