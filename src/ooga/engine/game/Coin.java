package ooga.engine.game;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import ooga.engine.jetpack.JetpackGameWorld;

public class Coin extends Powerup {
    public static final int SPEED = 10;
    public static final int COIN_OFFSET = 30;
    public static final int PLAYER_OFFSET = 30;
    public static final int COIN_BONUS = 1500;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();
    private double standardY = JetpackGameWorld.FLOOR_HEIGHT;
    private String image = "Sprites/general_coin.png";


    public Coin(double x, double y){
        super();
        this.x.setValue(x);
        this.y.setValue(y);
    }

    @Override
    public boolean collide(Player player) {
        boolean xTouch = (player.getXPos() <= this.getXPos() + COIN_OFFSET && player.getXPos() + PLAYER_OFFSET >= this.getXPos());
        boolean yTouch = (player.getYPos() <= this.getYPos() + COIN_OFFSET && player.getYPos() + PLAYER_OFFSET >= this.getYPos());
        return (xTouch && yTouch);
    }

    @Override
    public void setXPos(double x) { this.x.setValue(x); }

    @Override
    public void setYPos(double y) { this.y.setValue(y); }

    @Override
    public double getXPos() { return this.x.getValue(); }

    @Override
    public double getYPos() { return this.y.getValue(); }

    @Override
    public String getImage() { return image; }

    @Override
    public void setStandardY() { setYPos(standardY); }

    @Override
    public void scroll() {
        setXPos(getXPos() - SPEED);
    }

    @Override
    public int scoreBonus(){
        return COIN_BONUS;
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
