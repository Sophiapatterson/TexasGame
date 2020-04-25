package ooga.engine.game;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import ooga.engine.jetpack.JetpackGameWorld;

/**
 * Coin class extends Powerup abstract class.
 * Coin object used in Game to give score boost.
 */
public class Coin extends Powerup {
    public static final int SPEED = 10;
    public static final int COIN_OFFSET = 30;
    public static final int PLAYER_OFFSET = 30;
    public static final int COIN_BONUS = 1500;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();
    private double standardY = JetpackGameWorld.FLOOR_HEIGHT;
    private String image = "Sprites/general_coin.png";

    /**
     * Coin constructor creates new Coin object.
     * @param x double that initializes the Coin's x double property.
     * @param y double that initializes the Coin's y double property.
     */
    public Coin(double x, double y){
        super();
        this.x.setValue(x);
        this.y.setValue(y);
    }

    /**
     * collide method implemented to fit Coin object parameters.
     * method determines whether the player is colliding with a Coin.
     * @param player the Player in the game.
     * @return isColliding a boolean that represents whether the Player and Coin are colliding.
     */
    @Override
    public boolean collide(Player player) {
        boolean xTouch = (player.getXPos() <= this.getXPos() + COIN_OFFSET && player.getXPos() + PLAYER_OFFSET >= this.getXPos());
        boolean yTouch = (player.getYPos() <= this.getYPos() + COIN_OFFSET && player.getYPos() + PLAYER_OFFSET >= this.getYPos());
        return (xTouch && yTouch);
    }
    /**
     * sets Coin object's X-coordinate
     * @param x desired X-coordinate
     */
    @Override
    public void setXPos(double x) { this.x.setValue(x); }
    /**
     * sets Coin object's Y-coordinate
     * @param y desired Y-coordinate
     */
    @Override
    public void setYPos(double y) { this.y.setValue(y); }
    /**
     * gets Coin object's X-coordinate
     * @return double x that represents the Coin's X-coordinate.
     */
    @Override
    public double getXPos() { return this.x.getValue(); }
    /**
     * gets Coin object's Y-coordinate
     * @return double y that represents the Coin's Y-coordinate.
     */
    @Override
    public double getYPos() { return this.y.getValue(); }
    /**
     * getImage method returns the Coin's Sprite path.
     * @return String that indicates where Coin image is stored.
     */
    @Override
    public String getImage() { return image; }

    /**
     * setStandardY method used to set an Coin's position to be appropriately positioned
     * with the Y-coordinate of Game's floor.
     */
    @Override
    public void setStandardY() { setYPos(standardY); }
    /**
     * scroll method used to make Pipe move across screen.
     */
    @Override
    public void scroll() {
        setXPos(getXPos() - SPEED);
    }

    /**
     * scoreBonus method used to boost score when player hits Coin.
     * @return int COIN_BONUS
     */
    @Override
    public int scoreBonus(){
        return COIN_BONUS;
    }
    /**
     * getXProperty returns the Coin's X double property.
     * @return x which is the Coin's X double property.
     */
    @Override
    public DoubleProperty getXProperty(){
        return x;
    }
    /**
     * getYProperty returns the Coin's Y double property.
     * @return y which is the Coin's Y double property.
     */
    @Override
    public DoubleProperty getYProperty(){
        return y;
    }
}
