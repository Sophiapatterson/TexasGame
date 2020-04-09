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

    //commented out constructor for now.
//    public Player() {
//        super(PLAYER_WIDTH, PLAYER_HEIGHT, PLAYER_COLOR);
//    }

    public abstract void jump();

    public abstract void resetJumpStrength();

    public abstract boolean isAirborne(double floorY);

    public ImageView getPlayerImage(){ return playerImage; }

    public abstract void setXPos(double x);

    public abstract void setYPos(double y);

    public abstract double getXPos();

    public abstract double getYPos();

}
