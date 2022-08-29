package riot.account.manager.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the change password view.
 *
 * @author Chris Simbeck
 */

public class changePasswortViewController {


    @FXML
    public Label errorLabel;

    @FXML
    public TextField newPasswordField;

    @FXML
    public Button saveButton;

    /**
     * called when the save button is pressed and saves the new password
     * @param event
     */
    @FXML
    void saveButtonPressed(ActionEvent event) {
        if(newPasswordField.getText().equals("")){
        }
        else{
            MainViewController.getSelectedAccount().setPassword(newPasswordField.getText());
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        }
    }

}



