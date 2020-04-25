package ooga.screens;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.engine.dinosaur.DinoGameWorld;
import ooga.engine.flappy.FlappyGameWorld;
import ooga.engine.generic.GenericGameWorld;
import ooga.engine.jetpack.JetpackGameWorld;

import java.util.ResourceBundle;

import static ooga.engine.dinosaur.DinoGameWorld.DINO_IMAGE;
import static ooga.engine.flappy.FlappyGameWorld.BIRD_IMAGE;
import static ooga.engine.jetpack.JetpackGameWorld.BARRY_IMAGE;

/**
 * This class is used to display the scene where the user chooses which game to play, as well as all of the buttons and text that goes with the scene. This class depends on the different game worlds that are used to implement the different games.
 */
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
        genericgame = new GenericGameWorld("ooga.engine.generic.DINO_GameRules");
        changeResources = ResourceBundle.getBundle("ooga.Screens.Properties.ChangeScreen");
        title = initTitle();
    }

    /**
     * Creates the scene with which the user chooses which game to play.
     * @param currentstage
     * @return
     * @throws RuntimeException
     */
    @Override
    public Scene createMainScreen(Stage currentstage) throws RuntimeException {
        myStage = currentstage;
        VBox changerlayout = new VBox();
        initLayout(changerlayout);
        Image dinoimage = new Image(getClass().getClassLoader().getResourceAsStream(DINO_IMAGE));
        Button dinosaur = createButtonWithImage(changeResources.getString("DINO-MESSAGE"), "dino", dinoimage);
        dinosaur.setOnAction(e -> {
            myStage.setScene(dinogame.setupScene(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND, currentstage, false));
            dinogame.setUpAnimation();
        });
        Image flappyimage = new Image(getClass().getClassLoader().getResourceAsStream(BIRD_IMAGE));
        Button flappy = createButtonWithImage(changeResources.getString("FLAPPY-MESSAGE"), "flappybird", flappyimage);
        flappy.setOnAction(e -> {
            myStage.setScene(flappygame.setupScene(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND, currentstage, false));
            flappygame.setUpAnimation();
        });
        Image jetimage = new Image(getClass().getClassLoader().getResourceAsStream(BARRY_IMAGE));
        Button jetpack = createButtonWithImage(changeResources.getString("JET-MESSAGE"), "jet", jetimage);
        jetpack.setOnAction(e -> {
            myStage.setScene(jetpackgame.setupScene(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND, currentstage, false));
            jetpackgame.setUpAnimation();
        });
        Button generic = createButton(changeResources.getString("GENERIC-MESSAGE"), "generic");
        generic.setGraphic(makeStar());
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

    private Polygon makeStar(){
        Double[] points = {205.0/3,150.0/3, 217.0/3,186.0/3, 259.0/3,186.0/3,
                223.0/3,204.0/3, 233.0/3,246.0/3, 205.0/3,222.0/3, 177.0/3,246.0/3, 187.0/3,204.0/3,
                151.0/3,186.0/3, 193.0/3,186.0/3};
        Polygon star = new Polygon();
        star.getPoints().addAll(points);
        star.setStroke(Color.BLACK);
        LinearGradient gradient =  new LinearGradient(0f, 1f, 1f, 0f, true, CycleMethod.NO_CYCLE,
                new Stop[]{
                new Stop(0, Color.RED),
                new Stop(0.4, Color.YELLOW),
                new Stop(0.5, Color.CHARTREUSE),
                new Stop(0.7, Color.DEEPSKYBLUE),
                new Stop(1, Color.DARKORCHID),});
        //star.getTransforms().add(new Scale(0.5, 0.5));
        star.setFill(gradient);
        return star;
    }

}
