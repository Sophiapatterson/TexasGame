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
import ooga.screens.*;
import ooga.data.config.GameConfiguration;
import ooga.data.config.GenericGameConfiguration;
import ooga.engine.game.*;
import ooga.view.EnemyView;
import ooga.view.PlayerView;
import ooga.view.PowerupView;
import ooga.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class GenericGameWorld extends GameWorld {

    public static final int FRAMES_PER_SECOND = 30;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Player myPlayer;
    private PlayerView myPlayerView;
    private List<Enemy> enemies;
    private List<View> enemiesView;
    private List<Powerup> powerups;
    private List<View> powerupsView;
    private List<Scrolling> scrollers;
    private Scene myScene;
    private GameManager gameManager;
    private GameConfiguration gameConfig;
    private Text myScoreText = new Text();
    private EndScreen endScreen;
    private Stage myStage;
    private Group root;
    private GameRules rules;
    private String rulesPath;
    private String VERSION_NAME;
    private Tutorial myTutorial;
    private List<Text> tutorialtext;

    /**
     * This class is a flexible implementation of the GameWorld superclass where all relevant details are drawn
     * from a properties file. It has support for tutorial functionalities.
     * @param rulesPath - a String that represents the filepath to the appropriate properties file
     */
    public GenericGameWorld(String rulesPath) {
        super();
        this.rulesPath = rulesPath;
    }

    public Scene setupScene(int width, int height, Paint background, Stage currentstage, Boolean t) throws RuntimeException {
        rules = new GameRules(rulesPath);
        myTutorial = new Tutorial();
        VERSION_NAME = rules.VERSION_NAME;
        endScreen = new EndScreen(rules.VERSION_NAME);
        myStage = currentstage;
        ImageView imageView = getImageView();
        root = new Group(imageView);
        gameConfig = new GenericGameConfiguration(rulesPath);
        if(rules.TUTORIAL){
            myTutorial.setFinalDistance(rules.TUTORIAL_LENGTH);
            addText(root);
        }
        addPlayer(root);
        addEnemies(root);
        addPowerups(root);
        scrollers = gameConfig.getScrollers();
        gameManager = new GenericGameManager(myPlayer, enemies, powerups, rulesPath);
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
            tempView.setEnemyProperties(enemy);
            tempView.setWidthAndHeight(rules.OBJECT_VIEW_SIZE, rules.OBJECT_VIEW_SIZE);
            enemiesView.add(tempView);
            root.getChildren().add(tempView.getView());
        }
    }

    private void addPowerups(Group root) {
        powerups = new ArrayList<>(gameConfig.getPowerups());
        powerupsView = new ArrayList<>();
        for (Powerup coin : powerups){
            PowerupView tempCoinView = new PowerupView(new Image(coin.getImage()), coin.getXPos(), coin.getYPos());
            tempCoinView.setPowerupProperties(coin);
            tempCoinView.setWidthAndHeight(rules.OBJECT_VIEW_SIZE, rules.OBJECT_VIEW_SIZE);
            powerupsView.add(tempCoinView);
            root.getChildren().add(tempCoinView.getView());
        }
    }

    private void addPlayer(Group root) {
        Image playerImage = new Image(this.getClass().getClassLoader().getResourceAsStream(rules.PLAYER_IMAGE));
        myPlayer = new GenericPlayer(rules.INITIAL_X_POS, rules.FLOOR_HEIGHT, rulesPath);
        myPlayerView = new PlayerView(playerImage, rules.INITIAL_X_POS, rules.FLOOR_HEIGHT);
        myPlayerView.setPlayerProperties((GenericPlayer) myPlayer);
        myPlayerView.setWidthAndHeight(rules.PLAYER_WIDTH, rules.PLAYER_HEIGHT);
        root.getChildren().add(myPlayerView.getView());
    }

    private void addText(Group root){
        List<String> tutorialstrings = new ArrayList<>();
        ResourceBundle tutorialResources = ResourceBundle.getBundle(rules.TUTORIAL_TEXT);
        for(String key: tutorialResources.keySet()){
            tutorialstrings.add(tutorialResources.getString(key));
        }
        tutorialtext = myTutorial.createTutorialText(tutorialstrings, true);
        root.getChildren().add(tutorialtext.get(0));
    }

    private ImageView getImageView() {
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(rules.BACKGROUND_IMAGE));
        ImageView imageView = new ImageView(image);
        imageView.setY(rules.BACKGROUND_HEIGHT);
        if(rules.STRETCH_BACKGROUND)
            imageView.setFitHeight(rules.SCREEN_HEIGHT);
        imageView.setFitWidth(StartScreen.SCREEN_WIDTH);
        return imageView;
    }

    // Change properties of shapes to animate them
    public void step (double elapsedTime) {
        gameManager.handleJump(rules.FLOOR_HEIGHT);
        // move the enemies
        for(Enemy enemy: enemies) {
            enemy.move();
        }
        //manages tutorial functions
        if(rules.TUTORIAL){
            myTutorial.tutorialObstacles(myPlayer, scrollers, root, tutorialtext);
            if(myPlayer.getXPos()>scrollers.get(scrollers.size()-1).getXPos()+myTutorial.GAMEOVERDISTANCE){
                stopAnimation();
                myStage.setScene(endScreen.createEndScreen(myStage, gameManager.getScore()));
            }
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
