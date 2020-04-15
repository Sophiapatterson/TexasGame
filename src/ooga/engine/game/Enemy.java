package ooga.engine.game;

import javafx.beans.property.DoubleProperty;
import javafx.scene.image.ImageView;


public abstract class Enemy implements Collidable, Scrolling {
    protected ImageView enemyImage;

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

    public ImageView getEnemyImage(){ return enemyImage; }

    public abstract void setXPos(double x);

    public abstract void setYPos(double y);

    public abstract double getXPos();
    public abstract double getYPos();

    public abstract String getImage();

}
