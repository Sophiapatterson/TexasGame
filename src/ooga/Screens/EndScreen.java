package ooga.Screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
    public static final int POPUP_WIDTH = 500;
    public static final int POPUP_HEIGHT = 475;
    public static final Color SCREEN_COLOR = Color.GOLD;
    private ResourceBundle endResources;
    private ResourceBundle creditsResources;
    private boolean allowSubmissions;
    private Stage myStage;
    private int score;
    private String version;
    private final String screenCSS = "Styling/Screen.css";
    private HighScores highscores;

    public EndScreen(String version){
        this.version = version;
        allowSubmissions = true;
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

    public Scene createEndScreen(Stage currentstage, int gamescore){
        score = gamescore;
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
        Button scores = new Button(endResources.getString("LEADERBOARD-MESSAGE"));
        scores.setId("scores");
        scores.setOnAction( e -> {
            myStage.setScene(createLeaderboard(myStage));
        });
        title.setText(endResources.getString("GAME-OVER"));
        title.getStyleClass().add("titletxt");
        layout.getChildren().addAll(title, playagain, credits, scores, quit);
        Scene EndScreen = new Scene(layout, SCREEN_WIDTH, SCREEN_HEIGHT);
//        if (Screen.isDarkMode){
//            this.setDarkModeTrue();
//        }
        return EndScreen;
    }

    public Scene createLeaderboard(Stage currentstage) {
        myStage = currentstage;
        VBox layout = new VBox();
        initLayout(layout);
        title.setText(endResources.getString("LEADERBOARD-MESSAGE"));
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
        Button newScore = new Button(endResources.getString("ADD-SCORE"));
        newScore.setId("newscore");
        newScore.setOnAction( e -> {
            submitScore();
        });
        if(allowSubmissions){
            layout.getChildren().add(newScore);
        }
        layout.getChildren().add(makeBackButton());
        Scene Leaderboard = new Scene(layout, SCREEN_WIDTH, SCREEN_HEIGHT);
        return Leaderboard;
    }

    private Button makeBackButton() {
        Button back = new Button(endResources.getString("BACK-BUTTON"));
        back.setId("back");
        back.setOnAction( e -> {
            myStage.setScene(createEndScreen(myStage, score));
        });
        return back;
    }

    private void submitScore() {
        Stage enterData = new Stage();
        VBox dataBox = new VBox();
        initLayout(dataBox);
        enterData.setTitle(endResources.getString("NAME-PROMPT"));
        Label name = new Label(endResources.getString("NAME-PROMPT"));
        name.getStyleClass().add("medtxt");
        TextField nameTextField = new TextField();
        Button submit = new Button(endResources.getString("SUBMIT-SCORE"));
        submit.setId("submit");
        submit.setOnAction( f -> {
            highscores.addScore(new Score(nameTextField.getText(), score));
            enterData.close();
            allowSubmissions = false;
            myStage.setScene(createLeaderboard(myStage));
        });
        dataBox.getChildren().addAll(name, nameTextField, submit);
        Scene scene = new Scene(dataBox, POPUP_WIDTH, POPUP_HEIGHT);
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
        Button back = makeBackButton();
        layout.getChildren().addAll(title, producers, luke, jeff, sophia, justin, back);
        Scene Credits = new Scene(layout, SCREEN_WIDTH, SCREEN_HEIGHT);
        return Credits;
    }

    public Text createCreditText(String message){
        Text returnedtext = new Text();
        returnedtext.setText(creditsResources.getString(message));
        returnedtext.getStyleClass().add("smalltxt");
        return returnedtext;
    }

    public void initLayout(VBox layout) {
        super.initLayout(layout);
    }
}
