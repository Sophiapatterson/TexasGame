package ooga.data.config;

import ooga.data.LevelFileException;
import ooga.engine.game.Coin;
import ooga.engine.game.Enemy;
import ooga.engine.game.Powerup;
import ooga.engine.game.Scrolling;
import ooga.engine.generic.GameRules;
import ooga.engine.generic.GenericEnemy;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/**
 * GenericGameConfiguration extends parent GameConfiguration class.
 * Reads in CSV file and sets up game model accordingly for a Generic Game.
 * Initializes enemies, powerups, scrolling objects.
 * Customizable for any scrolling platform game.
 */
public class GenericGameConfiguration extends GameConfiguration {
    private List<Scrolling> scrollers;
    private List<Enemy> allEnemies;
    private List<Powerup> allPU;
    private int length;
    private GameRules rules;
    private String rulesPath;

    /**
     * this class provides a flexible implementation of the GameConfiguration superclass where all
     * relevant details are drawn from properties files. It parses the CSV file to create the different elements of
     * the game. The constructor access the CSV file and the superclass performs the abstracted parsing
     * @param rulesPath - a String that represents the filepath to the appropriate properties file
     * @throws LevelFileException - from the call to getLines when the requested file and the default can't be found
     */
    public GenericGameConfiguration(String rulesPath) throws LevelFileException {
        this.rulesPath = rulesPath;
        rules = new GameRules(rulesPath);
        scrollers = new ArrayList<>();
        allEnemies = new ArrayList<>();
        allPU = new ArrayList<>();
        if(rules.TUTORIAL){
            List<String> lines = getLines(Paths.get(rules.TUTORIAL_CSV));

        }
        else{
            List<String> lines = getLines(Paths.get(rules.LEVEL_CSV));
        }
        List<String> lines = getLines(Paths.get(rules.LEVEL_CSV));
        length = getLength(lines);
        parseCSV(lines);
    }
    /**
     * makeCoin method initializes Coins in GenericGame.
     * @param xCoef double X value used to initialize position.
     * @param yCoef double Y value used to initialize position.
     */
    @Override
    public void makeCoin(double xCoef, double yCoef) {
        if(rules.ALLOW_COINS) {
            Coin pu = new Coin(xCoef * length, yCoef * rules.SCREEN_HEIGHT);
            pu.setYPos(rules.FLOOR_HEIGHT);
            scrollers.add(pu);
            allPU.add(pu);
        }
    }
    /**
     * makeEnemy method initializes Enemy in GenericGame.
     * @param xCoef double X value used to initialize position.
     * @param yCoef double Y value used to initialize position.
     */
    @Override
    public void makeEnemy(double xCoef, double yCoef){
        GenericEnemy c = new GenericEnemy(rulesPath);
        c.setStandardY();
        c.setXPos(xCoef*length);
        scrollers.add(c);
        allEnemies.add(c);
    }
    /**
     * getScrollers method returns list of scrolling objects in game.
     * @return list of scrollers.
     */
    public List<Scrolling> getScrollers() {
        return scrollers;
    }
    /**
     * getEnemies method returns list of enemies in game.
     * @return list of enemies.
     */
    public List<Enemy> getEnemies() {
        return allEnemies;
    }
    /**
     * getPowerups method returns list of powerups in game.
     * @return list of powerups.
     */
    public List<Powerup> getPowerups() {
        return allPU;
    }
}
