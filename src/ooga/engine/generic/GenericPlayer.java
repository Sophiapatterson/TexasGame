package ooga.engine.generic;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import ooga.engine.game.Player;

public class GenericPlayer extends Player {

    private double jumpStrength;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();
    private GameRules rules;

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

    public DoubleProperty getXProperty(){
        return x;
    }

    public DoubleProperty getYProperty(){
        return y;
    }

    public double getXPos(){ return this.x.getValue(); }
    public double getYPos(){ return this.y.getValue(); }

}

