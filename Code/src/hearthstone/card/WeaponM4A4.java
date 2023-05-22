package hearthstone.card;

import hearthstone.player.Player;

public class WeaponM4A4 extends Card implements Weapon {

    /**
     * Constructor for WeaponM4A4 - creates a new M4A4 weapon card
     * @param player player to whom the card belongs
     */
    public WeaponM4A4(Player player) {
        super("M4A4", 8, 1, 5, "Counter Terrorist have won", "m4a4.png", player);
    }

    @Override
    public boolean useCard() {
        return false;
    }
}
