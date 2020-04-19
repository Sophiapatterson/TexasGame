package ooga.engine.game;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import ooga.engine.jetpack.JetpackGameWorld;

public class Coin extends Powerup {
    private static final int SPEED = 10;
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
        System.out.println(this.getXPos());
        System.out.println(player.getXPos());
        boolean xTouch = (player.getXPos() >= this.getXPos()&& player.getXPos() <= this.getXPos());
        System.out.println(xTouch);
        boolean yTouch = (player.getYPos() == this.getYPos());
        System.out.println(yTouch);
        return (xTouch && yTouch);
//        boolean xTouch = ((player.getXPos() >= this.getXPos() && player.getXPos() <= this.getXPos()) || (player.getXPos()>= this.getXPos() && player.getXPos() <= this.getXPos()));
//        boolean yTouch = ((player.getYPos() >= this.getYPos() && player.getYPos() <= this.getYPos()) || (player.getYPos() >= this.getYPos() && player.getYPos() <= this.getYPos()));
//        return (xTouch && yTouch);
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
        return 1500;
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
