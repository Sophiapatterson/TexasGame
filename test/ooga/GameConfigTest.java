package ooga;

import ooga.data.GameConfiguration;
import ooga.engine.game.Scrolling;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameConfigTest {
    GameConfiguration config;
    List<Scrolling> scroll;

    public void testSetUp() throws IOException {
        config = new GameConfiguration(Paths.get("data/CSV configurations/levelOne.csv"));
        scroll = config.getScrollers();
    }

    @Test
    void testCacti() throws IOException {
        testSetUp();
        assertEquals(33, scroll.size());
    }
}
