package riot.account.manager.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import riot.account.manager.Controller.AccountController;
import riot.account.manager.Core.Account;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

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
            accountNames.put(AES.encrypt(account.getUserName(), SECRETS.getEncryptionKey()));
        }
        json.put(AES.encrypt("accountnames", SECRETS.getEncryptionKey()), accountNames);

        for (Account account : accountList) {
            JSONArray accountArray = new JSONArray();
            try {
                accountArray.put(AES.encrypt(account.getUserName(), SECRETS.getEncryptionKey()));
                accountArray.put(AES.encrypt(account.getRiotID(), SECRETS.getEncryptionKey()));
                accountArray.put(AES.encrypt(account.getTag(), SECRETS.getEncryptionKey()));
                accountArray.put(AES.encrypt(account.getLeagueName(), SECRETS.getEncryptionKey()));
                accountArray.put(AES.encrypt(account.getUserValorantRank(), SECRETS.getEncryptionKey()));
                accountArray.put(AES.encrypt(account.getLeagueRankSolo(), SECRETS.getEncryptionKey()));
                accountArray.put(AES.encrypt(account.getLeagueRankFlex(), SECRETS.getEncryptionKey()));
                accountArray.put(AES.encrypt(account.getPassword(), SECRETS.getEncryptionKey()));
                accountArray.put(AES.encrypt(String.valueOf(account.isAvailable()), SECRETS.getEncryptionKey()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            json.put(AES.encrypt(account.getUserName(), SECRETS.getEncryptionKey()), accountArray);
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
        accountNames.put(AES.encrypt("ExampleAccount", SECRETS.getEncryptionKey()));
        json.put(AES.encrypt("accountnames", SECRETS.getEncryptionKey()), accountNames);
        JSONArray accountArray = new JSONArray();
        try {
            accountArray.put(AES.encrypt("ExampleAccount", SECRETS.getEncryptionKey()));
            accountArray.put(AES.encrypt("Example", SECRETS.getEncryptionKey()));
            accountArray.put(AES.encrypt("#1234", SECRETS.getEncryptionKey()));
            accountArray.put(AES.encrypt("Example", SECRETS.getEncryptionKey()));
            accountArray.put(AES.encrypt("Immortal 1", SECRETS.getEncryptionKey()));
            accountArray.put(AES.encrypt("Challenger 2", SECRETS.getEncryptionKey()));
            accountArray.put(AES.encrypt("Diamond 3", SECRETS.getEncryptionKey()));
            accountArray.put(AES.encrypt("Password1234", SECRETS.getEncryptionKey()));
            accountArray.put(AES.encrypt("false", SECRETS.getEncryptionKey()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        json.put(AES.encrypt("ExampleAccount", SECRETS.getEncryptionKey()), accountArray);


        try (PrintWriter out = new PrintWriter(new FileWriter(STATICS.JSONPATH))) {
            String text = json.toString();
            out.write(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
