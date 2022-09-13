package riot.account.manager.Controller;

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

public class NewMainViewController {

    @FXML
    private TableColumn<?, ?> accountColumn;

    @FXML
    private CheckBox accountUsableCheckBox;

    @FXML
    private Button addNewButton;

    @FXML
    private ChoiceBox<?> changeFlexRank;

    @FXML
    private ChoiceBox<?> changeSoloRank;

    @FXML
    private ChoiceBox<?> changeValoRank;

    @FXML
    private Button copyPasswordButton;

    @FXML
    private Button copyUserNameButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editAccountButton;

    @FXML
    private TableColumn<?, ?> flexColumn;

    @FXML
    private ChoiceBox<?> gameFilterChoiceBox;

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
    private TableView<?> mainTable;

    @FXML
    private TextField nameFilterField;

    @FXML
    private ChoiceBox<?> rankFilterChoiceBox;

    @FXML
    private Label riotIDLabel;

    @FXML
    private TableColumn<?, ?> soloColumn;

    @FXML
    private Label updateLabel;

    @FXML
    private ImageView valoBackgroundImage;

    @FXML
    private ImageView valoRankImg;

    @FXML
    private TableColumn<?, ?> valorantColumn;

    @FXML
    private Label versionLabel;

    @FXML
    public void initialize() {
        // TODO
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

    }

}

