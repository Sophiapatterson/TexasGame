package ooga.engine.dinosaur;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.engine.game.Player;

public class DinoPlayer extends Player {
    public static final double GRAVITY = 2.5;
    public static final int DEFAULT_JUMP_STRENGTH = 24;
    private double jumpStrength = DEFAULT_JUMP_STRENGTH;
    private ImageView imageView;


    public DinoPlayer(Image image) {
        super();
        this.imageView = new ImageView(image);
        this.imageView.setFitWidth(100);
        this.imageView.setFitHeight(300);
        setX(100);
        setY(300);
        this.imageView.setPreserveRatio(true);
        this.imageView.visibleProperty();
    }

    public ImageView getImageView(){
        return imageView;
    }

    @Override
    public void jump() {
        setY(getY() - jumpStrength);
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
