package riot.account.manager.Util;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import riot.account.manager.Model.Account;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * loads the savestate from the json file at the start of the program
 *
 * @author Chris Simbeck
 */
public class ReadSavestate {

    public static void readSavestate() throws IOException, ParseException {
        ArrayList<String> accountNames = new ArrayList<>();

        JSONParser parser = new JSONParser();
        JSONObject json;

        FileReader reader = new FileReader(STATICS.JSONPATH);
        decryptSavestate(reader);

        json = (JSONObject) parser.parse(reader);
        JSONArray grabber = (JSONArray) json.get("accountnames");
        for(int i = 0; i < grabber.size(); i++){
            accountNames.add(grabber.get(i).toString());
        }
        for (String name : accountNames){
            grabber = (JSONArray) json.get(name);
            String userName = (String) grabber.get(0);
            String publicName = (String) grabber.get(1);
            String userTag = (String) grabber.get(2);
            String userValorantRank = (String) grabber.get(3);
            String userLeagueRank = (String) grabber.get(4);
            String userPasswort = (String) grabber.get(5);
            boolean userIsAvailable = (boolean) grabber.get(6);
            new Account(userName,publicName,userTag,userValorantRank,userLeagueRank,userPasswort,userIsAvailable);
        }

    }

    private static void decryptSavestate(FileReader reader) {
        String text = reader.toString();
    }
}
