package riot.account.manager.Model;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import riot.account.manager.Util.CreateSavestate;
import riot.account.manager.Util.ReadSavestate;

/**
 * Main class of the project
 *
 * @author Chris Simbeck
 */
public class App extends Application {


    //TODO: Open first account on startup


    public static void main(String[] args){
        ReadSavestate.readSavestate();
        launch(args);
    }


    /**
     * The start function is the main function of the program. It is responsible for
     * loading all of the other classes and setting up all of their connections.

     *
     * @param primaryStage Get the stage of the application
     */
    @Override
    public void start(Stage primaryStage){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/MainView.fxml"));
            Scene scene = new Scene (fxmlLoader.load());
            scene.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());
            primaryStage.setTitle("Riot Account Manager");
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(false);
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {
                    CreateSavestate.createSavestate();
                }
            });

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}
