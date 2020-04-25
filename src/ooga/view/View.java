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

    public abstract ImageView getView();

    public ImageView initializeView(Image objectImage) {
        myImage = new ImageView(objectImage);
        myImage.setPreserveRatio(true);
        myImage.visibleProperty();
        return myImage;
    }

    public void setWidthAndHeight(double width, double height) {
        myImage.setFitWidth(width);
        myImage.setFitHeight(height);
    }

    public abstract void setPlayerProperties(Player player);

    public abstract void setPowerupProperties(Powerup powerup);

    public abstract void setEnemyProperties(Enemy enemy);

}
