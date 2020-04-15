package ooga.engine.flappy;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import ooga.engine.game.Enemy;
import ooga.engine.game.Player;

public class Pipe extends Enemy {
    public static final int SPEED = 10;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();
    public static final double X_OFFSET = 40;
    public static final double Y_OFFSET = 50;

    public Pipe(double x, double y) {
        super();
        this.x.setValue(x);
        this.y.setValue(y);
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
    public DoubleProperty getXProperty(){
        return x;
    }

    @Override
    public boolean collide(Player player) {
        return false;
    }

    @Override
    public DoubleProperty getYProperty(){
        return y;
    }
}
