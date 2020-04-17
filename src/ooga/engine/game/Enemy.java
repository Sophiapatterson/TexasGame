package ooga.engine.game;

import javafx.beans.property.DoubleProperty;


public abstract class Enemy implements Collidable, Scrolling {

    public DoubleProperty getXProperty(){
        return null;
    }

    public abstract boolean collide(Player player);

    public DoubleProperty getYProperty(){
        return null;
    }

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
