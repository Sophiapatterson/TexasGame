package ooga.engine.generic;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import ooga.engine.game.Enemy;
import ooga.engine.game.Player;

import java.util.Random;

public class GenericEnemy extends Enemy {

    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();
    private double standardY;
    private GameRules rules;

    /**
     * This class is a flexible implementation of the Enemy to be paired with the GenericGameWorld. Most methods
     * are simple getters or implementations of superclass methods.
     * @param rulesPath - a String that represents the filepath to the appropriate properties file
     */
    public GenericEnemy(String rulesPath) {
        super();
        rules = new GameRules(rulesPath);
        standardY = rules.ENEMY_STANDARD_Y;
    }

    /**
     * Method implementation of Collidable interface method that employs two helper methods to calculate collisions.
     * @param player - the GenericGameWorld's player
     * @return - a boolean that represents whether or not the Enemy collided with the Player
     */
    @Override
    public boolean collide(Player player) {
        if(rules.IMMORTAL) return false;
        return (inXBounds(player) && inYBounds(player));
    }

    private boolean inXBounds(Player p){
        return ((p.getXPos() >= this.getXPos() && p.getXPos() <= this.getXPos()+ rules.ENEMY_X_OFFSET) ||
                (p.getXPos()+ rules.ENEMY_X_OFFSET >= this.getXPos() && p.getXPos() <= this.getXPos()+ rules.ENEMY_X_OFFSET));
    }

    private boolean inYBounds(Player p){
        return ((p.getYPos() >= this.getYPos() && p.getYPos() <= this.getYPos()+ rules.ENEMY_Y_OFFSET) ||
                (p.getYPos()+ rules.PLAYER_X_OFFSET >= this.getYPos() && p.getYPos() <= this.getYPos()+ rules.ENEMY_Y_OFFSET));
    }

    @Override
    public void setStandardY(){
        setYPos(standardY);
    }

    @Override
    public void scroll() {
        setXPos(getXPos() - rules.ENEMY_SPEED);
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
    public String getImage() {
        Random r = new Random();
        int pngNum = r.nextInt(rules.COUNT_OF_ENEMY);
        if(pngNum == 0) {
            return rules.ENEMY_PNG;
        } else if(pngNum == 1) {
            return rules.ENEMY_PNG2;
        } else if(pngNum == 2) {
            return rules.ENEMY_PNG3;
        } else
            return rules.ENEMY_PNG4;
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
