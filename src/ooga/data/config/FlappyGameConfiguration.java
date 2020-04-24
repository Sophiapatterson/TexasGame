package ooga.data.config;

import ooga.data.LevelFileException;
import ooga.engine.flappy.Pipe;
import ooga.engine.flappy.Pipe2;
import ooga.engine.flappy.Pipe3;
import ooga.engine.game.Enemy;
import ooga.engine.game.Powerup;
import ooga.engine.game.Scrolling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlappyGameConfiguration extends GameConfiguration {
    private List<Scrolling> scrollers;
    private List<Enemy> allEnemies;
    private List<Powerup> allPU;
    private int length;
    private final int COUNT_OF_PIPES = 3;

    public FlappyGameConfiguration(Path path) throws LevelFileException{
        scrollers = new ArrayList<>();
        allEnemies = new ArrayList<>();
        allPU = new ArrayList<>();
        List<String> lines = getLines(path);
        length = getLength(lines);
        parseCSV(lines);
    }

    @Override
    public void makeCoin(double xCoef, double yCoef) {
        //commented out coins because we don't need coins in flappy game
//        Coin pu = new Coin(xCoef*length, yCoef*600);
//        pu.setYPos(FlappyGameWorld.FLOOR_HEIGHT/2);
//        scrollers.add(pu);
//        allPU.add(pu);
    }

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
