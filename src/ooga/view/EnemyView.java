package ooga.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.engine.game.Player;
import ooga.engine.game.Powerup;

/**
 * EnemyView extends parent View class. Initializes View for Enemy
 * objects and binds EnemyView to Enemy objects.
 */
public class EnemyView extends View{
    private ImageView myImage;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();

    public EnemyView(Image enemyImage, double x, double y){
        super();
        this.myImage = initializeView(enemyImage);
        this.myImage.xProperty().bind(this.x);
        this.myImage.yProperty().bind(this.y);
    }

    public void setEnemyProperties(ooga.engine.game.Enemy enemy){
        x.bindBidirectional(enemy.getXProperty());
        y.bindBidirectional(enemy.getYProperty());
    }

    @Override
    public ImageView getView() {
        return myImage;
    }

    @Override
    public void setPlayerProperties(Player player) { }

    @Override
    public void setPowerupProperties(Powerup powerup) { }

}
