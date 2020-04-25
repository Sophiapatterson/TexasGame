package ooga.engine.game;

import javafx.beans.property.DoubleProperty;
/**
 * abstract Powerup class outlines basic model for all Powerups
 * implemented in all of our renditions of scrolling platformer games.
 */
public abstract class Powerup implements Collidable, Scrolling {

    public static final int BONUS = 1000;

    public abstract DoubleProperty getXProperty();

    public abstract boolean collide(Player player);

    public abstract DoubleProperty getYProperty();

    public abstract void setXPos(double x);

    public abstract void setYPos(double y);

    public abstract double getXPos();
    public abstract double getYPos();

    public abstract String getImage();

    public abstract void setStandardY();

    public int scoreBonus(){
        return BONUS;
    }

    public void move() {
        scroll();
    }
}
