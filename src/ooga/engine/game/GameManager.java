package ooga.engine.game;

import java.util.List;

public abstract class GameManager {

    public abstract boolean isGameOver();

    public abstract void handleCollisions();

    public abstract void handlePowerups();

    public abstract void tick();

    public abstract int getScore();

    public abstract void handleJump(double floorY);
}
