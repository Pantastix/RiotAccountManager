package riot.account.manager.Util;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class STATICS {
    public final static String JSONPATH = "Json/SaveState.Json";
    public final static String VERSION = "2.0.0";

    public final static String DOWNLOADURL = "https://github.com/Pantastix/RiotAccountManager/releases/latest/download/RiotAccountManager-update.jar";
    public final static String CHECKURL = "https://github.com/Pantastix/RiotAccountManager/releases/v[0].[1].[2]";

    public static String[] getVersionArray() {
        return VERSION.split("\\.");
    }

    public static Image getValoBackground() {
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream("images/ValorantBackground.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new Image(inputStream);
    }

    public static Image getLeagueBackground() {

        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream("images/LeagueBackground.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new Image(inputStream);
    }

    public static Image getAddAccountBackground() {
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream("images/AddNewAccountBG.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new Image(inputStream);
    }

    public static Image getEditAccountBackground() {
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream("images/EditAccountBG.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new Image(inputStream);
    }
}
