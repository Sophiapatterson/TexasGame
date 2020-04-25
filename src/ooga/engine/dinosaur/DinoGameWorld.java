package ooga.engine.dinosaur;

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
import ooga.data.config.DinoGameConfiguration;
import ooga.data.config.GameConfiguration;
import ooga.engine.game.*;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class DinoGameWorld extends GameWorld {
    public static final double FLOOR_HEIGHT = 275;
    public static final int FRAMES_PER_SECOND = 30;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final double INITIAL_PLAYER_XPOS = 100;
    private static final String VERSION_NAME = "Dinosaur";
    public static final String DINO_IMAGE  = "Sprites/dino_trexx.png";
    public static final String HORIZON_IMAGE = "Sprites/dino_horizon.png";
    public static final String TutorialCSV = "data/CSV configurations/dinoflappyTutorial.csv";
    public static final String LevelOne = "data/CSV configurations/Dinosaur_Level.csv";
    public static final double OBJECT_VIEW_SIZE = 72;
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
    private TutorialScreen tutorialscreen;
    private Text myScoreText = new Text();
    private EndScreen endScreen;
    private Stage myStage;
    private List<Text> tutorialtext;
    private boolean tutorialcheck;
    private Group root;
    private Tutorial myTutorial;

    public DinoGameWorld() {
        super();
    }

    // Create the game's "scene": what shapes will be in the game and their starting properties
    public Scene setupScene(int width, int height, Paint background, Stage currentstage, Boolean tutorial) throws RuntimeException {
        myTutorial = new Tutorial();
        tutorialcheck = tutorial;
        tutorialscreen = new TutorialScreen();
        endScreen = new EndScreen(VERSION_NAME);
        myStage = currentstage;
        ImageView imageView = getImageView();
        root = new Group(imageView);
        if(tutorialcheck){
            gameConfig = new DinoGameConfiguration(Paths.get(TutorialCSV));
            addText(root);
        }
        else{
            gameConfig = new DinoGameConfiguration(Paths.get(LevelOne));
        }
        addDino(root);
        addEnemies(root);
        addPowerups(root);
        scrollers = gameConfig.getScrollers();
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

    private void addEnemies(Group root) {
        enemies = new ArrayList<>(gameConfig.getEnemies());
        enemiesView = new ArrayList<>();
        for (Enemy cactus : enemies){
            View tempCacView = new EnemyView(new Image(cactus.getImage()), cactus.getXPos(), FLOOR_HEIGHT);
            tempCacView.setEnemyProperties(cactus);
            tempCacView.setWidthAndHeight(OBJECT_VIEW_SIZE, OBJECT_VIEW_SIZE);
            enemiesView.add(tempCacView);
            root.getChildren().add(tempCacView.getView());
        }
    }

    private void addPowerups(Group root) {
        powerups = new ArrayList<>(gameConfig.getPowerups());
        powerupsView = new ArrayList<>();
        for (Powerup coin : powerups){
            View tempCoinView = new PowerupView(new Image(coin.getImage()), coin.getXPos(), coin.getYPos());
            tempCoinView.setPowerupProperties(coin);
            tempCoinView.setWidthAndHeight(OBJECT_VIEW_SIZE, OBJECT_VIEW_SIZE);
            powerupsView.add(tempCoinView);
            root.getChildren().add(tempCoinView.getView());
        }
    }

    private void addDino(Group root) {
        Image dinoImage = new Image(this.getClass().getClassLoader().getResourceAsStream(DINO_IMAGE));
        myPlayer = new DinoPlayer(INITIAL_PLAYER_XPOS, FLOOR_HEIGHT);
        myPlayerView = new PlayerView(dinoImage, INITIAL_PLAYER_XPOS, FLOOR_HEIGHT);
        myPlayerView.setPlayerProperties(myPlayer);
        root.getChildren().add(myPlayerView.getView());
    }

    private void addText(Group root){
        List<String> tutorialstring = new ArrayList<>();
        ResourceBundle tutorialResources = ResourceBundle.getBundle("Properties.DINO-TUTORIAL");
        tutorialstring.add(tutorialResources.getString("DINO1-MESSAGE"));
        tutorialstring.add(tutorialResources.getString("DINO2-MESSAGE"));
        tutorialstring.add(tutorialResources.getString("DINO3-MESSAGE"));
        tutorialtext = myTutorial.createTutorialText(tutorialstring, true);
        root.getChildren().add(tutorialtext.get(0));
    }

    private ImageView getImageView() {
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(HORIZON_IMAGE));
        ImageView imageView = new ImageView(image);
        imageView.setY(320);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    // Change properties of shapes to animate them
    public void step (double elapsedTime) {
        gameManager.handleJump(FLOOR_HEIGHT);
        // move the enemies
        for(Enemy enemy: enemies) {
            enemy.move();
        }
        if(tutorialcheck){
            myTutorial.tutorialObstacles(myPlayer, scrollers, root, tutorialtext);
            if(myPlayer.getXPos()>scrollers.get(scrollers.size()-1).getXPos()+myTutorial.GAMEOVERDISTANCE){
                stopAnimation();
                myStage.setScene(tutorialscreen.TutorialorGameChooser(myStage));
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
        }
    }
}
