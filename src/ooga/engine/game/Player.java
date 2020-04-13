package ooga.engine.game;

import javafx.beans.property.DoubleProperty;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public abstract class Player implements Gravity {

    public abstract void jump();

    public abstract void resetJumpStrength();

    public abstract boolean isAirborne(double floorY);

    public abstract void setXPos(double x);

    public abstract void setYPos(double y);

    public abstract double getXPos();

    public abstract double getYPos();

    public abstract DoubleProperty getXProperty();

    public abstract DoubleProperty getYProperty();
}
