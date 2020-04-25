package ooga.engine.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;

import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * GameWorld abstract class includes the basic framework used to setup for a game,
 * including abstract methods that can be inherited to initialize scenes in a game,
 * stop a game, and a step function.
 */
public abstract class GameWorld {
    public static final int SCORE_X = 30;
    public static final int SCORE_Y = 30;
    public static final int SCORE_TEXT_SIZE = 30;
    public static final int FRAMES_PER_SECOND = 30;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private Timeline myAnimation;

    /**
     * GameWorld Constuctor. Initializes timeline.
     */
    public GameWorld() {
        myAnimation = new Timeline();
    }

    public abstract Scene setupScene(int width, int height, Paint background, Stage currentstage, Boolean tutorial) throws IOException;

    /**
     * sets up Animation.
     */
    public void setUpAnimation(){
        KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.play();
    }

    /**
     * stops the Animation.
     */
    protected void stopAnimation() {
        myAnimation.stop();
    }

    public abstract void step(double elapsedTime);
}


