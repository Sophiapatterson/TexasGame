package ooga;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.Screens.ChangeScreen;
import ooga.Screens.EndScreen;
import ooga.Screens.StartScreen;
import ooga.Screens.TutorialScreen;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.matcher.base.WindowMatchers;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScreensTest extends DukeApplicationTest{
    private TutorialScreen tutorialscreen;
    private ChangeScreen changescreen;
    private EndScreen endscreen;
    private Stage myStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        myStage = primaryStage;
        myStage.setTitle("Texas");
        StartScreen startMenu = new StartScreen();
        Scene firstscene = startMenu.createStartScreen(myStage);
        myStage.setScene(firstscene);
        myStage.show();
    }

    @Test
    void testStartScreen(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                changescreen = new ChangeScreen();
                tutorialscreen = new TutorialScreen();
                Button start = lookup("#startbutton").query();
                Button tutorial = lookup("#tutorial").query();
                Button quit = lookup("#quitbutton").query();
                Text title = lookup("#Title").query();
                assertEquals("Start Game",start.getText());
                assertEquals("Tutorial",tutorial.getText());
                assertEquals("Quit Game",quit.getText());
                assertEquals("Welcome to TEXAS",title.getText());
                Parent change = changescreen.createChangeScreen(myStage).getRoot();
                start.fire();
                for(int i = 0; i<change.getChildrenUnmodifiable().size(); i++){
                    assertEquals(change.getChildrenUnmodifiable().get(i).getId(),myStage.getScene().getRoot().getChildrenUnmodifiable().get(i).getId());
                }
                tutorial.fire();
                Parent tutorialroot = tutorialscreen.createTutorial(myStage).getRoot();
                for(int i = 0; i<tutorialroot.getChildrenUnmodifiable().size(); i++){
                    assertEquals(tutorialroot.getChildrenUnmodifiable().get(i).getId(),myStage.getScene().getRoot().getChildrenUnmodifiable().get(i).getId());
                }
            }
        });
    }

    @Test
    void testTutorialScreen(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                showTutorialScreen();
                tutorialscreen = new TutorialScreen();
                Button dinosaur = lookup("#dino").query();
                Button flappy = lookup("#flappybird").query();
                Button jetpack = lookup("#jet").query();
                Text title = lookup("#Title").query();
                assertEquals("Dinosaur Tutorial",dinosaur.getText());
                assertEquals("Flappy Tutorial",flappy.getText());
                assertEquals("Jetpack Tutorial",jetpack.getText());
                assertEquals("Tutorial Chooser",title.getText());
                Parent placeholder = tutorialscreen.placeholderScene().getRoot();
                dinosaur.fire();
                for(int i = 0; i<placeholder.getChildrenUnmodifiable().size(); i++){
                    assertEquals(placeholder.getChildrenUnmodifiable().get(i).getId(),myStage.getScene().getRoot().getChildrenUnmodifiable().get(i).getId());
                }
            }
        });
    }

    private void showTutorialScreen(){
        tutorialscreen = new TutorialScreen();
        Scene tutorialscene = tutorialscreen.createTutorial(myStage);
        myStage.setScene(tutorialscene);
        myStage.show();
    }

    @Test
    void testChangeScreen(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                showChangeScreen();
                Button dinosaur = lookup("#dino").query();
                Button flappy = lookup("#flappybird").query();
                Button jetpack = lookup("#jet").query();
                Text title = lookup("#Title").query();
                assertEquals("Dinosaur",dinosaur.getText());
                assertEquals("Flappy Bird",flappy.getText());
                assertEquals("Jetpack Joyride",jetpack.getText());
                assertEquals("Choose A Game",title.getText());

                jetpack.fire();
                Parent phscreen = changescreen.placeholderScene().getRoot();
                for(int i = 0; i<phscreen.getChildrenUnmodifiable().size(); i++){
                    assertEquals(phscreen.getChildrenUnmodifiable().get(i).getId(),myStage.getScene().getRoot().getChildrenUnmodifiable().get(i).getId());
                }
            }
        });
    }

    private void showChangeScreen(){
        changescreen = new ChangeScreen();
        Scene changescene = changescreen.createChangeScreen(myStage);
        myStage.setScene(changescene);
        myStage.show();
    }

    @Test
    void testEndScreen(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                changescreen = new ChangeScreen();
                Parent change = changescreen.createChangeScreen(myStage).getRoot();
                showEndScreen();
                Button playagain = lookup("#again").query();
                Button quit = lookup("#quitbutton").query();
                Text title = lookup("#Title").query();
                assertEquals("Play Again",playagain.getText());
                assertEquals("Quit Game",quit.getText());
                assertEquals("Game Over",title.getText());
                playagain.fire();
                for(int i = 0; i<change.getChildrenUnmodifiable().size(); i++){
                   assertEquals(change.getChildrenUnmodifiable().get(i).getId(),myStage.getScene().getRoot().getChildrenUnmodifiable().get(i).getId());
                   System.out.println(myStage.getScene().getRoot().getChildrenUnmodifiable().get(i).getId());
                }
            }
        });
    }

    private void showEndScreen(){
        endscreen = new EndScreen();
        Scene endscene = endscreen.createEndScreen(myStage, 400, "DINOSAUR");
        myStage.setScene(endscene);
        myStage.show();
    }

    @Test
    void testCreditScreen(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                showCreditsScreen();
                Text producers = lookup("#producers").query();
                Text luke = lookup("#luke").query();
                Text jeff = lookup("#jeff").query();
                Text sophia = lookup("#sophia").query();
                Text justin = lookup("#justin").query();
                Text title = lookup("#Title").query();
                assertEquals("Credits",title.getText());
                assertEquals("Producers/Designers",producers.getText());
                assertEquals("Luke Evans",luke.getText());
                assertEquals("Jeffrey Luo",jeff.getText());
                assertEquals("Sophia Patterson",sophia.getText());
                assertEquals("Justin Wu",justin.getText());
            }
        });
    }

    private void showCreditsScreen(){
        endscreen = new EndScreen();
        Scene credits = endscreen.createCredits(myStage);
        myStage.setScene(credits);
        myStage.show();
    }
}
