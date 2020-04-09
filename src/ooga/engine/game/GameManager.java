package ooga.engine.game;

import java.util.List;

public abstract class GameManager {

    public boolean isGameOver() {
        return false;
    }

    public abstract void handleCollisions();

    public abstract void handlePowerups();

    public abstract void tick();

    public abstract int getScore();

    public abstract void handleJump(double floorY);
}
