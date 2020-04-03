package ooga.player;

/**
 * For objects that can be controlled with key presses
 */
public interface Controllable {
    /**
     * Given a key press, move object left.
     */
    public void moveLeft();

    /**
     * Given a key press, move object right.
     */
    public void moveRight();

    /**
     * Given a key press, move object up.
     */
    public void jump();

}
