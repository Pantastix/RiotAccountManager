package riot.account.manager.Util;


import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * includes all information about ranks
 *
 * @author Chris Simbeck
 */
public class Ranks {
    static final String valorantPath = "Valorant_rank_pictures/";
    static final String leaguePath = "League_rank_pictures/";
    private static final String[] valorantRanks = {"Iron","Bronze", "Silver", "Gold", "Platinum", "Diamond", "Ascendant", "Immortal", "Radiant"};
    private static final String[] leagueRanks = {"Iron","Bronze", "Silver", "Gold", "Platinum", "Diamond", "Master", "Grandmaster", "Challenger"};

    public static Image getRankImage(String rank, String game) throws FileNotFoundException {
        FileInputStream inputStream;
        if(game.equals("Valorant")){
            inputStream = new FileInputStream(valorantPath + rank + ".png");
        } else {
            inputStream = new FileInputStream(leaguePath + rank + ".png");
        }
        return new Image(inputStream);
    }

    public static String[] getValorantRankArray(){
        return valorantRanks;
    }
    public static String[] getLeagueRankArray(){
        return leagueRanks;
    }

}
