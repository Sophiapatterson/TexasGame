package ooga.engine.game;

import javafx.scene.Group;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Tutorial {

    public static final int TEXT_X = 50;
    public static final int TEXT_Y = 100;
    public static final int GAMEOVERDISTANCE = 700;
    public static final int FIRST_TEXT = 0;
    public static final int SECOND_TEXT = 1;
    public static final int THIRD_TEXT = 2;
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
        }
    }
}
