package ooga.engine.game;

import java.util.List;

/**
 * GameManager is a public parent class that outlines the basic model for each scrolling game.
 * Initializes the initial score value to be zero and score increment value to be 5.
 * Includes abstract methods to handle end of game, collisions,
 * powerups, jumps, and score increments as well as a score getter method.
 */
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
