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
import ooga.screens.*;
import ooga.view.*;
import ooga.data.config.FlappyGameConfiguration;
import ooga.data.config.GameConfiguration;
import ooga.engine.dinosaur.DinoGameWorld;
import ooga.engine.game.*;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FlappyGameWorld extends GameWorld, initializes all scenes in FlappyGame and includes step function.
 */
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
    private View myPlayerView;
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

    /**
     * setupScene method creates scenes for new instance of FlappyGame. Initializes game by creating new instances of Tutorial, Screens, Group, GameManager.
     * Creates scene by creating Group given background image and by adding Player, Enemy, Powerup, ScoreText to the scene.
     * to the scene.
     * @param width scene's width.
     * @param height scene's height.
     * @param background background color.
     * @param currentstage stage used previously for start and change screen.
     * @param tutorial boolean determining whether to cue tutorial or actual game.
     * @return myScene, scene that includes all aspects of a new FlappyGame, created via getScene.
     * @throws RuntimeException
     */
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
        scrollers = gameConfig.getScrollers();
        gameManager = new FlappyGameManager(myPlayer, enemies, powerups);
        myScoreText = new Text(SCORE_X, SCORE_Y, "" + gameManager.getScore());
        myScoreText.setFont(new Font(SCORE_TEXT_SIZE));
        root.getChildren().add(myScoreText);
        return getScene(width, height, background, root);
    }
    /**
     * helper method for setupScene. Initializes the FlappyGame's scene.
     * @param width scene'swidth
     * @param height scene's height
     * @param background Game's background color
     * @param root Group where all game objects are added.
     * @return myScene, scene that includes all aspects of a new FlappyGame.
     */
    private Scene getScene(int width, int height, Paint background, Group root) {
        myScene = new Scene(root, width, height, background);
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return myScene;
    }
    /**
     * helper method for setupScene. Creates new list of pipes/pipe views based on data
     * from FlappyGameConfiguration. Binds pipe to pipe views, adds these pipes to the scene.
     * @param root Game scene's Group.
     */
    private void addEnemies(Group root) {
        enemies = new ArrayList<>(gameConfig.getEnemies());
        enemiesView = new ArrayList<>();
        for (Enemy pipe : enemies){
            EnemyView tempPipeView = new EnemyView(new Image(pipe.getImage()), pipe.getXPos(), pipe.getYPos());
            tempPipeView.setWidthAndHeight(PIPE_WIDTH, PIPE_HEIGHT);
            tempPipeView.setEnemyProperties(pipe);
            enemiesView.add(tempPipeView);
            root.getChildren().add(tempPipeView.getView());
        }
    }
    /**
     * helper method for setupScene. Creates new list of coins/coin views based on data
     * from FlappyGameConfiguration. Binds coin to coin views, adds these coins to the scene.
     * @param root Game scene's Group.
     */
    private void addPowerups(Group root) {
        powerups = new ArrayList<>(gameConfig.getPowerups());
        powerupsView = new ArrayList<>();
        for (Powerup coin : powerups) {
            View tempCoinView = new PowerupView(new Image(coin.getImage()), coin.getXPos(), coin.getYPos());
            tempCoinView.setWidthAndHeight(50,50);
            tempCoinView.setPowerupProperties(coin);
            powerupsView.add(tempCoinView);
            root.getChildren().add(tempCoinView.getView());
        }
    }
    /**
     * helper method for setupScene. Creates new Player and PlayerView.
     * Binds Player to PlayerView, adds this Player to the scene.
     * @param root Game scene's Group.
     */
    private void addBird(Group root) {
        Image birdImage = new Image(this.getClass().getClassLoader().getResourceAsStream(BIRD_IMAGE));
        myPlayer = new BirdPlayer(DinoGameWorld.INITIAL_PLAYER_XPOS, INITIAL_PLAYER_YPOS);
        myPlayerView = new PlayerView(birdImage, DinoGameWorld.INITIAL_PLAYER_XPOS, FLOOR_HEIGHT);
        myPlayerView.setPlayerProperties(myPlayer);
        root.getChildren().add(myPlayerView.getView());
    }

    /**
     * helper method for setupScene. Creates tutorial messages from properties file, adds tutorial text to the screen.
     * @param root Game scene's Group.
     */
    private void addText(Group root){
        List<String> tutorialstring = new ArrayList<>();
        ResourceBundle tutorialResources = ResourceBundle.getBundle("Properties.FLAPPY-TUTORIAL");
        tutorialstring.add(tutorialResources.getString("FLAPPY1-MESSAGE"));
        tutorialstring.add(tutorialResources.getString("FLAPPY2-MESSAGE"));
        tutorialstring.add(tutorialResources.getString("FLAPPY3-MESSAGE"));
        tutorialtext = myTutorial.createTutorialText(tutorialstring, true);
        root.getChildren().add(tutorialtext.get(0));
    }

    /**
     * helper method for setupScene. Creates and initializes background ImageView from image.
     * @return imageView to be applied as background.
     */
    private ImageView getImageView() {
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(BACKGROUND_IMAGE));
        ImageView imageView = new ImageView(image);
        imageView.setY(0);
        imageView.setFitHeight(IMAGE_HEIGHT);
        imageView.setFitWidth(StartScreen.SCREEN_WIDTH);
        return imageView;
    }

    /**
     * step function handles game activity over time.
     * @param elapsedTime standardizes time for all devices.
     */
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
            myTutorial.tutorialObstacles(myPlayer, scrollers, root, tutorialtext);
            if(myPlayer.getXPos()>scrollers.get(scrollers.size()-1).getXPos()+myTutorial.GAMEOVERDISTANCE){
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
    
    /**
     * handle's key input and cues Player jump when space bar is pressed.
     * @param code represents the code of the key pressed.
     */
    private void handleKeyInput (KeyCode code) {
        if(code == KeyCode.SPACE) {
            myPlayer.resetJumpStrength();
            myPlayer.jump();
        }
    }
}
