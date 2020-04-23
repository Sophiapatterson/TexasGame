package ooga.engine;

/**
 * Objects that implement this interface will have movement that is affected by gravity.
 */
public interface Gravity {
    /**
     * object will fall as if being pulled downwards by gravity
     */
    public void accelerate();
}
