package hearthstone.card;

import hearthstone.player.Player;

public class SpellWater extends Card implements Spell {
    /**
     * Constructor for SpellWater card
     * @param player - player who owns the card
     */
    public SpellWater(Player player) {
        super("Water spell", 2, 1, 2, "Throw water on your enemies", "water.png", player);
    }

    @Override
    public boolean useCard() {
        return false;
    }
}
