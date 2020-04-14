package ooga.Screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class EndScreen extends Screen {
    private StartScreen startscreen;
    private ChangeScreen changescreen;
    private Text title;
    private Button quit;
    public static final int SCREEN_WIDTH = 850;
    public static final int SCREEN_HEIGHT = 600;
    private ResourceBundle endResources;
    private Stage myStage;
    private final String screenCSS = "Styling/Screen.css";

    public EndScreen(){
        myStage = new Stage();
        endResources = ResourceBundle.getBundle("ooga.Screens.Properties.EndScreen");
        startscreen = new StartScreen();
        changescreen = new ChangeScreen();
        title = initTitle();
        quit = startscreen.quitButton();
    }

    @Override
    public Text initTitle() {
        return super.initTitle();
    }

    public Scene createEndScreen(Stage currentstage){
        myStage = currentstage;
        VBox layout = new VBox();
        initLayout(layout);
        Button playagain = new Button(endResources.getString("AGAIN-MESSAGE"));
        playagain.setId("again");
        playagain.setOnAction(e -> {
            myStage.setScene(changescreen.createChangeScreen(myStage));
        });
        title.setText(endResources.getString("GAME-OVER"));
        layout.getChildren().addAll(title, playagain, quit);
        Scene EndScreen = new Scene(layout, SCREEN_WIDTH, SCREEN_HEIGHT);
        return EndScreen;
    }

    @Override
    public void initLayout(VBox layout) {
        layout.setPadding(new Insets(10, 50, 50, 50));
        layout.setSpacing(25);
        layout.setAlignment(Pos.CENTER);
        layout.getStylesheets().add(screenCSS);
    }
}
