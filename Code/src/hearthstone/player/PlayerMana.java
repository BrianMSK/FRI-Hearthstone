package hearthstone.player;

import fri.shapesge.FontStyle;
import fri.shapesge.Image;
import fri.shapesge.Text;

import java.util.ArrayList;

public class PlayerMana {
    private int numberOfMana;
    private int manaLimit;
    private Text manaText;
    private ArrayList<Image> manaIcons;
    private PlayerType playerType;

    /**
     * Constructor for PlayerMana - sets up mana for player
     * @param playerType - defines if player is player one or player two
     */
    public PlayerMana(PlayerType playerType) {
        this.numberOfMana = 1;
        this.manaLimit = 1;
        this.playerType = playerType;
        this.manaIconSetup();
    }

    private void manaIconSetup() {
        this.manaIcons = new ArrayList<>(this.manaLimit);
        switch (this.playerType) {
            case PLAYER_TWO -> {
                this.manaText = new Text(String.format("%d/%d", this.numberOfMana, this.manaLimit), 700, 53);
                for (int i = 0; i < this.manaLimit; i++) {
                    this.manaIcons.add(new Image("mana_on.png", (760 + (i * 21)), 37));
                    this.manaIcons.get(i).makeVisible();
                }
            }
            case PLAYER_ONE -> {
                this.manaText = new Text(String.format("%d/%d", this.numberOfMana, this.manaLimit), 723, 657);
                for (int i = 0; i < this.manaLimit; i++) {
                    this.manaIcons.add(new Image("mana_on.png", (785 + (i * 21)), 640));
                    this.manaIcons.get(i).makeVisible();
                }
            }
        }
        this.manaText.changeColor("white");
        this.manaText.changeFont("Arial", FontStyle.BOLD, 19);
        this.manaText.makeVisible();
    }

    private void addManaIcon() {
        switch (this.playerType) {
            case PLAYER_TWO -> {
                this.manaIcons.add(new Image("mana_on.png", (760 + ((this.manaLimit - 1) * 21)), 37));
                this.manaIcons.get(this.manaLimit - 1).makeVisible();
            }
            case PLAYER_ONE -> {
                this.manaIcons.add(new Image("mana_on.png", (785 + ((this.manaLimit - 1) * 21)), 640));
                this.manaIcons.get(this.manaLimit - 1).makeVisible();
            }
        }

    }

    /**
     * Updates mana text and mana icons
     */
    public void manaIconUpdate() {
        this.manaText.changeText(String.format("%d/%d", this.numberOfMana, this.manaLimit));
        for (int i = 0; i < this.manaLimit; i++) {
            if (i > this.numberOfMana) {
                this.manaIcons.get(i).changeImage("mana_off.png");
            } else {
                this.manaIcons.get(i).changeImage("mana_on.png");
            }
            this.manaIcons.get(i).makeVisible();
        }
    }

    /**
     * Resets mana to mana limit
     */
    public void resetMana() {
        this.numberOfMana = this.manaLimit;
    }

    /**
     * Adds mana up to number 10 and calls addManaIcon
     * @param numberOfMana - number of mana to add
     */
    public void addMana(int numberOfMana) {
        if (this.manaLimit == 10) {
            return;
        }
        this.manaLimit += numberOfMana;
        for (int i = 0; i < numberOfMana; i++) {
            this.addManaIcon();
        }
    }

    /**
     * Uses mana and returns true if mana is available
     * @param amount - amount of mana to use
     * @return - true if mana is available
     */
    public boolean useMana(int amount) {
        if (amount > this.numberOfMana) {
            return false;
        }
        this.numberOfMana -= amount;
        return true;
    }
}
