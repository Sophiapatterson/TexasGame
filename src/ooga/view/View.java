package ooga.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.engine.game.Powerup;

public abstract class View {
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();
    private ImageView myImageView;

//can you make this abstract? or if not, what to do with the getter method?
    public abstract ImageView getView();

    public void initializeView(Image objectImage) {
        this.myImageView = new ImageView(objectImage);
        this.myImageView.setPreserveRatio(true);
        this.myImageView.visibleProperty();
    }

    public void setWidthAndHeight(double width, double height) {
        myImageView.setFitWidth(width);
        myImageView.setFitHeight(height);

    }

    public abstract void setProperties(Powerup coin);
}
