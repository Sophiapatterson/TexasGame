package ooga.engine.flappy;

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
import ooga.data.config.FlappyGameConfiguration;
import ooga.data.config.GameConfiguration;
import ooga.engine.dinosaur.DinoGameWorld;
import ooga.engine.game.*;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FlappyGameWorld extends GameWorld {

    public static final double FLOOR_HEIGHT = 450;
    public static final int FRAMES_PER_SECOND = 30;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final String BIRD_IMAGE  = "Sprites/flappy_yellowbird.png";
    public static final String BACKGROUND_IMAGE = "Sprites/flappy_background.png";
    public static final int IMAGE_HEIGHT = 695;
    public static final String VERSION_NAME = "Flappy";
    public static final String LevelOne = "data/CSV configurations/Flappy_Level.csv";
    public static final String TutorialCSV = "data/CSV configurations/dinoflappyTutorial.csv";
    public static final int INITIAL_PLAYER_YPOS = 250;
    public static final int PIPE_WIDTH = 100;
    public static final int PIPE_HEIGHT = 550;
    private Player myPlayer;
    private BirdPlayerView myPlayerView;
    private List<Enemy> enemies;
    private List<EnemyView> enemiesView;
    private List<Powerup> powerups;
    private List<PowerupView> powerupsView;
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

    // Create the game's "scene": what shapes will be in the game and their starting properties
    public Scene setupScene(int width, int height, Paint background, Stage currentstage, Boolean tutorial) throws RuntimeException {
        tutorialcheck = tutorial;
        myTutorial = new Tutorial();
        tutorialscreen = new TutorialScreen();
        endScreen = new EndScreen(VERSION_NAME);
        myStage = currentstage;
        ImageView imageView = getImageView();
        root = new Group(imageView);
        if(tutorialcheck){
            gameConfig = new FlappyGameConfiguration(Paths.get(TutorialCSV));
            addText(root);
        }
        else{
            gameConfig = new FlappyGameConfiguration(Paths.get(LevelOne));
        }
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

    private void addEnemies(Group root) {
        enemies = new ArrayList<>(gameConfig.getEnemies());
        enemiesView = new ArrayList<>();
        for (Enemy pipe : enemies){
            EnemyView tempPipeView = new EnemyView(new Image(pipe.getImage()), pipe.getXPos(), pipe.getYPos());
            tempPipeView.setWidthAndHeight(PIPE_WIDTH, PIPE_HEIGHT);
            tempPipeView.setProperties(pipe);
            enemiesView.add(tempPipeView);
            root.getChildren().add(tempPipeView.getEnemyImage());
        }
    }

    private void addPowerups(Group root) {
        powerups = new ArrayList<>(gameConfig.getPowerups());
        powerupsView = new ArrayList<>();
        for (Powerup coin : powerups) {
            PowerupView tempCoinView = new PowerupView(new Image(coin.getImage()), coin.getXPos(), coin.getYPos());
            tempCoinView.setWidthAndHeight(50,50);
            tempCoinView.setProperties(coin);
            powerupsView.add(tempCoinView);
            root.getChildren().add(tempCoinView.getPowerupImage());
        }
    }

    private void addBird(Group root) {
        Image birdImage = new Image(this.getClass().getClassLoader().getResourceAsStream(BIRD_IMAGE));
        myPlayer = new BirdPlayer(DinoGameWorld.INITIAL_PLAYER_XPOS, INITIAL_PLAYER_YPOS);
        myPlayerView = new BirdPlayerView(birdImage, DinoGameWorld.INITIAL_PLAYER_XPOS, FLOOR_HEIGHT);
        myPlayerView.setProperties((BirdPlayer) myPlayer);
        root.getChildren().add(myPlayerView.getPlayerImage());
    }

    private void addText(Group root){
        List<String> tutorialstring = new ArrayList<>();
        ResourceBundle tutorialResources = ResourceBundle.getBundle("Properties.FLAPPY-TUTORIAL");
        tutorialstring.add(tutorialResources.getString("FLAPPY1-MESSAGE"));
        tutorialstring.add(tutorialResources.getString("FLAPPY2-MESSAGE"));
        tutorialstring.add(tutorialResources.getString("FLAPPY3-MESSAGE"));
        tutorialtext = myTutorial.createTutorialText(tutorialstring, true);
        root.getChildren().add(tutorialtext.get(0));
    }

    private ImageView getImageView() {
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(BACKGROUND_IMAGE));
        ImageView imageView = new ImageView(image);
        imageView.setY(0);
        imageView.setFitHeight(IMAGE_HEIGHT);
        imageView.setFitWidth(StartScreen.SCREEN_WIDTH);
        return imageView;
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
            myTutorial.tutorialObstacles(myPlayer, enemies, root, tutorialtext);
            if(myPlayer.getXPos()>enemies.get(1).getXPos()+myTutorial.GAMEOVERDISTANCE){
                stopAnimation();
                myStage.setScene(tutorialscreen.TutorialorGameChooser(myStage));
            }
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
            else {
                myStage.setScene(endScreen.createEndScreen(myStage, gameManager.getScore()));
            }
        }
    }

    private void handleKeyInput (KeyCode code) {
        if(code == KeyCode.SPACE) {
            myPlayer.resetJumpStrength();
            myPlayer.jump();
        }
    }
}
