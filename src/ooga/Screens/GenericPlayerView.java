package ooga.Screens;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.engine.generic.GameRules;
import ooga.engine.generic.GenericPlayer;

public class GenericPlayerView {

    private ImageView playerImage;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();

    public GenericPlayerView(Image playerImage, double x, double y, String rulesPath){
        GameRules rules = new GameRules(rulesPath);
        this.playerImage = new ImageView(playerImage);
        this.playerImage.setFitWidth(rules.PLAYER_WIDTH);
        this.playerImage.setFitHeight(rules.PLAYER_HEIGHT);
        this.playerImage.setPreserveRatio(true);
        this.playerImage.visibleProperty();
        this.playerImage.xProperty().bind(this.x);
        this.playerImage.yProperty().bind(this.y);
    }

    public void setProperties(GenericPlayer character){
        x.bindBidirectional(character.getXProperty());
        y.bindBidirectional(character.getYProperty());
    }

    public ImageView getPlayerImage(){
        return playerImage;
    }
}
