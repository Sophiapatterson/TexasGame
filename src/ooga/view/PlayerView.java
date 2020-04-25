package ooga.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.engine.game.Enemy;
import ooga.engine.game.Player;
import ooga.engine.game.Powerup;
/**
 * PlayerView extends parent View class. Initializes View for Player
 * objects and binds PlayerView to Player objects.
 */
public class PlayerView extends View{
    public static final double PLAYER_SIZE = 50;
    private ImageView myImage;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();
    /**
     * PlayerView constructor used to create View for any type of player.
     * @param playerImage image of player object's sprite.
     * @param x double value to initialize x DoubleProperty
     * @param y double value to initialize y DoubleProperty
     */
    public PlayerView(Image playerImage, double x, double y){
        super();
        this.myImage = initializeView(playerImage);
        setWidthAndHeight(PlayerView.PLAYER_SIZE,PlayerView.PLAYER_SIZE);
        this.myImage.xProperty().bind(this.x);
        this.myImage.yProperty().bind(this.y);
    }
    /**
     * setPlayerProperties used to bind X and Y DoubleProperties of this PlaeyrView to those of respective backend Player.
     * @param player Player object that an instance of PlayerView will bind to.
     */
    public void setPlayerProperties(Player player) {
        x.bindBidirectional(player.getXProperty());
        y.bindBidirectional(player.getYProperty());
    }

    @Override
    public void setPowerupProperties(Powerup powerup) { }

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

}
