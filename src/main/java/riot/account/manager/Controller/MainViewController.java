package riot.account.manager.Controller;

import riot.account.manager.Model.Account;
import riot.account.manager.Model.Ranks;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * Controller for the Main view
 *
 * @author Chris Simbeck
 */

public class MainViewController {


    @FXML
    public Button addNewButton;

    @FXML
    public Button deleteButton;

    @FXML
    public Button copyUserNameButton;

    @FXML
    public Button copyPasswordButton;

    @FXML
    public Button changePasswordButton;

    @FXML
    public ImageView rankImage;

    @FXML
    public TextField nameField;

    @FXML
    public CheckBox accountUsableCheckBox;

    @FXML
    public ChoiceBox<String> rankChoiceBox;

    @FXML
    public ChoiceBox<String> gameChoiceBox;
    @FXML
    public ChoiceBox<String> changeRankChoiceBox;

    @FXML
    public TableView<Account>  mainTable;

    @FXML
    public TableColumn<Account, String> accountColumn;

    @FXML
    public TableColumn<Account, String> rankColumn;

    @FXML
    public AnchorPane accountInfoAnchorPane;

    @FXML
    public Label accountNameLabel;

    boolean closed = true;

    private Account selectedAccount;
    private static Account selectedStaticAccount;

    /**
     * The initialize function is called when the FXML file is loaded.
     * It initializes all of the controls in this class and sets up their listeners.
     *
     */
    @FXML
    public void initialize() {
        mainTable.setItems(AccountController.getAccountListObservable());
        accountColumn.setCellValueFactory(cellData -> cellData.getValue().getUserNameProperty());
        rankColumn.setCellValueFactory(cellData -> cellData.getValue().getUserValorantRankProperty());

        gameChoiceBox.getItems().addAll("Valorant", "League");
        rankChoiceBox.getItems().addAll(Ranks.getValorantRankArray());
        rankChoiceBox.getItems().add("all Ranks");
        changeRankChoiceBox.getItems().addAll("none");
        changeRankChoiceBox.getItems().addAll(Ranks.getValorantRankArray());
        rankChoiceBox.setValue("all Ranks");
        gameChoiceBox.setValue("Valorant");
        changeRankChoiceBox.setValue("Bronze");
        accountInfoAnchorPane.setVisible(false);
        accountInfoAnchorPane.setDisable(true);
        mainTable.getSelectionModel().select(0);

        mainTable.getFocusModel().focusedCellProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                if (closed) {
                    closed = false;
                    accountInfoAnchorPane.setVisible(true);
                    accountInfoAnchorPane.setDisable(false);
                }

                try {
                    if(mainTable.getSelectionModel().getSelectedItems().get(0) != null) {
                        selectedAccount = mainTable.getSelectionModel().getSelectedItems().get(0);
                    }
                    openAccountWindow(selectedAccount);
                }catch (Exception e){
                    accountInfoAnchorPane.setVisible(false);
                    accountInfoAnchorPane.setDisable(true);
                }
            }
        });


        accountUsableCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (accountUsableCheckBox.isSelected()) {
                    selectedAccount.setAvailable(true);
                } else {
                    selectedAccount.setAvailable(false);
                }
            }
        });


        changeRankChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if(gameChoiceBox.getValue().equals("Valorant")){
                        selectedAccount.setValorantRank(newValue);
                    }else{
                        selectedAccount.setLeagueRank(newValue);
                    }
                    mainTable.refresh();
                    rankImage.setImage(AccountController.getRankImage(selectedAccount, gameChoiceBox.getSelectionModel().getSelectedItem()));
                }
            }
        });


        rankChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                mainTable.getSelectionModel().clearSelection();
                nameField.setText("");
                mainTable.setItems(AccountController.getAccountListFilteredByRank(newValue, gameChoiceBox.getValue()));
                mainTable.refresh();
                accountInfoAnchorPane.setVisible(true);
                accountInfoAnchorPane.setDisable(false);
            }
        });

        gameChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                mainTable.getSelectionModel().clearSelection();

                changeRankChoiceBox.getItems().clear();
                rankChoiceBox.getItems().clear();
                if(gameChoiceBox.getSelectionModel().getSelectedItem().equals("Valorant")) {
                    rankColumn.setCellValueFactory(cellData -> cellData.getValue().getUserValorantRankProperty());
                    rankChoiceBox.getItems().addAll(Ranks.getValorantRankArray());
                    changeRankChoiceBox.getItems().add("none");
                    changeRankChoiceBox.getItems().addAll(Ranks.getValorantRankArray());
                }else{
                    rankColumn.setCellValueFactory(cellData -> cellData.getValue().getUserLeagueRankProperty());
                    rankChoiceBox.getItems().clear();
                    rankChoiceBox.getItems().addAll(Ranks.getLeagueRankArray());
                    changeRankChoiceBox.getItems().add("none");
                    changeRankChoiceBox.getItems().addAll(Ranks.getLeagueRankArray());
                }
                rankChoiceBox.getItems().add("all Ranks");
                rankChoiceBox.setValue("all Ranks");

                mainTable.getSelectionModel().select(selectedAccount);
                mainTable.refresh();
                accountInfoAnchorPane.setVisible(true);
                accountInfoAnchorPane.setDisable(false);
            }
        });


        nameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                mainTable.getSelectionModel().clearSelection();
                rankChoiceBox.setValue("all Ranks");
                mainTable.setItems(AccountController.getAccountListFilteredByName(newValue));
                mainTable.refresh();
                accountInfoAnchorPane.setVisible(true);
                accountInfoAnchorPane.setDisable(false);
            }
        });


        AccountController.getAccountListObservable().addListener(new ListChangeListener(){

            @Override
            public void onChanged(Change pChange) {
                while(pChange.next()) {
                    mainTable.refresh();
                    reloadTable();
                }
            }
        });

    }

    /**
     * opens the window to add a new account to the list
     * @param event
     * @throws IOException
     */
    @FXML
    void addNewButtonPressed(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/addNewAccountView.fxml"));
        Stage stage = new Stage();
        Scene scene =  new Scene (fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("/mainViewStyle.css").toExternalForm());
        stage.setScene(scene);
//        stage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                reloadTable();
            }
        });
        stage.show();
        stage.setResizable(false);
        mainTable.getSelectionModel().clearSelection();

    }

    /**
     * deletes the selected account from the list and the database
     * @param event
     * @throws IOException
     */
    @FXML
    void deleteButtonPressed(ActionEvent event) {

        if (selectedAccount != null) {
            AccountController.deleteAccount(selectedAccount);
            mainTable.getSelectionModel().clearSelection();
            reloadTable();
            mainTable.getSelectionModel().select(0);
        }
    }

    /**
     * opens the change password window
     * @param event
     * @throws IOException
     */
    @FXML
    void changePasswordButtonPressed(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/changePasswortView.fxml"));
        Stage stage = new Stage();
        Scene scene =  new Scene (fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("/mainViewStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        stage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
    }

    /**
     * copy the username into the clipboard
     * @param event
     */
    @FXML
    void copyUserNameButtonPressed(ActionEvent event) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(selectedAccount.getUserName());
        clipboard.setContent(content);
    }

    /**
     * copy the passwort into the clipboard
     * @param event
     */
    @FXML
    void copyPasswordButtonPressed(ActionEvent event) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(selectedAccount.getPassword());
        clipboard.setContent(content);
    }

    /**
     * opens the account panel for the selected account
     * @param account
     */
    private void openAccountWindow(Account account) {
        accountNameLabel.setText(account.getUserName()+" "+account.getTag());
        accountUsableCheckBox.setSelected(account.isAvailable());
        if(gameChoiceBox.getSelectionModel().getSelectedItem().equals("Valorant")) {
            changeRankChoiceBox.setValue(account.getUserValorantRank());
        }else{
            changeRankChoiceBox.setValue(account.getUserLeagueRank());
        }
        selectedStaticAccount = account;
        rankImage.setImage(AccountController.getRankImage(account, gameChoiceBox.getSelectionModel().getSelectedItem()));
    }

    /**
     * reloads the table with the current data
     * after a window is closed
     *
     * @param t
     * @param <T>
     */
    private <T extends Event> void closeWindowEvent(T t) {

    }

    /**
     * Reloads the table with the current account list
     */
    public void reloadTable(){
        mainTable.getItems().clear();
        mainTable.getItems().addAll(AccountController.getAccountListObservable());
        accountInfoAnchorPane.setDisable(false);
        accountInfoAnchorPane.setVisible(true);
    }

    public static Account getSelectedAccount() {
        return selectedStaticAccount;
    }
}
