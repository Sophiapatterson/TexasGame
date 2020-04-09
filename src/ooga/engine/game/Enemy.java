package ooga.engine.game;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public abstract class Enemy implements Collidable, Scrolling {
    public static final double ENEMY_WIDTH = 10;
    public static final double ENEMY_HEIGHT = 10;
    public static final javafx.scene.paint.Paint ENEMY_COLOR = Color.RED;
    protected ImageView enemyImage;
    protected double x;
    protected double y;

//    public Enemy() {
//        super(ENEMY_WIDTH, ENEMY_HEIGHT, ENEMY_COLOR);
//    }

    public void move() {
        scroll();
    }

    public ImageView getEnemyImage(){ return enemyImage; }

    public void setXPos(double x) {
        this.x = x;
        this.enemyImage.setX(x);
    }

    public void setYPos(double y){
        this.y = y;
        this.enemyImage.setY(y);
    }

    public double getXPos(){ return this.x; }
    public double getYPos(){ return this.y; }


}
