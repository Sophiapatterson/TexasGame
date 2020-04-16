package ooga.engine.jetpack;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import ooga.engine.dinosaur.DinoGameWorld;
import ooga.engine.game.Enemy;
import ooga.engine.game.Player;

public class Zapper extends Enemy {
    public static final int SPEED = 10;
    private String image = "Sprites/jetpack_zapper.png";
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();
    public static final double X_OFFSET = 40;
    public static final double Y_OFFSET = 50;
    private double standardY = 0;

    public Zapper(double x, double y) {
        super();
        this.x.setValue(x);
        this.y.setValue(y);
    }

    public Zapper() {
        super();
    }

    public boolean collide(Player player) {
        boolean xTouch = ((player.getXPos() >= this.getXPos() && player.getXPos() <= this.getXPos()+X_OFFSET) || (player.getXPos()+X_OFFSET >= this.getXPos() && player.getXPos() <= this.getXPos()+X_OFFSET));
        boolean yTouch = ((player.getYPos() >= this.getYPos() && player.getYPos() <= this.getYPos()+Y_OFFSET) || (player.getYPos()+Y_OFFSET >= this.getYPos() && player.getYPos() <= this.getYPos()+Y_OFFSET));
        return (xTouch && yTouch);
    }

    @Override
    public void setStandardY(){
        setYPos(standardY);
    }

    @Override
    public void scroll() {
        setXPos(getXPos() - SPEED);
    }

    @Override
    public void setXPos(double x) {
        this.x.setValue(x);
    }

    @Override
    public void setYPos(double y){
        this.y.setValue(y);
    }

    @Override
    public double getXPos(){ return x.getValue(); }

    @Override
    public double getYPos(){ return y.getValue(); }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public DoubleProperty getXProperty(){
        return x;
    }

    @Override
    public DoubleProperty getYProperty(){
        return y;
    }
}
