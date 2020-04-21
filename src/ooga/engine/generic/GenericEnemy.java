package ooga.engine.generic;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import ooga.engine.game.Enemy;
import ooga.engine.game.Player;

public class GenericEnemy extends Enemy {

    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();
    private double standardY;
    private GameRules rules;

    public GenericEnemy() {
        super();
        rules = new GameRules();
        standardY = rules.ENEMY_STANDARD_Y;
    }

    public boolean collide(Player player) {
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
        return rules.ENEMY_PNG;
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
