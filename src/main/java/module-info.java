module riot.account.manager.riotaccountmanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens riot.account.manager.riotaccountmanager to javafx.fxml;
    exports riot.account.manager.riotaccountmanager;
}