package ooga;

import ooga.engine.game.Player;
import ooga.engine.jetpack.JetpackGameWorld;
import ooga.engine.jetpack.JetpackPlayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JetpackPlayerTest {

    @Test
    void jump() {
        Player player = new JetpackPlayer(100, JetpackGameWorld.FLOOR_HEIGHT);
        double oldPos = player.getYPos();
        player.jump();
        double newPos = player.getYPos();
        assertEquals(JetpackPlayer.DEFAULT_JUMP_STRENGTH, oldPos - newPos);
    }

    @Test
    void isAirborne() {
        Player player = new JetpackPlayer(100, JetpackGameWorld.FLOOR_HEIGHT);
        assertEquals(false, player.isAirborne(JetpackGameWorld.FLOOR_HEIGHT));
        player.jump();
        assertEquals(true, player.isAirborne(JetpackGameWorld.FLOOR_HEIGHT));
        Player player2 = new JetpackPlayer(100, JetpackGameWorld.FLOOR_HEIGHT - 1);
        assertEquals(true, player2.isAirborne(JetpackGameWorld.FLOOR_HEIGHT));
    }

    @Test
    void fall() {
        Player player = new JetpackPlayer(100, JetpackGameWorld.FLOOR_HEIGHT);
        double oldPos = player.getYPos();
        player.fall();
        player.jump();
        double newPos = player.getYPos();
        assertEquals(JetpackPlayer.DEFAULT_JUMP_STRENGTH - JetpackPlayer.GRAVITY
                , oldPos - newPos);
    }
}