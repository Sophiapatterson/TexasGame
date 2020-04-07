package ooga.engine.game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public abstract class Enemy extends Rectangle implements Collidable {
    public static final double ENEMY_WIDTH = 100;
    public static final double ENEMY_HEIGHT = 100;
    public static final javafx.scene.paint.Paint ENEMY_COLOR = Color.RED;

    public Enemy() {
        super(ENEMY_WIDTH, ENEMY_HEIGHT, ENEMY_COLOR);
    }

    public abstract void move();
}
