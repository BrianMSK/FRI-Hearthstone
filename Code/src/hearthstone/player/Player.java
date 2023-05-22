package hearthstone.player;

import hearthstone.card.Card;
import hearthstone.deck.Deck;
import fri.shapesge.FontStyle;
import fri.shapesge.Image;
import fri.shapesge.Text;

public class Player {

    private String name;
    private Text nameText;
    private Deck deck;
    private int health;
    private PlayerMana mana;
    private int attack;
    private Image glow;
    private Image healthIcon;
    private Text healthText;
    private PlayerType playerType;

    /**
     * Constructor for Player
     *
     * @param name - name of player
     * @param playerType - defines if player is player one or player two
     */
    public Player(String name, PlayerType playerType) {
        this.name = name;
        this.health = 30;
        this.attack = 0;
        this.playerType = playerType;
        this.deck = new Deck(name, this);

        // setup of glow, mana, and health
        this.mana = new PlayerMana(playerType);
        this.glowSetup();
        this.healthIconSetup();
        this.playerNameSetup();
    }

    /**
     * Getter for deck class
     * @return - deck class
     */
    public Deck getDeck() {
        return this.deck;
    }

    private void playerNameSetup() {
        switch (this.playerType) {
            case PLAYER_ONE -> {
                this.nameText = new Text(String.format("%s", this.name), 500, 610);
            }
            case PLAYER_TWO -> {
                this.nameText = new Text(String.format("%s", this.name), 500, 210);
            }
        }
        this.nameText.changeColor("white");
        this.nameText.changeFont("Arial", FontStyle.BOLD, 20);
        this.nameText.makeVisible();
    }

    /**
     * Method for updating player names
     */
    public void updatePlayerNames() {
        this.nameText.makeInvisible();
        this.nameText.makeVisible();
    }

    /**
     * Method for updating player mana - mana is updated after each round change
     */
    public void updateMana() {
        this.mana.manaIconUpdate();
    }

    /**
     * Method for adding mana to player after each round change
     */
    public void addMana() {
        this.mana.addMana(1);
    }

    /**
     * Method for resetting mana after each round change
     */
    public void resetMana() {
        this.mana.resetMana();
    }

    private void glowSetup() {

        switch (this.playerType) {
            case PLAYER_TWO -> {
                this.glow = new Image("glow.png", 495, 65);
            }
            case PLAYER_ONE -> {
                this.glow = new Image("glow.png", 495, 465);
            }
        }
    }

    private void healthIconSetup() {

        switch (this.playerType) {
            case PLAYER_TWO -> {
                this.healthIcon = new Image("health.png", 600, 160);
                this.healthText = new Text(String.valueOf(this.health), 605, 195);
            }
            case PLAYER_ONE -> {
                this.healthIcon = new Image("health.png", 600, 560);
                this.healthText = new Text(String.valueOf(this.health), 605, 595);
            }
        }
        this.healthText.changeFont("Arial", FontStyle.BOLD, 20);
        this.healthText.changeColor("white");
        this.healthIcon.makeVisible();
        this.healthText.makeVisible();
    }

    /**
     * Method for updating health icon and text
     */
    public void updateHealthIcon() {
        this.healthIcon.makeInvisible();
        this.healthText.makeInvisible();
        this.healthText.changeText(String.valueOf(this.health));
        this.healthIcon.makeVisible();
        this.healthText.makeVisible();
    }


    /**
     * Unfinished method to use a card - feature is TBA and does not work
     * @param card - card to be used
     * @return - returns true if card was used, false if not
     */
    public boolean useCard(Card card) {
        // use card
        return false;
    }

    /**
     * Method to get player type
     * @return - returns player type
     */
    public PlayerType getPlayerType() {
        return this.playerType;
    }

    /**
     * Method to get player health
     * @return - returns player health
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Method to get player name - used to display player name
     * @return - returns player name as String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method to enable player glow
     */
    public void enableGlow() {
        this.glow.makeVisible();
    }

    /**
     * Method to disable player glow
     */
    public void disableGlow() {
        this.glow.makeInvisible();
    }
}
