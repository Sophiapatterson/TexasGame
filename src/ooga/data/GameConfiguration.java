package ooga.data;

import ooga.engine.dinosaur.Cactus;
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

public abstract class GameConfiguration {

    protected void parseCSV(List<String> lines) {
        String[] array;
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

                if(val == 1){
                    makeEnemy(xCoef);
                }

                if(val == 3){
                    makeCoin(xCoef);
                }

                countCol++;
            }
            countCol=0;
            countRow++;
        }
    }

    public abstract void makeCoin(double xCoef);
    public abstract void makeEnemy(double xCoef);

    //TODO is this allowed idk
    public abstract List<Scrolling> getScrollers();

    public abstract List<Enemy> getEnemies();

    public abstract List<Powerup> getPowerups();
}
