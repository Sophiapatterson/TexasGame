package ooga;

import ooga.data.HighScores;
import ooga.data.LevelFileException;
import ooga.data.Score;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScoresTest {
    HighScores hs;

    @Test
    void testConstructor() throws IOException {
        hs = new HighScores("Dinosaur");
        assertEquals(3, hs.getHighScores().size());
        assertDoesNotThrow(() -> {
            new HighScores("Dinosaur");
        });
        assertDoesNotThrow(() -> {
            new HighScores("dafasdf");
        });
    }

    @Test
    void testScores() throws IOException {
        hs = new HighScores("Dinosaur");

        List<String> lines = Files.readAllLines(Paths.get("data/Properties/DINOSAUR-SCORES.properties"));
        assertEquals("1=TRex - 10000", lines.get(0));

        hs.addScore(new Score("asteroid", 10001));
        assertEquals(10001, hs.getHighScores().get(0).getScore());

        hs.saveHighScores();
        lines = Files.readAllLines(Paths.get("data/Properties/DINOSAUR-SCORES.properties"));
        assertEquals("1=asteroid - 10001", lines.get(0));

        hs.removeScore(0);
        hs.saveHighScores();
        lines = Files.readAllLines(Paths.get("data/Properties/DINOSAUR-SCORES.properties"));
        assertEquals("1=TRex - 10000", lines.get(0));
    }
}
