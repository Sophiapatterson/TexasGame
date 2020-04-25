package ooga.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.engine.game.Enemy;
import ooga.engine.game.Player;
import ooga.engine.game.Powerup;
/**
 * PowerupView extends parent View class. Initializes View for Powerup
 * objects and binds PowerupView to Powerup objects.
 */
public class PowerupView extends View {
    private ImageView myImage;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();
    /**
     * PowerupView constructor used to create View for any type of Powerup.
     * @param powerupImage image of Powerup object's sprite.
     * @param x double value to initialize x DoubleProperty
     * @param y double value to initialize y DoubleProperty
     */
    public PowerupView(Image powerupImage, double x, double y) {
        super();
        this.myImage = initializeView(powerupImage);
        this.myImage.xProperty().bind(this.x);
        this.myImage.yProperty().bind(this.y);
    }

    /**
     * setPowerupProperties used to bind X and Y DoubleProperties of this PowerupView to those of respective backend powerup.
     * @param powerup Powerup object that an instance of PowerupView will bind to.
     */
    public void setPowerupProperties(Powerup powerup) {
        x.bindBidirectional(powerup.getXProperty());
        y.bindBidirectional(powerup.getYProperty());
    }

    @Override
    public void setEnemyProperties(Enemy enemy) { }
    /**
     * getter method for this instance of view
     * @return ImageView myImage
     */
    @Override
    public ImageView getView() {
        return myImage;
    }

    @Override
    public void setPlayerProperties(Player player) { }
}
