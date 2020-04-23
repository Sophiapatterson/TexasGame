package ooga.View;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BirdPlayerView extends PlayerView{
    private ImageView playerImage;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();

    public BirdPlayerView(Image playerImage, double x, double y){
        initializeView(playerImage);
        this.playerImage.xProperty().bind(this.x);
        this.playerImage.yProperty().bind(this.y);
    }

    private void initializeView(Image playerImage) {
        this.playerImage = new ImageView(playerImage);
        this.playerImage.setFitWidth(50);
        this.playerImage.setFitHeight(50);
        this.playerImage.setPreserveRatio(true);
        this.playerImage.visibleProperty();
    }

    public void setProperties(ooga.engine.flappy.BirdPlayer bird){
        x.bindBidirectional(bird.getXProperty());
        y.bindBidirectional(bird.getYProperty());
    }

    public ImageView getPlayerImage(){
        return playerImage;
    }
}
