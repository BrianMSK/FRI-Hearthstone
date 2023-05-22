package hearthstone.card;

import fri.shapesge.FontStyle;
import fri.shapesge.Image;
import fri.shapesge.Text;
import hearthstone.player.Player;
import java.util.ArrayList;

public abstract class Card {
    private String cardName;
    private int cardAttack;
    private int cardHealth;
    private int cardManaCost;
    private String cardDescription;
    private Image cardBase;
    private Image cardImage;
    private Image cardGlow;
    private Text cardHealthText;
    private Text cardAttackText;
    private Text cardManaCostText;
    private Text cardNameText;
    private Text cardDescriptionText;
    private boolean isCardSelected;
    private Player player;
    private int x;
    private int y;
    private final int width = 130;
    private final int height = 183;

    /**
     * Constructor for Card class
     * Sets all the texts and images for the card
     * @param cardName - name of the card
     * @param cardAttack - attack of the card
     * @param cardHealth - health of the card
     * @param cardManaCost - mana cost of the card
     * @param cardDescription - description of the card
     * @param cardImage - src image of the card
     * @param player - player who owns the card
     */
    public Card(String cardName, int cardAttack, int cardHealth, int cardManaCost, String cardDescription, String cardImage, Player player) {
        this.cardName = cardName;
        this.cardAttack = cardAttack;
        this.cardHealth = cardHealth;
        this.cardManaCost = cardManaCost;
        this.cardDescription = cardDescription;
        this.player = player;

        // card setup
        this.cardImage = new Image(cardImage, 30, 5);
        this.cardImage.makeVisible();
        this.cardBase = new Image("card.png", 0, 0);
        this.x = 0;
        this.y = 0;
        this.cardBase.makeVisible();
        this.cardHealthText = new Text(String.format("%d", this.cardHealth), 110, 175);
        this.cardHealthText.changeFont("Arial", FontStyle.BOLD, 20);
        this.cardHealthText.changeColor("white");
        this.cardHealthText.makeVisible();
        this.cardAttackText = new Text(String.format("%d", this.cardAttack), 15, 175);
        this.cardAttackText.changeFont("Arial", FontStyle.BOLD, 20);
        this.cardAttackText.changeColor("blue");
        this.cardAttackText.makeVisible();
        this.cardManaCostText = new Text(String.format("%d", this.cardManaCost), 15, 30);
        this.cardManaCostText.changeFont("Arial", FontStyle.BOLD, 20);
        this.cardManaCostText.changeColor("white");
        this.cardManaCostText.makeVisible();
        this.cardNameText = new Text(String.format("%s", this.cardName), 40, 100);
        this.cardNameText.changeFont("Arial", FontStyle.BOLD, 11);
        this.cardNameText.changeColor("black");
        this.cardNameText.makeVisible();
        String[] cardDescriptionArray = this.cardDescription.split(" ");
        ArrayList<String> finalList = new ArrayList<String>();
        for (int i = 0; i < cardDescriptionArray.length; i += 2) {
            if (i + 1 < cardDescriptionArray.length) {
                finalList.add(cardDescriptionArray[i] + " " + cardDescriptionArray[i + 1]);
            }
        }
        // build string with break lines from finalList
        StringBuilder finalString = new StringBuilder();
        for (String s : finalList) {
            finalString.append(s).append("\n");
        }
        this.cardDescription = finalString.toString();
        this.cardDescriptionText = new Text(String.format("%s", this.cardDescription), 30, 140);
        this.cardDescriptionText.changeFont("Arial", FontStyle.PLAIN, 12);
        this.cardDescriptionText.changeColor("black");
        this.cardDescriptionText.makeVisible();

        this.cardGlow = new Image("card-glow.png", 0, 0);
    }


    /**
     * Update the card position by x and y
     * @param x - x position
     * @param y - y position
     */
    public void updateCardPosition(int x, int y) {
        this.x += x;
        this.y += y;
        this.cardBase.moveHorizontal(x);
        this.cardBase.moveVertical(y);
        this.cardImage.moveHorizontal(x);
        this.cardImage.moveVertical(y);
        this.cardHealthText.moveHorizontal(x);
        this.cardHealthText.moveVertical(y);
        this.cardAttackText.moveHorizontal(x);
        this.cardAttackText.moveVertical(y);
        this.cardManaCostText.moveHorizontal(x);
        this.cardManaCostText.moveVertical(y);
        this.cardNameText.moveHorizontal(x);
        this.cardNameText.moveVertical(y);
        this.cardDescriptionText.moveHorizontal(x);
        this.cardDescriptionText.moveVertical(y);
    }

    /**
     * Check if the card is clicked
     * @param x - x position of the mouse
     * @param y - y position of the mouse
     * @return - true if the card is clicked, false otherwise
     */
    public boolean isCardClicked(int x, int y) {
        if ((x >= this.getX() && x <= this.getX() + this.width) && (y >= this.getY() && y <= this.getY() + this.height)) {
            return true;
        }
        return false;
    }

    /**
     * Selects the card
     */
    public void selectCard() {
        System.out.println("Card " + this.cardName + " selected");
        this.cardGlow = new Image("card-glow.png", this.x, this.y);
        this.cardGlow.makeVisible();
        this.isCardSelected = true;
    }

    /**
     * Deselects the card
     */
    public void deselectCard() {
        this.cardGlow.makeInvisible();
        this.isCardSelected = false;
    }

    /**
     * Getter for X coord
     * @return - x coord
     */
    public int getX() {
        return this.x;
    }

    /**
     * Getter for Y coord
     * @return - y coord
     */
    public int getY() {
        return this.y;
    }

    /**
     * TBA method to call use of card on specific events
     * @return - true if the card is used, false otherwise
     */
    public abstract boolean useCard();
}
