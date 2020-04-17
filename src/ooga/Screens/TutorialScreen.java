package ooga.Screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.engine.dinosaur.DinoGameWorld;
import ooga.engine.flappy.FlappyGameWorld;

import java.io.IOException;

import static ooga.engine.flappy.FlappyGameWorld.BIRD_IMAGE;
import static ooga.engine.dinosaur.DinoGameWorld.DINO_IMAGE;
import static ooga.engine.jetpack.JetpackGameWorld.BARRY_IMAGE;

public class TutorialScreen extends Screen {

    public static final int SCREEN_WIDTH = 850;
    public static final int SCREEN_HEIGHT = 600;
    public static final Paint BACKGROUND = Color.AZURE;
    private Stage myStage;
    private Text title;
    private DinoGameWorld dinogame;
    private FlappyGameWorld flappygame;
    private ChangeScreen changescreen;
    private final String screenCSS = "Styling/Screen.css";
    public static final String TutorialCSV = "data/CSV configurations/dinoTutorial.csv";

    public TutorialScreen(){
        myStage = new Stage();
        title = initTitle();
        dinogame = new DinoGameWorld();
        flappygame = new FlappyGameWorld();
        changescreen = new ChangeScreen();
    }

    public Scene createTutorial(Stage currentstage){
        myStage = currentstage;
        VBox tutoriallayout = new VBox();
        initLayout(tutoriallayout);
        Button dinosaur = new Button("Dinosaur Tutorial");
        dinosaur.setId("dino");
        Image dinoimage = new Image(getClass().getClassLoader().getResourceAsStream(DINO_IMAGE));
        dinosaur.setGraphic(createButtonImage(dinoimage));
        dinosaur.setOnAction(e -> {
            try {
                myStage.setScene(dinogame.setupScene(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND, currentstage, true));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            dinogame.setUpAnimation();
        });
        Button flappy = new Button("Flappy Tutorial");
        flappy.setId("flappybird");
        Image flappyimage = new Image(getClass().getClassLoader().getResourceAsStream(BIRD_IMAGE));
        flappy.setGraphic(createButtonImage(flappyimage));
        flappy.setOnAction(e -> {
            try {
                myStage.setScene(flappygame.setupScene(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND, currentstage, true));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            flappygame.setUpAnimation();
        });
        Button jetpack = new Button("Jetpack Tutorial");
        jetpack.setId("jet");
        Image jetimage = new Image(getClass().getClassLoader().getResourceAsStream(BARRY_IMAGE));
        jetpack.setGraphic(createButtonImage(jetimage));
        jetpack.setOnAction(e -> {
            myStage.setScene(placeholderScene());
        });
        title.setText("Tutorial Chooser");
        title.getStyleClass().add("titletxt");
        tutoriallayout.getChildren().addAll(title, dinosaur, flappy, jetpack);
        Scene TutorialScreen = new Scene(tutoriallayout, SCREEN_WIDTH, SCREEN_HEIGHT);
        return TutorialScreen;
    }

    public Scene TutorialorGameChooser(Stage currentstage){
        myStage = currentstage;
        VBox tutoriallayout = new VBox();
        initLayout(tutoriallayout);
        Button tutorial = new Button("Go back to tutorials");
        tutorial.setId("tutorial");
        tutorial.setOnAction(e -> {
            myStage.setScene(createTutorial(myStage));
        });
        Button gamechooser = new Button("Go to game chooser");
        gamechooser.setId("gamechooser");
        gamechooser.setOnAction(e -> {
            myStage.setScene(changescreen.createChangeScreen(myStage));
        });
        title.setText("Are you ready to play, or do you need another tutorial?");
        title.getStyleClass().add("titletxt");
        tutoriallayout.getChildren().addAll(title, tutorial, gamechooser);
        Scene TutorialGame = new Scene(tutoriallayout, SCREEN_WIDTH, SCREEN_HEIGHT);
        return TutorialGame;
    }

    public void initLayout(VBox layout) {
        super.initLayout(layout);
    }

    @Override
    public Text initTitle() {
        return super.initTitle();
    }

    @Override
    public ImageView createButtonImage(Image gameimage) {
        return super.createButtonImage(gameimage);
    }

    //Delete once we have the tutorials
    public Scene placeholderScene(){
        VBox placeholder = new VBox();
        initLayout(placeholder);
        Text holdertext = new Text();
        holdertext.setText("This is a placeholder for the tutorial screens");
        placeholder.getChildren().add(holdertext);
        Scene PlaceHolder = new Scene(placeholder, SCREEN_WIDTH, SCREEN_HEIGHT);
        return PlaceHolder;
    }
}
