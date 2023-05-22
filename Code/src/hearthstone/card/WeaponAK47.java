package hearthstone.card;

import hearthstone.player.Player;

public class WeaponAK47 extends Card implements Weapon {
    /**
     * Constructor for WeaponAK47 - creates a new AK47 weapon card
     * @param player - player who owns the card
     */
    public WeaponAK47(Player player) {
        super("AK47 - ratata", 10, 1, 5, "PIF-PAF", "ak47.png", player);
    }

    @Override
    public boolean useCard() {
        return false;
    }
}
