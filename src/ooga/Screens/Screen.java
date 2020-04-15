package ooga.Screens;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public abstract class Screen {
    private boolean isDarkMode;
    public abstract void initLayout(VBox layout);

    public Text initTitle(){
        Text title = new Text();
        title.setId("Title");
        return title;
    }

    public boolean getIsDarkMode(){
        return isDarkMode;
    }

    public void setisDarkMode(boolean input){
        isDarkMode = input;
    }

    public void checkandSetDarkMode(Stage myStage) {
        if (getIsDarkMode()){
            myStage.getScene().getStylesheets().add("dark-theme.css");
        }
    }


}
