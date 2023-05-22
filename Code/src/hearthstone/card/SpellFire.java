package hearthstone.card;

import hearthstone.player.Player;

public class SpellFire extends Card implements Spell {
    /**
     * Constructor for SpellFire card
     * @param player - player who owns the card
     */
    public SpellFire(Player player) {
        super("Fire spell", 3, 1, 2, "Throw spell on your enemies", "fire.png", player);
    }

    @Override
    public boolean useCard() {
        return false;
    }
}
