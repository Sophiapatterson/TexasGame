package ooga.data;

import java.io.*;
import java.util.*;

/**
 * HighScores class stores old and creates new game high scores.
 */
public class HighScores {
    private ResourceBundle scores;
    private List<Score> list;
    private String version;

    /**
     * HighScores constructor/
     * @param version is the game type for which the high scores are applicable.
     */
    public HighScores(String version) {
        this.version = version;

        try{
            scores = fromFile("data/Properties/"+this.version.toUpperCase()+"-SCORES.properties");
        } catch (IOException e){
            this.version = "default";
            try{
                scores = fromFile("data/Properties/DEFAULT-SCORES.properties");
            } catch (IOException e2){
                throw new LevelFileException("Couldn't load game scores or default scores. Resources may be corrupted", e2);
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

    /**
     * addScore method adds a new score to the list of stored scores and
     * sorts the new score list using the SCORE_COMPARATOR to determine
     * the new score order.
     * @param newScore is the new score being added.
     */
    public void addScore(Score newScore){
        list.add(newScore);
        list.sort(Score.SCORE_COMPARATOR);
    }

    /**
     * removeScore method removes a score from the list of stored scores
     * and sorts the new list of scores using the SCORE_COMPARATOR.
     * @param zeroIndex
     */
    public void removeScore(int zeroIndex){
        list.sort(Score.SCORE_COMPARATOR);
        list.remove(zeroIndex);
        list.sort(Score.SCORE_COMPARATOR);
    }

    /**
     * getHighScores is a getter method for the list of
     * stored high scores for a given game.
     * @return list which is the current list of high scores.
     */
    public List<Score> getHighScores(){
        return list;
    }

    /**
     * getHighScoresAsStrings method returns the list of high scores
     * as a string list.
     * @return stringList which is the high scores list converted to a string list.
     */
    public List<String> getHighScoresAsStrings(){
        List<String> stringList = new ArrayList();
        for(int i = 0; i<list.size(); i++){
            stringList.add(list.get(i).toString());
        }
        return stringList;
    }

    /**
     * saveHighScores method saves the high scores into a Properties file that is
     * stored in the data folder. This data may be retrieved at a later time.
     */
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
