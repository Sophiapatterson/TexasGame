package ooga.screens;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class StartScreen extends Screen {

    private ChangeScreen changescreen;
    private TutorialScreen tutorialscreen;
    private ResourceBundle startResources;
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

    @Override
    public Scene createMainScreen(Stage currentStage) {
        myStage = currentStage;
        VBox startlayout = new VBox();
        initLayout(startlayout);
        Button start = createButton(startResources.getString("START-MESSAGE"), "startbutton");
        start.setOnAction(e -> {
            myStage.setScene(changescreen.createMainScreen(myStage));
        });
        Button tutorial = createButton(startResources.getString("TUTORIAL-MESSAGE"), "tutorial");
        tutorial.setOnAction(e -> {
            myStage.setScene(tutorialscreen.createMainScreen(myStage));
        });
        Button quit = quitButton();
        Button darkMode = darkModeButton();
        darkMode.setOnAction(event -> {
            setDarkModeTrue();
            startlayout.getStylesheets().remove("Styling/Screen.css");
            startlayout.getStylesheets().add("Styling/dark-theme.css");
        });
        title.setText("Welcome to TEXAS");
        title.getStyleClass().add("titletxt");
        startlayout.getChildren().addAll(title, start,tutorial,quit,darkMode);
        Scene StartScreen = new Scene(startlayout, SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_COLOR);
        return StartScreen;
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
}
