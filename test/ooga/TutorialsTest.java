package ooga;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.Screens.StartScreen;
import ooga.Screens.TutorialScreen;
import ooga.engine.dinosaur.DinoGameWorld;
import ooga.engine.flappy.FlappyGameWorld;
import ooga.engine.jetpack.JetpackGameWorld;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ResourceBundle;

import static ooga.Screens.Screen.BACKGROUND;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TutorialsTest extends DukeApplicationTest{

    public static final int SCREEN_WIDTH = 850;
    public static final int SCREEN_HEIGHT = 600;
    private DinoGameWorld dinogame;
    private FlappyGameWorld flappygame;
    private JetpackGameWorld jetpackgame;
    private Stage myStage;

    @Test
    void testDinoTutorial(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                showDinoTutorial();
                Text message0 = lookup("#message0").query();
                assertEquals("Press Space to jump over the cactus!",message0.getText());
            }
        });
    }

    private void showDinoTutorial(){
        myStage = new Stage();
        dinogame = new DinoGameWorld();
        myStage.setScene(dinogame.setupScene(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND, myStage, true));
        myStage.show();
    }

    @Test
    void testFlappyTutorial(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                showFlappyTutorial();
                Text message0 = lookup("#message0").query();
                assertEquals("Press Space to keep flying and go through the pipes!",message0.getText());
            }
        });
    }

    private void showFlappyTutorial(){
        myStage = new Stage();
        flappygame = new FlappyGameWorld();
        myStage.setScene(flappygame.setupScene(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND, myStage, true));
        myStage.show();
    }

    @Test
    void testJetpackTutorial(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                showJetpackTutorial();
                Text message0 = lookup("#message0").query();
                assertEquals("Press Space to keep flying and avoid the zappers!",message0.getText());
            }
        });
    }
    
    private void showJetpackTutorial(){
        myStage = new Stage();
        jetpackgame = new JetpackGameWorld();
        myStage.setScene(jetpackgame.setupScene(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND, myStage, true));
        myStage.show();
    }
}
