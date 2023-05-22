package hearthstone.card;

import hearthstone.player.Player;

public class MinionBlue extends Card implements Minion {

    /**
     * Constructor for MinionBlue card
     * @param player - player who owns the card
     */
    public MinionBlue(Player player) {
        super("Blue Minion", 2, 5, 3, "Minion from the blue realm", "blue-minion.png", player);
    }

    @Override
    public boolean useCard() {
        return false;
    }
}
