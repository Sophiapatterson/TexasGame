package ooga;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.Screens.StartScreen;
import ooga.Screens.TutorialScreen;
import ooga.engine.dinosaur.DinoGameWorld;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ResourceBundle;

import static ooga.Screens.Screen.BACKGROUND;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TutorialsTest extends DukeApplicationTest{

    public static final int SCREEN_WIDTH = 850;
    public static final int SCREEN_HEIGHT = 600;
    private DinoGameWorld dinogame;
    private Stage myStage;

    /**@Override
    public void start(Stage primaryStage) throws IOException {
        myStage = primaryStage;
        myStage.setTitle("Texas");
        StartScreen startMenu = new StartScreen();
        Scene firstscene = startMenu.createMainScreen(myStage);
        myStage.setScene(firstscene);
        myStage.show();
    }*/

    @Test
    void testDinoTutorial(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                showDinoTutorial();
                Text message0 = lookup("#message0").query();
                assertEquals("Press Space to jump over the cactus!",message0.getText());
                System.out.println(myStage.getScene().getRoot().getChildrenUnmodifiable());
                System.out.println(message0.getText());
            }
        });
    }

    private void showDinoTutorial(){
        myStage = new Stage();
        dinogame = new DinoGameWorld();
        myStage.setScene(dinogame.setupScene(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND, myStage, true));
        myStage.show();
    }

}
