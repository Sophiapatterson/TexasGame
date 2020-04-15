package ooga.engine.game;

import ooga.Screens.DinoPlayerView;

public class Coin extends Powerup {

    private static final int SPEED = 5;

    public Coin(){
        super();
    }

    @Override
    public boolean collide(Player player) {
        return false;
    }

    @Override
    public void scroll() {
        setX(getX() - SPEED);
    }

    @Override
    public int scoreBonus(){
        return 1500;
    }
}
