package ooga;

import ooga.data.HighScores;
import ooga.data.Score;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HighScoresTest {
    HighScores hs;

    public void testSetUp() throws IOException {
        hs = new HighScores("Dinosaur");
    }

    @Test
    void testConstructor() throws IOException {
        testSetUp();
        assertEquals(3, hs.getHighScores().size());
    }

    @Test
    void testExportScores() throws IOException {
        testSetUp();
        hs.saveHighScores();
        assertEquals(true, Files.exists(Paths.get("data/Properties/DINOSAUR.properties")));
        List<String> lines = Files.readAllLines(Paths.get("data/Properties/DINOSAUR.properties"));
        assertEquals("1=Sophia - 1234", lines.get(0));
        hs.addScore(new Score("tester", 10000));
        assertEquals(10000, hs.getHighScores().get(0).getScore());
        hs.saveHighScores();
        lines = Files.readAllLines(Paths.get("data/Properties/DINOSAUR.properties"));
        assertEquals("1=tester - 10000", lines.get(0));
    }
}
