package ooga.engine.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;

import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public abstract class GameWorld {
    public static final int FRAMES_PER_SECOND = 30;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private Timeline myAnimation;

    public GameWorld() {
        myAnimation = new Timeline();
    }

    public abstract Scene setupScene(int width, int height, Paint background, Stage currentstage, Boolean tutorial) throws IOException;

    public void setUpAnimation(){
        KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.play();
    }

    protected void stopAnimation() {
        myAnimation.stop();
    }

    public abstract void step(double elapsedTime);
}


