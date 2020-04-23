package ooga.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.engine.game.Powerup;

public class EnemyView extends View{
    private ImageView enemyImage;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();

    public EnemyView(Image enemyImage, double x, double y){
        initializeView(enemyImage);
        setWidthAndHeight(PlayerView.PLAYER_SIZE,PlayerView.PLAYER_SIZE);
        this.enemyImage.xProperty().bind(this.x);
        this.enemyImage.yProperty().bind(this.y);
    }

    public void setProperties(ooga.engine.game.Enemy enemy){
        x.bindBidirectional(enemy.getXProperty());
        y.bindBidirectional(enemy.getYProperty());
    }

    @Override
    public ImageView getView() {
        return enemyImage;
    }

    @Override
    public void setProperties(Powerup coin) {

    }


}
