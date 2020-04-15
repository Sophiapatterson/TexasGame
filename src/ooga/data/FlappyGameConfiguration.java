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

public class FlappyGameConfiguration {
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

        int countCol = 0;
        int totalRow = lines.size();
        int countRow = 0;
        int totalCols = 0;
        double xCoef;
        int yCoef;
        int val;

        for(int i = 0; i<lines.size(); i++){
            array = lines.get(i).split(",");
            if(array.length == 0) break;
            totalCols = array.length;
            for(String element: array){
                if(element.isEmpty()) break;
                val = Integer.parseInt(element);
                xCoef= (double)countCol/totalCols;
                yCoef = countRow/totalRow;

                //TODO Decide between an approach below: setting locations with ratio or coordinate?
                if(val == 1){
                    Pipe pipe = new Pipe(500, -61);
                    pipe.setXPos(xCoef*length);
                    //cac.setY(cac.getY()*yCoef);
                    scrollers.add(pipe);
                    allEnemies.add(pipe);
                }

                if(val == 3){
                    Coin coin = new Coin();
                    coin.setX(xCoef*length);
                    //cac.setY(cac.getY()*yCoef);
                    scrollers.add(coin);
                    allPU.add(coin);
                }
                //TODO Implement other classes if that's ok design

                countCol++;
            }
            countCol=0;
            countRow++;
        }

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
