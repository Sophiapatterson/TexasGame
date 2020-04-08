package ooga.engine.dinosaur;

import ooga.engine.game.Enemy;

public class Cactus extends Enemy {
    public static final int SPEED = 5;

    public Cactus() {
        super();
        setX(500);
        setY(300);
    }

    @Override
    public void collide() {

    }

    @Override
    public void scroll() {
        setX(getX() - 5);
    }
}
