package ooga.engine.flappy;

import ooga.engine.game.Enemy;
import ooga.engine.game.GameManager;
import ooga.engine.game.Player;
import ooga.engine.game.Powerup;

import java.util.List;

public class FlappyGameManager extends GameManager {
    private Player bird;
    private List<Enemy> pipes;
    private List<Powerup> powerups;
    private boolean gameOver = false;
    private int score;

    public FlappyGameManager(Player bird, List<Enemy> pipes, List<Powerup> powerups) {
        this.bird = bird;
        this.pipes = pipes;
        this.powerups = powerups;
        score = 0;
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public void handleCollisions() {
//        for(Enemy pipe: pipes) {
//            if(pipe.collide(bird)) {
//                gameOver = true;
//            }
//        }
    }

    @Override
    public void handlePowerups() {
//        for(Powerup pu: powerups){
//            if(pu.collide(bird) && pu.isVisible()){
//                score+=pu.scoreBonus();
//                pu.setVisible(false);
//            }
//        }
    }

    @Override
    public void tick() {
        score += 5;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    // if the bird falls onto the floor, we also end the game
    public void handleJump(double floorY) {
        if(bird.isAirborne(floorY)) {
            bird.jump();
        }
        else {
            bird.setYPos(floorY);
            gameOver = true;
        }
    }
}
