package riot.account.manager.Model;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.json.simple.parser.ParseException;
import riot.account.manager.Util.CreateSavestate;
import riot.account.manager.Util.Ranks;
import riot.account.manager.Util.ReadSavestate;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Main class of the project
 *
 * @author Chris Simbeck
 */
public class App extends Application {

    //TODO: readme.md verbessern (image)
    //TODO: json verschlüsseln
    //TODO: missing json bug umgehen (neue erstellen und meldung anzeigen mit option ja oder schließen)

    static boolean ranks = false;

    public static void main(String[] args) {


        try {
            ReadSavestate.readSavestate();
        } catch (FileNotFoundException e1) {
            CreateSavestate.createNewSavestate();
            try {
                ReadSavestate.readSavestate();
            } catch (IOException | ParseException e2) {
                e2.printStackTrace();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        if (Ranks.isAvailable()) {
            ranks = true;
        }
        launch(args);
    }


    /**
     * The start function is the main function of the program. It is responsible for
     * loading all of the other classes and setting up all of their connections.
     *
     * @param primaryStage Get the stage of the application
     */
    @Override
    public void start(Stage primaryStage) {
        Updater.updaterThread.start();

        try {
            Scene scene;
            if (ranks) {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/MainView.fxml"));
                scene = new Scene(fxmlLoader.load());
            }else{
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/MissingFilesView.fxml"));
                scene = new Scene(fxmlLoader.load());
            }
            scene.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());
            primaryStage.setTitle("Riot Account Manager");
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(false);
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {
                    CreateSavestate.createSavestate();
                    primaryStage.setIconified(true);
                    try {
                        Updater.updaterThread.join();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
