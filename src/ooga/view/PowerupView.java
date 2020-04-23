package ooga.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PowerupView extends View {
    private ImageView powerupImage;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();

    public PowerupView(Image powerupImage, double x, double y) {
        //what is the purpose of this
//        super();
        initializeView(powerupImage);
        this.powerupImage.xProperty().bind(this.x);
        this.powerupImage.yProperty().bind(this.y);
    }

    public void setProperties(ooga.engine.game.Powerup powerup){
        x.bindBidirectional(powerup.getXProperty());
        y.bindBidirectional(powerup.getYProperty());
    }

    @Override
    public ImageView getView() {
        return powerupImage;
    }
}
