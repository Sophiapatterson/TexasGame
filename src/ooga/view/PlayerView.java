package ooga.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.engine.game.Player;
import ooga.engine.game.Powerup;

public class PlayerView extends View{
    public static final double PLAYER_SIZE = 50;
    private ImageView myplayerImage;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();

    public PlayerView(Image playerImage, double x, double y){
        initializeView(playerImage);
        setWidthAndHeight(PlayerView.PLAYER_SIZE,PlayerView.PLAYER_SIZE);
        this.myplayerImage.xProperty().bind(this.x);
        this.myplayerImage.yProperty().bind(this.y);
    }

//why
    public PlayerView() {
    }

    public void setPlayerProperties(Player player){
        x.bindBidirectional(player.getXProperty());
        y.bindBidirectional(player.getYProperty());
    }

    @Override
    public ImageView getView() {
        return myplayerImage;
    }

    @Override
    public void setProperties(Powerup coin) {

    }
}
