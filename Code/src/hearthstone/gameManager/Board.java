package hearthstone.gameManager;

import fri.shapesge.Image;
import hearthstone.player.PlayerType;

public class Board {

    private Image boardImage;
    private Image boardGlow;

    /**
     * Constructor for Board - creates new board image
     */
    public Board() {
        this.boardImage = new Image("board.jpg", 0, 0);
        this.boardImage.makeVisible();
    }

    /**
     * Enables glow on the board for the player if card is selected
     * @param playerType - player who owns the card
     */
    public void enableGlow(PlayerType playerType) {
        this.disableGlow();
        switch (playerType) {
            case PLAYER_ONE -> {
                this.boardGlow = new Image("board-glow.png", 200, 320);
            }
            case PLAYER_TWO -> {
                this.boardGlow = new Image("board-glow.png", 200, 190);
            }
        }
        this.boardGlow.makeVisible();
    }

    /**
     * Disables glow on the board if its existing
     */
    public void disableGlow() {
        if (this.boardGlow != null) {
            this.boardGlow.makeInvisible();
            this.boardGlow = null;
        }
    }

}
