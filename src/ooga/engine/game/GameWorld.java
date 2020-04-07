package ooga.engine.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.engine.dinosaur.DinoPlayer;

/**
 * Basic game world tailored for dinosaur game at the moment for testing. Need to figure out a way to move this game
 * into a larger game -- possibly find a way to make this game a small screen to import into our final game?
 */
public class GameWorld extends Application {

    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 400;
    public static final double FLOOR_HEIGHT = 300;
    public static final Paint BACKGROUND = Color.AZURE;
    public static final int FRAMES_PER_SECOND = 30;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Player myPlayer;
    private Scene myScene;
    private Timeline myAnimation = new Timeline();

    private JumpManager jumpManager;

    @Override
    public void start(Stage stage) throws Exception {
        myScene = setupScene(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND);
        stage.setScene(myScene);
        stage.setTitle("Dinosaur Game");
        stage.show();

        KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.play();
    }

    // Create the game's "scene": what shapes will be in the game and their starting properties
    Scene setupScene(int width, int height, Paint background) {
        Group root = new Group();

        myPlayer = new DinoPlayer();
        jumpManager = new JumpManager(myPlayer);

        root.getChildren().add(myPlayer);

        myScene = new Scene(root, width, height, background);
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));

        return myScene;
    }

    // Change properties of shapes to animate them
    void step (double elapsedTime) {
        jumpManager.handleJump(FLOOR_HEIGHT);
    }

    private void handleKeyInput (KeyCode code) {
        if(code == KeyCode.SPACE) {
            myPlayer.jump();
        }
    }

    /**
     * We don't want to be launching in the game world later on. This is just to get things working for now.
     * @param args
     */
    public static void main (String[] args) {
        launch(args);
    }
}
