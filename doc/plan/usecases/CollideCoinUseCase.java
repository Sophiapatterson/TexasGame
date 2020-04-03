package ooga.usecases;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CollideCoinUseCase {
    Player p1 = new Player();
    GameManager manager = new GameManager();

    public void performUseCase(Circle c){
        if(Shape.intersect(p1, c).getBoundsInLocal().getWidth()!=-1){
            manager.addScore(10);
        }
    }
}
