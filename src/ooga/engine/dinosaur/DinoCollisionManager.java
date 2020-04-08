package ooga.engine.dinosaur;

import javafx.scene.paint.Color;
import ooga.engine.game.CollisionManager;
import ooga.engine.game.Enemy;
import ooga.engine.game.Player;

import java.util.List;

/**
 * This class handles the rules of collision in the dinosaur game
 */
public class DinoCollisionManager extends CollisionManager {
    private Player myPlayer;
    private List<Enemy> enemies;

    public DinoCollisionManager(Player player, List<Enemy> enemies) {
        myPlayer = player;
        this.enemies = enemies;
    }

    @Override
    public void handleCollisions() {
        for(Enemy enemy: enemies) {
            if(enemy.collide(myPlayer)) {
                enemy.setFill(Color.BLACK);
            }
        }
    }
}
