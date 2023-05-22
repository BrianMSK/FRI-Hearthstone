package hearthstone.card;

import hearthstone.player.Player;

public class MinionRed extends Card implements Minion {

    /**
     * Constructor for MinionRed card
     * @param player - player who owns the card
     */
    public MinionRed(Player player) {
        super("Red Minion", 7, 2, 5, "Minion of red color", "red-minion.png", player);
    }

    @Override
    public boolean useCard() {
        return false;
    }
}
