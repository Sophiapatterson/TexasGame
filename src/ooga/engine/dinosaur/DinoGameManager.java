package ooga.engine.dinosaur;

import ooga.engine.game.Enemy;
import ooga.engine.game.GameManager;
import ooga.engine.game.Player;
import ooga.engine.game.Powerup;

import java.util.List;

public class DinoGameManager extends GameManager {
    private Player dino;
    private List<Enemy> enemies;
    private List<Powerup> powerups;
    private boolean gameOver = false;
    private int score;

    public DinoGameManager(Player dino, List<Enemy> enemies, List<Powerup> powerups) {
        this.dino = dino;
        this.enemies = enemies;
        this.powerups = powerups;
        score = -4500;
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
    public void handlePowerups(){
        for(Powerup pu: powerups){
            if(pu.collide(dino) && pu.isVisible()){
                score+=pu.scoreBonus();
                pu.setVisible(false);
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
            dino.setYPos(floorY);
        }
    }

    @Override
    public void tick(){
        score+=5;
    }

    @Override
    public int getScore() {
        return score;
    }
}
