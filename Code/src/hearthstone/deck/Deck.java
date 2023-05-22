package hearthstone.deck;

import hearthstone.card.Card;
import hearthstone.card.MinionBlue;
import hearthstone.card.MinionRed;
import hearthstone.card.MinionGreen;
import hearthstone.card.SpellFire;
import hearthstone.card.SpellJungle;
import hearthstone.card.SpellWater;
import hearthstone.card.WeaponDeagle;
import hearthstone.card.WeaponAK47;
import hearthstone.card.WeaponM4A4;
import hearthstone.player.Player;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> cardsList;
    private boolean isDeckFull;
    private String deckName;
    private Player player;
    private boolean isCardSelected;

    /**
     * Constructor for Deck - creates new deck with 3 cards
     * It also sets the position of the cards based on the player type
     * Player one has cards at the bottom of the screen
     * Player two has cards at the top of the screen
     * @param deckName name of the deck
     * @param player player to whom the deck belongs
     */
    public Deck(String deckName, Player player) {
        this.cardsList = new ArrayList<Card>();
        this.isDeckFull = true;
        this.deckName = deckName.strip().trim();
        this.player = player;
        this.drawFirstHand();
        int iterator = 0;
        switch (this.player.getPlayerType()) {
            case PLAYER_ONE -> {
                for (Card card : this.cardsList) {
                    card.updateCardPosition(150 * iterator, 500);
                    iterator++;
                }
            }
            case PLAYER_TWO -> {
                for (Card card : this.cardsList) {
                    card.updateCardPosition(150 * iterator, 0);
                    iterator++;
                }
            }
        }
    }

    /**
     * Method to select a specific card based on the coordinates of the mouse click
     * @param x x coordinate of the mouse click
     * @param y y coordinate of the mouse click
     */
    public boolean selectCard(int x, int y) {
        if (this.isCardSelected) {
            for (Card card : this.cardsList) {
                card.deselectCard();
                this.isCardSelected = false;
            }
        }
        for (Card card : this.cardsList) {
            if (card.isCardClicked(x, y)) {
                card.selectCard();
                this.isCardSelected = true;
                return true;
            }
        }
        return false;
    }

    private void drawFirstHand() {
        for (int i = 0; i < 3; i++) {
            this.drawCard();
        }
    }

    /**
     * Method to draw a card from the deck based on random number generator - 1/3 chance to draw a card
     */
    public void drawCard() {
        Random random = new Random();
        int randomCard = random.nextInt(1, 4);
        int randomSelection = random.nextInt(1, 4);
        switch (randomCard) {
            case 1 -> {
                if (randomSelection == 1) {
                    this.cardsList.add(new MinionRed(this.player));
                } else if (randomSelection == 2) {
                    this.cardsList.add(new MinionBlue(this.player));
                } else {
                    this.cardsList.add(new MinionGreen(this.player));
                }
            }
            case 2 -> {
                if (randomSelection == 1) {
                    this.cardsList.add(new SpellFire(this.player));
                } else if (randomSelection == 2) {
                    this.cardsList.add(new SpellJungle(this.player));
                } else {
                    this.cardsList.add(new SpellWater(this.player));
                }
            }
            case 3 -> {
                if (randomSelection == 1) {
                    this.cardsList.add(new WeaponAK47(this.player));
                } else if (randomSelection == 2) {
                    this.cardsList.add(new WeaponDeagle(this.player));
                } else {
                    this.cardsList.add(new WeaponM4A4(this.player));
                }
            }
        }
    }

}
