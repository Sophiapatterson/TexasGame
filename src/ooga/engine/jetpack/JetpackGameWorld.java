package ooga.engine.jetpack;

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
import ooga.view.*;
import ooga.data.config.GameConfiguration;
import ooga.data.config.JetpackGameConfiguration;
import ooga.engine.game.*;

import java.nio.file.Paths;
import java.util.*;

public class JetpackGameWorld extends GameWorld {

    public static final double FLOOR_HEIGHT = 500;
    public static final int FRAMES_PER_SECOND = 30;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final String BACKGROUND_IMAGE = "Sprites/jetpack_background.png";
    public static final String AIRBORNE_BARRY_IMAGE = "Sprites/jetpack_airbornBarry.png";
    public static final String ZAPPER_IMAGE = "Sprites/jetpack_zapper.png";
    public static final int IMAGE_HEIGHT = 600;
    public static final int SMALL_COIN_SIZE = 35;
    public static final String BARRY_IMAGE  = "Sprites/jetpack_normalBarry.png";
    public static final String LevelOne = "data/CSV configurations/Jetpack_Level.csv";
    public static final String TutorialCSV = "data/CSV configurations/jetpackTutorial.csv";
    public static final String VERSION_NAME = "Jetpack";
    public static final int ENEMY_WIDTH = 40;
    public static final int ENEMY_HEIGHT = 180;
    public static final int PLAYER_XPOS = 100;
    private JetpackPlayer myPlayer;
    private List<Enemy> enemies;
    private List<View> enemiesView;
    private List<Powerup> powerups;
    private List<View> powerupsView;
    private List<Scrolling> scrollers;
    private GameManager gameManager;
    private Text myScoreText = new Text();
    private PlayerView myPlayerView;
    private GameConfiguration gameConfig;
    private EndScreen endScreen;
    private TutorialScreen tutorialscreen;
    private Stage myStage;
    private Scene myScene;
    private Group myRoot;
    private Map<Powerup, View> myPowerupMap;
    private Tutorial myTutorial;
    private List<Text> tutorialtext;
    private boolean tutorialcheck;
    private List<Text> tutorialtext;

    @Override
    public Scene setupScene(int width, int height, Paint background, Stage currentstage, Boolean tutorial) throws RuntimeException {
        myTutorial = new Tutorial();
        tutorialcheck = tutorial;
        tutorialscreen = new TutorialScreen();
        endScreen = new EndScreen(VERSION_NAME);
        myStage = currentstage;
        ImageView imageView = getImageView();
        myRoot = new Group(imageView);
        if(tutorialcheck){
            gameConfig = new JetpackGameConfiguration(Paths.get(TutorialCSV));
            addText(myRoot);
        }
        else{
            gameConfig = new JetpackGameConfiguration(Paths.get(LevelOne));
        }
        addBarry(myRoot);
        addEnemies(myRoot);
        addPowerups(myRoot);
        scrollers = gameConfig.getScrollers();
        gameManager = new JetpackGameManager(myPlayer, enemies, powerups);
        myScoreText = new Text(SCORE_X, SCORE_Y, "" + gameManager.getScore());
        myScoreText.setFont(new Font(SCORE_TEXT_SIZE));
        myRoot.getChildren().add(myScoreText);
        return getScene(width, height, background, myRoot);
    }

    private Scene getScene(int width, int height, Paint background, Group root) {
        myScene = new Scene(root, width, height, background);
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return myScene;
    }

    private void addEnemies(Group root){
        enemies = new ArrayList<>(gameConfig.getEnemies());
        enemiesView = new ArrayList<>();
        //Add for loop for the enemies once images are added
        for (Enemy enemy : enemies){
            EnemyView tempEnemyView = new EnemyView(new Image(ZAPPER_IMAGE), enemy.getXPos(), enemy.getYPos());
            tempEnemyView.setEnemyProperties(enemy);
            tempEnemyView.setWidthAndHeight(ENEMY_WIDTH, ENEMY_HEIGHT);
            enemiesView.add(tempEnemyView);
            root.getChildren().add(tempEnemyView.getView());
        }
    }

    private void addPowerups(Group root){
        powerups = new ArrayList<>(gameConfig.getPowerups());
        powerupsView = new ArrayList<>();
        myPowerupMap = new HashMap<>();
        for (Powerup coin : powerups){
            View tempCoinView = new PowerupView(new Image(coin.getImage()), coin.getXPos(), coin.getYPos());
            myPowerupMap.put(coin, tempCoinView);
            tempCoinView.setPowerupProperties(coin);
            tempCoinView.setWidthAndHeight(SMALL_COIN_SIZE,SMALL_COIN_SIZE);
            powerupsView.add(tempCoinView);
            root.getChildren().add(tempCoinView.getView());
        }    }

    private void addBarry(Group root) {
        Image barryImage = new Image(this.getClass().getClassLoader().getResourceAsStream(AIRBORNE_BARRY_IMAGE));
        myPlayer = new JetpackPlayer(PLAYER_XPOS, FLOOR_HEIGHT);
        myPlayerView = new PlayerView(barryImage, PLAYER_XPOS, FLOOR_HEIGHT);
        myPlayerView.setPlayerProperties(myPlayer);
        root.getChildren().add(myPlayerView.getView());
    }

    private ImageView getImageView() {
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(BACKGROUND_IMAGE));
        ImageView imageView = new ImageView(image);
        imageView.setY(0);
        imageView.setFitWidth(StartScreen.SCREEN_WIDTH);
        imageView.setFitHeight(IMAGE_HEIGHT);
        return imageView;
    }

    private void addText(Group root){
        List<String> tutorialstring = new ArrayList<>();
        ResourceBundle tutorialResources = ResourceBundle.getBundle("Properties.JET-TUTORIAL");
        tutorialstring.add(tutorialResources.getString("JET1-MESSAGE"));
        tutorialstring.add(tutorialResources.getString("JET2-MESSAGE"));
        tutorialstring.add(tutorialResources.getString("JET3-MESSAGE"));
        tutorialstring.add(tutorialResources.getString("JET4-MESSAGE"));
        tutorialtext = myTutorial.createTutorialText(tutorialstring, false);
        root.getChildren().add(tutorialtext.get(0));
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
        if(tutorialcheck){
            myTutorial.tutorialObstacles(myPlayer, scrollers, myRoot, tutorialtext);

            if(myPlayer.getXPos()>scrollers.get(scrollers.size()-1).getXPos()+myTutorial.GAMEOVERDISTANCE){
                stopAnimation();
                myStage.setScene(tutorialscreen.TutorialorGameChooser(myStage));
            }
        }

        //increment score
        gameManager.tick();

        // collisions
        gameManager.handleCollisions();
        List<Powerup> removePowerups = gameManager.handlePowerups();
        for (Powerup each : removePowerups){
            myRoot.getChildren().remove(myPowerupMap.get(each).getView());

        }

        //update score
        myScoreText.setText(""+gameManager.getScore());

        if(gameManager.isGameOver()) {
            stopAnimation();
            if(tutorialcheck){
                myStage.setScene(tutorialscreen.TutorialorGameChooser(myStage));
            }
            else{
                myStage.setScene(endScreen.createEndScreen(myStage, gameManager.getScore()));
            }
        }
    }

    private void handleKeyInput (KeyCode code) {
        if(code == KeyCode.SPACE) {
            myPlayer.jump();
            myPlayer.resetJumpStrength();
        }
    }
}
