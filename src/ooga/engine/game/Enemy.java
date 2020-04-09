package ooga.engine.game;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public abstract class Enemy implements Collidable, Scrolling {
    public static final double ENEMY_WIDTH = 10;
    public static final double ENEMY_HEIGHT = 10;
    public static final javafx.scene.paint.Paint ENEMY_COLOR = Color.RED;
    protected ImageView enemyImage;

//    public Enemy() {
//        super(ENEMY_WIDTH, ENEMY_HEIGHT, ENEMY_COLOR);
//    }

    public void move() {
        scroll();
    }

    public ImageView getEnemyImage(){ return enemyImage; }

    public abstract void setXPos(double x);

    public abstract void setYPos(double y);

    public abstract double getXPos();
    public abstract double getYPos();


}
