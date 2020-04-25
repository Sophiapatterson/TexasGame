package ooga.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This is the abstract class for all of the "Screen" classes. It contains all of the constants that are used by most or all subclasses. It also contains methods that are used by most or all subclasses.
 */
public abstract class Screen {

    public static final String DEFAULT_STYLING = "Styling/Screen.css";
    public static final String DARK_STYLING = "Styling/dark-theme.css";
    public static final int SCREEN_WIDTH = 850;
    public static final int SCREEN_HEIGHT = 600;
    public static final int DEFAULT_NODE_SPACING = 25;
    public static final int DEFAULT_TOP_PADDING = 10;
    public static final int DEFAULT_BOTTOM_PADDING = 50;
    public static final int DEFAULT_LEFT_PADDING = 50;
    public static final int DEFAULT_RIGHT_PADDING = 50;
    public static final Paint BACKGROUND = Color.AZURE;
    public static final Color SCREEN_COLOR = Color.GOLD;
    private static boolean isDarkMode;

    /**
     * Creates the main screen for each class.
     * @param currentstage
     * @return
     */
    public Scene createMainScreen(Stage currentstage){
        return null;
    }

    /**
     * Initializes the layout for each scene.
     * @param layout
     */
    public void initLayout(VBox layout){
        layout.setPadding(new Insets(DEFAULT_TOP_PADDING, DEFAULT_RIGHT_PADDING, DEFAULT_BOTTOM_PADDING, DEFAULT_LEFT_PADDING));
        layout.setSpacing(DEFAULT_NODE_SPACING);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(SCREEN_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        if (isDarkMode){
            layout.getStylesheets().add(DARK_STYLING);
        } else {
            layout.getStylesheets().add(DEFAULT_STYLING);
        }
    };

    /**
     * Initializes the title for each scene.
     * @return
     */
    public Text initTitle(){
        Text title = new Text();
        title.setId("Title");
        return title;
    }

    /**
     * Creates the image for buttons that use an image, such as the ones in the game chooser.
     * @param gameimage
     * @return
     */
    public ImageView createButtonImage(Image gameimage){
        ImageView imageview = new ImageView(gameimage);
        imageview.setFitHeight(50);
        imageview.setFitWidth(50);
        return imageview;
    };

    /**
     * Sets the screens to dark mode
     */
    public void setDarkModeTrue() {
        isDarkMode = true;
    }

    /**
     * Creates buttons with images.
     * @param buttontext
     * @param id
     * @param buttonimage
     * @return
     */
    public Button createButtonWithImage(String buttontext, String id, Image buttonimage){
        Button createdbutton = new Button(buttontext);
        createdbutton.setId(id);
        createdbutton.setGraphic(createButtonImage(buttonimage));
        return createdbutton;
    }

    /**
     * Creates buttons without images.
     * @param buttontext
     * @param id
     * @return
     */
    public Button createButton(String buttontext, String id){
        Button createdbutton = new Button(buttontext);
        createdbutton.setId(id);
        return createdbutton;
    }

}
