package ooga.usecases;

public class GameManager {
    int score;

    public GameManager() {
        score = 0;
    }

    public void addScore(int amount) {
        score += amount;
    }
}
