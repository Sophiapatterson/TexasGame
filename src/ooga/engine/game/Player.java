package ooga.engine.game;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public abstract class Player implements Gravity {
    protected ImageView playerImage;

    public abstract void jump();

    public abstract void resetJumpStrength();

    public abstract boolean isAirborne(double floorY);

    public ImageView getPlayerImage(){ return playerImage; }

    public abstract void setXPos(double x);

    public abstract void setYPos(double y);

    public abstract double getXPos();

    public abstract double getYPos();

}
