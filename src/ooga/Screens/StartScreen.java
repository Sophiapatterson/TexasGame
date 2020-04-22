package ooga.Screens;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class StartScreen extends Screen {

    private ChangeScreen changescreen;
    private TutorialScreen tutorialscreen;
    private ResourceBundle startResources;
    public static final int SCREEN_WIDTH = 850;
    public static final int SCREEN_HEIGHT = 600;
    public static final Color SCREEN_COLOR = Color.GOLD;
    private Stage myStage;
    private Text title;
    private Button darkMode;

    public StartScreen(){
        changescreen = new ChangeScreen();
        tutorialscreen = new TutorialScreen();
        myStage = new Stage();
        startResources = ResourceBundle.getBundle("ooga.Screens.Properties.StartScreen");
        title = initTitle();
    }

    public Button quitButton(){
        Button quit = new Button(startResources.getString("QUIT-MESSAGE"));
        quit.setId("quitbutton");
        quit.setOnAction(e -> {
            Platform.exit();
        });
        return quit;
    }

    public Button darkModeButton(){
        darkMode = new Button(startResources.getString("DARKMODE-MESSAGE"));
        darkMode.setId("darkmodebutton");
        return darkMode;
    }

//    public void setOnDarkMode(EventHandler<ActionEvent> e){
//        darkMode.setOnAction(event -> e.handle(event));
//    }

    public Scene createStartScreen(Stage currentStage){
        myStage = currentStage;
        VBox startlayout = new VBox();
        initLayout(startlayout);
        Button start = new Button(startResources.getString("START-MESSAGE"));
        start.setId("startbutton");
        start.setOnAction(e -> {
            myStage.setScene(changescreen.createChangeScreen(myStage));
        });
        Button tutorial = new Button(startResources.getString("TUTORIAL-MESSAGE"));
        tutorial.setId("tutorial");
        tutorial.setOnAction(e -> {
            myStage.setScene(tutorialscreen.createTutorial(myStage));
        });
        Button quit = quitButton();
        Button darkMode = darkModeButton();
        darkMode.setOnAction(event -> {
            this.setDarkModeTrue();
            startlayout.getStylesheets().remove("Styling/Screen.css");
            startlayout.getStylesheets().add("Styling/dark-theme.css");
            changescreen.setDarkModeTrue();
            tutorialscreen.setDarkModeTrue();
        });
        title.setText("Welcome to TEXAS");
        title.getStyleClass().add("titletxt");
        startlayout.getChildren().addAll(title, start,tutorial,quit,darkMode);
        Scene StartScreen = new Scene(startlayout, SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_COLOR);
        return StartScreen;
    }
}
