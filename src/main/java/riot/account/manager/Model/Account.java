package riot.account.manager.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import riot.account.manager.Controller.AccountController;

/**
 * Account object with all the informations about the account.
 *
 * @author Chris Simbeck
 */

public class Account {

    private String userName;
    private String valorantRank;
    private String leagueRank;
    private boolean available;
    private String password;
    private String tag;

    public Account(String userName, String tag, String valorantRank,String leagueRank, String password) {
        this.userName = userName;
        this.tag = tag;
        this.valorantRank = valorantRank;
        this.leagueRank = leagueRank;
        this.password = password;
        this.available = true;
        AccountController.addAccount(userName, this);
    }
    public Account(String userName, String tag,  String valorantRank,String leagueRank, String password, Boolean isAvailable) {
        this.userName = userName;
        this.tag = tag;
        this.valorantRank = valorantRank;
        this.leagueRank = leagueRank;
        this.password = password;
        this.available = isAvailable;
        AccountController.addAccount(userName, this);
    }

    public String getUserName() {
        return userName;
    }

    public String getUserValorantRank() {
        return valorantRank;
    }
    public String getUserLeagueRank() {
        return leagueRank;
    }

    public void setValorantRank(String rank) {
        this.valorantRank = rank;
    }
    public void setLeagueRank(String rank) {
        this.leagueRank = rank;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getTag() {
        return tag;
    }

    public StringProperty getUserNameProperty() {
        return new SimpleStringProperty(this.userName);
    }

    public StringProperty getUserValorantRankProperty() {
        return new SimpleStringProperty(this.valorantRank);
    }
    public StringProperty getUserLeagueRankProperty() {
        return new SimpleStringProperty(this.leagueRank);
    }
}
