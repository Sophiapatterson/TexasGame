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

    public GenericEnemy(String rulesPath) {
        super();
        rules = new GameRules(rulesPath);
        standardY = rules.ENEMY_STANDARD_Y;
    }

    public boolean collide(Player player) {
        if(rules.IMMORTAL) return false;
        return (inXBounds(player) && inYBounds(player));
    }

    public boolean inXBounds(Player p){
        return ((p.getXPos() >= this.getXPos() && p.getXPos() <= this.getXPos()+ rules.ENEMY_X_OFFSET) ||
                (p.getXPos()+ rules.ENEMY_X_OFFSET >= this.getXPos() && p.getXPos() <= this.getXPos()+ rules.ENEMY_X_OFFSET));
    }

    public boolean inYBounds(Player p){

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
