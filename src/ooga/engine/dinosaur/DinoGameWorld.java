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
import ooga.Screens.*;
import ooga.data.config.DinoGameConfiguration;
import ooga.data.config.GameConfiguration;
import ooga.engine.game.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class DinoGameWorld extends GameWorld {
    public static final double FLOOR_HEIGHT = 275;
    public static final int FRAMES_PER_SECOND = 30;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final double INITIAL_PLAYER_XPOS = 100;
    private static final String VERSION_NAME = "Dinosaur";
    public static final String DINO_IMAGE  = "Sprites/dino_trexx.png";
    public static final String HORIZON_IMAGE = "Sprites/dino_horizon.png";
    public static final String TutorialCSV = "data/CSV configurations/dinoTutorial.csv";
    public static final String LevelOne = "data/CSV configurations/levelOne.csv";
    public static final int SCORE_X = 30;
    public static final int SCORE_Y = 30;
    public static final int SCORE_TEXT_SIZE = 30;
    public static final double OBJECT_VIEW_SIZE = 50;
    private Player myPlayer;
    private DinoPlayerView myPlayerView;
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

    public DinoGameWorld() {
        super();
    }

    // Create the game's "scene": what shapes will be in the game and their starting properties
    public Scene setupScene(int width, int height, Paint background, Stage currentstage, Boolean tutorial) throws IOException {
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
        enemies = new ArrayList<>(gameConfig.getEnemies());
        enemiesView = new ArrayList<>();
        for (Enemy cactus : enemies){
            EnemyView tempCacView = new EnemyView(new Image(cactus.getImage()), cactus.getXPos(), FLOOR_HEIGHT);
            tempCacView.setProperties(cactus);
            tempCacView.setWidthAndHeight(OBJECT_VIEW_SIZE, OBJECT_VIEW_SIZE);
            enemiesView.add(tempCacView);
            root.getChildren().add(tempCacView.getEnemyImage());
        }
    }

    private void addPowerups(Group root) throws IOException {
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

    private void addDino(Group root) {
        Image dinoImage = new Image(this.getClass().getClassLoader().getResourceAsStream(DINO_IMAGE));
        myPlayer = new DinoPlayer(INITIAL_PLAYER_XPOS, FLOOR_HEIGHT);
        myPlayerView = new DinoPlayerView(dinoImage, INITIAL_PLAYER_XPOS, FLOOR_HEIGHT);
        myPlayerView.setProperties((DinoPlayer) myPlayer);
        root.getChildren().add(myPlayerView.getPlayerImage());
    }

    private void addText(Group root){
        tutorialtext = new ArrayList<>();
        Text first = new Text(50, 100, "Press Space to jump over the cactus!");
        Text second = new Text(50, 100, "Good job! Try that again!");
        Text third = new Text(50, 100, "Great! Now try to get that coin!");
        tutorialtext.add(first);
        tutorialtext.add(second);
        tutorialtext.add(third);
        root.getChildren().add(first);
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
            if(myPlayer.getXPos()>enemies.get(0).getXPos() && myPlayer.getXPos()<enemies.get(1).getXPos()){
                if(root.getChildren().contains(tutorialtext.get(0))){
                    root.getChildren().remove(tutorialtext.get(0));
                }
                if(!root.getChildren().contains(tutorialtext.get(1))){
                    root.getChildren().add(tutorialtext.get(1));
                }
            }
            else if(myPlayer.getXPos()>enemies.get(1).getXPos()){
                if(root.getChildren().contains(tutorialtext.get(1))){
                    root.getChildren().remove(tutorialtext.get(1));
                }
                if(!root.getChildren().contains(tutorialtext.get(2))){
                    root.getChildren().add(tutorialtext.get(2));
                }
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
            if(tutorialcheck){
                stopAnimation();
                myStage.setScene(tutorialscreen.TutorialorGameChooser(myStage));
            }
            else{
                stopAnimation();
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
