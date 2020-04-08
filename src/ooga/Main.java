package ooga;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.Screens.Screens;
import ooga.engine.dinosaur.DinoGameWorld;

import java.io.IOException;

import static javafx.application.Application.launch;

public class Main extends Application {

    private static Stage myStage;
    private Timeline myAnimation;
    private DinoGameWorld dinogame;
    public static final int FRAMES_PER_SECOND = 30;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        myStage = primaryStage;
        myStage.setTitle("Texas");
        Screens startMenu = new Screens();
        Scene firstscene = startMenu.createStartScreen(myStage);
        myStage.setScene(firstscene);
        myStage.show();
        //dinogame = new DinoGameWorld();
        //dinogame.setUpAnimation();
    }

    /**public void startGame(){
        dinogame = new DinoGameWorld();
        myAnimation = new Timeline();
        KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> dinogame.step(SECOND_DELAY));
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.play();
    }*/

}
