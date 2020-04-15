package ooga.Screens;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public abstract class Screen {

    public abstract void initLayout(VBox layout);

    public Text initTitle(){
        Text title = new Text();
        title.setId("Title");
        return title;
    }

    public ImageView createButtonImage(Image gameimage){
        ImageView imageview = new ImageView(gameimage);
        imageview.setFitHeight(50);
        imageview.setFitWidth(50);
        return imageview;
    };

}
