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
import ooga.Screens.JetpackPlayerView;
import ooga.Screens.EndScreen;
import ooga.Screens.EnemyView;
import ooga.Screens.StartScreen;
import ooga.data.config.GameConfiguration;
import ooga.data.config.JetpackGameConfiguration;
import ooga.engine.game.Enemy;
import ooga.engine.game.GameManager;
import ooga.engine.game.Powerup;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JetpackGameWorld {

    public static final double FLOOR_HEIGHT =400;
    public static final int FRAMES_PER_SECOND = 30;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final String COIN_IMAGE = "Sprites/jetpack_coin.png";
    public static final String BACKGROUND_IMAGE = "Sprites/jetpack_background.png";
    public static final String FLAPPY_BG_IMAGE = "Sprites/flappy_background.png";
    public static final String AIRBORNE_BARRY_IMAGE = "Sprites/jetpack_airbornBarry.png";
    public static final String LASER_IMAGE = "Sprites/jetpack_laser.png";
    public static final String MISSILE_IMAGE = "Sprites/jetpack_missile.png";
    public static final String ZAPPER_IMAGE = "Sprites/jetpack_zapper.png";
    public static final int IMAGE_HEIGHT = 695;
    public static final String BARRY_IMAGE  = "Sprites/jetpack_normalBarry.png";
    private static final String CSVfilepath = "data/CSV configurations/Jetpack_Level.csv";
    private static final int SCORE_X = 30;
    private static final int SCORE_Y = 30;
    private static final int SCORE_TEXT_SIZE = 30;
    private JetpackPlayer myPlayer;
    private List<Enemy> enemies;
    private List<EnemyView> enemiesView;
    private List<Powerup> powerups;
    private Timeline myAnimation = new Timeline();
    private GameManager gameManager;
    private Text myScoreText = new Text();
    private JetpackPlayerView myPlayerView;
    private GameConfiguration gameConfig;
    private EndScreen endScreen;
    private Stage myStage;
    private Scene myScene;

    public Scene setupScene(int width, int height, Paint background, Stage currentstage) throws IOException {
        endScreen = new EndScreen("Jetpack");
        myStage = currentstage;
        ImageView imageView = getImageView();
        Group root = new Group(imageView);
        gameConfig = new JetpackGameConfiguration(Paths.get(CSVfilepath));
        addBarry(root);
        addEnemies(root);
        addPowerups(root);
        gameManager = new JetpackGameManager(myPlayer, enemies, powerups);
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
        //Add for loop for the enemies once images are added
        for (Enemy enemy : enemies){
            EnemyView tempEnemyView = new EnemyView(new Image(ZAPPER_IMAGE), enemy.getXPos(), enemy.getYPos());
            tempEnemyView.setProperties(enemy);
            tempEnemyView.setWidthAndHeight(40, 180);
            enemiesView.add(tempEnemyView);
            root.getChildren().add(tempEnemyView.getEnemyImage());
        }
    }

    private void addPowerups(Group root) throws IOException {
        powerups = new ArrayList<>(gameConfig.getPowerups()); //use jetpack gameconfig once implemented
        root.getChildren().addAll(powerups);
    }

    private void addBarry(Group root) {
        Image barryImage = new Image(this.getClass().getClassLoader().getResourceAsStream(AIRBORNE_BARRY_IMAGE));
        myPlayer = new JetpackPlayer(10, FLOOR_HEIGHT);
        myPlayerView = new JetpackPlayerView(barryImage, 10, FLOOR_HEIGHT);
        myPlayerView.setProperties(myPlayer);
        root.getChildren().add(myPlayerView.getPlayerImage());
    }

    private ImageView getImageView() {
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(BACKGROUND_IMAGE));
        ImageView imageView = new ImageView(image);
        imageView.setY(0);
        imageView.setFitHeight(IMAGE_HEIGHT);
        imageView.setFitWidth(StartScreen.SCREEN_WIDTH);
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
//            myStage.setScene(endScreen.createEndScreen(myStage));
        }
    }

    private void handleKeyInput (KeyCode code) {
        if(code == KeyCode.SPACE) {

            myPlayer.jump();
            myPlayer.resetJumpStrength();
        }
    }
}
