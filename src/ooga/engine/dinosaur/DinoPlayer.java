package ooga.engine.dinosaur;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.engine.game.Player;

public class DinoPlayer extends Player {
    public static final double GRAVITY = 2.5;
    public static final int DEFAULT_JUMP_STRENGTH = 24;
    private double jumpStrength = DEFAULT_JUMP_STRENGTH;
    private double x;
    private double y;


    public DinoPlayer(Image image) {
        super();
        x = 100;
        y = DinoGameWorld.FLOOR_HEIGHT;
        initializePlayerImage(image);
    }

    private void initializePlayerImage(Image image) {
        this.playerImage = new ImageView(image);
        this.playerImage.setFitWidth(50);
        this.playerImage.setFitHeight(50);
        this.playerImage.setX(100);
        this.playerImage.setY(DinoGameWorld.FLOOR_HEIGHT);
        this.playerImage.setPreserveRatio(true);
        this.playerImage.visibleProperty();
    }

    @Override
    public void jump() {
        this.setYPos(getYPos() - jumpStrength);
        fall();
    }

    @Override
    public void fall() {
        jumpStrength -= GRAVITY;
    }

    public void resetJumpStrength() {
        jumpStrength = DEFAULT_JUMP_STRENGTH;
    }

    @Override
    public boolean isAirborne(double floorY) {
        return (y < floorY && y - jumpStrength - GRAVITY < floorY);
    }

    @Override
    public void setXPos(double x) {
        this.x = x;
        this.playerImage.setX(x);

    }

    @Override
    public void setYPos(double y) {
        this.y = y;
        this.playerImage.setY(y);
    }

    public double getXPos(){ return this.x; }
    public double getYPos(){ return this.y; }
}
