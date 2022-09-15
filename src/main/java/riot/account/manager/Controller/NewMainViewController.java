package riot.account.manager.Controller;

import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import riot.account.manager.Core.Account;
import riot.account.manager.Core.Updater;
import riot.account.manager.Util.Ranks;
import riot.account.manager.Util.STATICS;

public class NewMainViewController {

    //Standart window elements

    @FXML
    private TableView<Account> mainTable;
    @FXML
    private TableColumn<Account, String> accountColumn;
    @FXML
    private TableColumn<Account, String> flexColumn;
    @FXML
    private TableColumn<Account, String> soloColumn;
    @FXML
    private TableColumn<Account, String> valorantColumn;
    @FXML
    private ChoiceBox<String> gameFilterChoiceBox;
    @FXML
    private ChoiceBox<String> rankFilterChoiceBox;
    @FXML
    private TextField nameFilterField;
    @FXML
    private Button addNewButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editAccountButton;
    @FXML
    private Label versionLabel;


    //Account window elements
    @FXML
    private Pane openAccountPane;
    @FXML
    private CheckBox accountUsableCheckBox;
    @FXML
    private ChoiceBox<String> changeFlexRank;
    @FXML
    private ChoiceBox<String> changeSoloRank;
    @FXML
    private ChoiceBox<String> changeValoRank;
    @FXML
    private Button copyPasswordButton;
    @FXML
    private Button copyUserNameButton;
    @FXML
    private ImageView leagueBackgroundImage;
    @FXML
    private ImageView leagueFlexImg;
    @FXML
    private Label leagueNameLabel;
    @FXML
    private ImageView leagueSoloImg;
    @FXML
    private Label loginNameLabel;

    @FXML
    private Label riotIDLabel;

    @FXML
    private Label updateLabel;

    @FXML
    private ImageView valoBackgroundImage;

    @FXML
    private ImageView valoRankImg;



    //Add new account window elements
    @FXML
    private Pane addNewAccountPane;
    @FXML
    private ImageView addAccountBackground;
    @FXML
    private TextField loginNameTextFieldA;
    @FXML
    private TextField riotIDTextFieldA;
    @FXML
    private TextField tagTextFieldA;
    @FXML
    private TextField leagueNameTextFieldA;
    @FXML
    private TextField passwordTextFieldA;
    @FXML
    private ChoiceBox<String> flexRankChoiceBoxA;
    @FXML
    private ChoiceBox<String> soloRankChoiceBoxA;
    @FXML
    private ChoiceBox<String> valoRankChoiceBoxA;
    @FXML
    private Label errorLabelA;
    @FXML
    private Button saveButtonA;
    @FXML
    private Button cancelButtonA;

    //Edit account window elements
    @FXML
    private Pane editAccountPane;
    @FXML
    private ImageView editAccountBackground;





    @FXML
    public void initialize() {
        fillMainTable();
        loadWindow();

        mainTable.getFocusModel().focusedCellProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {

                try {
                    if (mainTable.getSelectionModel().getSelectedItems().get(0) != null) {
                        openAccountWindow(mainTable.getSelectionModel().getSelectedItems().get(0));
                    }
                } catch (Exception e) {
                }
            }
        });


        gameFilterChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(javafx.beans.value.ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("All")) {
                    rankFilterChoiceBox.getItems().clear();
                    mainTable.getItems().clear();
                    rankFilterChoiceBox.getItems().add("All Ranks");
                    rankFilterChoiceBox.setValue("All Ranks");
                    rankFilterChoiceBox.setDisable(true);
                } else if (newValue.equals("Valorant")) {
                    nameFilterField.setText("");
                    mainTable.getItems().clear();
                    mainTable.setItems(AccountController.getAccountListFilteredByRank("Iron", "Valorant"));
                    reloadTable();
                    rankFilterChoiceBox.setDisable(false);
                    rankFilterChoiceBox.getItems().clear();
                    rankFilterChoiceBox.getItems().addAll(Ranks.getValorantFilterArray());
                    rankFilterChoiceBox.setValue("Iron");
                } else {
                    nameFilterField.setText("");
                    mainTable.getItems().clear();
                    mainTable.setItems(AccountController.getAccountListFilteredByRank("Iron", "League"));
                    reloadTable();
                    rankFilterChoiceBox.setDisable(false);
                    rankFilterChoiceBox.getItems().clear();
                    rankFilterChoiceBox.getItems().addAll(Ranks.getLeagueFilterArray());
                    rankFilterChoiceBox.setValue("Iron");
                }
            }


        });

        rankFilterChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(javafx.beans.value.ObservableValue<? extends String> observable, String oldValue, String newValue) {
                mainTable.getSelectionModel().clearSelection();
                if (gameFilterChoiceBox.getValue().equals("Valorant")) {
                    mainTable.getItems().clear();
                    mainTable.setItems(AccountController.getAccountListFilteredByRank(rankFilterChoiceBox.getValue(), "Valorant"));
                    mainTable.refresh();
                } else if(gameFilterChoiceBox.getValue().equals("League Solo")){
                    mainTable.getItems().clear();
                    mainTable.setItems(AccountController.getAccountListFilteredByRank(rankFilterChoiceBox.getValue(), "LeagueSolo"));
                    mainTable.refresh();
                } else{
                    mainTable.getItems().clear();
                    mainTable.setItems(AccountController.getAccountListFilteredByRank(rankFilterChoiceBox.getValue(), "LeagueFlex"));
                    mainTable.refresh();
                }
                mainTable.refresh();
            }
        });

        nameFilterField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                mainTable.getSelectionModel().clearSelection();
                rankFilterChoiceBox.setValue("All Ranks");
                gameFilterChoiceBox.setValue("All");
                rankFilterChoiceBox.setDisable(true);
                mainTable.setItems(AccountController.getAccountListFilteredByName(newValue));
                mainTable.refresh();
            }
        });

        changeValoRank.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    mainTable.getSelectionModel().getSelectedItem().setValorantRank(newValue);
                    mainTable.refresh();
                    valoRankImg.setImage(AccountController.getRankImage(mainTable.getSelectionModel().getSelectedItem().getUserValorantRank(), "Valorant"));
                }
            }
        });

        changeSoloRank.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    mainTable.getSelectionModel().getSelectedItem().setLeagueRankSolo(newValue);
                    mainTable.refresh();
                    leagueSoloImg.setImage(AccountController.getRankImage(mainTable.getSelectionModel().getSelectedItem().getLeagueRankSolo(), "League"));
                }
            }
        });

        changeFlexRank.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    mainTable.getSelectionModel().getSelectedItem().setLeagueRankFlex(newValue);
                    mainTable.refresh();
                    leagueFlexImg.setImage(AccountController.getRankImage(mainTable.getSelectionModel().getSelectedItem().getLeagueRankFlex(), "League"));
                }
            }
        });

        accountUsableCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                Account selectedAccount = mainTable.getSelectionModel().getSelectedItem();
                if (accountUsableCheckBox.isSelected()) {
                    selectedAccount.setAvailable(true);
                } else {
                    selectedAccount.setAvailable(false);
                }
            }
        });

    }


    @FXML
    void addNewButtonPressed(ActionEvent event) {
        openAccountPane.setVisible(false);
        addNewAccountPane.setVisible(true);
        valoRankChoiceBoxA.setValue("none");
        flexRankChoiceBoxA.setValue("none");
        soloRankChoiceBoxA.setValue("none");
    }

    @FXML
    void editAccountButtonPressed(ActionEvent event) {
        fillEditAccountWindow();
    }

    @FXML
    void copyPasswordButtonPressed(ActionEvent event) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(mainTable.getSelectionModel().getSelectedItem().getPassword());
        clipboard.setContent(content);
    }

    @FXML
    void copyUserNameButtonPressed(ActionEvent event) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(mainTable.getSelectionModel().getSelectedItem().getUserName());
        clipboard.setContent(content);
    }

    @FXML
    void deleteButtonPressed(ActionEvent event) {
        if (mainTable.getSelectionModel().getSelectedItems().size() > 0) {
            AccountController.deleteAccount(mainTable.getSelectionModel().getSelectedItems().get(0));
            mainTable.getSelectionModel().clearSelection();
            reloadTable();
            mainTable.getSelectionModel().select(0);
        }
    }

    @FXML
    void saveNewButtonPressed(ActionEvent event) {
        if (loginNameTextFieldA.getText().isEmpty() || passwordTextFieldA.getText().isEmpty()) {
            errorLabelA.setText("Please fill in all fields with a *");
            FadeTransition ft = new FadeTransition(Duration.millis(3000), errorLabelA);
            ft.setFromValue(1.0);
            ft.setToValue(0);

            ft.play();
        } else {
            Account acc;
            if (riotIDTextFieldA.getText().isEmpty()) {
                if (leagueNameTextFieldA.getText().isEmpty()) {
                    acc = new Account(loginNameTextFieldA.getText(), loginNameTextFieldA.getText(), tagTextFieldA.getText(), loginNameTextFieldA.getText(), valoRankChoiceBoxA.getValue(), soloRankChoiceBoxA.getValue(), flexRankChoiceBoxA.getValue(), passwordTextFieldA.getText());
                } else {
                    acc = new Account(loginNameTextFieldA.getText(), loginNameTextFieldA.getText(), tagTextFieldA.getText(), leagueNameTextFieldA.getText(), valoRankChoiceBoxA.getValue(), soloRankChoiceBoxA.getValue(), flexRankChoiceBoxA.getValue(), passwordTextFieldA.getText());
                }
            } else {
                if (leagueNameTextFieldA.getText().isEmpty()) {
                    acc = new Account(loginNameTextFieldA.getText(), riotIDTextFieldA.getText(), tagTextFieldA.getText(), loginNameTextFieldA.getText(), valoRankChoiceBoxA.getValue(), soloRankChoiceBoxA.getValue(), flexRankChoiceBoxA.getValue(), passwordTextFieldA.getText());
                } else {
                    acc = new Account(loginNameTextFieldA.getText(), riotIDTextFieldA.getText(), tagTextFieldA.getText(), leagueNameTextFieldA.getText(), valoRankChoiceBoxA.getValue(), soloRankChoiceBoxA.getValue(), flexRankChoiceBoxA.getValue(), passwordTextFieldA.getText());
                }
            }

            addNewAccountPane.setVisible(false);
            openAccountPane.setVisible(true);
            nameFilterField.setText("");
            rankFilterChoiceBox.setValue("All Ranks");
            gameFilterChoiceBox.setValue("All");
            reloadTable();
            mainTable.getSelectionModel().select(acc);

            loginNameTextFieldA.setText("");
            riotIDTextFieldA.setText("");
            leagueNameTextFieldA.setText("");
            passwordTextFieldA.setText("");
            tagTextFieldA.setText("");
            valoRankChoiceBoxA.setValue("none");
            flexRankChoiceBoxA.setValue("none");
            soloRankChoiceBoxA.setValue("none");
        }
    }

    @FXML
    void closeNewButtonPressed(ActionEvent event) {
        loginNameTextFieldA.setText("");
        riotIDTextFieldA.setText("");
        tagTextFieldA.setText("");
        leagueNameTextFieldA.setText("");
        passwordTextFieldA.setText("");
        addNewAccountPane.setVisible(false);
        openAccountPane.setVisible(true);
    }

    private void fillMainTable() {
        mainTable.setItems(AccountController.getAccountListObservable());
        accountColumn.setCellValueFactory(cellData -> cellData.getValue().getUserNameProperty());
        valorantColumn.setCellValueFactory(cellData -> cellData.getValue().getUserValorantRankProperty());
        soloColumn.setCellValueFactory(cellData -> cellData.getValue().getUserSoloRankProperty());
        flexColumn.setCellValueFactory(cellData -> cellData.getValue().getUserFlexRankProperty());

    }

    public void loadWindow() {
        Thread updateThread = new Thread(updater);
        updateThread.start();


        gameFilterChoiceBox.getItems().addAll("All", "Valorant", "League Solo", "League Flex");
        gameFilterChoiceBox.setValue("All");
        changeValoRank.getItems().addAll(Ranks.getValorantRanks());
        changeSoloRank.getItems().addAll(Ranks.getLeagueRanks());
        changeFlexRank.getItems().addAll(Ranks.getLeagueRanks());
        rankFilterChoiceBox.getItems().addAll("All Ranks");
        rankFilterChoiceBox.setValue("All Ranks");

        versionLabel.setText("v. " + STATICS.VERSION);

        try {
            mainTable.getSelectionModel().selectFirst();
            openAccountWindow(mainTable.getSelectionModel().getSelectedItem());
        } catch (NullPointerException e) {
            leagueNameLabel.setText("");
            riotIDLabel.setText("");
            loginNameLabel.setText("");
        }

        //Backgrounds
        valoBackgroundImage.setImage(STATICS.getValoBackground());
        leagueBackgroundImage.setImage(STATICS.getLeagueBackground());
        addAccountBackground.setImage(STATICS.getAddAccountBackground());
        editAccountBackground.setImage(STATICS.getEditAccountBackground());

        //Fill edit window
        valoRankChoiceBoxA.getItems().clear();
        valoRankChoiceBoxA.getItems().addAll("none");
        valoRankChoiceBoxA.getItems().addAll(Ranks.getValorantRanks());
        valoRankChoiceBoxA.setValue("none");
        flexRankChoiceBoxA.getItems().clear();
        flexRankChoiceBoxA.getItems().addAll("none");
        flexRankChoiceBoxA.getItems().addAll(Ranks.getLeagueRanks());
        flexRankChoiceBoxA.setValue("none");
        soloRankChoiceBoxA.getItems().clear();
        soloRankChoiceBoxA.getItems().addAll("none");
        soloRankChoiceBoxA.getItems().addAll(Ranks.getLeagueRanks());
        soloRankChoiceBoxA.setValue("none");
    }

    public void fillEditAccountWindow(){
        //TODO: fill all fields with the selected account information
    }

    private void openAccountWindow(Account account) {
        if (account.getRiotID().equals(account.getUserName())) {
            riotIDLabel.setVisible(false);
        } else {
            riotIDLabel.setVisible(true);
            riotIDLabel.setText(account.getRiotID()+" "+account.getTag());
        }

        if (account.getLeagueName().equals(account.getUserName())) {
            leagueNameLabel.setVisible(false);
        } else {
            leagueNameLabel.setVisible(true);
            leagueNameLabel.setText(account.getRiotID());
        }
        loginNameLabel.setText(account.getUserName());
        accountUsableCheckBox.setSelected(account.isAvailable());

        if(account.getUserValorantRank().equals("none")) {
            valoRankImg.setVisible(false);
        }else{
            valoRankImg.setVisible(true);
            valoRankImg.setImage(Ranks.getRankImage(account.getUserValorantRank(), "Valorant"));
        }
        changeValoRank.setValue(account.getUserValorantRank());
        if(account.getLeagueRankSolo().equals("none")) {
            leagueSoloImg.setVisible(false);
        }else{
            leagueSoloImg.setVisible(true);
            leagueSoloImg.setImage(Ranks.getRankImage(account.getLeagueRankSolo(), "League"));
        }
        changeSoloRank.setValue(account.getLeagueRankSolo());
        if(account.getLeagueRankFlex().equals("none")) {
            leagueSoloImg.setVisible(false);
        }else {
            leagueFlexImg.setVisible(true);
            leagueFlexImg.setImage(Ranks.getRankImage(account.getLeagueRankFlex(), "League"));
        }
        changeFlexRank.setValue(account.getLeagueRankFlex());

    }

    public void reloadTable() {
        mainTable.getItems().clear();
        mainTable.getItems().addAll(AccountController.getAccountListObservable());
        mainTable.refresh();
    }

    Runnable updater = () -> {
        if (!Updater.isAvailable()) {
            updateLabel.setVisible(false);
        }
    };

}

