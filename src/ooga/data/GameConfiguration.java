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
    private List<Scrolling> scrollers;
    private List<Enemy> allEnemies;
    private List<Powerup> allPU;
    private int length;

    public GameConfiguration(Path path) throws IOException {

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

    private void parseCSV(List<String> lines) {
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

    protected void makeCoin(double xCoef) {
        Coin pu = new Coin();
        pu.setX(xCoef*length);
        //cac.setY(cac.getY()*yCoef);
        scrollers.add(pu);
        allPU.add(pu);
    }

    protected void makeEnemy(double xCoef) {
        Enemy e = new Cactus(500, 0);
        e.setXPos(xCoef*length);
        //cac.setY(cac.getY()*yCoef);
        scrollers.add(e);
        allEnemies.add(e);
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
