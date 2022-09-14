package riot.account.manager.Controller;

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
import javafx.scene.layout.VBox;
import riot.account.manager.Core.Account;
import riot.account.manager.Core.Updater;
import riot.account.manager.Util.Ranks;
import riot.account.manager.Util.STATICS;

public class NewMainViewController {

    @FXML
    private TableColumn<Account, String> accountColumn;

    @FXML
    private CheckBox accountUsableCheckBox;

    @FXML
    private Button addNewButton;

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
    private Button deleteButton;

    @FXML
    private Button editAccountButton;

    @FXML
    private TableColumn<Account, String> flexColumn;

    @FXML
    private ChoiceBox<String> gameFilterChoiceBox;

    @FXML
    private ImageView leagueBackgroundImage;

    @FXML
    private ImageView leagueFlexImg;

    @FXML
    private Label leagueLabel;

    @FXML
    private Label leagueLabel2;

    @FXML
    private Label leagueNameLabel;

    @FXML
    private ImageView leagueSoloImg;

    @FXML
    private Label loginNameLabel;

    @FXML
    private TableView<Account> mainTable;

    @FXML
    private TextField nameFilterField;

    @FXML
    private ChoiceBox<String> rankFilterChoiceBox;

    @FXML
    private Label riotIDLabel;

    @FXML
    private TableColumn<Account, String> soloColumn;

    @FXML
    private Label updateLabel;

    @FXML
    private ImageView valoBackgroundImage;

    @FXML
    private ImageView valoRankImg;

    @FXML
    private TableColumn<Account, String> valorantColumn;

    @FXML
    private Label versionLabel;

    @FXML
    private VBox openAccountPane;

    @FXML
    public void initialize() {
        fillMainTable();
        fillWindow();
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
                    rankFilterChoiceBox.getItems().add("All Ranks");
                    rankFilterChoiceBox.setValue("All Ranks");
                    rankFilterChoiceBox.setDisable(true);
                } else if (newValue.equals("Valorant")) {
                    nameFilterField.setText("");
                    mainTable.setItems(AccountController.getAccountListFilteredByRank("Iron","Valorant"));
                    reloadTable();
                    rankFilterChoiceBox.setDisable(false);
                    rankFilterChoiceBox.getItems().clear();
                    rankFilterChoiceBox.getItems().addAll(Ranks.getValorantFilterArray());
                    rankFilterChoiceBox.setValue("Iron");
                } else {
                    nameFilterField.setText("");
                    mainTable.setItems(AccountController.getAccountListFilteredByRank("Iron","League"));
                    reloadTable();
                    rankFilterChoiceBox.setDisable(false);
                    rankFilterChoiceBox.getItems().clear();
                    rankFilterChoiceBox.getItems().addAll(Ranks.getLeagueFilterArray());
                    rankFilterChoiceBox.setValue("Iron");
                }
            }


        });

        gameFilterChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(javafx.beans.value.ObservableValue<? extends String> observable, String oldValue, String newValue) {
                mainTable.getSelectionModel().clearSelection();
                if(gameFilterChoiceBox.getValue().equals("Valorant")) {
                    mainTable.setItems(AccountController.getAccountListFilteredByRank(rankFilterChoiceBox.getValue(), "Valorant"));
                }else{
                    mainTable.setItems(AccountController.getAccountListFilteredByRank(rankFilterChoiceBox.getValue(), "League"));
                }
                reloadTable();
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
                    leagueFlexImg.setImage(AccountController.getRankImage(mainTable.getSelectionModel().getSelectedItem().getLeagueRankFlex(), "Valorant"));
                }
            }
        });

    }


    @FXML
    void addNewButtonPressed(ActionEvent event) {

    }

    @FXML
    void changePasswordButtonPressed(ActionEvent event) {

    }

    @FXML
    void copyPasswordButtonPressed(ActionEvent event) {

    }

    @FXML
    void copyUserNameButtonPressed(ActionEvent event) {

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

    private void fillMainTable() {
        mainTable.setItems(AccountController.getAccountListObservable());
        accountColumn.setCellValueFactory(cellData -> cellData.getValue().getUserNameProperty());
        valorantColumn.setCellValueFactory(cellData -> cellData.getValue().getUserValorantRankProperty());
        soloColumn.setCellValueFactory(cellData -> cellData.getValue().getUserSoloRankProperty());
        flexColumn.setCellValueFactory(cellData -> cellData.getValue().getUserFlexRankProperty());

    }

    public void fillWindow() {
        if (!Updater.isAvailable()) {
            updateLabel.setVisible(false);
        }
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

        valoBackgroundImage.setImage(STATICS.getValoBackground());
        leagueBackgroundImage.setImage(STATICS.getLeagueBackground());
    }

    private void openAccountWindow(Account account) {
        if (account.getRiotID().equals(account.getUserName())) {
            riotIDLabel.setVisible(false);
        } else {
            riotIDLabel.setVisible(true);
            riotIDLabel.setText(account.getRiotID());
        }

        if (account.getLeagueName().equals(account.getUserName())) {
            leagueNameLabel.setVisible(false);
        } else {
            leagueLabel.setVisible(true);
            leagueNameLabel.setText(account.getRiotID());
        }

        loginNameLabel.setText(account.getUserName());
        accountUsableCheckBox.setSelected(account.isAvailable());

        changeValoRank.setValue(account.getUserValorantRank());
        valoRankImg.setImage(Ranks.getRankImage(account.getUserValorantRank(), "Valorant"));
        changeSoloRank.setValue(account.getLeagueRankSolo());
        leagueSoloImg.setImage(Ranks.getRankImage(account.getLeagueRankSolo(), "League"));
        changeFlexRank.setValue(account.getLeagueRankFlex());
        leagueFlexImg.setImage(Ranks.getRankImage(account.getLeagueRankFlex(), "League"));
    }

    public void reloadTable() {
        mainTable.getItems().clear();
        mainTable.getItems().addAll(AccountController.getAccountListObservable());
        mainTable.refresh();
    }
}

