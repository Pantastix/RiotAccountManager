package riot.account.manager.Controller;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller for the change password view.
 *
 * @author Chris Simbeck
 */

public class EditAccountViewController {


    @FXML
    public Label errorLabel;

    @FXML
    public Label headder;

    @FXML
    public TextField newPasswordField;
    @FXML
    public TextField newNameField;
    @FXML
    public TextField newTagField;

    @FXML
    public Button saveButton;


    @FXML
    public void initialize() {
        headder.setText("Edit "+MainViewController.getSelectedAccount().getUserName());
    }



    /**
     * called when the save button is pressed and saves the new password
     * @param event
     */
    @FXML
    void saveButtonPressed(ActionEvent event) {
        if(newPasswordField.getText().equals("")&&newNameField.getText().equals("")&&newTagField.getText().equals("")){
            errorLabel.setText("there is no field filled");
            FadeTransition ft = new FadeTransition(Duration.millis(3000), errorLabel);
            ft.setFromValue(1.0);
            ft.setToValue(0);

            ft.play();
        } else{
            if(!newPasswordField.getText().equals("")){
                MainViewController.getSelectedAccount().setPassword(newPasswordField.getText());
            }
            if(!newNameField.getText().equals("")){
                MainViewController.getSelectedAccount().setPublicName(newNameField.getText());
            }
            if(!newTagField.getText().equals("")){
                MainViewController.getSelectedAccount().setTag(newTagField.getText());
            }
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        }
    }

}



