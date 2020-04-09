package ooga.engine.game;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public abstract class Player implements Gravity {
//    public static final double PLAYER_WIDTH = 10;
//    public static final double PLAYER_HEIGHT = 20;
//    public static final Paint PLAYER_COLOR = Color.GREEN;
    protected ImageView playerImage;
    protected double x;
    protected double y;

    //commented out constructor for now.
//    public Player() {
//        super(PLAYER_WIDTH, PLAYER_HEIGHT, PLAYER_COLOR);
//    }

    public abstract void jump();

    public abstract void resetJumpStrength();

    public abstract boolean isAirborne(double floorY);

    public ImageView getPlayerImage(){ return playerImage; }

    public void setXPos(double x) {
        this.x = x;
        this.playerImage.setX(x);
    }

    public void setYPos(double y){
        this.y = y;
        this.playerImage.setY(y);
    }

    public double getXPos(){ return this.x; }
    public double getYPos(){ return this.y; }

}
