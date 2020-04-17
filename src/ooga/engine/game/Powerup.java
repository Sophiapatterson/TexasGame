package ooga.engine.game;

import javafx.beans.property.DoubleProperty;

public abstract class Powerup implements Collidable, Scrolling {

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
        return 1000;
    }

    public void move() {
        scroll();
    }
}
