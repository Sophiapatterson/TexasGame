package ooga.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.engine.game.Enemy;
import ooga.engine.game.Player;
import ooga.engine.game.Powerup;

public class PowerupView extends View {
    private ImageView myImage;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();

    public PowerupView(Image powerupImage, double x, double y) {
        super();
        this.myImage = initializeView(powerupImage);
        this.myImage.xProperty().bind(this.x);
        this.myImage.yProperty().bind(this.y);
    }

    public void setPowerupProperties(Powerup powerup) {
        x.bindBidirectional(powerup.getXProperty());
        y.bindBidirectional(powerup.getYProperty());
    }

    @Override
    public void setEnemyProperties(Enemy enemy) { }

    @Override
    public ImageView getView() {
        return myImage;
    }

    @Override
    public void setPlayerProperties(Player player) { }
}
