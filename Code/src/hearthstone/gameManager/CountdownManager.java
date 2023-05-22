package hearthstone.gameManager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CountdownManager implements Runnable {

    private ScheduledExecutorService scheduler;
    private int countdownSeconds;
    private final int countdownSecondsDefaultValue;
    private boolean isRunning;
    private GameManager gameManager;

    public CountdownManager(GameManager gameManager) {
        this.countdownSeconds = 60;
        this.countdownSecondsDefaultValue = 60;
        this.isRunning = false;
        this.gameManager = gameManager;
    }

    @Override
    public void run() {
        this.isRunning = true;
        this.countdownSeconds = this.countdownSecondsDefaultValue;
        this.gameManager.changeTurn();
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.scheduler.scheduleAtFixedRate(() -> {
            if (this.countdownSeconds > 0) {
                this.countdownSeconds--;
            } else {
                this.stop();
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    public void stop() {
        if (this.scheduler != null) {
            this.scheduler.shutdown();
            this.isRunning = false;
        }
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public int getCountdownSeconds() {
        return this.countdownSeconds;
    }
}