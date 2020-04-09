package ooga.engine.dinosaur;

import ooga.engine.game.Enemy;
import ooga.engine.game.GameManager;
import ooga.engine.game.Player;

import java.util.List;

public class DinoGameManager extends GameManager {
    private Player dino;
    private List<Enemy> enemies;
    private boolean gameOver = false;

    public DinoGameManager(Player dino, List<Enemy> enemies) {
        this.dino = dino;
        this.enemies = enemies;
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public void handleCollisions() {
        for(Enemy enemy: enemies) {
            if(enemy.collide(dino)) {
                gameOver = true;
            }
        }
    }

    @Override
    public void handleJump(double floorY) {
        if(dino.isAirborne(floorY)) {
            dino.jump();
        }
        else {
            dino.resetJumpStrength();
            dino.setyPos(floorY);
        }
    }
}
