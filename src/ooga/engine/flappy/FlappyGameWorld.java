package ooga.engine.flappy;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.Screens.BirdPlayerView;
import ooga.Screens.EndScreen;
import ooga.Screens.EnemyView;
import ooga.Screens.StartScreen;
import ooga.data.DinoGameConfiguration;
import ooga.data.FlappyGameConfiguration;
import ooga.engine.game.Enemy;
import ooga.engine.game.GameManager;
import ooga.engine.game.Powerup;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FlappyGameWorld {

    public static final double FLOOR_HEIGHT = 450;
    public static final int FRAMES_PER_SECOND = 30;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final String BIRD_IMAGE  = "flappy_yellowbird.png";
    public static final String BACKGROUND_IMAGE = "flappy_background.png";
    private static final String CSVfilepath = "data/CSV configurations/levelOne.csv";
    private static final int SCORE_X = 30;
    private static final int SCORE_Y = 30;
    private static final int SCORE_TEXT_SIZE = 30;
    private BirdPlayer myPlayer;
    private BirdPlayerView myPlayerView;
    private List<Enemy> enemies;
    private List<EnemyView> enemiesView;
    private List<Powerup> powerups;
    private Scene myScene;
    private Timeline myAnimation = new Timeline();
    private GameManager gameManager;
    private FlappyGameConfiguration gameConfig;
    private Text myScoreText = new Text();
    private EndScreen endScreen;
    private Stage myStage;

    // Create the game's "scene": what shapes will be in the game and their starting properties
    public Scene setupScene(int width, int height, Paint background, Stage currentstage) throws IOException {
        endScreen = new EndScreen("Flappy");
        myStage = currentstage;
        ImageView imageView = getImageView();
        Group root = new Group(imageView);
        gameConfig = new FlappyGameConfiguration(Paths.get(CSVfilepath));
        addBird(root);
        addEnemies(root);
        addPowerups(root);
        gameManager = new FlappyGameManager(myPlayer, enemies, powerups);
        myScoreText = new Text(SCORE_X, SCORE_Y, "" + gameManager.getScore());
        myScoreText.setFont(new Font(SCORE_TEXT_SIZE));
        root.getChildren().add(myScoreText);
        return getScene(width, height, background, root);
    }

    private Scene getScene(int width, int height, Paint background, Group root) {
        myScene = new Scene(root, width, height, background);
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return myScene;
    }

    private void addEnemies(Group root) throws IOException {
        enemies = new ArrayList<>(gameConfig.getEnemies());
        enemiesView = new ArrayList<>();
        for (Enemy pipe : enemies){
            EnemyView tempPipeView = new EnemyView(new Image(pipe.getImage()), pipe.getXPos(), pipe.getYPos());
            tempPipeView.setWidthAndHeight(100, 550);
            tempPipeView.setProperties(pipe);
            enemiesView.add(tempPipeView);
            root.getChildren().add(tempPipeView.getEnemyImage());
        }
    }

    private void addPowerups(Group root) throws IOException {
        powerups = new ArrayList<>(gameConfig.getPowerups());
        root.getChildren().addAll(powerups);
    }

    private void addBird(Group root) {
        Image birdImage = new Image(this.getClass().getClassLoader().getResourceAsStream(BIRD_IMAGE));
        myPlayer = new BirdPlayer(100, 250);
        myPlayerView = new BirdPlayerView(birdImage, 100, FLOOR_HEIGHT);
        myPlayerView.setProperties(myPlayer);
        root.getChildren().add(myPlayerView.getPlayerImage());
    }

    private ImageView getImageView() {
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(BACKGROUND_IMAGE));
        ImageView imageView = new ImageView(image);
        imageView.setY(0);
        imageView.setFitHeight(StartScreen.SCREEN_HEIGHT);
        imageView.setFitWidth(StartScreen.SCREEN_WIDTH);
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

        //move the powerups
        for(Powerup pu: powerups) {
            pu.move();
        }

        //increment score
        gameManager.tick();

        // collisions
        gameManager.handleCollisions();
        gameManager.handlePowerups();

        //update score
        myScoreText.setText(""+gameManager.getScore());

        if(gameManager.isGameOver()) {
            myAnimation.stop();
//            myStage.setScene(endScreen.createEndScreen(myStage, gameManager.getScore()));
        }
    }

    private void handleKeyInput (KeyCode code) {
        if(code == KeyCode.SPACE) {
            myPlayer.resetJumpStrength();
            myPlayer.jump();
        }
    }
}
