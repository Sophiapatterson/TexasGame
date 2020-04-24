package ooga.Screens;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.engine.dinosaur.DinoGameWorld;
import ooga.engine.flappy.FlappyGameWorld;
import ooga.engine.jetpack.JetpackGameWorld;

import java.util.ResourceBundle;

import static ooga.engine.flappy.FlappyGameWorld.BIRD_IMAGE;
import static ooga.engine.dinosaur.DinoGameWorld.DINO_IMAGE;
import static ooga.engine.jetpack.JetpackGameWorld.BARRY_IMAGE;

public class TutorialScreen extends Screen {

    private Stage myStage;
    private Text title;
    private DinoGameWorld dinogame;
    private ResourceBundle tutorialResources;
    private FlappyGameWorld flappygame;
    private JetpackGameWorld jetpackgame;
    private ChangeScreen changescreen;

    public TutorialScreen(){
        myStage = new Stage();
        title = initTitle();
        dinogame = new DinoGameWorld();
        flappygame = new FlappyGameWorld();
        jetpackgame = new JetpackGameWorld();
        changescreen = new ChangeScreen();
        tutorialResources = ResourceBundle.getBundle("ooga.Screens.Properties.TutorialScreen");
    }

    @Override
    public Scene createMainScreen(Stage currentstage){
        myStage = currentstage;
        VBox tutoriallayout = new VBox();
        initLayout(tutoriallayout);
        Button dinosaur = new Button(tutorialResources.getString("DINO-TUTORIAL"));
        dinosaur.setId("dino");
        Image dinoimage = new Image(getClass().getClassLoader().getResourceAsStream(DINO_IMAGE));
        dinosaur.setGraphic(createButtonImage(dinoimage));
        dinosaur.setOnAction(e -> {
            myStage.setScene(dinogame.setupScene(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND, currentstage, true));
            dinogame.setUpAnimation();
        });
        Button flappy = new Button(tutorialResources.getString("FLAPPY-TUTORIAL"));
        flappy.setId("flappybird");
        Image flappyimage = new Image(getClass().getClassLoader().getResourceAsStream(BIRD_IMAGE));
        flappy.setGraphic(createButtonImage(flappyimage));
        flappy.setOnAction(e -> {
            myStage.setScene(flappygame.setupScene(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND, currentstage, true));
            flappygame.setUpAnimation();
        });
        Button jetpack = new Button(tutorialResources.getString("JET-TUTORIAL"));
        jetpack.setId("jet");
        Image jetimage = new Image(getClass().getClassLoader().getResourceAsStream(BARRY_IMAGE));
        jetpack.setGraphic(createButtonImage(jetimage));
        jetpack.setOnAction(e -> {
            myStage.setScene(jetpackgame.setupScene(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND, currentstage, true));
            jetpackgame.setUpAnimation();
        });
        title.setText(tutorialResources.getString("TUTORIAL-CHOOSER"));
        title.getStyleClass().add("titletxt");
        tutoriallayout.getChildren().addAll(title, dinosaur, flappy, jetpack);
        Scene TutorialScreen = new Scene(tutoriallayout, SCREEN_WIDTH, SCREEN_HEIGHT);
        return TutorialScreen;
    }

    public Scene TutorialorGameChooser(Stage currentstage){
        myStage = currentstage;
        VBox tutoriallayout = new VBox();
        initLayout(tutoriallayout);
        Button tutorial = new Button(tutorialResources.getString("GO-TUTORIAL"));
        tutorial.setId("tutorial");
        tutorial.setOnAction(e -> {
            myStage.setScene(createMainScreen(myStage));
        });
        Button gamechooser = new Button(tutorialResources.getString("GO-GAMECHOOSER"));
        gamechooser.setId("gamechooser");
        gamechooser.setOnAction(e -> {
            myStage.setScene(changescreen.createMainScreen(myStage));
        });
        title.setText(tutorialResources.getString("READY-ORNOT"));
        title.getStyleClass().add("titletxt");
        tutoriallayout.getChildren().addAll(title, tutorial, gamechooser);
        Scene TutorialGame = new Scene(tutoriallayout, SCREEN_WIDTH, SCREEN_HEIGHT);
        return TutorialGame;
    }
}
