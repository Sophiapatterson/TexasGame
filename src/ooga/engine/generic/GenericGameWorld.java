package ooga.engine.generic;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.Screens.*;
import ooga.data.config.DinoGameConfiguration;
import ooga.data.config.GameConfiguration;
import ooga.data.config.GenericGameConfiguration;
import ooga.engine.game.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class GenericGameWorld extends GameWorld {

    public static final int FRAMES_PER_SECOND = 30;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final double OBJECT_VIEW_SIZE = 50;

    private Player myPlayer;
    private GenericPlayerView myPlayerView;
    private List<Enemy> enemies;
    private List<EnemyView> enemiesView;
    private List<Powerup> powerups;
    private List<PowerupView> powerupsView;
    private Scene myScene;
    private GameManager gameManager;
    private GameConfiguration gameConfig;
    private Text myScoreText = new Text();
    private EndScreen endScreen;
    private Stage myStage;
    private Group root;
    private GameRules rules;

    public GenericGameWorld() {
        super();
    }

    public Scene setupScene(int width, int height, Paint background, Stage currentstage, Boolean t) throws RuntimeException {
        rules = new GameRules();
        endScreen = new EndScreen(rules.VERSION_NAME);
        myStage = currentstage;
        ImageView imageView = getImageView();
        root = new Group(imageView);
        gameConfig = new GenericGameConfiguration(Paths.get(rules.LEVEL_CSV));
        addPlayer(root);
        addEnemies(root);
        addPowerups(root);
        gameManager = new GenericGameManager(myPlayer, enemies, powerups);
        myScoreText = new Text(rules.SCORE_X, rules.SCORE_Y, "" + gameManager.getScore());
        myScoreText.setFont(new Font(rules.SCORE_TEXT_SIZE));
        root.getChildren().add(myScoreText);
        return getScene(width, height, background, root);
    }

    private Scene getScene(int width, int height, Paint background, Group root) {
        myScene = new Scene(root, width, height, background);
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return myScene;
    }

    private void addEnemies(Group root) {
        enemies = new ArrayList<>(gameConfig.getEnemies());
        enemiesView = new ArrayList<>();
        for (Enemy enemy : enemies){
            EnemyView tempView = new EnemyView(new Image(enemy.getImage()), enemy.getXPos(), rules.FLOOR_HEIGHT);
            tempView.setProperties(enemy);
            tempView.setWidthAndHeight(OBJECT_VIEW_SIZE, OBJECT_VIEW_SIZE);
            enemiesView.add(tempView);
            root.getChildren().add(tempView.getEnemyImage());
        }
    }

    private void addPowerups(Group root) {
        powerups = new ArrayList<>(gameConfig.getPowerups());
        powerupsView = new ArrayList<>();
        for (Powerup coin : powerups){
            PowerupView tempCoinView = new PowerupView(new Image(coin.getImage()), coin.getXPos(), coin.getYPos());
            tempCoinView.setProperties(coin);
            tempCoinView.setWidthAndHeight(OBJECT_VIEW_SIZE, OBJECT_VIEW_SIZE);
            powerupsView.add(tempCoinView);
            root.getChildren().add(tempCoinView.getPowerupImage());
        }
    }

    private void addPlayer(Group root) {
        Image playerImage = new Image(this.getClass().getClassLoader().getResourceAsStream(rules.PLAYER_IMAGE));
        myPlayer = new GenericPlayer(rules.INITIAL_X_POS, rules.FLOOR_HEIGHT);
        myPlayerView = new GenericPlayerView(playerImage, rules.INITIAL_X_POS, rules.FLOOR_HEIGHT);
        myPlayerView.setProperties((GenericPlayer) myPlayer);
        root.getChildren().add(myPlayerView.getPlayerImage());
    }

    private void addText(Group root){

    }

    private ImageView getImageView() {
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(rules.BACKGROUND_IMAGE));
        ImageView imageView = new ImageView(image);
        imageView.setY(320);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    // Change properties of shapes to animate them
    public void step (double elapsedTime) {
        gameManager.handleJump(rules.FLOOR_HEIGHT);
        // move the enemies
        for(Enemy enemy: enemies) {
            enemy.move();
        }
//      move the powerups
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
            stopAnimation();
            myStage.setScene(endScreen.createEndScreen(myStage, gameManager.getScore()));
        }
    }

    private void handleKeyInput (KeyCode code) {
        if(code == KeyCode.SPACE) {
            myPlayer.jump();
        }
    }
}
