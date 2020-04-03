package ooga.usecases;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class CollidePowerUpUseCase {
    Player p1 = new Player();

    public void performUseCase(Circle c){
        if(Shape.intersect(p1, c).getBoundsInLocal().getWidth()!=-1){
            p1.collide(c);
        }
    }
}
