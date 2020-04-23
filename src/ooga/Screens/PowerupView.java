package ooga.Screens;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PowerupView {
    private ImageView powerupImage;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();

    public PowerupView(Image playerImage, double x, double y){
        this.powerupImage = new ImageView(playerImage);
        this.powerupImage.setPreserveRatio(true);
        this.powerupImage.visibleProperty();
        this.powerupImage.xProperty().bind(this.x);
        this.powerupImage.yProperty().bind(this.y);
    }

    public void setProperties(ooga.engine.game.Powerup powerup){
        x.bindBidirectional(powerup.getXProperty());
        y.bindBidirectional(powerup.getYProperty());
    }

    public ImageView getPowerupImage(){
        return powerupImage;
    }

    public void setWidthAndHeight(double width, double height) {
        powerupImage.setFitWidth(width);
        powerupImage.setFitHeight(height);
    }
}
