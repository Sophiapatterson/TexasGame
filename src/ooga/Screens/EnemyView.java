package ooga.Screens;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EnemyView {
    private ImageView enemyImage;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();

    public EnemyView(Image playerImage, double x, double y){
        this.enemyImage = new ImageView(playerImage);
        this.enemyImage.setFitWidth(50);
        this.enemyImage.setFitHeight(50);
        this.enemyImage.visibleProperty();
        this.enemyImage.xProperty().bind(this.x);
        this.enemyImage.yProperty().bind(this.y);
    }

    public void setProperties(ooga.engine.game.Enemy enemy){
        x.bindBidirectional(enemy.getXProperty());
        y.bindBidirectional(enemy.getYProperty());
    }

    public ImageView getEnemyImage(){
        return enemyImage;
    }

    public void setWidthAndHeight(double width, double height) {
        enemyImage.setFitWidth(width);
        enemyImage.setFitHeight(height);
    }

}
