package riot.account.manager.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import riot.account.manager.Util.STATICS;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MissingFilesView {


    @FXML
    public Hyperlink link;

    private String downloadLink = "https://github.com/Pantastix/RiotAccountManager/releases/download/v"+STATICS.VERSION +"/RiotAccountManager.rar";
    @FXML
    public void openLink(ActionEvent event){

        try {
            java.awt.Desktop.getDesktop().browse(new URI(downloadLink));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
