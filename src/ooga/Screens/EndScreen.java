package ooga.Screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.data.HighScores;
import ooga.data.Score;

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
       // this.setisDarkMode(Screen.getIsDarkMode());
        //currently does not work ^ TODO fix @Sophia
    }

    @Override
    public Text initTitle() {
        return super.initTitle();
    }

    public Scene createEndScreen(Stage currentstage, int gamescore, String version){
        myStage = currentstage;
        highscores = new HighScores(version);
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
        Button scores = new Button("Leaderboard");
        scores.setId("scores");
        scores.setOnAction( e -> {
            myStage.setScene(createLeaderboard(myStage, gamescore, true));
        });
        title.setText(endResources.getString("GAME-OVER"));
        layout.getChildren().addAll(title, playagain, credits, scores, quit);
        Scene EndScreen = new Scene(layout, SCREEN_WIDTH, SCREEN_HEIGHT);
        return EndScreen;
    }

    public Scene createLeaderboard(Stage currentstage, int score, boolean allowSubmission) {
        myStage = currentstage;
        VBox layout = new VBox();
        initLayout(layout);
        title.setText("Leaderboards");
        title.getStyleClass().add("titletxt");
        layout.getChildren().add(title);
        int i = 0;
        for(String s: highscores.getHighScoresAsStrings()) {
            Text currScore = new Text();
            currScore.setId("scoreNumber"+i);
            currScore.setText(s);
            currScore.getStyleClass().add("medtxt");
            i++;
            layout.getChildren().add(currScore);
        }
        Button newScore = new Button("Add Your Score");
        newScore.setId("newscore");
        newScore.setOnAction( e -> {
            submitScore(score);
        });
        if(allowSubmission){
            layout.getChildren().add(newScore);
        }
        Scene Leaderboard = new Scene(layout, SCREEN_WIDTH, SCREEN_HEIGHT);
        return Leaderboard;
    }

    private void submitScore(int score) {
        Stage enterData = new Stage();
        VBox dataBox = new VBox();
        initLayout(dataBox);
        enterData.setTitle("Enter your name");
        Label name = new Label("Enter your name");
        name.getStyleClass().add("medtxt");
        TextField nameTextField = new TextField();
        Button submit = new Button("submit");
        submit.setId("submit");
        submit.setOnAction( f -> {
            highscores.addScore(new Score(nameTextField.getText(), score));
            enterData.close();
            myStage.setScene(createLeaderboard(myStage, score, false));
        });
        dataBox.getChildren().addAll(name, nameTextField, submit);
        Scene scene = new Scene(dataBox, 500, 475);
        enterData.setScene(scene);
        enterData.show();
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
