package ooga.engine.dinosaur;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.engine.game.Enemy;
import ooga.engine.game.Player;

public class Cactus extends Enemy {
    public static final int SPEED = 5;
    private double x;
    private double y;
    //using this private property for binding *later
    //private ObjectProperty<javafx.scene.image.Image> imageProperty = new SimpleObjectProperty<>();

    public Cactus(String filename) {
        super();
        initializeCactusImage(new Image(filename));
        setXPos(500);
        setYPos(DinoGameWorld.FLOOR_HEIGHT + 15);
    }

    private void initializeCactusImage(Image image) {
        this.enemyImage = new ImageView(image);
        this.enemyImage.setFitWidth(50);
        this.enemyImage.setFitHeight(50);
        this.enemyImage.setX(100);
        this.enemyImage.setY(DinoGameWorld.FLOOR_HEIGHT + 15);
        this.enemyImage.setPreserveRatio(true);
        this.enemyImage.visibleProperty();
    }


    @Override
    public boolean collide(Player player) {
        return (this.getEnemyImage().getBoundsInParent().intersects(player.getPlayerImage().getBoundsInParent()));
    }

    @Override
    public void scroll() {
        setXPos(getXPos() - SPEED);
    }

    @Override
    public void setXPos(double x) {
        this.x = x;
        this.enemyImage.setX(x);
    }

    @Override
    public void setYPos(double y){
        this.y = y;
        this.enemyImage.setY(y);
    }

    @Override
    public double getXPos(){ return this.x; }

    @Override
    public double getYPos(){ return this.y; }
}
