module riot.account.manager.riotaccountmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires json.simple;


    opens riot.account.manager.Controller to javafx.fxml;
    opens riot.account.manager.Model to javafx.fxml;
    exports riot.account.manager.Controller;
    exports riot.account.manager.Model;
    exports riot.account.manager.Savestate;
}