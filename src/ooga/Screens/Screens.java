package ooga.Screens;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.engine.dinosaur.DinoGameWorld;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ResourceBundle;

public class Screens {

    private DinoGameWorld dinogame;
    private Text title;
    private Button quit;
    private ResourceBundle startResources;
    private ResourceBundle endResources;
    private ResourceBundle changeResources;
    public static final int SCREEN_WIDTH = 850;
    public static final int SCREEN_HEIGHT = 600;
    public static final Paint BACKGROUND = Color.AZURE;

    public Screens(){
        dinogame = new DinoGameWorld();
        startResources = ResourceBundle.getBundle("ooga.Screens.Properties.StartScreen");
        endResources = ResourceBundle.getBundle("ooga.Screens.Properties.EndScreen");
        changeResources = ResourceBundle.getBundle("ooga.Screens.Properties.ChangeScreen");
        title = new Text();
        title.setId("Title");
        quit = new Button(startResources.getString("QUIT-MESSAGE"));
        quit.setId("quitbutton");
        quit.setOnAction(e -> {
            Platform.exit();
        });
    }

    private void initLayout(VBox layout){
        layout.setPadding(new Insets(10, 50, 50, 50));
        layout.setSpacing(25);
        layout.setAlignment(Pos.CENTER);
    }

    public Scene createStartScreen(Stage currentStage){
        VBox startlayout = new VBox();
        initLayout(startlayout);
        Button start = new Button(startResources.getString("START-MESSAGE"));
        start.setId("startbutton");
        start.setOnAction(e -> {
            currentStage.setScene(createChangeScreen(currentStage));
        });
        Button end = new Button(startResources.getString("END-MESSAGE"));
        end.setId("endbutton");
        end.setOnAction(e -> {
            currentStage.setScene(createEndScreen(currentStage));
        });
        title.setText("Welcome to TEXAS");
        startlayout.getChildren().addAll(title, start,quit, end);
        Scene StartScreen = new Scene(startlayout, SCREEN_WIDTH, SCREEN_HEIGHT);
        return StartScreen;
    }

    public Scene createChangeScreen(Stage currenstage){
        VBox changerlayout = new VBox();
        initLayout(changerlayout);
        Button dinosaur = new Button(changeResources.getString("DINO-MESSAGE"));
        dinosaur.setId("dino");
        dinosaur.setOnAction(e -> {
            try {
                currenstage.setScene(dinogame.setupScene(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            dinogame.setUpAnimation();
        });
        Button flappy = new Button(changeResources.getString("FLAPPY-MESSAGE"));
        flappy.setId("flappybird");
        flappy.setOnAction(e -> {
            currenstage.setScene(placeholderScene());
        });
        Button jetpack = new Button(changeResources.getString("JET-MESSAGE"));
        jetpack.setId("jet");
        jetpack.setOnAction(e -> {
            currenstage.setScene(placeholderScene());
        });
        title.setText(changeResources.getString("CHOOSE-MESSAGE"));
        changerlayout.getChildren().addAll(title, dinosaur, flappy, jetpack);
        Scene ChangeScreen = new Scene(changerlayout, SCREEN_WIDTH, SCREEN_HEIGHT);
        return ChangeScreen;
    }

    public Scene createEndScreen(Stage currentStage){
        VBox endlayout = new VBox();
        initLayout(endlayout);
        Button playagain = new Button(endResources.getString("AGAIN-MESSAGE"));
        playagain.setId("again");
        playagain.setOnAction(e -> {
            currentStage.setScene(createChangeScreen(currentStage));
        });
        title.setText(endResources.getString("GAME-OVER"));
        endlayout.getChildren().addAll(title, playagain, quit);
        Scene EndScreen = new Scene(endlayout, SCREEN_WIDTH, SCREEN_HEIGHT);
        return EndScreen;
    }

    public Scene placeholderScene(){
        VBox placeholder = new VBox();
        initLayout(placeholder);
        Text holdertext = new Text();
        holdertext.setText("This is a placeholder for the game screens");
        placeholder.getChildren().add(holdertext);
        Scene PlaceHolder = new Scene(placeholder, SCREEN_WIDTH, SCREEN_HEIGHT);
        return PlaceHolder;
    }

}
