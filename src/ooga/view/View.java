package ooga.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.engine.game.Enemy;
import ooga.engine.game.Player;
import ooga.engine.game.Powerup;

/**
 * View is an abstract parent class which includes
 * generic methods to intitialize View, setWidthAndHeight, and getView.
 */
public abstract class View {

    private ImageView myImage;

    /**
     * abstract getter method inherited by Enemy, Powerup, and PlayerViews. Returns ImageView
     * representing respective Object Views.
     * @return ImageView
     */
    public abstract ImageView getView();

    /**
     * initializeView method used to create ImageView
     * and initialize ratio, visibility of image.
     * @param objectImage image from which to create ImageView
     * @return ImageView initialized in method
     */
    public ImageView initializeView(Image objectImage) {
        myImage = new ImageView(objectImage);
        myImage.setPreserveRatio(true);
        myImage.visibleProperty();
        return myImage;
    }

    /**
     * setWidthandHeight method sets an Imageview's width and height respectively.
     * @param width double for desired width.
     * @param height double for desired height.
     */
    public void setWidthAndHeight(double width, double height) {
        myImage.setFitWidth(width);
        myImage.setFitHeight(height);
    }

    /**
     * Abstract method - Binds Player View to Player backend object.
     * @param player Player backend object for View to bind to.
     */
    public abstract void setPlayerProperties(Player player);
    /**
     * Abstract method - Binds Powerup View to Powerup backend object.
     * @param powerup Player backend object for View to bind to.
     */
    public abstract void setPowerupProperties(Powerup powerup);
    /**
     * Abstract method - Binds Enemy View to Enemy backend object.
     * @param enemy Player backend object for View to bind to.
     */
    public abstract void setEnemyProperties(Enemy enemy);

}
