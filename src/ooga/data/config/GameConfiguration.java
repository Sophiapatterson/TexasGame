package ooga.data.config;

import ooga.data.LevelFileException;
import ooga.engine.game.Enemy;
import ooga.engine.game.Powerup;
import ooga.engine.game.Scrolling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
/**
 * GameConfiguration is the parent GameConfiguration class.
 * Reads in CSV file.
 * Includes abstract methods to initialize enemies, powerups, scrolling objects.
 */
public abstract class GameConfiguration {

    public static final String ENEMY_VALUE = "1";
    public static final String COIN_VALUE = "3";
    public static final String DATA_FILE_REGEX = ",";
    public static final int DEFAULT_LENGTH = 30000;
    public static final String DEFAULT_LEVEL_CSV = "data/CSV configurations/Dinosaur_Level.csv";

    protected void parseCSV(List<String> lines) {
        String[] array;
        int countCol = 0;
        int totalRow = lines.size();
        int countRow = 0;
        int totalCols = 0;
        double xCoef;
        double yCoef;
        String val;

        for(int i = 0; i<lines.size(); i++){
            array = lines.get(i).split(DATA_FILE_REGEX);
            if(array.length == 0) break;
            totalCols = array.length;
            for(String element: array){
                if(element.isEmpty()) continue;
                val = element;
                xCoef= (double)countCol/totalCols;
                yCoef = (double)countRow/totalRow;

                if(val.equals(ENEMY_VALUE)){
                    makeEnemy(xCoef, yCoef);
                }

                if(val.equals(COIN_VALUE)){
                    makeCoin(xCoef, yCoef);
                }

                countCol++;
            }
            countCol=0;
            countRow++;
        }
    }

    public int getLength(List<String> lines){
        int length;
        try {
            length = Integer.parseInt(lines.get(0));
            lines.remove(0);
            return length;
        }catch (NumberFormatException numEx){
            System.out.println("Level length couldn't be parsed. Using default length.");
            return DEFAULT_LENGTH;
        }
    }

    public List<String> getLines(Path path){
        List<String> lines = null;
        try {
            lines = Files.readAllLines(path);
            try{
                if(lines.size()==0){
                    lines = Files.readAllLines(Paths.get(DEFAULT_LEVEL_CSV));
                    System.out.println("Level csv couldn't be parsed. Using default csv.");
                }
            }catch (IOException e){
                throw new LevelFileException("The level csv was blank and the default level csv couln't be accessed", e);
            }
        } catch (IOException e){
            try {
                lines = Files.readAllLines(Paths.get(DEFAULT_LEVEL_CSV));
                System.out.println("Level csv couldn't be parsed. Using default csv.");
            } catch (IOException ex) {
                throw new LevelFileException("The level csv and default level csv both couldn't be accessed", ex);
            }
        }
        return lines;
    }

    public abstract void makeCoin(double xCoef, double yCoef);
    public abstract void makeEnemy(double xCoef, double yCoef);
    /**
     * getScrollers method returns list of scrolling objects in game.
     * @return list of scrollers.
     */
    public abstract List<Scrolling> getScrollers();

    public abstract List<Enemy> getEnemies();

    public abstract List<Powerup> getPowerups();
}
