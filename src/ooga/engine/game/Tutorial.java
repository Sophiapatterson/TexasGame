package ooga.engine.game;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tutorial {

    public static final int TEXT_X = 50;
    public static final int TEXT_Y = 100;
    public static int GAMEOVERDISTANCE = 700;
    public static final int FIRST_TEXT = 0;
    public static final int SECOND_TEXT = 1;
    public static final int THIRD_TEXT = 2;

    public Tutorial(){

    }

    public void setFinalDistance(int x){
        GAMEOVERDISTANCE = x;
    }

    public List<Text> createTutorialText(List<String> messages, boolean blacktext){
        List<Text> tutorialtext = new ArrayList<>();
        for(int i = 0; i<messages.size(); i++){
            Text message = new Text(TEXT_X, TEXT_Y, messages.get(i));
            message.setId("message"+i);
            message.setFont(new Font(30));
            if(!blacktext){
                message.setFill(Color.WHITE);
            }
            tutorialtext.add(message);
        }
        return tutorialtext;
    }

    public void tutorialObstacles(Player myPlayer, List<Enemy> enemies, Group root, List<Text> tutorialtext){
        /*if(myPlayer.getXPos()>enemies.get(0).getXPos() && myPlayer.getXPos()<enemies.get(1).getXPos()){
            if(root.getChildren().contains(tutorialtext.get(FIRST_TEXT))){
                root.getChildren().remove(tutorialtext.get(FIRST_TEXT));
            }
            if(!root.getChildren().contains(tutorialtext.get(SECOND_TEXT))){
                root.getChildren().add(tutorialtext.get(SECOND_TEXT));
            }
        }
        else if(myPlayer.getXPos()>enemies.get(1).getXPos()){
            if(root.getChildren().contains(tutorialtext.get(SECOND_TEXT))){
                root.getChildren().remove(tutorialtext.get(SECOND_TEXT));
            }
            if(!root.getChildren().contains(tutorialtext.get(THIRD_TEXT))){
                root.getChildren().add(tutorialtext.get(THIRD_TEXT));
            }
        }*/

        for(int i =0; i<tutorialtext.size() || i<enemies.size() ; i++){
            if(myPlayer.getXPos()>enemies.get(i).getXPos()&& myPlayer.getXPos()<enemies.get(i+1).getXPos()){
                root.getChildren().remove(tutorialtext.get(i));
                if(!root.getChildren().contains(tutorialtext.get(i+1))){
                    root.getChildren().add(tutorialtext.get(i+1));
                }
            }
        }
    }

    public void tutorialPowerUps(Player myPlayer, List<Powerup> powerups, Group root, List<Text> tutorialtext){
        if(myPlayer.getXPos()>powerups.get(0).getXPos() ){
            if(root.getChildren().contains(tutorialtext.get(THIRD_TEXT))) {
                root.getChildren().remove(tutorialtext.get(THIRD_TEXT));
            }
            if(!root.getChildren().contains(tutorialtext.get(tutorialtext.size()-1))){
                root.getChildren().add(tutorialtext.get(tutorialtext.size()-1));
            }
        }
    }
}
