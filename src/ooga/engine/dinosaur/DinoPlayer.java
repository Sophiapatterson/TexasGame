package ooga.engine.dinosaur;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import ooga.engine.game.Player;

/**
 * DinoPlayer class extends Player class.
 * DinoPlayer object to be used in DinoGame.
 */
public class DinoPlayer extends Player {
    public static final double GRAVITY = 2.5;
    public static final int DEFAULT_JUMP_STRENGTH = 24;
    private double jumpStrength = DEFAULT_JUMP_STRENGTH;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();

    /**
     * DinoPlayer constructor initializes new DinoPlayer.
     * @param x sets initial DoubleProperty x value of DinoPlayer.
     * @param y sets initial DoubleProperty y value of DinoPlayer.
     */
    public DinoPlayer(double x, double y) {
        super();
        this.x.setValue(x);
        this.y.setValue(y);
    }
    /**
     * Jump method determined change in DinoPlayer YPos after DinoPlayer jumps.
     */
    @Override
    public void jump() {
        this.setYPos(getYPos() - jumpStrength);
        fall();
    }

    @Override
    public void fall() {
        jumpStrength -= GRAVITY;
    }
    /**
     * resetJumpStrength method used to reset DinoPlayer's jump strength
     * after jumping. Each jump requires jump strength 24.
     */
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
        return (y.getValue() < floorY && y.getValue() - jumpStrength - GRAVITY < floorY);
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
     * getter method for Player's DoubleProperty x.
     * @return x which is Player's SimpleDoubleProperty.
     */
    public DoubleProperty getXProperty(){
        return x;
    }

    /**
     * getter method for Player's DoubleProperty y.
     * @return y which is Player's SimpleDoubleProperty.
     */
    public DoubleProperty getYProperty(){
        return y;
    }
    /**
     * getter method for Player's x-coordinate.
     * @return XPos which is the Player's double x-value representing it's x-position.
     */
    public double getXPos(){ return this.x.getValue(); }
    /**
     * getter method for Player's y-coordinate.
     * @return YPos which is the Player's double y-value representing its y-position.
     */
    public double getYPos(){ return this.y.getValue(); }

}

