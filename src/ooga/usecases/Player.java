package ooga.usecases;

import javafx.scene.shape.Rectangle;
import ooga.engine.Collidable;
import ooga.engine.Gravity;
import ooga.player.Controllable;

public class Player extends Rectangle implements Controllable, Gravity, Collidable {

    public static final double SPEED = 5;
    public static final double GRAVITY_SPEED = 10;

    private double x;
    private double y;

    private boolean facingRight;
    private boolean isStanding;

    public Player() {
        facingRight = true;
        setX(100);
        setY(100);
    }

    @Override
    public void moveLeft() {
        setX(getX() - SPEED);
    }

    @Override
    public void moveRight() {
        setX(getX() + SPEED);
    }

    @Override
    public void jump() {
        setY(getY() + SPEED);
    }

    @Override
    public void accelerate() {
        if(!isStanding) {
            setY(getY() - GRAVITY_SPEED);
        }
    }

    @Override
    public void collide() {

    }
}
