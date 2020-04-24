package ooga.data.config;

import ooga.data.LevelFileException;
import ooga.engine.dinosaur.Cactus;
import ooga.engine.game.Enemy;
import ooga.engine.game.Powerup;
import ooga.engine.game.Scrolling;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * DinoGameConfiguration extends parent GameConfiguration class.
 * Reads in CSV file and sets up game model accordingly for DinoGame.
 * Initializes cacti, scrolling objects.
 */
public class DinoGameConfiguration extends GameConfiguration {
    private List<Scrolling> scrollers;
    private List<Enemy> allEnemies;
    private List<Powerup> allPU;
    private int length;

    public DinoGameConfiguration(Path path) throws LevelFileException {
        scrollers = new ArrayList<>();
        allEnemies = new ArrayList<>();
        allPU = new ArrayList<>();
        List<String> lines = getLines(path);
        length = getLength(lines);
        parseCSV(lines);
    }

    @Override
    public void makeCoin(double xCoef, double yCoef) {
        //commented out coins because we don't need coins in dino game
//        Coin pu = new Coin(xCoef*length, yCoef*600);
//        pu.setYPos(DinoGameWorld.FLOOR_HEIGHT);
//        scrollers.add(pu);
//        allPU.add(pu);
    }

    @Override
    public void makeEnemy(double xCoef, double yCoef){
        Cactus c = new Cactus();
        c.setStandardY();
        c.setXPos(xCoef*length);
        scrollers.add(c);
        allEnemies.add(c);
    }

    public List<Scrolling> getScrollers() {
        return scrollers;
    }

    public List<Enemy> getEnemies() {
        return allEnemies;
    }

    public List<Powerup> getPowerups() {
        return allPU;
    }

}
