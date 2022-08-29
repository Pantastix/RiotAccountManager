package riot.account.manager.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import riot.account.manager.Controller.AccountController;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Creates the save-state of the current loaded accounts
 *
 * @author Chris Simbeck
 */
public class CreateSavestate {
    private static String path = "src/main/resources/Json/SaveState.Json";
    public static void createSavestate(){
        JSONObject json = new JSONObject();
        ArrayList<Account> accountList = AccountController.getAccountList();
        JSONArray accountNames = new JSONArray();
        for(Account account : accountList){
            accountNames.put(account.getUserName());
        }
        json.put("accountnames",accountNames);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for(Account account : accountList){
            JSONArray accountArray = new JSONArray();
            try {
                accountArray.put(account.getUserName());
                accountArray.put(account.getTag());
                accountArray.put(account.getUserValorantRank());
                accountArray.put(account.getUserLeagueRank());
                accountArray.put(account.getPassword());
                accountArray.put(account.isAvailable());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            json.put(account.getUserName(),accountArray);
        }

        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            out.write(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
