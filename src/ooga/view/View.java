package ooga.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.engine.game.Player;
import ooga.engine.game.Powerup;

public abstract class View {
//    private DoubleProperty x = new SimpleDoubleProperty();
//    private DoubleProperty y = new SimpleDoubleProperty();
    private ImageView myImage;

    public abstract ImageView getView();

    public ImageView initializeView(Image objectImage) {
        myImage = new ImageView(objectImage);
        myImage.setPreserveRatio(true);
        myImage.visibleProperty();
        return myImage;
    }

    public void setWidthAndHeight(double width, double height) {
        myImage.setFitWidth(width);
        myImage.setFitHeight(height);
    }

    public abstract void setPlayerProperties(Player player);
//        x.bindBidirectional(player.getXProperty());
//        y.bindBidirectional(player.getYProperty());
//    }

    public abstract void setPowerupProperties(Powerup powerup);
//        x.bindBidirectional(powerup.getXProperty());
//        y.bindBidirectional(powerup.getYProperty());
//    }
//
    public abstract void setEnemyProperties(ooga.engine.game.Enemy enemy);
//        x.bindBidirectional(enemy.getXProperty());
//        y.bindBidirectional(enemy.getYProperty());
//    }
}
