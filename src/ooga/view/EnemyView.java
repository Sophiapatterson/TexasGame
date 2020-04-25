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

    /**
     * EnemyView constructor used to create View for any type of enemy.
     * @param enemyImage image of enemy object's sprite.
     * @param x double value to initialize EnemyView x DoubleProperty
     * @param y double value to initialize EnemyView y DoubleProperty
     */
    public EnemyView(Image enemyImage, double x, double y){
        super();
        this.myImage = initializeView(enemyImage);
        this.myImage.xProperty().bind(this.x);
        this.myImage.yProperty().bind(this.y);
    }

    /**
     * setEnemyProperties used to bind X and Y DoubleProperties of this EnemyView to those of respective backend Enemy.
     * @param enemy Enemy object that an instance of EnemyView will bind to.
     */
    public void setEnemyProperties(ooga.engine.game.Enemy enemy){
        x.bindBidirectional(enemy.getXProperty());
        y.bindBidirectional(enemy.getYProperty());
    }

    /**
     * getter method for this instance of view
     * @return ImageView myImage
     */
    @Override
    public ImageView getView() {
        return myImage;
    }

    @Override
    public void setPlayerProperties(Player player) { }

    @Override
    public void setPowerupProperties(Powerup powerup) { }

}
