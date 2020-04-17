package ooga;


import ooga.engine.flappy.BirdPlayer;
import ooga.engine.flappy.FlappyGameWorld;
import ooga.engine.game.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BirdPlayerTest {

    @Test
    void jump() {
        Player bird = new BirdPlayer(100, FlappyGameWorld.FLOOR_HEIGHT);
        double oldPos = bird.getYPos();
        bird.jump();
        double newPos = bird.getYPos();
        // bird jumpstrength starts at zero
        assertEquals(0, oldPos - newPos);
    }

    @Test
    void isAirborne() {
        Player bird = new BirdPlayer(100, FlappyGameWorld.FLOOR_HEIGHT);
        // bird starts just above floor and is therefore airborne
        assertEquals(true, bird.isAirborne(FlappyGameWorld.FLOOR_HEIGHT));
        bird.jump();
        // bird falls onto the ground and is thus no longer airborne
        assertEquals(false, bird.isAirborne(FlappyGameWorld.FLOOR_HEIGHT));
    }

    @Test
    void fall() {
        Player bird = new BirdPlayer(100, FlappyGameWorld.FLOOR_HEIGHT);
        double oldPos = bird.getYPos();
        bird.fall();
        bird.jump();
        double newPos = bird.getYPos();
        assertEquals(BirdPlayer.DEFAULT_JUMP_STRENGTH - (BirdPlayer.DEFAULT_JUMP_STRENGTH + BirdPlayer.GRAVITY)
                , oldPos - newPos);
    }
}