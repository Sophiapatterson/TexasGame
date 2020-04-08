package ooga.data;

import java.net.SecureCacheResponse;
import java.util.Comparator;

public class Score {
    private int score;
    public String version;
    private String name;
    public static final String SCORE_RADIX = " - ";
    public static final Comparator<Score> SCORE_COMPARATOR =
            (Score s1, Score s2)->s2.getScore().compareTo(s1.getScore());

    public Score(String name, int score, String version){
        this.score = score;
        this.name = name;
        this.version = version;
    }

    public Score(String scoreString, String version) {
        String[] array = scoreString.split(SCORE_RADIX);
        int num = Integer.parseInt(array[1]);
        this.version = version;
        score = num;
        name = array[0];
    }

    public Integer getScore(){
        return score;
    }

    @Override
    public String toString(){
       return ""+name+SCORE_RADIX+score;
    }
}
