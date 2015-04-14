package com.hearthsim.event.effect;

import com.hearthsim.event.FilterInterface;

public interface CardEffectOnResolveRandomInterface<T extends CardEffectInterface, U extends FilterInterface> {
    public T getRandomTargetEffect();

    public default T getRandomTargetSecondaryEffect() {
        return null;
    }

    public U getRandomTargetFilter();
}
