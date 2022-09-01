package riot.account.manager.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import riot.account.manager.Model.Account;
import riot.account.manager.Util.Ranks;

/**
 * Controller for the add new account view.
 *
 * @author Chris Simbeck
 */
public class addNewAccountViewController {

    @FXML
    public Label errorLabel;
    @FXML
    private TextField nameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private ChoiceBox<String> valoRankChoiceBox;

    @FXML
    private ChoiceBox<String> leagueRankChoiceBox;

    @FXML
    private Button saveButton;

    @FXML TextField tagTextField;

    /**
     * Initializes the controller class. This method is automatically called after the fxml file has been loaded.
     */
    @FXML
    void initialize() {
        valoRankChoiceBox.getItems().add("none");
        valoRankChoiceBox.getItems().addAll(Ranks.getValorantRankArray());
        valoRankChoiceBox.setValue("none");
        leagueRankChoiceBox.getItems().add("none");
        leagueRankChoiceBox.getItems().addAll(Ranks.getLeagueRankArray());
        leagueRankChoiceBox.setValue("none");
    }


    /**
     * This method is called when the save button is clicked.
     * saves the new account to the account list
     * @param event
     */
    @FXML
    void saveButtonPressed(ActionEvent event) {
        if (nameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            errorLabel.setText("Please fill in all fields");
        } else {
            new Account(nameTextField.getText(), tagTextField.getText(), valoRankChoiceBox.getValue(),leagueRankChoiceBox.getValue(), passwordTextField.getText());
        }
    }

}

