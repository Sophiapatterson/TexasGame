package ooga.data;

import java.util.Comparator;

/**
 * Score class is used to define the Score object, calculated throughout
 * the duration of a scrolling platform game, used to calculate HighScores
 * and also to be displayed on a game's leader board.
 */
public class Score {
    private int score;
    private String name;
    public static final String SCORE_RADIX = " - ";
    public static final Comparator<Score> SCORE_COMPARATOR =
            (Score s1, Score s2)->s2.getScore().compareTo(s1.getScore());

    /**
     * Score constructor. This initializes a Score's
     * int score value and String name value.
     * @param name String that represents the name of the person who achieved the score.
     * @param score int that represents the end score after playing a game.
     */
    public Score(String name, int score){
        this.score = score;
        this.name = name;
    }

    /**
     * Score constructor.
     * @param scoreString which is the player's score represented as a string.
     */
    public Score(String scoreString) {
        String[] array = scoreString.split(SCORE_RADIX);
        int num = Integer.parseInt(array[1]);
        score = num;
        name = array[0];
    }

    /**
     * getScore method is a getter method.
     * @return returns score.
     */
    public Integer getScore(){
        return score;
    }

    /**
     * toString method converts a score to a String.
     * @return String containing the player's "name - score".
     */
    @Override
    public String toString(){
       return ""+name+SCORE_RADIX+score;
    }
}
