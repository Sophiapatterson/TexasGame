package ooga.data.config;

import ooga.data.LevelFileException;
import ooga.engine.flappy.Pipe;
import ooga.engine.flappy.Pipe2;
import ooga.engine.flappy.Pipe3;
import ooga.engine.game.Enemy;
import ooga.engine.game.Powerup;
import ooga.engine.game.Scrolling;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * FlappyGameConfiguration extends parent GameConfiguration class.
 * Reads in CSV file and sets up game model accordingly for FlappyGame.
 * Initializes three different variants of pipes, scrolling objects.
 */
public class FlappyGameConfiguration extends GameConfiguration {
    private List<Scrolling> scrollers;
    private List<Enemy> allEnemies;
    private List<Powerup> allPU;
    private int length;
    private final int COUNT_OF_PIPES = 3;

    /**
     * FlappyGameConfiguration constructor.
     * @param path
     * @throws LevelFileException
     */
    public FlappyGameConfiguration(Path path) throws LevelFileException{
        scrollers = new ArrayList<>();
        allEnemies = new ArrayList<>();
        allPU = new ArrayList<>();
        List<String> lines = getLines(path);
        length = getLength(lines);
        parseCSV(lines);
    }
    /**
     * makeCoin method initializes Coins in FlappyGame.
     * @param xCoef double X value used to initialize position.
     * @param yCoef double Y value used to initialize position.
     */
    @Override
    public void makeCoin(double xCoef, double yCoef) {
        //commented out coins because we don't need coins in flappy game
//        Coin pu = new Coin(xCoef*length, yCoef*600);
//        pu.setYPos(FlappyGameWorld.FLOOR_HEIGHT/2);
//        scrollers.add(pu);
//        allPU.add(pu);
    }
    /**
     * makeEnemy method initializes Pipes in FlappyGame.
     * @param xCoef double X value used to initialize position.
     * @param yCoef double Y value used to initialize position.
     */
    @Override
    public void makeEnemy(double xCoef, double yCoef){
        Random rand = new Random();
        int pipeNum = rand.nextInt(COUNT_OF_PIPES);
        Enemy p;
        if(pipeNum == 0) {
            p = new Pipe();
        }
        else if(pipeNum == 1) {
            p = new Pipe2();
        }
        else {
            p = new Pipe3();
        }
        p.setStandardY();
        p.setXPos(xCoef*length);
        scrollers.add(p);
        allEnemies.add(p);
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
