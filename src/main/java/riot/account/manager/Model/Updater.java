package riot.account.manager.Model;

import riot.account.manager.Util.STATICS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Updater {

    public static Thread updaterThread = new Thread(() -> {downlaodUpdate();});


        public static void downlaodUpdate(){

        InputStream in = null;
        try {
            try {
                in = new URL(STATICS.CHECKURL).openStream();
            }catch(FileNotFoundException e){
                in = new URL(STATICS.DOWNLOADURL).openStream();
                Files.copy(in, Paths.get("RiotAccountManager.jar"), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Thread getUpdaterThread() {
        return updaterThread;
    }
}
