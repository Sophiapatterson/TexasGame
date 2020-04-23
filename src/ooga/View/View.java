package ooga.View;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.ImageView;

public abstract class View {
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();
    private ImageView myView;

//can you make this abstract? or if not, what to do with the getter method?
    public ImageView getView(){
        return myView;
    }

    public void setWidthAndHeight(double width, double height) {
        myView.setFitWidth(width);
        myView.setFitHeight(height);

    }
}
