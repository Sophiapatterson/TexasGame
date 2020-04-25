package ooga.screens;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.data.HighScores;
import ooga.data.Score;
import ooga.engine.generic.GenericGameWorld;

import java.util.ResourceBundle;

/**
 * This class is used to display the scene after a user loses the game. It's used for the end of every game, but the scores used are different. It depends on the classes that have to do with the scores and the leaderboard, as well as the other screens that it redirects the user to.
 */
public class EndScreen extends Screen {
    private StartScreen startscreen;
    private ChangeScreen changescreen;
    private GenericGameWorld creditsgame;
    private Text title;
    private Button quit;
    public static final int POPUP_WIDTH = 500;
    public static final int POPUP_HEIGHT = 475;
    private ResourceBundle endResources;
    private ResourceBundle creditsResources;
    private boolean allowSubmissions;
    private Stage myStage;
    private int score;
    private String version;
    private HighScores highscores;

    public EndScreen(String version){
        this.version = version;
        allowSubmissions = true;
        myStage = new Stage();
        creditsgame = new GenericGameWorld("ooga.engine.generic.CREDITS_GameRules");
        endResources = ResourceBundle.getBundle("ooga.Screens.Properties.EndScreen");
        creditsResources = ResourceBundle.getBundle("ooga.Screens.Properties.Credits");
        startscreen = new StartScreen();
        changescreen = new ChangeScreen();
        title = initTitle();
        quit = startscreen.quitButton();
    }

    /**
     * Creates the end screen, which shows buttons to redirect the user to the game chooser, the credits minigame, or the leaderboard.
     * @param currentstage
     * @param gamescore
     * @return
     */
    public Scene createEndScreen(Stage currentstage, int gamescore){
        score = gamescore;
        highscores = new HighScores(version);
        myStage = currentstage;
        VBox layout = new VBox();
        initLayout(layout);
        Button playagain = createButton(endResources.getString("AGAIN-MESSAGE"), "again");
        playagain.setOnAction(e -> {
            myStage.setScene(changescreen.createMainScreen(myStage));
        });
        Button credits = createButton(endResources.getString("CREDITS-MESSAGE"), "credits");
        credits.setOnAction( e -> {
            //myStage.setScene(createCredits(myStage));
            myStage.setScene(creditsgame.setupScene(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND, currentstage, false));
            creditsgame.setUpAnimation();
        });
        Button scores = createButton(endResources.getString("LEADERBOARD-MESSAGE"), "scores");
        scores.setOnAction( e -> {
            myStage.setScene(createLeaderboard(myStage));
        });
        title.setText(endResources.getString("GAME-OVER"));
        title.getStyleClass().add("titletxt");
        layout.getChildren().addAll(title, playagain, credits, scores, quit);
        Scene EndScreen = new Scene(layout, SCREEN_WIDTH, SCREEN_HEIGHT);
        return EndScreen;
    }

    /**
     * Creates the leaderboard that is shown when the scores are displayed.
     * @param currentstage
     * @return
     */
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
        Button newScore = createButton(endResources.getString("ADD-SCORE"), "newscore");
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
        Button submit = createButton(endResources.getString("SUBMIT-SCORE"), "submit");
        submit.setOnAction( f -> {
            highscores.addScore(new Score(nameTextField.getText(), score));
            highscores.saveHighScores();
            enterData.close();
            allowSubmissions = false;
            myStage.setScene(createLeaderboard(myStage));
        });
        dataBox.getChildren().addAll(name, nameTextField, submit);
        Scene scene = new Scene(dataBox, POPUP_WIDTH, POPUP_HEIGHT);
        enterData.setScene(scene);
        enterData.show();
    }

    /**
     * Creates the scene with the credits.
     * @param currentstage
     * @return
     */
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
        Text luke = createCreditText("LUKE-MESSAGE", "luke");
        Text jeff = createCreditText("JEFF-MESSAGE", "jeff");
        Text sophia = createCreditText("SOPHIA-MESSAGE", "sophia");
        Text justin = createCreditText("JUSTIN-MESSAGE", "justin");
        Button back = makeBackButton();
        layout.getChildren().addAll(title, producers, luke, jeff, sophia, justin, back);
        Scene Credits = new Scene(layout, SCREEN_WIDTH, SCREEN_HEIGHT);
        return Credits;
    }


    private Text createCreditText(String message, String id){
        Text returnedtext = new Text();
        returnedtext.setText(creditsResources.getString(message));
        returnedtext.setId(id);
        returnedtext.getStyleClass().add("smalltxt");
        return returnedtext;
    }

}
