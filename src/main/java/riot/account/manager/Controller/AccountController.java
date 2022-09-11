package riot.account.manager.Controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import riot.account.manager.Core.Account;
import riot.account.manager.Util.Ranks;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * controller for all accounts
 *
 * @author Chris Simbeck
 */
public class AccountController {

    private static HashMap<String, Account> accountList = new HashMap<>();
    private static ObservableList<Account> accountListObservable = FXCollections.observableArrayList();

    public static void addAccount(String name, Account account) {
        accountList.put(name, account);
        accountListObservable.add(account);
    }

    /**
     * The getAccountList function returns a observablelist of all the accounts in the system.
     *
     * @return An observablelist of account objects
     */
    public static ObservableList<Account> getAccountListObservable() {
        return FXCollections.observableArrayList(accountList.values());
    }

    /**
     * The getAccountListFilteredByRank function returns an ObservableList of Account objects that have the same rank as
     * the user input. If no accounts are found, it returns an empty list.
     *
     *
     * @param filterRank Filter the account list by rank
     *
     * @return An observablelist of account objects that have a userrank matching the newvalue parameter
     */
    public static ObservableList<Account> getAccountListFilteredByRank(String filterRank, String game) {
        ArrayList<Account> filteredAccountList = new ArrayList<>();
        try {
            if (filterRank.equals("all Ranks")) {
                filteredAccountList.addAll(accountList.values());
            } else {
                for (Account account : accountList.values()) {
                    if(game.equals("Valorant")){
                        if (account.getUserValorantRank().equals(filterRank)) {
                            filteredAccountList.add(account);
                        }
                    } else {
                        if (account.getLeagueRankSolo().equals(filterRank)) {
                            filteredAccountList.add(account);
                        }
                    }

                }
            }
        }catch(NullPointerException e){
            //Do nothing
        }

        return FXCollections.observableArrayList(filteredAccountList);
}


    /**
     * The getAccountListFilteredByName function returns an ObservableList of Account objects that have the same Name as
     * the user input. If no accounts are found, it returns an empty list.
     *
     *
     * @param newValue Filter the account list by Name
     *
     * @return An observablelist of account objects that have a username matching the newvalue parameter
     */
    public static ObservableList<Account> getAccountListFilteredByName(String newValue) {

        ArrayList<Account> filteredAccountList = new ArrayList<>();
        for (String key : accountList.keySet()) {
            if (accountList.get(key).getUserName().toLowerCase().contains(newValue.toLowerCase())) {
                filteredAccountList.add(accountList.get(key));
            }
        }
        return FXCollections.observableArrayList(filteredAccountList);
    }

    /**
     * deletes the account
     *
     * @param account that will be deleted
     */
    public static void deleteAccount(Account account) {
        accountList.remove(account.getUserName());
        accountListObservable.remove(account);
    }

    /**
     * fetches the Rank Image and returns the Image for the given rank and game.
     * @param rank of the account
     * @param game that the rank is for
     * @return the image of the rank
     */
    public static Image getRankImage(String rank, String game) {
        try{
            if(game.equals("Valorant")){
                return Ranks.getRankImage(rank, game);
            } else {
                return Ranks.getRankImage(rank, game);
            }

        } catch (Exception e) {
            return null;
        }

    }

    /**
     * returns the account list
     *
     * @return ArrayList of all accounts
     */
    public static ArrayList<Account> getAccountList(){
        ArrayList <Account> accountArrayList = new ArrayList<>();
        for (Account account : accountList.values()) {
            accountArrayList.add(account);
        }
        return accountArrayList;
    }
}
