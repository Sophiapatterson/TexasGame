package ooga.data;

import java.io.*;
import java.util.*;

public class HighScores {
    private ResourceBundle scores;
    private List<Score> list;
    private String version;

    public HighScores(String version) {
        this.version = version;

        try{
            scores = fromFile("data/Properties/"+this.version.toUpperCase()+"-SCORES.properties");
        } catch (IOException e){
            this.version = "default";
            try{
                scores = fromFile("data/Properties/DEFAULT-SCORES.properties");
            } catch (IOException e2){
                System.out.println("Couldn't load game scores or default scores. Resources may be corrupted");
            }
        }
        list = new ArrayList<>();
        makeList();
    }

    //sourced from https://gist.github.com/mobleyc/73f1a0036d9ef3fdc7ab
    private static ResourceBundle fromFile(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        try {
            return new PropertyResourceBundle(fis);
        } finally {
            fis.close();
        }
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
            PrintWriter output = new PrintWriter("data/Properties/"+this.version.toUpperCase()+"-SCORES.properties");
            for(int i = 0; i < getHighScores().size(); i++){
                String str = getHighScores().get(i).toString();
                output.write(""+(i+1)+"="+str+"\n");
            }
            output.close();
        }
        catch(IOException e){
            System.out.println("New leaderboard score couldn't be added. Sorry!");
        }
    }
}
