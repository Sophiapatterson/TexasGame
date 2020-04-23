package ooga.View;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.ImageView;

public class PlayerView extends View{
    private ImageView myPlayerView;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();

    public void setProperties(ooga.engine.flappy.BirdPlayer bird){
        x.bindBidirectional(bird.getXProperty());
        y.bindBidirectional(bird.getYProperty());
    }


//    public ImageView getPlayerImage(){
//        return myPlayerView;
//    }

    //should be removed bc in View
//    public void setWidthAndHeight(double width, double height) {
//        myPlayerView.setFitWidth(width);
//        myPlayerView.setFitHeight(height);


}
