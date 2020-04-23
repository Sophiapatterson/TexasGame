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

    public GenericGameManager(Player player, List<Enemy> enemies, List<Powerup> powerups) {
        rules = new GameRules();
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
