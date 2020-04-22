package ooga.engine.game;

import javafx.scene.Group;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Tutorial {

    public static final int TEXT_X = 50;
    public static final int TEXT_Y = 100;
    public Tutorial(){

    }
    public List<Text> createTutorialText(List<String> messages){
        List<Text> tutorialtext = new ArrayList<>();
        for(int i = 0; i<messages.size(); i++){
            Text message = new Text(TEXT_X, TEXT_Y, messages.get(i));
            tutorialtext.add(message);
        }
        return tutorialtext;
    }

    public void tutorialAddRemoveText(Player myPlayer, List<Enemy> enemies, Group root, List<Text> tutorialtext){
        if(myPlayer.getXPos()>enemies.get(0).getXPos() && myPlayer.getXPos()<enemies.get(1).getXPos()){
            if(root.getChildren().contains(tutorialtext.get(0))){
                root.getChildren().remove(tutorialtext.get(0));
            }
            if(!root.getChildren().contains(tutorialtext.get(1))){
                root.getChildren().add(tutorialtext.get(1));
            }
        }
        else if(myPlayer.getXPos()>enemies.get(1).getXPos()){
            if(root.getChildren().contains(tutorialtext.get(1))){
                root.getChildren().remove(tutorialtext.get(1));
            }
            if(!root.getChildren().contains(tutorialtext.get(2))){
                root.getChildren().add(tutorialtext.get(2));
            }
        }
    }
}
