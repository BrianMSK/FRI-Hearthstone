package hearthstone.card;

import hearthstone.player.Player;

public class MinionGreen extends Card implements Minion {

    /**
     * Constructor for MinionGreen card
     * @param player - player who owns the card
     */
    public MinionGreen(Player player) {
        super("Green Minion", 3, 6, 4, "Minion of green color", "green-minion.png", player);
    }

    @Override
    public boolean useCard() {
        return false;
    }
}
