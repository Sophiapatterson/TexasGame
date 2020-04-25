package ooga.engine.game;

/**
 * Scrolling interface implemented by all game objects the move across the screen, including enemies and powerups.
 */
public interface Scrolling {
    /**
     * scroll method used to determine rate that object moves across screen in game.
     */
    public void scroll();

    /**
     * getter method for scrolling object's X position. Used in scroll method to set new X position.
     * @return double representing objects X position.
     */
    public double getXPos();
}
