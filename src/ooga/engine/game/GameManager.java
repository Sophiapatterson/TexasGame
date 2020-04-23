package ooga.engine.game;

import java.util.List;

public abstract class GameManager {

    public static final int INIT_SCORE = 0;
    public static final int SCORE_TICK = 5;

    public abstract boolean isGameOver();

    public abstract void handleCollisions();

    public abstract List<Powerup> handlePowerups();

    public abstract void tick();

    public abstract int getScore();

    public abstract void handleJump(double floorY);
}
