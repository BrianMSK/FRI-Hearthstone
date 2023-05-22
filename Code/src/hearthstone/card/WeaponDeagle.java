package hearthstone.card;

import hearthstone.player.Player;

public class WeaponDeagle extends Card implements Weapon {
    /**
     * Constructor for WeaponDeagle card
     * @param player - player who owns the card
     */
    public WeaponDeagle(Player player) {
        super("Deagle", 7, 1, 4, "Headshot amigo", "deagle.png", player);
    }

    @Override
    public boolean useCard() {
        return false;
    }
}
