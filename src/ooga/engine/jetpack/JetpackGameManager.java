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
    public void handleCollisions() {

    }

    @Override
    public void handlePowerups() {

    }

    @Override
    public void tick() {

    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public void handleJump(double floorY) {

    }
}
