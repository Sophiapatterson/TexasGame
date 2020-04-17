package ooga.engine.dinosaur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.engine.game.Enemy;
import ooga.engine.game.GameManager;
import ooga.engine.game.Powerup;

import java.io.IOException;

import static ooga.Screens.ChangeScreen.*;
import static ooga.engine.dinosaur.DinoGameWorld.FLOOR_HEIGHT;

public class DinoGameTutorial {

    public static final int FRAMES_PER_SECOND = 30;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public DinoGameWorld dinogame;
    private GameManager gameManager;
    public Stage myStage;
    public Scene myScene;
    public Timeline myAnimation = new Timeline();
    //public static final String TutorialCSV = "data/CSV configurations/dinoTutorial.csv";
    public static final String TutorialCSV = "data/CSV configurations/levelOne.csv";

    public DinoGameTutorial(){
        dinogame = new DinoGameWorld();
    }

    public Scene createTutorial(int width, int height, Paint background, Stage currentstage) throws IOException {
        myStage = currentstage;
        //VBox currentlayout = new VBox();
        //currentlayout.getChildren().addAll(dinoScene.getRoot().getChildrenUnmodifiable());
        Parent tutorialroot = myScene.getRoot();
        Text test = new Text(50,50, "Hi");
        //test.setText("Hi");
        //myScene = new Scene(currentlayout, SCREEN_WIDTH, SCREEN_HEIGHT);
        return myScene;
    }

    public void setUpAnimation(){
        KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.play();
    }

    public void step(double elapsedTime){
        dinogame.step(elapsedTime);
        gameManager.handleJump(FLOOR_HEIGHT);

    }
}
