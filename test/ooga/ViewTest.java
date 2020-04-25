package ooga;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.engine.dinosaur.Cactus;
import ooga.engine.flappy.BirdPlayer;
import ooga.engine.game.Coin;
import ooga.engine.game.Enemy;
import ooga.engine.game.Player;
import ooga.engine.game.Powerup;
import ooga.view.EnemyView;
import ooga.view.PlayerView;
import ooga.view.PowerupView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewTest extends DukeApplicationTest {

    @Test
    void testinitializeView(){
        Image myImage = new Image("Sprites/flappy_yellowbird.png");
        PlayerView tester = new PlayerView(myImage, 50,50);
        ImageView myImageView = tester.initializeView(myImage);
        assertEquals(true, myImageView.isPreserveRatio());
        assertEquals(true, myImageView.isVisible());
    }

    @Test
    void setWidthandHeight(){
        Image myImage = new Image("Sprites/flappy_yellowbird.png");
        PlayerView tester = new PlayerView(myImage, 50,50);
        ImageView myImageView = tester.initializeView(myImage);
        tester.setWidthAndHeight(100,100);
        assertEquals(100, myImageView.getFitHeight());
        assertEquals(100, myImageView.getFitWidth());
    }

    @Test
    void testsetPlayerProperties(){
        Image myImage = new Image("Sprites/flappy_yellowbird.png");
        Player tester = new BirdPlayer(50,50);
        PlayerView testerView = new PlayerView(myImage, 100,100);
        ImageView myImageView = testerView.initializeView(myImage);
        testerView.setPlayerProperties(tester);
        assertEquals(tester.getXPos(), testerView.getView().getX());
        assertEquals(tester.getYPos(), testerView.getView().getY());
    }

    @Test
    void testsetPlayerProperties2(){
        Image myImage = new Image("Sprites/flappy_yellowbird.png");
        Player tester = new BirdPlayer(999,99);
        PlayerView testerView = new PlayerView(myImage, 100,100);
        ImageView myImageView = testerView.initializeView(myImage);
        testerView.setPlayerProperties(tester);
        assertEquals(tester.getXPos(), testerView.getView().getX());
        assertEquals(tester.getYPos(), testerView.getView().getY());
    }

    @Test
    void testsetEnemyProperties(){
        Image myImage = new Image("Sprites/dino_smallcactusgroup.png");
        Enemy cactus = new Cactus(50,50);
        EnemyView cactusView = new EnemyView(myImage, 100,100);
        cactusView.setEnemyProperties(cactus);
        assertEquals(cactus.getXPos(),cactusView.getView().getX());
        assertEquals(cactus.getYPos(),cactusView.getView().getY());
    }


    @Test
    void testsetEnemyProperties2(){
        Image myImage = new Image("Sprites/dino_smallcactusgroup.png");
        Enemy cactus = new Cactus(-10,50);
        EnemyView cactusView = new EnemyView(myImage, 100,100);
        cactusView.setEnemyProperties(cactus);
        assertEquals(cactus.getXPos(),cactusView.getView().getX());
        assertEquals(cactus.getYPos(),cactusView.getView().getY());
    }

    @Test
    void testsetPowerupProperties(){
        Image myImage = new Image("Sprites/general_coin.png");
        Powerup coin = new Coin(50,50);
        PowerupView coinView = new PowerupView(myImage, 100,100);
        coinView.setPowerupProperties(coin);
        assertEquals(coin.getXPos(),coinView.getView().getX());
        assertEquals(coin.getYPos(),coinView.getView().getY());
    }

    @Test
    void testsetPowerupProperties2(){
        Image myImage = new Image("Sprites/general_coin.png");
        Powerup coin = new Coin(50,0);
        PowerupView coinView = new PowerupView(myImage, 0,100);
        coinView.setPowerupProperties(coin);
        assertEquals(coin.getXPos(),coinView.getView().getX());
        assertEquals(coin.getYPos(),coinView.getView().getY());
    }

}
