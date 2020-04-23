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
import ooga.data.LevelFileException;
import ooga.engine.dinosaur.DinoGameWorld;
import ooga.engine.flappy.FlappyGameWorld;
import ooga.engine.generic.GenericGameWorld;
import ooga.engine.jetpack.JetpackGameWorld;

import java.io.IOException;
import java.util.ResourceBundle;

import static ooga.engine.dinosaur.DinoGameWorld.DINO_IMAGE;
import static ooga.engine.flappy.FlappyGameWorld.BIRD_IMAGE;
import static ooga.engine.jetpack.JetpackGameWorld.BARRY_IMAGE;

public class ChangeScreen extends Screen {
    private ResourceBundle changeResources;
    public static final int SCREEN_WIDTH = 850;
    public static final int SCREEN_HEIGHT = 600;
    public static final Paint BACKGROUND = Color.AZURE;
    private Text title;
    private Stage myStage;
    private DinoGameWorld dinogame;
    private FlappyGameWorld flappygame;
    private JetpackGameWorld jetpackgame;
    private GenericGameWorld genericgame;

    public ChangeScreen(){

        myStage = new Stage();
        dinogame = new DinoGameWorld();
        flappygame = new FlappyGameWorld();
        jetpackgame = new JetpackGameWorld();
        genericgame = new GenericGameWorld();
        changeResources = ResourceBundle.getBundle("ooga.Screens.Properties.ChangeScreen");
        title = initTitle();
    }

    @Override
    public Scene createMainScreen(Stage currentstage) throws RuntimeException {
        myStage = currentstage;
        VBox changerlayout = new VBox();
        initLayout(changerlayout);
        Button dinosaur = new Button(changeResources.getString("DINO-MESSAGE"));
        dinosaur.setId("dino");
        Image dinoimage = new Image(getClass().getClassLoader().getResourceAsStream(DINO_IMAGE));
        dinosaur.setGraphic(createButtonImage(dinoimage));
        dinosaur.setOnAction(e -> {
            myStage.setScene(dinogame.setupScene(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND, currentstage, false));
            dinogame.setUpAnimation();
        });
        Button flappy = new Button(changeResources.getString("FLAPPY-MESSAGE"));
        flappy.setId("flappybird");
        Image flappyimage = new Image(getClass().getClassLoader().getResourceAsStream(BIRD_IMAGE));
        flappy.setGraphic(createButtonImage(flappyimage));
        flappy.setOnAction(e -> {
            myStage.setScene(flappygame.setupScene(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND, currentstage, false));
            flappygame.setUpAnimation();
        });
        Button jetpack = new Button(changeResources.getString("JET-MESSAGE"));
        jetpack.setId("jet");
        Image jetimage = new Image(getClass().getClassLoader().getResourceAsStream(BARRY_IMAGE));
        jetpack.setGraphic(createButtonImage(jetimage));
        jetpack.setOnAction(e -> {
            myStage.setScene(jetpackgame.setupScene(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND, currentstage, false));
            jetpackgame.setUpAnimation();
        });
        Button generic = new Button(changeResources.getString("GENERIC-MESSAGE"));
        generic.setId("generic");
        Image dinoimage2 = new Image(getClass().getClassLoader().getResourceAsStream(DINO_IMAGE));
        generic.setGraphic(createButtonImage(dinoimage2));
        generic.setOnAction(e -> {
            myStage.setScene(genericgame.setupScene(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND, currentstage, false));
            genericgame.setUpAnimation();
        });
        title.setText(changeResources.getString("CHOOSE-MESSAGE"));
        title.getStyleClass().add("titletxt");
        changerlayout.getChildren().addAll(title, dinosaur, flappy, jetpack, generic);
        Scene ChangeScreen = new Scene(changerlayout, SCREEN_WIDTH, SCREEN_HEIGHT);
        return ChangeScreen;
    }

    public ImageView createButtonImage(Image gameimage){
        return super.createButtonImage(gameimage);
    }

}
