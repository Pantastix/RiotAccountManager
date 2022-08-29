module riot.account.manager {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires json.simple;



    exports riot.account.manager.Controller;
    exports riot.account.manager.Model;
    opens riot.account.manager.Controller to javafx.fxml;
    opens riot.account.manager.Model to javafx.fxml;

}