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
    private String publicNameLeague;
    private String userName;
    private String publicNameValorant;
    private String valorantRank;
    private String leagueRankSolo;
    private boolean available;
    private String password;
    private String tag;

    public Account(String userName, String publicNameValorant, String tag,String publicNameLeague, String valorantRank, String leagueRankSolo,String leagueRankFlex, String password) {
        this.userName = userName;
        this.publicNameValorant = publicNameValorant;
        this.publicNameLeague = publicNameLeague;
        this.tag = tag;
        this.valorantRank = valorantRank;
        this.leagueRankSolo = leagueRankSolo;
        this.leagueRankFlex = leagueRankFlex;
        this.password = password;
        this.available = true;
        AccountController.addAccount(userName, this);
    }
    public Account(String userName, String publicNameValorant, String tag,String publicNameLeague, String valorantRank, String leagueRankSolo,String leagueRankFlex, String password, Boolean isAvailable) {
        this.userName = userName;
        this.publicNameValorant = publicNameValorant;
        this.publicNameLeague = publicNameLeague;
        this.tag = tag;
        this.valorantRank = valorantRank;
        this.leagueRankSolo = leagueRankSolo;
        this.leagueRankFlex = leagueRankFlex;
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
        out = this.publicNameValorant +" "+this.tag;
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

    public String getPublicNameValorant() {
        return this.publicNameValorant;
    }

    public void setPublicNameValorant(String publicNameValorant) {
        this.publicNameValorant = publicNameValorant;
    }
    public String getPublicNameLeague() {
        return this.publicNameLeague;
    }

    public void setPublicNameLeague(String publicNameLeague) {
        this.publicNameLeague = publicNameLeague;
    }
}
