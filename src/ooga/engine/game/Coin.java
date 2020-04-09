package ooga.engine.game;

public class Coin extends Powerup {

    private static final int SPEED = 5;

    public Coin(){
        super();
    }

    @Override
    public boolean collide(Player player) {
        return (this.getBoundsInParent().intersects(player.getPlayerImage().getBoundsInParent()));
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
