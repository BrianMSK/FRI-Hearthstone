package hearthstone.gameManager;

import java.util.Random;

import hearthstone.deck.Deck;
import hearthstone.player.Player;
import hearthstone.player.PlayerType;
import fri.shapesge.FontStyle;
import fri.shapesge.Manager;
import fri.shapesge.Text;

public class GameManager {

    private Player playerOne;
    private Deck playerOneDeck;
    private Player playerTwo;
    private Deck playerTwoDeck;
    private Player currentTurn;
    private Player coinFlipWinner;
    private boolean gameStarted;
    private Board board;
    private Manager manager;
    private CountdownManager timer;
    private Text currentTurnText;
    private Text timerText;
    private int turnCounter = 0;

    /**
     * Main method - starts the game
     * @param args arguments to main method - no arguemnts are needed
     */
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
    }

    /**
     * Constructor for GameManager - creates new manager and starts the game
     */
    public GameManager() {
        this.manager = new Manager();
        this.timer = new CountdownManager(this);
        this.startGame();
    }

    /**
     * Tick method based on the game engine ShapesGe - runs every 25ms
     * It is checked whether game is started or win condition is met
     */
    public void tick() {
        if (!this.gameStarted) {
            return;
        }
        if (this.checkWinCondition()) {
            System.out.println("Game ended");
            if (this.playerOne.getHealth() <= 0) {
                System.out.println("Player two won");
            } else {
                System.out.println("Player one won");
            }
            System.out.println("Game lasted " + this.turnCounter + " turns");
            System.out.println("Congrats!");
            this.gameStarted = false;
            return;
        }

        this.timerTextManager();
        this.currentTurnTextManager();
        if (!this.timer.isRunning()) {
            this.timer.run();
        }
    }

    /**
     * Method to stop the timer
     */
    public void stopTimer() {
        this.timer.stop();
    }

    private Player coinToss() {
        Random random = new Random();
        int vyherca = random.nextInt(0, 2);
        if (vyherca == 0) {
            return this.playerOne;
        } else {
            return this.playerTwo;
        }
    }

    /**
     * Method to react on click from mouse adapter - chooses coordinates of click - based on ShapesGe engine
     * @param x x coordinate of click
     * @param y y coordinate of click
     */
    public void chooseCoordinates(int x, int y) {
        if (!this.gameStarted) {
            return;
        }
        System.out.println("X: " + x + " Y: " + y);
        if ((x >= 900 && x <= 985) && (y >= 302 && y <= 336)) {
            this.stopTimer();
            System.out.println("Clicked on end turn button");
        }
        if (this.currentTurn == this.playerOne) {
            if (this.playerOneDeck.selectCard(x, y)) {
                this.board.enableGlow(PlayerType.PLAYER_ONE);
            } else {
                this.board.disableGlow();
            }
        } else {
            if (this.playerTwoDeck.selectCard(x, y)) {
                this.board.enableGlow(PlayerType.PLAYER_TWO);
            } else {
                this.board.disableGlow();
            }
        }
    }

    /**
     * Nethod to start the game - creates new window for game setup and waits until both players are ready
     * Then it creates new board and players and starts the game
     */
    public void startGame() {
        // we create new window for game setup, where players can choose their names and check if they are ready
        GameSetup gameSetup = new GameSetup();

        // until both players are ready, we wait
        // je to spravene cez try catch kvoli thread sleepu lebo runtime compiler nepracuje s prazdnym while loopom
        boolean isFinished = false;
        while (!isFinished) {
            try {
                isFinished = gameSetup.isFinished();
                Thread.sleep(250);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // vytvorime si dosku ktora nam nacita nas obrazok z resources zlozky
        this.board = new Board();

        // vytvorime si hracov na zaklade mena ktore zadali, je spravena aj kontrola ci su prazdne
        this.playerOne = new Player(gameSetup.getPlayer1Name(), PlayerType.PLAYER_ONE);
        this.playerTwo = new Player(gameSetup.getPlayer2Name(), PlayerType.PLAYER_TWO);
        this.playerOneDeck = this.playerOne.getDeck();
        this.playerTwoDeck = this.playerTwo.getDeck();

        // nastavime si kto zacina, vyberieme to pomocou metody coinToss()
        this.coinFlipWinner = this.coinToss();
        this.currentTurn = this.coinFlipWinner;
        
        // text for timer
        this.timerText = new Text("Time left: " + this.timer.getCountdownSeconds(), 5, 395);
        this.timerText.changeColor("white");
        this.timerText.changeFont("Arial", FontStyle.BOLD, 20);
        this.timerText.makeVisible();

        // text for turns
        this.currentTurnText = new Text("Turn of player: " + this.currentTurn.getName(), 5, 370);
        this.currentTurnText.changeColor("white");
        this.currentTurnText.changeFont("Arial", FontStyle.BOLD, 20);
        this.currentTurnText.makeVisible();

        this.changeTurn();

        // Text for coinflip winner
        Text coinFlipWinnerText = new Text("Coinflip winner is: " + this.coinFlipWinner.getName(), 5, 345);
        coinFlipWinnerText.changeColor("white");
        coinFlipWinnerText.changeFont("Arial", FontStyle.BOLD, 20);
        coinFlipWinnerText.makeVisible();

        // bool for our tick function if its supposed to be running or not
        this.gameStarted = true;

        // call for our engine to manage this object
        this.manager.manageObject(this);
    }


    private void timerTextManager() {
        this.timerText.changeText("Time left: " + this.timer.getCountdownSeconds());
    }

    private void currentTurnTextManager() {
        this.currentTurnText.changeText("Turn of player: " + this.currentTurn.getName());
    }

    /**
     * Method to change the turn according to the current turn
     * calls to add mana and changes the glow of player + updates all the texts
     */
    public void changeTurn() {
        switch (this.currentTurn.getPlayerType()) {
            case PLAYER_ONE:
                if (this.turnCounter > 1) {
                    this.playerOne.addMana();
                }
                break;
            case PLAYER_TWO:
                if (this.turnCounter > 1) {
                    this.playerTwo.addMana();
                }
                break;
        }
        if (this.currentTurn == this.playerOne) {
            this.currentTurn = this.playerTwo;
            this.playerOne.disableGlow();
            this.playerTwo.enableGlow();

        } else {
            this.currentTurn = this.playerOne;
            this.playerTwo.disableGlow();
            this.playerOne.enableGlow();
        }
        this.playerOne.updatePlayerNames();
        this.playerTwo.updatePlayerNames();
        this.playerOne.updateHealthIcon();
        this.playerTwo.updateHealthIcon();
        this.playerOne.resetMana();
        this.playerTwo.resetMana();
        this.playerOne.updateMana();
        this.playerTwo.updateMana();
        this.currentTurnTextManager();
        this.turnCounter++;
    }

    /**
     * Method to check if the game is over
     * @return true if game is over, false if not
     */
    public boolean checkWinCondition() {
        // if player has 0 hp, his opponents wins
        if (this.playerOne.getHealth() <= 0) {
            return true;
        }
        if (this.playerTwo.getHealth() <= 0) {
            return true;
        }

        return false;
    }
}
