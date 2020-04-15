package ooga.data;

import ooga.engine.dinosaur.Cactus;
import ooga.engine.dinosaur.DinoGameWorld;
import ooga.engine.flappy.Pipe;
import ooga.engine.flappy.Pipe2;
import ooga.engine.flappy.Pipe3;
import ooga.engine.game.Coin;
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

    public FlappyGameConfiguration(Path path) throws IOException {
        scrollers = new ArrayList<>();
        allEnemies = new ArrayList<>();
        allPU = new ArrayList<>();
        List<String> lines = null;
        //TODO implement custom exceptions
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e){
            throw new IOException("your level configuration file couldn't be read", e);
        }
        String[] array;
        length = Integer.parseInt(lines.get(0));
        lines.remove(0);
        parseCSV(lines);
    }

    @Override
    public void makeCoin(double xCoef) {
        Coin pu = new Coin();
        pu.setX(xCoef*length);
        //cac.setY(cac.getY()*yCoef);
        scrollers.add(pu);
        allPU.add(pu);
    }

    @Override
    public void makeEnemy(double xCoef){
        Random rand = new Random();
        int pipeNum = rand.nextInt(3);
        System.out.println(pipeNum);
        Enemy p;
        if(pipeNum == 0) {
            p = new Pipe(500, -61);
        }
        else if(pipeNum == 1) {
            p = new Pipe2(500, -61);
        }
        else {
            p = new Pipe3(500, -61);
        }
        p.setXPos(xCoef*length);
        scrollers.add(p);
        allEnemies.add(p);
    }

    //TODO is this allowed idk
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
