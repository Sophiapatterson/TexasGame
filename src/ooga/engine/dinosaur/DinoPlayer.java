package ooga.engine.dinosaur;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.engine.game.Player;

public class DinoPlayer extends Player {
    public static final double GRAVITY = 2.5;
    public static final int DEFAULT_JUMP_STRENGTH = 24;
    private double jumpStrength = DEFAULT_JUMP_STRENGTH;


    public DinoPlayer(Image image) {
        super();
        this.playerImage = new ImageView(image);
        this.playerImage.setFitWidth(100);
        this.playerImage.setFitHeight(300);
        this.playerImage.setX(100);
        this.playerImage.setY(300);
        x = 100;
        y = 300;
        this.playerImage.setPreserveRatio(true);
        this.playerImage.visibleProperty();
    }

    @Override
    public void jump() {
        this.setyPos(getyPos() - jumpStrength);
        fall();
    }

    @Override
    public void fall() {
        jumpStrength -= GRAVITY;
    }

    public void resetJumpStrength() {
        jumpStrength = DEFAULT_JUMP_STRENGTH;
    }
}
