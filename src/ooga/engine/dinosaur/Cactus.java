package ooga.engine.dinosaur;

import javafx.scene.paint.Color;
import ooga.engine.game.Enemy;
import ooga.engine.game.Player;

public class Cactus extends Enemy {
    public static final int SPEED = 5;

    public Cactus() {
        super();
        setX(500);
        setY(300);
    }

    @Override
    public boolean collide(Player player) {
        return (this.getBoundsInParent().intersects(player.getBoundsInParent()));
    }

    @Override
    public void scroll() {
        setX(getX() - SPEED);
    }
}
