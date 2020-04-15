package ooga.engine.jetpack;

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
import ooga.Screens.DinoPlayerView;
import ooga.Screens.EndScreen;
import ooga.Screens.EnemyView;
import ooga.data.DinoGameConfiguration;
import ooga.engine.dinosaur.DinoGameManager;
import ooga.engine.dinosaur.DinoPlayer;
import ooga.engine.game.Enemy;
import ooga.engine.game.GameManager;
import ooga.engine.game.Powerup;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JetpackGameWorld {

    public static final double FLOOR_HEIGHT = 275;
    public static final int FRAMES_PER_SECOND = 30;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    //placeholder for images
    private static final int SCORE_X = 30;
    private static final int SCORE_Y = 30;
    private static final int SCORE_TEXT_SIZE = 30;
    private JetpackPlayer myPlayer;
    //placeholder for jetpackplayerview
    private List<Enemy> enemies;
    private List<EnemyView> enemiesView;
    private List<Powerup> powerups;
    private Timeline myAnimation = new Timeline();
    private GameManager gameManager;
    private Text myScoreText = new Text();
    private EndScreen endScreen;
    private Stage myStage;
    private Scene myScene;

    public Scene setupScene(int width, int height, Paint background, Stage currentstage) throws IOException {
        endScreen = new EndScreen();
        myStage = currentstage;
        ImageView imageView = getImageView();
        Group root = new Group(imageView);
        //gameConfig = new DinoGameConfiguration(Paths.get(CSVfilepath)); add gameconfig for jetpack here
        addDino(root);
        addEnemies(root);
        addPowerups(root);
        gameManager = new DinoGameManager(myPlayer, enemies, powerups);
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
        //enemies = new ArrayList<>(gameConfig.getEnemies()); use jetpack gameconfig once implemented
        enemiesView = new ArrayList<>();
        //Add for loop for the enemies once images are added
        /**for (Enemy cactus : enemies){
            EnemyView tempCacView = new EnemyView(new Image(SMALLCACTUS_IMAGE), cactus.getXPos(), FLOOR_HEIGHT);
            tempCacView.setProperties(cactus);
            enemiesView.add(tempCacView);
            root.getChildren().add(tempCacView.getEnemyImage());
        }*/
    }

    private void addPowerups(Group root) throws IOException {
        //powerups = new ArrayList<>(gameConfig.getPowerups()); use jetpack gameconfig once implemented
        root.getChildren().addAll(powerups);
    }

    private void addDino(Group root) {
        //Image dinoImage = new Image(this.getClass().getClassLoader().getResourceAsStream(DINO_IMAGE)); Use images once they're added
        //myPlayer = new DinoPlayer(100, FLOOR_HEIGHT); use jetpack player once implemented
        //myPlayerView = new DinoPlayerView(dinoImage, 100, FLOOR_HEIGHT); use jetpack player view once implemented
        //myPlayerView.setProperties(myPlayer); use jetpack player view once implemented
        //root.getChildren().add(myPlayerView.getPlayerImage()); use jetpack player view once implemented
    }

    private ImageView getImageView() {
        //Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(HORIZON_IMAGE)); use background image once added
        //ImageView imageView = new ImageView(image);
        //imageView.setY(320);
        //imageView.setPreserveRatio(true);
        //return imageView;
        return null; //remove once method is complete
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
            myStage.setScene(endScreen.createEndScreen(myStage, gameManager.getScore(), "JETPACK"));
            endScreen.checkandSetDarkMode(myStage);
        }
    }

    private void handleKeyInput (KeyCode code) {
        if(code == KeyCode.SPACE) {
            myPlayer.jump();
        }
    }
}
