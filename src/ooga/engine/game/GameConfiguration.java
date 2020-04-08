package ooga.engine.game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GameConfiguration {

    private ArrayList<Scrolling> scrollers;
    private int length;
    private boolean difficulty;

    public GameConfiguration(Path path) throws IOException {

        scrollers = new ArrayList<>();
        List<String> lines = null;

        try {
            lines = Files.readAllLines(path);
        } catch (IOException e){
            throw new IOException("your level configuration file couldn't be read",e);
        }

        String[] array;
        length = Integer.parseInt(lines.get(0));
        lines.remove(0);

        int countCol = 0;
        int totalRow = lines.size();
        int countRow = 0;
        int totalCols = 0;
        int xCoord;
        int yCoef;

        for(int i = 0; i<lines.size(); i++){
            array = lines.get(i).split(",");
            if(array.length == 0) break;
            totalCols = array.length;
            for(String element: array){
                if(element.isEmpty()) break;
                xCoord = (countCol/totalCols) * length;
                yCoef = countRow/totalRow;

                //scrollers.add(new GameObject(xCoord, yCoef))

                countCol++;
            }
            countRow++;
        }

    }

}
