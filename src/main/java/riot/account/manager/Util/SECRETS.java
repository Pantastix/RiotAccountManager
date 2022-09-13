package riot.account.manager.Util;

public class SECRETS {

    private final static String encryptionKey = System.getenv("ENCRYPTION_KEY");
    public static String getEncryptionKey() {
        return encryptionKey;
    }

}
