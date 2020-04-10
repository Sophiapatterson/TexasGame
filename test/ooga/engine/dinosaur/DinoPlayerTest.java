package ooga.engine.dinosaur;

import javafx.scene.image.Image;
import ooga.engine.game.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DinoPlayerTest {

    @Test
    /**
     * Test jump works.
     */
    void jumpTest() {
        Player dino = new DinoPlayer(100, DinoGameWorld.FLOOR_HEIGHT);
        double oldPos = dino.getYPos();
        dino.jump();
        double newPos = dino.getYPos();
        assertEquals(DinoPlayer.DEFAULT_JUMP_STRENGTH, oldPos - newPos);
    }

    @Test
    void fallTest() {

    }

    @Test
    /**
     * Test to check that isAirborne works properly.
     */
    void isAirborneTest() {
        Player dino = new DinoPlayer(100, DinoGameWorld.FLOOR_HEIGHT);
        assertEquals(false, dino.isAirborne(DinoGameWorld.FLOOR_HEIGHT));
        dino.jump();
        assertEquals(true, dino.isAirborne(DinoGameWorld.FLOOR_HEIGHT));
    }
}