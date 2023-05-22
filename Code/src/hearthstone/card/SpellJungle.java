package hearthstone.card;

import hearthstone.player.Player;

public class SpellJungle extends Card implements Spell {
    /**
     * Constructor for SpellJungle card
     * @param player - player who owns the card
     */
    public SpellJungle(Player player) {
        super("Jungle spell", 1, 1, 1, "Spell of jungle", "jungle.png", player);
    }

    @Override
    public boolean useCard() {
        return false;
    }
}
