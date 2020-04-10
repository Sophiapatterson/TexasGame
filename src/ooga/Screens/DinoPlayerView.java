package ooga.Screens;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DinoPlayerView {
    private ImageView playerImage;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();

    public DinoPlayerView(Image playerImage, double x, double y){
        this.playerImage = new ImageView(playerImage);
        this.playerImage.setFitWidth(50);
        this.playerImage.setFitHeight(50);
        this.playerImage.setPreserveRatio(true);
        this.playerImage.visibleProperty();
        this.playerImage.xProperty().bind(this.x);
        this.playerImage.yProperty().bind(this.y);
    }

    public void setProperties(ooga.engine.dinosaur.DinoPlayer dino){
        x.bindBidirectional(dino.getXProperty());
        y.bindBidirectional(dino.getYProperty());
    }

    public ImageView getPlayerImage(){
        return playerImage;
    }

}
