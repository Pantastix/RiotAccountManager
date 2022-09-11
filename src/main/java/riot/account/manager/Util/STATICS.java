package riot.account.manager.Util;

public class STATICS {
    public final static String JSONPATH = "Json/SaveState.Json";
    public final static String VERSION = "1.1.0";

//    public final static String CHECKURL = "https://github.com/Pantastix/RiotAccountManager/releases/latest/download/RiotAccountManager-v"+VERSION+".rar";
    public final static String DOWNLOADURL = "https://github.com/Pantastix/RiotAccountManager/releases/latest/download/RiotAccountManager-update.jar";
    public final static String CHECKURL = "https://github.com/Pantastix/RiotAccountManager/releases/v[0].[1].[2]";

    public static String[] getVersionArray(){
        return VERSION.split("\\.");
    }
}
