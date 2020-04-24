package ooga;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.Screens.ChangeScreen;
import ooga.Screens.EndScreen;
import ooga.Screens.StartScreen;
import ooga.Screens.TutorialScreen;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScreensTest extends DukeApplicationTest{
    private TutorialScreen tutorialscreen;
    private ChangeScreen changescreen;
    private EndScreen endscreen;
    private Stage myStage;
    private ResourceBundle creditsResources;

    @Override
    public void start(Stage primaryStage) throws IOException {
        creditsResources = ResourceBundle.getBundle("ooga.Screens.Properties.Credits");
        myStage = primaryStage;
        myStage.setTitle("Texas");
        StartScreen startMenu = new StartScreen();
        Scene firstscene = startMenu.createMainScreen(myStage);
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
                Parent change = changescreen.createMainScreen(myStage).getRoot();
                start.fire();
                for(int i = 0; i<change.getChildrenUnmodifiable().size(); i++){
                    assertEquals(change.getChildrenUnmodifiable().get(i).getId(),myStage.getScene().getRoot().getChildrenUnmodifiable().get(i).getId());
                }
                tutorial.fire();
                Parent tutorialroot = tutorialscreen.createMainScreen(myStage).getRoot();
                for(int i = 0; i<tutorialroot.getChildrenUnmodifiable().size(); i++){
                    assertEquals(tutorialroot.getChildrenUnmodifiable().get(i).getId(),myStage.getScene().getRoot().getChildrenUnmodifiable().get(i).getId());
                }
                System.out.println(creditsResources.keySet());
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
            }
        });
    }

    private void showTutorialScreen(){
        tutorialscreen = new TutorialScreen();
        Scene tutorialscene = tutorialscreen.createMainScreen(myStage);
        myStage.setScene(tutorialscene);
        myStage.show();
    }

    @Test
    void testTutorialEndScreen(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                showTutorialEndScreen();
                Button tutorial = lookup("#tutorial").query();
                Button gamechooser = lookup("#gamechooser").query();
                Text title = lookup("#Title").query();
                assertEquals("Go back to tutorials",tutorial.getText());
                assertEquals("Go to game chooser",gamechooser.getText());
                assertEquals("Are you ready to play, or do you need another tutorial?",title.getText());
            }
        });
    }

    private void showTutorialEndScreen(){
        tutorialscreen = new TutorialScreen();
        Scene tutorialend = tutorialscreen.TutorialorGameChooser(myStage);
        myStage.setScene(tutorialend);
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
            }
        });
    }

    private void showChangeScreen(){
        changescreen = new ChangeScreen();
        Scene changescene = changescreen.createMainScreen(myStage);
        myStage.setScene(changescene);
        myStage.show();
    }

    @Test
    void testEndScreen(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                changescreen = new ChangeScreen();
                Parent change = changescreen.createMainScreen(myStage).getRoot();
                showEndScreen();
                Button playagain = lookup("#again").query();
                Button credits = lookup("#credits").query();
                Button quit = lookup("#quitbutton").query();
                Button scores = lookup("#scores").query();
                Text title = lookup("#Title").query();
                assertEquals("Play Again",playagain.getText());
                assertEquals("Credits",credits.getText());
                assertEquals("Quit Game",quit.getText());
                assertEquals("Leaderboard",scores.getText());
                assertEquals("Game Over",title.getText());
                playagain.fire();
                for(int i = 0; i<change.getChildrenUnmodifiable().size(); i++){
                   assertEquals(change.getChildrenUnmodifiable().get(i).getId(),myStage.getScene().getRoot().getChildrenUnmodifiable().get(i).getId());
                }
            }
        });
    }

    private void showEndScreen(){
        endscreen = new EndScreen("Dinosaur");
        Scene endscene = endscreen.createEndScreen(myStage, 400);
        myStage.setScene(endscene);
        myStage.show();
    }

    @Test
    void testLeaderboards(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                showLeaderboards();
                Button newscore = lookup("#newscore").query();
                Button back = lookup("#back").query();
                Text title = lookup("#Title").query();
                assertEquals("Add your score",newscore.getText());
                assertEquals("Back",back.getText());
                assertEquals("Leaderboard",title.getText());
            }
        });
    }

    private void showLeaderboards(){
        endscreen = new EndScreen("Dinosaur");
        Scene leaderboards = endscreen.createLeaderboard(myStage, 500);
        myStage.setScene(leaderboards);
        //myStage.show();
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
        endscreen = new EndScreen("Dinosaur");
        Scene credits = endscreen.createCredits(myStage);
        myStage.setScene(credits);
        myStage.show();
    }
}
