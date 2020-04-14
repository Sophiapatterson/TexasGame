package ooga.Screens;

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
import ooga.engine.flappy.FlappyGameWorld;

import java.io.IOException;
import java.util.ResourceBundle;

public class ChangeScreen extends Screen {
    private ResourceBundle changeResources;
    public static final int SCREEN_WIDTH = 850;
    public static final int SCREEN_HEIGHT = 600;
    public static final Paint BACKGROUND = Color.AZURE;
    private Text title;
    private Stage myStage;
    private DinoGameWorld dinogame;
    private FlappyGameWorld flappygame;

    public ChangeScreen(){
        myStage = new Stage();
        dinogame = new DinoGameWorld();
        flappygame = new FlappyGameWorld();
        changeResources = ResourceBundle.getBundle("ooga.Screens.Properties.ChangeScreen");
        title = new Text();
        title.setId("Title");
    }

    public Scene createChangeScreen(Stage currentstage){
        myStage = currentstage;
        VBox changerlayout = new VBox();
        initLayout(changerlayout);
        Button dinosaur = new Button(changeResources.getString("DINO-MESSAGE"));
        dinosaur.setId("dino");
        dinosaur.setOnAction(e -> {
            try {
                myStage.setScene(dinogame.setupScene(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND, currentstage));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            dinogame.setUpAnimation();
        });
        Button flappy = new Button(changeResources.getString("FLAPPY-MESSAGE"));
        flappy.setId("flappybird");
        flappy.setOnAction(e -> {
            try {
                myStage.setScene(flappygame.setupScene(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND, currentstage));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            dinogame.setUpAnimation();
        });
        Button jetpack = new Button(changeResources.getString("JET-MESSAGE"));
        jetpack.setId("jet");
        jetpack.setOnAction(e -> {
            myStage.setScene(placeholderScene());
        });
        title.setText(changeResources.getString("CHOOSE-MESSAGE"));
        changerlayout.getChildren().addAll(title, dinosaur, flappy, jetpack);
        Scene ChangeScreen = new Scene(changerlayout, SCREEN_WIDTH, SCREEN_HEIGHT);
        return ChangeScreen;
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
    @Override
    public void initLayout(VBox layout) {
        layout.setPadding(new Insets(10, 50, 50, 50));
        layout.setSpacing(25);
        layout.setAlignment(Pos.CENTER);
    }
}
