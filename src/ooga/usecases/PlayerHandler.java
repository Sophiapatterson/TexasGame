package ooga.usecases;

import javafx.scene.input.KeyCode;
import ooga.player.KeyHandler;

public class PlayerHandler implements KeyHandler {
    private Player player;

    public PlayerHandler(Player p) {
        player = p;
    }

    @Override
    public void handleKey(KeyCode code) {
        if(code == KeyCode.SPACE) {
            player.jump();
        }
        if(code == KeyCode.RIGHT) {
            player.moveRight();
        }
        if(code == KeyCode.LEFT) {
            player.moveLeft();
        }
    }
}
