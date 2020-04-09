package ooga.engine.game;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public abstract class Player implements Gravity {
    protected ImageView playerImage;
    protected double x;
    protected double y;

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
