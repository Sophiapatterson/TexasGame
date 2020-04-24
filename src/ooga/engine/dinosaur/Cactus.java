package ooga.engine.dinosaur;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import ooga.engine.game.Enemy;
import ooga.engine.game.Player;

/**
 * Cactus class extends parent class Enemy.
 * This class houses the Cactus object constructor.
 */
public class Cactus extends Enemy {
    public static final int SPEED = 10;
    private String image = "Sprites/dino_smallcactusgroup.png";
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();
    public static final double X_OFFSET = 40;
    public static final double Y_OFFSET = 50;
    private double standardY = DinoGameWorld.FLOOR_HEIGHT;

    /**
     * Cactus constructor which sets the cactus's x and y double property.
     * @param x double that initializes the Cactus's x double property.
     * @param y double that initializes the Cactus's y double property.
     */
    public Cactus(double x, double y) {
        super();
        this.x.setValue(x);
        this.y.setValue(y);
    }

    /**
     * Cactus constructor.
     */
    public Cactus() {
        super();
    }

    /**
     * collide method implemented to fit Cactus object parameters.
     * method determines whether the Player is colliding with a Cactus enemy.
     * @param player the type of player in the game.
     * @return isColliding a boolean that represents whether the Player and Cactus are colliding.
     */
    public boolean collide(Player player) {
        boolean xTouch = ((player.getXPos() >= this.getXPos() && player.getXPos() <= this.getXPos()+X_OFFSET) || (player.getXPos()+X_OFFSET >= this.getXPos() && player.getXPos() <= this.getXPos()+X_OFFSET));
        boolean yTouch = player.getYPos()+Y_OFFSET >= this.getYPos();
        boolean isColliding = (xTouch && yTouch);
        return isColliding;
    }

    /**
     * setStandardY method used to set an cactus's position to line up
     * with the Y-coordinate of DinoGame's floor.
     */
    @Override
    public void setStandardY(){
        setYPos(standardY);
    }

    /**
     * scroll method used to make cacti scroll across screen.
     */
    @Override
    public void scroll() {
        setXPos(getXPos() - SPEED);
    }

    /**
     * sets Cactus object's X-coordinate
     * @param x desired X-coordinate
     */
    @Override
    public void setXPos(double x) {
        this.x.setValue(x);
    }
    /**
     * sets Cactus object's Y-coordinate
     * @param y desired Y-coordinate
     */
    @Override
    public void setYPos(double y){
        this.y.setValue(y);
    }

    /**
     * gets Cactus object's X-coordinate
     * @return double x that represents the cactus's X-coordinate.
     */
    @Override
    public double getXPos(){ return x.getValue(); }
    /**
     * gets Cactus object's Y-coordinate
     * @return double y that represents the cactus's Y-coordinate.
     */
    @Override
    public double getYPos(){ return y.getValue(); }

    /**
     * getImage method returns the cactus's Sprite path.
     * @return String that indicates where cactus image is stored.
     */
    @Override
    public String getImage() {
        return image;
    }

    /**
     * getXProperty returns the cactus's X double property.
     * @return x which is the cactus's X double property.
     */
    @Override
    public DoubleProperty getXProperty(){
        return x;
    }
    /**
     * getYProperty returns the cactus's Y double property.
     * @return y which is the cactus's Y double property.
     */
    @Override
    public DoubleProperty getYProperty(){
        return y;
    }
}
