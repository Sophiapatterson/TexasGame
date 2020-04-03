package ooga.engine;

public interface Upgradeable{
    /**
     * object will not stop movement when colliding with another object that implements PowerUp.
     * Instead, if object is the character, character will gain a specific powerup.
     */
    public void powerup();
}
