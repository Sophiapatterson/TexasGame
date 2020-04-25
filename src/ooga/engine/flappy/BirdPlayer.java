package ooga.engine.flappy;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import ooga.engine.game.Player;

/**
 * BirdPlayer class extends Player class.
 * BirdPlayer object to be used in FlappyGame.
 */
public class BirdPlayer extends Player {
    public static final double GRAVITY = 2;
    public static final int DEFAULT_JUMP_STRENGTH = 18;
    private double jumpStrength = 0;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();

    /**
     * BirdPlayer constructor initializes new BirdPlayer.
     * @param x sets initial DoubleProperty x value for BirdPlayer.
     * @param y sets initial DoubleProperty y value for BirdPlayer.
     */
    public BirdPlayer(double x, double y) {
        super();
        this.x.setValue(x);
        this.y.setValue(y);
    }

    /**
     * Jump method determined change in BirdPlayer YPos after BirdPlayer jumps.
     */
    @Override
    public void jump() {
        this.setYPos(getYPos() - jumpStrength);
        fall();
    }
    /**
     * resetJumpStrength method used to reset BirdPlayer's jump strength
     * after jumping. Each jump requires jump strength 18.
     */
    @Override
    public void resetJumpStrength() {
        jumpStrength = DEFAULT_JUMP_STRENGTH;
    }
    /**
     * boolean isAirborne determines whether Player is midair.
     * @param floorY double floorY taken as parameter because floor Y-coordinate varies by game.
     * @return returns boolean true if player is above floor, false if player is not.
     */
    @Override
    public boolean isAirborne(double floorY) {
        return y.getValue() < floorY || y.getValue() - jumpStrength - GRAVITY < floorY;
    }
    /**
     * setXPos allows Player's x-coordinate to be set.
     * @param x double x-value for desired new x-coordinate.
     */
    @Override
    public void setXPos(double x) {
        this.x.setValue(x);
    }
    /**
     * setYPos allows Player's y-coordinate to be set.
     * @param y double y-value for desired new y-coordinate.
     */
    @Override
    public void setYPos(double y) {
        this.y.setValue(y);
    }
    /**
     * getter method for Player's x-coordinate.
     * @return XPos which is the Player's double x-value representing it's x-position.
     */
    @Override
    public double getXPos() {
        return this.x.getValue();
    }
    /**
     * getter method for Player's y-coordinate.
     * @return YPos which is the Player's double y-value representing its y-position.
     */
    @Override
    public double getYPos() {
        return this.y.getValue();
    }

    /**
     * getter method for Player's DoubleProperty x.
     * @return x which is Player's SimpleDoubleProperty.
     */
    @Override
    public DoubleProperty getXProperty() {
        return x;
    }

    /**
     * getter method for Player's DoubleProperty y.
     * @return y which is Player's SimpleDoubleProperty.
     */
    @Override
    public DoubleProperty getYProperty() {
        return y;
    }

    @Override
    public void fall() {
        jumpStrength -= GRAVITY;
    }
}
