package riot.account.manager.Util;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import riot.account.manager.Model.Account;

import java.io.FileReader;
import java.util.ArrayList;

/**
 * loads the savestate from the json file at the start of the program
 *
 * @author Chris Simbeck
 */
public class ReadSavestate {
    private static String path = "src/main/resources/Json/SaveState.Json";

    public static void readSavestate(){
        ArrayList<String> accountNames = new ArrayList<>();

        JSONParser parser = new JSONParser();
        JSONObject json = new JSONObject();

        try {
            json = (JSONObject) parser.parse(new FileReader(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONArray grabber = (JSONArray) json.get("accountnames");
        for(int i = 0; i < grabber.size(); i++){
            accountNames.add(grabber.get(i).toString());
        }
        for (String name : accountNames){
            grabber = (JSONArray) json.get(name);
            String userName = (String) grabber.get(0);
            String userTag = (String) grabber.get(1);
            String userValorantRank = (String) grabber.get(2);
            String userLeagueRank = (String) grabber.get(3);
            String userPasswort = (String) grabber.get(4);
            boolean userIsAvailable = (boolean) grabber.get(5);
            Account account = new Account(userName,userTag,userValorantRank,userLeagueRank,userPasswort,userIsAvailable);
        }

    }

}
