package riot.account.manager.Util;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import riot.account.manager.Core.Account;

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

        json = (JSONObject) parser.parse(new FileReader(STATICS.JSONPATH));
        JSONArray grabber = (JSONArray) json.get(AES.encrypt("accountnames", SECRETS.getKey()));
        for(int i = 0; i < grabber.size(); i++){
            accountNames.add(AES.decrypt(grabber.get(i).toString(), SECRETS.getKey()));
        }

        for (String name : accountNames){
            grabber = (JSONArray) json.get(AES.encrypt(name, SECRETS.getKey()));
            String userName = AES.decrypt((String) grabber.get(0), SECRETS.getKey());
            String publicNameValo = AES.decrypt((String) grabber.get(1), SECRETS.getKey());
            String userTag = AES.decrypt((String) grabber.get(2), SECRETS.getKey());
            String publicNameLeague = AES.decrypt((String) grabber.get(3), SECRETS.getKey());
            String userValorantRank = AES.decrypt((String) grabber.get(4), SECRETS.getKey());
            String userLeagueRankSolo = AES.decrypt((String) grabber.get(5), SECRETS.getKey());
            String userLeagueRankFlex = AES.decrypt((String) grabber.get(6), SECRETS.getKey());
            String userPasswort = AES.decrypt((String) grabber.get(7), SECRETS.getKey());
            boolean userIsAvailable;
            if(AES.decrypt((String) grabber.get(6), SECRETS.getKey()).equals("true")){
                userIsAvailable = true;
            } else {
                userIsAvailable = false;
            }

            new Account(userName, publicNameValo, userTag,publicNameLeague, userValorantRank, userLeagueRankSolo,userLeagueRankFlex, userPasswort, userIsAvailable);
            }

        }

    }

