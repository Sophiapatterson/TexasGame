package ooga.engine.generic;

import ooga.engine.game.Enemy;
import ooga.engine.game.GameManager;
import ooga.engine.game.Player;
import ooga.engine.game.Powerup;

import java.util.List;

public class GenericGameManager extends GameManager {
    private Player player;
    private List<Enemy> enemies;
    private List<Powerup> powerups;
    private boolean gameOver = false;
    private int score;
    private GameRules rules;

    /**
     * this class is a helper class for the GameWorld that extends GameManager
     * it simply fulfills all GameManager classes while drawing all relevant details from a
     * properties file
     * @param player - the Player instance that is the game's Barry/Dino/etc
     * @param enemies - a List containing all Enemies created for the game
     * @param powerups - a List containing all Powerups (coins) created for the game
     * @param rulesPath - a String that represents the filepath to the appropriate properties file
     */
    public GenericGameManager(Player player, List<Enemy> enemies, List<Powerup> powerups, String rulesPath) {
        rules = new GameRules(rulesPath);
        this.player = player;
        this.enemies = enemies;
        this.powerups = powerups;
        score = 0;
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public void handleCollisions() {
        for(Enemy enemy: enemies) {
            if(enemy.collide(player)) {
                gameOver = true;
            }
        }
    }

    @Override
    public List<Powerup> handlePowerups(){
        return null;
    }

    @Override
    public void handleJump(double floorY) {
        if(player.isAirborne(floorY)) {
            player.jump();
        }
        else {
            player.resetJumpStrength();
            player.setYPos(floorY);
        }
    }

    @Override
    public void tick(){
        score+= rules.TICK_SCORE_AMOUNT;
    }

    @Override
    public int getScore() {
        return score;
    }
}
