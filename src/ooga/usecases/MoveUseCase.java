package ooga.usecases;

import javafx.scene.input.KeyCode;

public class MoveUseCase {
    Player p1 = new Player();
    PlayerHandler playerHandler = new PlayerHandler(p1);

    public void performUseCase() {
        playerHandler.handleKey(KeyCode.RIGHT);
        playerHandler.handleKey(KeyCode.LEFT);
    }
}
