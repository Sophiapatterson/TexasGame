package ooga.engine.flappy;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import ooga.engine.game.Player;

public class BirdPlayer extends Player {
    public static final double GRAVITY = 2.5;
    public static final int DEFAULT_JUMP_STRENGTH = 24;
    private double jumpStrength = DEFAULT_JUMP_STRENGTH;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();

    public BirdPlayer(double x, double y) {
        super();
        this.x.setValue(x);
        this.y.setValue(y);
    }

    @Override
    public void jump() {
        this.setYPos(getYPos() - jumpStrength);
        fall();
    }

    @Override
    public void resetJumpStrength() {
        jumpStrength = DEFAULT_JUMP_STRENGTH;
    }

    @Override
    public boolean isAirborne(double floorY) {
        return y.getValue() < floorY && y.getValue() - jumpStrength - GRAVITY < floorY;
    }

    @Override
    public void setXPos(double x) {
        this.x.setValue(x);
    }

    @Override
    public void setYPos(double y) {
        this.y.setValue(y);
    }

    @Override
    public double getXPos() {
        return this.x.getValue();
    }

    @Override
    public double getYPos() {
        return this.y.getValue();
    }

    @Override
    public DoubleProperty getXProperty() {
        return x;
    }

    @Override
    public DoubleProperty getYProperty() {
        return y;
    }

    @Override
    public void fall() {
        jumpStrength -= GRAVITY;
    }
}
