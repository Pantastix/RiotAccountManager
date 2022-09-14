package riot.account.manager.Util;


import javafx.scene.image.Image;

import java.io.File;
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

    private static final String[] leagueRanksFilter = {"Iron", "Bronze", "Silver", "Gold", "Platinum", "Diamond", "Master", "Grandmaster", "Challenger"};
    private static final String[] valorantRanksFilter = {"Iron", "Bronze", "Silver", "Gold", "Platinum", "Diamond","Ascendant" ,"Immortal", "Radiant"};
    private static final String[] valorantRanks = {
            "Iron 1","Iron 2","Iron 3",
            "Bronze 1","Bronze 2","Bronze 3",
            "Silver 1","Silver 2","Silver 3",
            "Gold 1","Gold 2","Gold 3",
            "Platinum 1","Platinum 2","Platinum 3",
            "Diamond 1","Diamond 2","Diamond 3",
            "Ascendant 1","Ascendant 2","Ascendant 3",
            "Immortal 1","Immortal 2","Immortal 3",
            "Radiant"
    };
    private static final String[] leagueRanks = {
            "Iron 4","Iron 3","Iron 2","Iron 1",
            "Bronze 4","Bronze 3","Bronze 2","Bronze 1",
            "Silver 4","Silver 3","Silver 2","Silver 1",
            "Gold 4","Gold 3","Gold 2","Gold 1",
            "Platinum 4","Platinum 3","Platinum 2","Platinum 1",
            "Diamond 4","Diamond 3","Diamond 2","Diamond 1",
            "Master 4","Master 3","Master 2","Master 1",
            "Grandmaster 4","Grandmaster 3","Grandmaster 2","Grandmaster 1",
            "Challenger 4","Challenger 3","Challenger 2","Challenger 1"
    };

    public static Image getRankImage(String rank, String game) {
        FileInputStream inputStream;
        try {
            if (game.equals("Valorant")) {
                if (rank.equals("Radiant")) {
                    inputStream = new FileInputStream(valorantPath + rank + ".png");
                } else {
                        inputStream = new FileInputStream(valorantPath + rank.split(" ")[0] + "_"+ rank.split(" ")[1]+".png");
                }

            } else {
                inputStream = new FileInputStream(leaguePath + rank.split(" ")[0] + "_"+ rank.split(" ")[1]+".png");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new Image(inputStream);
    }

    public static String[] getValorantRanks() {
        return valorantRanks;
    }

    public static String[] getLeagueRanks() {
        return leagueRanks;
    }
    public static String[] getLeagueFilterArray() {
        return leagueRanksFilter;
    }
    public static String[] getValorantFilterArray() {
        return valorantRanksFilter;
    }

    public static boolean isAvailable() {
        File league = new File(leaguePath);
        File valo = new File(valorantPath);
        if (valo.exists() && league.exists()) {
            return true;
        } else {
            return false;
        }
    }
}
