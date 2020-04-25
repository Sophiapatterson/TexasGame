package ooga.engine.game;

import javafx.beans.property.DoubleProperty;

/**
 * Player abstract class implements Gravity interface.
 * Extended by DinoPlayer, BirdPlayer, JetpackPlayer, and GenericPlayer.
 * Outlines basic Player functionality and methods.
 */
public abstract class Player implements Gravity {
    /**
     * abstract jump method customizes instructions for how a given player
     * can jump in given game.
     */
    public abstract void jump();

    /**
     * abstract resetJumpStrength method used to customize reset Player's jump strength
     * after jumping. Each game requires different jump strength.
     */
    public abstract void resetJumpStrength();

    /**
     * abstract boolean isAirborne determines whether Player is midair depending on floor of given game.
     * @param floorY double floorY taken as parameter because floor Y-coordinate varies by game.
     * @return returns boolean true if player is above floor, false if player is not.
     */
    public abstract boolean isAirborne(double floorY);

    /**
     * abstract setXPos allows Player's x-coordinate to be set.
     * @param x double x-value used for desired new x-coordinate.
     */
    public abstract void setXPos(double x);

    /**
     * abstract setYPos allows Player's y-coordinate to be set.
     * @param y double y-value used for desired new y-coordinate.
     */
    public abstract void setYPos(double y);

    /**
     * abstract getter method for Player's x-coordinate.
     * @return the Player's double x-value representing it's x-position.
     */
    public abstract double getXPos();

    /**
     * abstract getter method for Player's y-coordinate.
     * @return the Player's double y-value representing its y-position.
     */
    public abstract double getYPos();

    /**
     * abstract getter method for Player's DoubleProperty x.
     * @return Player's SimpleDoubleProperty.
     */
    public abstract DoubleProperty getXProperty();

    /**
     * abstract getter method for Player's DoubleProperty y.
     * @return Player's SimpleDoubleProperty.
     */
    public abstract DoubleProperty getYProperty();

}
