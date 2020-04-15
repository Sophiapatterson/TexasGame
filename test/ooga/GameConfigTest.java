package ooga;

import ooga.data.config.DinoGameConfiguration;
import ooga.engine.game.Enemy;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameConfigTest {
    DinoGameConfiguration config;
    List<Enemy> enemies;

    public void testSetUp() throws IOException {
        config = new DinoGameConfiguration(Paths.get("data/CSV configurations/levelOne.csv"));
        enemies = config.getEnemies();
    }

    @Test
    void testCacti() throws IOException {
        testSetUp();
        assertEquals(23, enemies.size());
    }
}
