package ooga.engine.game;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import ooga.Screens.DinoPlayerView;
import ooga.engine.dinosaur.DinoGameWorld;
import ooga.engine.jetpack.JetpackGameWorld;

public class Coin extends Powerup {
    private static final int SPEED = 5;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();
    private double standardY = JetpackGameWorld.FLOOR_HEIGHT;
    private String image = "Sprites/general_coin.png";


    public Coin(double x, double y){
        super();
        this.x.setValue(x);
        this.y.setValue(y);
    }

//    public Coin() {
//        super();
//    }

    @Override
    public boolean collide(Player player) {
        return false;
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
