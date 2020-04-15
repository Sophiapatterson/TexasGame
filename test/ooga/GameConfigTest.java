package ooga;

import ooga.data.DinoGameConfiguration;
import ooga.engine.game.Scrolling;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameConfigTest {
    DinoGameConfiguration config;
    List<Scrolling> scroll;

    public void testSetUp() throws IOException {
        config = new DinoGameConfiguration(Paths.get("data/CSV configurations/levelOne.csv"));
        scroll = config.getScrollers();
    }

    @Test
    void testCacti() throws IOException {
        testSetUp();
        assertEquals(33, scroll.size());
    }
}
