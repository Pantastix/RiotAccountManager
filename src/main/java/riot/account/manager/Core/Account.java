package riot.account.manager.Core;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import riot.account.manager.Controller.AccountController;

/**
 * Account object with all the informations about the account.
 *
 * @author Chris Simbeck
 */

public class Account {

    private String leagueRankFlex;
    private String leagueName;
    private String loginName;
    private String riotID;
    private String valorantRank;
    private String leagueRankSolo;
    private boolean available;
    private String password;
    private String tag;

    public Account(String loginName, String riotID, String tag, String leagueName, String valorantRank, String leagueRankSolo, String leagueRankFlex, String password) {
        this.loginName = loginName;
        this.riotID = riotID;
        this.leagueName = leagueName;
        this.tag = tag;
        this.valorantRank = valorantRank;
        this.leagueRankSolo = leagueRankSolo;
        this.leagueRankFlex = leagueRankFlex;
        this.password = password;
        this.available = true;
        AccountController.addAccount(loginName, this);
    }
    public Account(String loginName, String riotID, String tag, String leagueName, String valorantRank, String leagueRankSolo, String leagueRankFlex, String password, Boolean isAvailable) {
        this.loginName = loginName;
        this.riotID = riotID;
        this.leagueName = leagueName;
        this.tag = tag;
        this.valorantRank = valorantRank;
        this.leagueRankSolo = leagueRankSolo;
        this.leagueRankFlex = leagueRankFlex;
        this.password = password;
        this.available = isAvailable;
        AccountController.addAccount(loginName, this);
    }

    public String getLoginName() {
        return loginName;
    }

    public String getUserValorantRank() {
        return valorantRank;
    }
    public String getLeagueRankSolo() {
        return leagueRankSolo;
    }
    public String getLeagueRankFlex() {
        return leagueRankFlex;
    }

    public void setValorantRank(String rank) {
        this.valorantRank = rank;
    }
    public void setLeagueRankSolo(String rank) {
        this.leagueRankSolo = rank;
    }
    public void setLeagueRankFlex(String rank) {
        this.leagueRankFlex = rank;
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

    public void setTag(String tag) {
        this.tag = tag;
    }

    public StringProperty getUserNameProperty() {
        String out;
        out = "  "+this.riotID +" "+this.tag;
        return new SimpleStringProperty(out);
    }

    public StringProperty getUserValorantRankProperty() {
        return new SimpleStringProperty(this.valorantRank);
    }
    public StringProperty getUserSoloRankProperty() {
        return new SimpleStringProperty(this.leagueRankSolo);
    }
    public StringProperty getUserFlexRankProperty() {
        return new SimpleStringProperty(this.leagueRankFlex);
    }

    public String getRiotID() {
        return this.riotID;
    }

    public void setRiotID(String riotID) {
        this.riotID = riotID;
    }
    public String getLeagueName() {
        return this.leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }
}
