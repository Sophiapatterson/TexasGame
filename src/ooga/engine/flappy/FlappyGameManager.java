package ooga.engine.flappy;

import ooga.engine.game.Enemy;
import ooga.engine.game.GameManager;
import ooga.engine.game.Player;
import ooga.engine.game.Powerup;

import java.util.List;

/**
 * FlappyGameManager Class extends GameManager and is a specified
 * version of GameManager for FlappyGame.
 */
public class FlappyGameManager extends GameManager {
    private Player bird;
    private List<Enemy> pipes;
    private List<Powerup> powerups;
    private boolean gameOver = false;
    private int score;
    /**
     * FlappyGameManager constructor consolidates information of Game's Player, Enemies, and Powerups.
     * Initializes score to zero at start of game.
     * @param bird BirdPlayer used in game.
     * @param pipes List of Pipes in game.
     * @param powerups List of Coins in game.
     */
    public FlappyGameManager(Player bird, List<Enemy> pipes, List<Powerup> powerups) {
        this.bird = bird;
        this.pipes = pipes;
        this.powerups = powerups;
        score = INIT_SCORE;
    }
    /**
     * boolean isGameOver represents whether the the game is over.
     * @return true if the game is over, false if the game is not over.
     */
    @Override
    public boolean isGameOver() {
        return gameOver;
    }
    /**
     * handleCollisions method loops through all enemies and determines whether
     * any of them is colliding with the player. When player fails to dodge an
     * enemy, gameOver is set to true to indicate the end of the game.
     */
    @Override
    public void handleCollisions() {
        for(Enemy pipe: pipes) {
            if(pipe.collide(bird)) {
                gameOver = true;
            }
        }
    }
    /**
     * handlePowerups method handles the powerups in the game. There are no Powerups in the current version
     * of FlappyGame, therefore the method returns null.
     * @return null
     */
    @Override
    public List<Powerup> handlePowerups() {
        return null;
//        for(Powerup pu: powerups){
//            if(pu.collide(bird) && pu.isVisible()){
//                score+=pu.scoreBonus();
//                pu.setVisible(false);
//            }
//        }
    }
    /**
     * tick method increments the score as the game continues.
     */
    @Override
    public void tick() {
        score += SCORE_TICK;
    }
    /**
     * getter method that returns the current score. Used in tick method.
     * @return int score that represents the current score.
     */
    @Override
    public int getScore() {
        return score;
    }
    /**
     * handleJump method handles a Player's jump.
     * @param floorY double y-value representing the game's floor.
     */
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
