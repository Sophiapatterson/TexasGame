package ooga.engine.generic;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import ooga.engine.game.Player;

public class GenericPlayer extends Player {

    private double jumpStrength;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();
    private GameRules rules;

    /**
     * The character of the GenericGameWorld that implements the Player superclass and its methods where all
     * relevant details are drawn from a properties file
     * @param x - the X coordinate of the player
     * @param y - the starting Y coordinate of the player
     * @param rulesPath - a String that represents the filepath to the appropriate properties file
     */
    public GenericPlayer(double x, double y, String rulesPath) {
        super();
        rules = new GameRules(rulesPath);
        this.x.setValue(x);
        this.y.setValue(y);

        if(rules.START_FALLING){
            jumpStrength = 0;
        } else {
            jumpStrength = rules.DEFAULT_JUMP_STRENGTH;
        }
    }

    @Override
    public void jump() {
        this.setYPos(getYPos() - jumpStrength);
        fall();
    }

    @Override
    public void fall() {
        jumpStrength -= rules.GRAVITY;
    }

    @Override
    public void resetJumpStrength() {
        jumpStrength = rules.DEFAULT_JUMP_STRENGTH;
    }

    @Override
    public boolean isAirborne(double floorY) {
        if(rules.PROPULSION){
             return (y.getValue() < floorY && y.getValue() - jumpStrength - rules.GRAVITY < floorY);
        } else {
            return (y.getValue() < floorY || y.getValue() - jumpStrength - rules.GRAVITY < floorY);
        }
    }

    @Override
    public void setXPos(double x) {
        this.x.setValue(x);
    }

    @Override
    public void setYPos(double y) {
        this.y.setValue(y);
    }

    @Override
    public DoubleProperty getXProperty(){
        return x;
    }

    @Override
    public DoubleProperty getYProperty(){
        return y;
    }

    @Override
    public double getXPos(){ return this.x.getValue(); }

    @Override
    public double getYPos(){ return this.y.getValue(); }

}

