package ooga.engine.game;


import ooga.engine.game.Player;

/**
 * Handles jumping
 */
public class JumpManager {
    private Player myPlayer;

    public JumpManager(Player player) {
        myPlayer = player;
    }

    /**
     * determines if a player is still in the air
     * @param floorY
     * @return
     */
    public boolean stillJumping(double floorY) {
        return (myPlayer.getY() < floorY);
    }

    public void handleJump(double floorY) {
        if(stillJumping(floorY)) {
            myPlayer.jump();
        }
        else {
            myPlayer.resetJumpStrength();
            myPlayer.setY(floorY);
        }
    }
}
