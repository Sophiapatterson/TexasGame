package ooga.engine.dinosaur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.engine.game.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Basic game world tailored for dinosaur game at the moment for testing. Need to figure out a way to move this game
 * into a larger game -- possibly find a way to make this game a small screen to import into our final game?
 */
public class DinoGameWorld extends Application {

    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 400;
    public static final double FLOOR_HEIGHT = 275;
    public static final Paint BACKGROUND = Color.AZURE;
    public static final int FRAMES_PER_SECOND = 30;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final String DINO_IMAGE  = "dino_trexx.png";
    public static final String HORIZON_IMAGE = "dino_horizon.png";
    private DinoPlayer myPlayer;
    private List<Enemy> enemies;
    private Scene myScene;
    private Timeline myAnimation = new Timeline();
    private GameManager gameManager;

    @Override
    public void start(Stage stage) throws Exception {
        myScene = setupScene(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND);
        stage.setScene(myScene);
        stage.setTitle("Dinosaur Game");
        stage.show();
        KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.play();
    }

    // Create the game's "scene": what shapes will be in the game and their starting properties
    public Scene setupScene(int width, int height, Paint background) throws FileNotFoundException{
        ImageView imageView = getImageView();
        Group root = new Group(imageView);
        addDino(root);
        addEnemies(root);
        gameManager = new DinoGameManager(myPlayer, enemies);
        return getScene(width, height, background, root);
    }

    private Scene getScene(int width, int height, Paint background, Group root) {
        myScene = new Scene(root, width, height, background);
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return myScene;
    }

    private void addEnemies(Group root) {
        enemies = new ArrayList<>();
        enemies.add(new Cactus());
        root.getChildren().addAll(enemies);
    }

    private void addDino(Group root) {
        Image dinoImage = new Image(this.getClass().getClassLoader().getResourceAsStream(DINO_IMAGE));
        myPlayer = new DinoPlayer(dinoImage);
        root.getChildren().add(myPlayer.getPlayerImage());
    }

    private ImageView getImageView() {
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(HORIZON_IMAGE));
        ImageView imageView = new ImageView(image);
        imageView.setY(320);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    public void setUpAnimation(){
        KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.play();
    }
    // Change properties of shapes to animate them
    public void step (double elapsedTime) {
        gameManager.handleJump(FLOOR_HEIGHT);

        // move the enemies
        for(Enemy enemy: enemies) {
            enemy.move();
        }
        // collisions
        gameManager.handleCollisions();

        if(gameManager.isGameOver()) {
            System.out.println("GAME OVER");

        }
    }

    private void handleKeyInput (KeyCode code) {
        if(code == KeyCode.SPACE) {
            myPlayer.jump();
        }
    }

    /**
     * We don't want to be launching in the game world later on. This is just to get things working for now.
     * @param args
     */
    public static void main (String[] args) {
        launch(args);
    }
}
