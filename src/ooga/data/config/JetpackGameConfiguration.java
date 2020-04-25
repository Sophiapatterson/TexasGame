package ooga.data.config;

import ooga.data.LevelFileException;
import ooga.engine.game.Coin;
import ooga.engine.game.Enemy;
import ooga.engine.game.Powerup;
import ooga.engine.game.Scrolling;
import ooga.engine.jetpack.Zapper;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
/**
 * JetpackGameConfiguration extends parent GameConfiguration class.
 * Reads in CSV file and sets up game model accordingly for JetpackGame.
 * Initializes zappers, coins, scrolling objects.
 */
public class JetpackGameConfiguration extends GameConfiguration {
    private List<Scrolling> scrollers;
    private List<Enemy> allEnemies;
    private List<Powerup> allPU;
    private int length;

    /**
     * JetpackGameConfiguration constructor.
     * @param path
     * @throws LevelFileException
     */
    public JetpackGameConfiguration(Path path) throws LevelFileException {
        scrollers = new ArrayList<>();
        allEnemies = new ArrayList<>();
        allPU = new ArrayList<>();
        List<String> lines = getLines(path);
        length = getLength(lines);
        parseCSV(lines);
    }
    /**
     * makeCoin method initializes Coins in JetpackGame.
     * @param xCoef double X value used to initialize position.
     * @param yCoef double Y value used to initialize position.
     */
    @Override
    public void makeCoin(double xCoef, double yCoef) {
        Coin pu = new Coin(xCoef*length, yCoef*600);
        scrollers.add(pu);
        allPU.add(pu);
    }
    /**
     * makeEnemy method initializes Zappers in JetpackGame.
     * @param xCoef double X value used to initialize position.
     * @param yCoef double Y value used to initialize position.
     */
    @Override
    public void makeEnemy(double xCoef, double yCoef){
        Zapper z = new Zapper();
        z.setXPos(xCoef*length);
        z.setYPos(yCoef*600);
        scrollers.add(z);
        allEnemies.add(z);
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
