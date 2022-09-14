package riot.account.manager.Controller;


import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import riot.account.manager.Core.Account;
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
    public Label accountSavedLabel;

    @FXML
    private TextField loginNameTextField;

    @FXML
    private TextField publicNameTextField;

    @FXML
    private TextField leagueNameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private ChoiceBox<String> valoRankChoiceBox;

    @FXML
    private ChoiceBox<String> leagueRankChoiceBoxS;

    @FXML
    private ChoiceBox<String> leagueRankChoiceBoxF;
    @FXML
    public Button saveButton;

    @FXML
    TextField tagTextField;

    /**
     * Initializes the controller class. This method is automatically called after the fxml file has been loaded.
     */
    @FXML
    void initialize() {
        valoRankChoiceBox.getItems().add("none");
        valoRankChoiceBox.getItems().addAll(Ranks.getValorantRanks());
        valoRankChoiceBox.setValue("none");
        leagueRankChoiceBoxS.getItems().add("none");
        leagueRankChoiceBoxS.getItems().addAll(Ranks.getLeagueRanks());
        leagueRankChoiceBoxS.setValue("none");
        leagueRankChoiceBoxF.getItems().add("none");
        leagueRankChoiceBoxF.getItems().addAll(Ranks.getLeagueRanks());
        leagueRankChoiceBoxF.setValue("none");
    }


    /**
     * This method is called when the save button is clicked.
     * saves the new account to the account list
     *
     * @param event
     */
    @FXML
    void saveButtonPressed(ActionEvent event) {
        if (loginNameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            errorLabel.setText("Please fill in all fields with a *");
            FadeTransition ft = new FadeTransition(Duration.millis(3000), errorLabel);
            ft.setFromValue(1.0);
            ft.setToValue(0);

            ft.play();
        } else {
            if (publicNameTextField.getText().isEmpty()) {
                if (leagueNameTextField.getText().isEmpty()) {
                    new Account(loginNameTextField.getText(), loginNameTextField.getText(), tagTextField.getText(), loginNameTextField.getText(), valoRankChoiceBox.getValue(), leagueRankChoiceBoxS.getValue(), leagueRankChoiceBoxF.getValue(), passwordTextField.getText());
                } else {
                    new Account(loginNameTextField.getText(), loginNameTextField.getText(), tagTextField.getText(), leagueNameTextField.getText(), valoRankChoiceBox.getValue(), leagueRankChoiceBoxS.getValue(), leagueRankChoiceBoxF.getValue(), passwordTextField.getText());
                }
            } else {
                if (leagueNameTextField.getText().isEmpty()) {
                    new Account(loginNameTextField.getText(), publicNameTextField.getText(), tagTextField.getText(), loginNameTextField.getText(), valoRankChoiceBox.getValue(), leagueRankChoiceBoxS.getValue(), leagueRankChoiceBoxF.getValue(), passwordTextField.getText());
                } else {
                    new Account(loginNameTextField.getText(), publicNameTextField.getText(), tagTextField.getText(), leagueNameTextField.getText(), valoRankChoiceBox.getValue(), leagueRankChoiceBoxS.getValue(), leagueRankChoiceBoxF.getValue(), passwordTextField.getText());
                }
            }

            accountSavedLabel.setText("Account saved");
            FadeTransition ft = new FadeTransition(Duration.millis(3000), accountSavedLabel);
            ft.setFromValue(1.0);
            ft.setToValue(0);

            ft.play();

            loginNameTextField.setText("");
            publicNameTextField.setText("");
            leagueNameTextField.setText("");
            passwordTextField.setText("");
            tagTextField.setText("");
            valoRankChoiceBox.setValue("none");
            leagueRankChoiceBoxS.setValue("none");
            leagueRankChoiceBoxF.setValue("none");
        }
    }

}

