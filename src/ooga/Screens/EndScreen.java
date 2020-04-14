package ooga.Screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.data.HighScores;

import java.util.ResourceBundle;

public class EndScreen extends Screen {
    private StartScreen startscreen;
    private ChangeScreen changescreen;
    private Text title;
    private Button quit;
    public static final int SCREEN_WIDTH = 850;
    public static final int SCREEN_HEIGHT = 600;
    private ResourceBundle endResources;
    private ResourceBundle creditsResources;
    private Stage myStage;
    private final String screenCSS = "Styling/Screen.css";
    private HighScores highscores;

    public EndScreen(){
        myStage = new Stage();
        endResources = ResourceBundle.getBundle("ooga.Screens.Properties.EndScreen");
        creditsResources = ResourceBundle.getBundle("ooga.Screens.Properties.Credits");
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
        Button credits = new Button(endResources.getString("CREDITS-MESSAGE"));
        credits.setId("credits");
        credits.setOnAction( e -> {
            myStage.setScene(createCredits(myStage));
        });
        title.setText(endResources.getString("GAME-OVER"));
        layout.getChildren().addAll(title, playagain, credits, quit);
        Scene EndScreen = new Scene(layout, SCREEN_WIDTH, SCREEN_HEIGHT);
        return EndScreen;
    }

    public Scene createCredits(Stage currentstage){
        myStage = currentstage;
        VBox layout = new VBox();
        initLayout(layout);
        title.setText(endResources.getString("CREDITS-MESSAGE"));
        title.getStyleClass().add("titletxt");
        Text producers = new Text();
        producers.setId("producers");
        producers.setText(creditsResources.getString("PRODUCERS-MESSAGE"));
        producers.getStyleClass().add("medtxt");
        Text luke = createCreditText("LUKE-MESSAGE");
        luke.setId("luke");
        Text jeff = createCreditText("JEFF-MESSAGE");
        jeff.setId("jeff");
        Text sophia = createCreditText("SOPHIA-MESSAGE");
        sophia.setId("sophia");
        Text justin = createCreditText("JUSTIN-MESSAGE");
        justin.setId("justin");
        layout.getChildren().addAll(title, producers, luke, jeff, sophia, justin);
        Scene Credits = new Scene(layout, SCREEN_WIDTH, SCREEN_HEIGHT);
        return Credits;
    }

    public Text createCreditText(String message){
        Text returnedtext = new Text();
        returnedtext.setText(creditsResources.getString(message));
        returnedtext.getStyleClass().add("smalltxt");
        return returnedtext;
    }
    @Override
    public void initLayout(VBox layout) {
        layout.setPadding(new Insets(10, 50, 50, 50));
        layout.setSpacing(25);
        layout.setAlignment(Pos.CENTER);
        layout.getStylesheets().add(screenCSS);
    }
}
