package ooga.engine.dinosaur;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.engine.game.Enemy;
import ooga.engine.game.Player;

public class Cactus extends Enemy {
    public static final int SPEED = 10;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();
    public static final double X_OFFSET = 40;
    public static final double Y_OFFSET = 50;

    public Cactus(double x, double y) {
        super();
        this.x.setValue(x);
        this.y.setValue(y);
//        initializeCactusImage(new Image(filename));
//        setXPos(500);
//        setYPos(DinoGameWorld.FLOOR_HEIGHT + 15);
    }

    public boolean collide(Player player) {
        boolean xTouch = ((player.getXPos() >= this.getXPos() && player.getXPos() <= this.getXPos()+X_OFFSET) || (player.getXPos()+X_OFFSET >= this.getXPos() && player.getXPos() <= this.getXPos()+X_OFFSET));
        boolean yTouch = player.getYPos()+Y_OFFSET >= this.getYPos();
        return (xTouch && yTouch);
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
    public DoubleProperty getYProperty(){
        return y;
    }


}
