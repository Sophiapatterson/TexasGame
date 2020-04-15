package ooga.Screens;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public abstract class Screen {
    public abstract void initLayout(VBox layout);

    public Text initTitle(){
        Text title = new Text();
        title.setId("Title");
        return title;
    }


}
