package ooga.View;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PowerupView extends View {
    private ImageView powerupImage;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();

    public PowerupView(Image image, double x, double y) {
        super();
        this.powerupImage = new ImageView(image);
        this.powerupImage.setPreserveRatio(true);
        this.powerupImage.visibleProperty();
        this.powerupImage.xProperty().bind(this.x);
        this.powerupImage.yProperty().bind(this.y);
    }

//    @Override
    public void setProperties(ooga.engine.game.Powerup powerup){
        x.bindBidirectional(powerup.getXProperty());
        y.bindBidirectional(powerup.getYProperty());
    }

}
