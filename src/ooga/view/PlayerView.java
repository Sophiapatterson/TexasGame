package ooga.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.engine.game.Enemy;
import ooga.engine.game.Player;
import ooga.engine.game.Powerup;

public class PlayerView extends View{
    public static final double PLAYER_SIZE = 50;
    private ImageView myImage;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();

    public PlayerView(Image playerImage, double x, double y){
        super();
        this.myImage = initializeView(playerImage);
        setWidthAndHeight(PlayerView.PLAYER_SIZE,PlayerView.PLAYER_SIZE);
        this.myImage.xProperty().bind(this.x);
        this.myImage.yProperty().bind(this.y);
    }

    public void setPlayerProperties(Player player) {
        x.bindBidirectional(player.getXProperty());
        y.bindBidirectional(player.getYProperty());
    }

    @Override
    public void setPowerupProperties(Powerup powerup) { }

    @Override
    public void setEnemyProperties(Enemy enemy) { }

    @Override
    public ImageView getView() {
        return myImage;
    }

}
