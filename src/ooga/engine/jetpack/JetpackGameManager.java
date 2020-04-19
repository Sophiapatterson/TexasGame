package ooga.engine.jetpack;

import ooga.engine.game.Enemy;
import ooga.engine.game.GameManager;
import ooga.engine.game.Player;
import ooga.engine.game.Powerup;

import java.util.List;

public class JetpackGameManager extends GameManager {
    private Player player;
    private List<Enemy> enemies;
    private List<Powerup> powerups;
    private boolean gameOver = false;
    private int score;

    public JetpackGameManager(Player player, List<Enemy> enemies, List<Powerup> powerups) {
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
    public void handlePowerups() {
        for(Powerup pu: powerups){
            if (pu.collide(player)){
                gameOver = true;
            }
//            if(pu.collide(player) && pu.isVisible()){
//                score+=pu.scoreBonus();
//                pu.setVisible(false);
//            }
        }
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
    public void handleJump(double floorY) {
        if(player.isAirborne(floorY)) {
            player.jump();
        }
        else {
            player.setYPos(floorY);
        }
    }
}
