package ooga.engine;

public interface PowerUp {
    /**
     * object will not stop movement when colliding with another object that implements PowerUp.
     * Instead, if object is the character, character will gain a specific powerup.
     */
    public void p1powerup();
}
