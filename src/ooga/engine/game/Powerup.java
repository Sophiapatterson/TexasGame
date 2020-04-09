package ooga.engine.game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Powerup extends Rectangle implements Collidable, Scrolling {

    private static final double DEF_PU_WIDTH = 10;
    private static final double DEF_PU_HEIGHT = 10;
    private static final double DEF_PU_X = 500;
    private static final double DEF_PU_Y = 300;
    private static final javafx.scene.paint.Paint DEF_PU_COLOR = Color.GREEN;

    public Powerup() {
        super(DEF_PU_WIDTH,DEF_PU_HEIGHT, DEF_PU_COLOR);
        setX(DEF_PU_X);
        setY(DEF_PU_Y);
    }

    public int scoreBonus(){
        return 1000;
    }

    public void move() {
        scroll();
    }
}
