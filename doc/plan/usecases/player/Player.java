package ooga.usecases;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import ooga.engine.Collidable;
import ooga.engine.Gravity;
import ooga.engine.Upgradeable;
import ooga.player.Controllable;

public class Player extends Rectangle implements Controllable, Gravity, Collidable, Upgradeable {

    public static final double SPEED = 5;
    public static final double GRAVITY_SPEED = 10;

    private double x;
    private double y;

    private boolean facingRight;
    private boolean isAlive;

    public Player() {
        facingRight = true;
        setX(100);
        setY(100);
        setY(getY() - GRAVITY_SPEED);
    }

    @Override
    public void jump() {
        setY(getY() + SPEED);
    }

    @Override
    public void accelerate() {
        setY(getY() - GRAVITY_SPEED);
    }

    @Override
    public void collide(Shape s) {
        isAlive = false;
    }

    @Override
    public void powerup() {
        setX(getX() + SPEED);
    }
}
