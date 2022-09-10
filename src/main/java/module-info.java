module riot.account.manager {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires json.simple;
    requires java.desktop;


    exports riot.account.manager.Controller;
    exports riot.account.manager.Core;
    opens riot.account.manager.Controller to javafx.fxml;
    opens riot.account.manager.Core to javafx.fxml;
    exports riot.account.manager.Util;
    opens riot.account.manager.Util to javafx.fxml;

}