package ooga.data;

import java.io.*;
import java.util.*;

public class HighScores {
    private ResourceBundle scores;
    private List<Score> list;
    private String version;
    private String filepath;

    public HighScores(String version){
        this.version = version;
        //TODO write and access at same location
        filepath = "ooga.data.Properties."+this.version.toUpperCase()+"-HighScores";
        scores = ResourceBundle.getBundle(filepath);
        list = new ArrayList<>();
        makeList();
    }

    private void makeList(){
        for(int i = 1; i<= scores.keySet().size(); i++){
            String key = String.format("%d", i);
            String value = scores.getString(key);
            list.add(new Score(value));
        }
        list.sort(Score.SCORE_COMPARATOR);
    }

    public void addScore(Score newScore){
        list.add(newScore);
        list.sort(Score.SCORE_COMPARATOR);
    }

    public List<Score> getHighScores(){
        return list;
    }

    public List<String> getHighScoresAsStrings(){
        List<String> stringList = new ArrayList();
        for(int i = 0; i<list.size(); i++){
            stringList.add(list.get(i).toString());
        }
        return stringList;
    }

    public void saveHighScores(){
        try {
            //TODO write and access at same location
            PrintWriter output = new PrintWriter("data/Properties/DINOSAUR.properties");
            for(int i = 0; i < getHighScores().size(); i++){
                String str = getHighScores().get(i).toString();
                output.write(""+(i+1)+"="+str+"\n");
            }
            output.close();
        }
        catch(IOException e){
            //TODO replace with custom exceptions
            throw new RuntimeException("prop files couldn't be saved at IV filepath", e);
        }
    }
}
