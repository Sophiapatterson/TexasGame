package ooga.data;

import ooga.engine.dinosaur.Cactus;
import ooga.engine.dinosaur.DinoGameWorld;
import ooga.engine.flappy.Pipe;
import ooga.engine.game.Coin;
import ooga.engine.game.Enemy;
import ooga.engine.game.Powerup;
import ooga.engine.game.Scrolling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FlappyGameConfiguration extends GameConfiguration {
    private List<Scrolling> scrollers;
    private List<Enemy> allEnemies;
    private List<Powerup> allPU;
    private int length;

    public FlappyGameConfiguration(Path path) throws IOException {
        super(path);
    }

    @Override
    protected void makeEnemy(double xCoef){
        Pipe p = new Pipe(500, -61);
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
