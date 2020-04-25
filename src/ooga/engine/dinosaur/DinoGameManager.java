package ooga.engine.dinosaur;

import ooga.engine.game.Enemy;
import ooga.engine.game.GameManager;
import ooga.engine.game.Player;
import ooga.engine.game.Powerup;

import java.util.List;

/**
 * DinoGameManager extends parent GameManager Class and is a
 * specified version of GameManager for DinoGame.
 */
public class DinoGameManager extends GameManager {
    private Player dino;
    private List<Enemy> enemies;
    private List<Powerup> powerups;
    private boolean gameOver = false;
    private int score;

    /**
     * DinoGameManager constructor consolidates information of Game's Player, Enemies, and Powerups.
     * Initializes score to zero at start of game.
     * @param dino DinoPlayer used in game.
     * @param enemies List of Cacti in game.
     * @param powerups List of Coins in game.
     */
    public DinoGameManager(Player dino, List<Enemy> enemies, List<Powerup> powerups) {
        this.dino = dino;
        this.enemies = enemies;
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
        for(Enemy enemy: enemies) {
            if(enemy.collide(dino)) {
                gameOver = true;
            }
        }
    }

    /**
     * handlePowerups method handles the powerups in the game. There are no Powerups in the current version
     * of DinoGame, therefore the method returns null.
     * @return null
     */
    @Override
    public List<Powerup> handlePowerups(){
        return null;
    }

    /**
     * handleJump method handles a Player's jump.
     * @param floorY double y-value representing the game's floor.
     */
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

    /**
     * tick method increments the score as the game continues.
     */
    @Override
    public void tick(){
        score+=SCORE_TICK;
    }

    /**
     * getter method that returns the current score. Used in tick method.
     * @return int score that represents the current score.
     */
    @Override
    public int getScore() {
        return score;
    }
}
