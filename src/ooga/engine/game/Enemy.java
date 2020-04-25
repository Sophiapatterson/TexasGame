package ooga.engine.game;

import javafx.beans.property.DoubleProperty;

/**
 * abstract Enemy class outlines basic model for all enemies
 * implemented in all of our renditions of scrolling platformer games.
 */
public abstract class Enemy implements Collidable, Scrolling {
    /**
     * getXProperty returns the Enemy's X double property.
     * @return x which is the Enemy's X double property.
     */
    public DoubleProperty getXProperty(){
        return null;
    }

    public abstract boolean collide(Player player);

    /**
     * getYProperty returns the Enemy's Y double property.
     * @return y which is the Enemy's Y double property.
     */
    public DoubleProperty getYProperty(){
        return null;
    }

    /**
     * move method allows enemies to move across screen using scroll.
     */
    public void move() {
        scroll();
    }

    public abstract void setXPos(double x);

    public abstract void setYPos(double y);

    public abstract double getXPos();
    public abstract double getYPos();

    public abstract String getImage();

    public abstract void setStandardY();
}
