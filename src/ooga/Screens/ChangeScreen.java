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
import ooga.engine.jetpack.JetpackGameWorld;

import java.io.IOException;
import java.util.ResourceBundle;

import static ooga.engine.dinosaur.DinoGameWorld.DINO_IMAGE;
import static ooga.engine.flappy.FlappyGameWorld.BIRD_IMAGE;
import static ooga.engine.jetpack.JetpackGameWorld.BARRY_IMAGE;

public class ChangeScreen extends Screen {
    //private static final String DINO_IMAGE = "dino_trexx.png";
    private ResourceBundle changeResources;
    public static final int SCREEN_WIDTH = 850;
    public static final int SCREEN_HEIGHT = 600;
    public static final Paint BACKGROUND = Color.AZURE;
    private static final String Dinofilepath = "data/CSV configurations/levelOne.csv";
    private static final String Flappyfilepath = "data/CSV configurations/levelOne.csv";
    private static final String Jetfilepath = "data/CSV configurations/Jetpack_Level.csv";
    private Text title;
    private Stage myStage;
    private DinoGameWorld dinogame;
    private final String screenCSS = "Styling/Screen.css";
    private FlappyGameWorld flappygame;
    private JetpackGameWorld jetpackgame;

    public ChangeScreen(){

        myStage = new Stage();
        dinogame = new DinoGameWorld();
        flappygame = new FlappyGameWorld();
        jetpackgame = new JetpackGameWorld();
        changeResources = ResourceBundle.getBundle("ooga.Screens.Properties.ChangeScreen");
        title = initTitle();
    }

    public Scene createChangeScreen(Stage currentstage){
        myStage = currentstage;
        VBox changerlayout = new VBox();
        initLayout(changerlayout);
        Button dinosaur = new Button(changeResources.getString("DINO-MESSAGE"));
        dinosaur.setId("dino");
        Image dinoimage = new Image(getClass().getClassLoader().getResourceAsStream(DINO_IMAGE));
        dinosaur.setGraphic(createButtonImage(dinoimage));
        dinosaur.setOnAction(e -> {
            try {
                myStage.setScene(dinogame.setupScene(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND, currentstage, false));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            dinogame.setUpAnimation();
        });
        Button flappy = new Button(changeResources.getString("FLAPPY-MESSAGE"));
        flappy.setId("flappybird");
        Image flappyimage = new Image(getClass().getClassLoader().getResourceAsStream(BIRD_IMAGE));
        flappy.setGraphic(createButtonImage(flappyimage));
        flappy.setOnAction(e -> {
            try {
                myStage.setScene(flappygame.setupScene(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND, currentstage, Flappyfilepath));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            flappygame.setUpAnimation();
        });
        Button jetpack = new Button(changeResources.getString("JET-MESSAGE"));
        jetpack.setId("jet");
        Image jetimage = new Image(getClass().getClassLoader().getResourceAsStream(BARRY_IMAGE));
        jetpack.setGraphic(createButtonImage(jetimage));
        jetpack.setOnAction(e -> {
            try {
                myStage.setScene(jetpackgame.setupScene(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND, currentstage, Jetfilepath));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            jetpackgame.setUpAnimation();
        });
        title.setText(changeResources.getString("CHOOSE-MESSAGE"));
        title.getStyleClass().add("titletxt");
        changerlayout.getChildren().addAll(title, dinosaur, flappy, jetpack);
        Scene ChangeScreen = new Scene(changerlayout, SCREEN_WIDTH, SCREEN_HEIGHT);
        return ChangeScreen;
    }

    public ImageView createButtonImage(Image gameimage){
        return super.createButtonImage(gameimage);
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

    public void initLayout(VBox layout) {
        super.initLayout(layout);
    }

    @Override
    public Text initTitle() {
        return super.initTitle();
    }
}
