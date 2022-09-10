package riot.account.manager.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import riot.account.manager.Controller.AccountController;
import riot.account.manager.Model.Account;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Creates the save-state of the current loaded accounts
 *
 * @author Chris Simbeck
 */
public class CreateSavestate {

    public static void createSavestate() {
        JSONObject json = new JSONObject();
        ArrayList<Account> accountList = AccountController.getAccountList();
        JSONArray accountNames = new JSONArray();
        for (Account account : accountList) {
            accountNames.put(account.getUserName());
        }
        json.put("accountnames", accountNames);

        for (Account account : accountList) {
            JSONArray accountArray = new JSONArray();
            try {
                accountArray.put(account.getUserName());
                accountArray.put(account.getPublicName());
                accountArray.put(account.getTag());
                accountArray.put(account.getUserValorantRank());
                accountArray.put(account.getUserLeagueRank());
                accountArray.put(account.getPassword());
                accountArray.put(account.isAvailable());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            json.put(account.getUserName(), accountArray);
        }

        try (PrintWriter out = new PrintWriter(new FileWriter(STATICS.JSONPATH))) {
            out.write(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createNewSavestate() {

        File dir = new File("Json");
        if (!dir.exists()){
            dir.mkdirs();
        }

        JSONObject json = new JSONObject();
        ArrayList<Account> accountList = AccountController.getAccountList();
        JSONArray accountNames = new JSONArray();
        accountNames.put("ExampleAccount");
        json.put("accountnames", accountNames);
        JSONArray accountArray = new JSONArray();
        try {
            accountArray.put("ExampleAccount");
            accountArray.put("Example");
            accountArray.put("#1234");
            accountArray.put("Immortal");
            accountArray.put("Challenger");
            accountArray.put("Password1234");
            accountArray.put(false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        json.put("ExampleAccount", accountArray);


        try (PrintWriter out = new PrintWriter(new FileWriter(STATICS.JSONPATH))) {
            String text = json.toString();
            System.out.println(AES.encrypt(json.toString(), SECRETS.getToken()));
            System.out.println(text);
            out.write(Objects.requireNonNull(AES.encrypt(json.toString(), SECRETS.getToken())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
