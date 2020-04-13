package ooga.Screens;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public abstract class Screen {

    public abstract void initLayout(VBox layout);

    public Text initTitle(){
        Text title = new Text();
        title.setId("Title");
        return title;
    }

}
