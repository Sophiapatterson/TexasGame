package ooga.Screens;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class StartScreen extends Screen {

    private ChangeScreen changescreen;
    private ResourceBundle startResources;
    public static final int SCREEN_WIDTH = 850;
    public static final int SCREEN_HEIGHT = 600;
    private Stage myStage;
    private Text title;

    public StartScreen(){
        changescreen = new ChangeScreen();
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
        Button darkMode = new Button(startResources.getString("DARKMODE-MESSAGE"));
        darkMode.setId("darkmodebutton");
        darkMode.setOnAction(e -> {
            //setUserAgentStylesheet("dark-theme.css");
            myStage.getScene().getStylesheets().add("dark-theme.css");
        });
        return darkMode;
    }

    public Scene createStartScreen(Stage currentStage){
        myStage = currentStage;
        VBox startlayout = new VBox();
        initLayout(startlayout);
        Button start = new Button(startResources.getString("START-MESSAGE"));
        start.setId("startbutton");
        start.setOnAction(e -> {
            myStage.setScene(changescreen.createChangeScreen(myStage));
        });
        Button quit = quitButton();
        Button darkMode = darkModeButton();
        title.setText("Welcome to TEXAS");
        startlayout.getChildren().addAll(title, start,quit,darkMode);
        Scene StartScreen = new Scene(startlayout, SCREEN_WIDTH, SCREEN_HEIGHT);
        return StartScreen;
    }

    @Override
    public void initLayout(VBox layout) {
        layout.setPadding(new Insets(10, 50, 50, 50));
        layout.setSpacing(25);
        layout.setAlignment(Pos.CENTER);
    }

    @Override
    public Text initTitle() {
        return super.initTitle();
    }
}
