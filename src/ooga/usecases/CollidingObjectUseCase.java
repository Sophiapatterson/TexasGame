package ooga.usecases;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class CollidingObjectUseCase  {
    Player p1 = new Player();

    public void performUseCase(Rectangle r){
        if(Shape.intersect(p1, r).getBoundsInLocal().getWidth()!=-1){
            p1.collide(r);
        }
    }
}
