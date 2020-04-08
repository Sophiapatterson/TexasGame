package ooga;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main extends Application {
    /**
     * Start of the program.
     */
    private static Stage myStage;
    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        myStage = primaryStage;
        myStage.setTitle("Texas");
        Screens startMenu = new Screens();
        Scene firstscene = startMenu.createStartScreen(myStage);
        myStage.setScene(firstscene);
        myStage.show();
    }
}
