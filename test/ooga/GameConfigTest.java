package ooga;

import ooga.data.LevelFileException;
import ooga.data.config.*;
import ooga.engine.game.Enemy;
import ooga.engine.game.Powerup;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameConfigTest {
    GameConfiguration config;
    List<Enemy> enemies;
    List<Powerup> powerups;
    double length;
    Map<Integer, Double> enemyLocations;
    Map<Integer, Double> coinLocations;


    @Test
    void testCacti() throws IOException {
        String data = "data/CSV configurations/Dinosaur_Level.csv";
        config = new DinoGameConfiguration(Paths.get(data));
        enemies = config.getEnemies();
        parse(data);
        assertEquals(enemyLocations.size(), enemies.size());
        assertEquals(enemyLocations.get(1), enemies.get(0).getXPos(), 0.1);
        assertEquals(enemyLocations.get(11), enemies.get(10).getXPos(), 0.1);
        assertEquals(enemyLocations.get(119), enemies.get(118).getXPos(), 0.1);
    }

    @Test
    void testPipes() throws IOException {
        config = new FlappyGameConfiguration(Paths.get("data/CSV configurations/Flappy_Level.csv"));
        enemies = config.getEnemies();
        parse("data/CSV configurations/Flappy_Level.csv");
        assertEquals(enemyLocations.size(), enemies.size());
        assertEquals(enemyLocations.get(1), enemies.get(0).getXPos(), 0.1);
        assertEquals(enemyLocations.get(11), enemies.get(10).getXPos(), 0.1);
        assertEquals(enemyLocations.get(119), enemies.get(118).getXPos(), 0.1);
    }

    @Test
    void testZappers() throws IOException {
        config = new JetpackGameConfiguration(Paths.get("data/CSV configurations/Jetpack_Level.csv"));
        enemies = config.getEnemies();
        parse("data/CSV configurations/Jetpack_Level.csv");
        assertEquals(enemyLocations.size(), enemies.size());
        assertEquals(enemyLocations.get(1), enemies.get(0).getXPos(), 0.1);
        assertEquals(enemyLocations.get(11), enemies.get(10).getXPos(), 0.1);
        assertEquals(enemyLocations.get(81), enemies.get(80).getXPos(), 0.1);
    }

    @Test
    void testCoins(){
        config = new JetpackGameConfiguration(Paths.get("data/CSV configurations/Jetpack_Level.csv"));
        parse("data/CSV configurations/Jetpack_Level.csv");
        powerups = config.getPowerups();
        assertEquals(coinLocations.size(), powerups.size());
        assertEquals(coinLocations.get(1), powerups.get(0).getXPos(), 0.1);
        assertEquals(coinLocations.get(11), powerups.get(10).getXPos(), 0.1);
        assertEquals(coinLocations.get(81), powerups.get(80).getXPos(), 0.1);
    }


    @Test
    void testGenericConfig(){
        config = new GenericGameConfiguration("ooga.engine.generic.DINO_GameRules");
        enemies = config.getEnemies();
        parse("data/CSV configurations/Dinosaur_Level.csv");
        assertEquals(enemyLocations.size(), enemies.size());
        assertEquals(enemyLocations.get(1), enemies.get(0).getXPos(), 0.1);
        assertEquals(enemyLocations.get(2), enemies.get(1).getXPos(), 0.1);
        assertEquals(enemyLocations.get(3), enemies.get(2).getXPos(), 0.1);
    }

    @Test
    void testConfigErrors(){
        assertDoesNotThrow(() -> {
            new JetpackGameConfiguration(Paths.get("NOT-A-REAL-PATH"));
        });
        assertDoesNotThrow(() -> {
            new JetpackGameConfiguration(Paths.get("data/CSV configurations/Empty_Level.csv"));
        });
        assertDoesNotThrow(() -> {
            new JetpackGameConfiguration(Paths.get("data/CSV configurations/Empty_Elements_Level.csv"));
        });
        assertDoesNotThrow(() -> {
            new JetpackGameConfiguration(Paths.get("data/CSV configurations/No_Length_Level.csv"));
        });
    }


    void parse(String path){
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e){
            throw new LevelFileException(e);
        }
        String[] array;
        length = Integer.parseInt(lines.get(0));
        lines.remove(0);
        int countCol = 0;
        int totalRow = lines.size();
        int countRow = 0;
        int totalCols = 0;
        double xCoef;
        double yCoef;
        String val;
        int coinCount = 1;
        int enemyCount = 1;
        enemyLocations =  new HashMap<Integer, Double>();
        coinLocations = new HashMap<Integer, Double>();

        for(int i = 0; i<lines.size(); i++){
            array = lines.get(i).split(",");
            if(array.length == 0) break;
            totalCols = array.length;
            for(String element: array){
                if(element.isEmpty()) break;
                val = element;
                xCoef= (double)countCol/totalCols;
                yCoef = (double)countRow/totalRow;

                if(val.equals("1")){
                    enemyLocations.put(enemyCount,xCoef*length);
                    enemyCount++;
                }

                if(val.equals("3")){
                    coinLocations.put(coinCount, xCoef*length);
                    coinCount++;
                }

                countCol++;
            }
            countCol=0;
            countRow++;
        }
    }
}
