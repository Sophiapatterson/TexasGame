package ooga.screens;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.engine.generic.GenericPlayer;

public class GenericPlayerView {

    private ImageView playerImage;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();

    public GenericPlayerView(Image playerImage, double x, double y){
        this.playerImage = new ImageView(playerImage);
        this.playerImage.setFitWidth(50);
        this.playerImage.setFitHeight(50);
        this.playerImage.setPreserveRatio(true);
        this.playerImage.visibleProperty();
        this.playerImage.xProperty().bind(this.x);
        this.playerImage.yProperty().bind(this.y);
    }

    public void setProperties(GenericPlayer character){
        x.bindBidirectional(character.getXProperty());
        y.bindBidirectional(character.getYProperty());
    }

    public ImageView getPlayerImage(){
        return playerImage;
    }
}
