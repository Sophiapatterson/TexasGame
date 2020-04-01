package ooga.engine;

public interface Collidable {
    /**
     * object will stop movement when colliding with another object that implements Collidable
     */
    public void collide();
}
