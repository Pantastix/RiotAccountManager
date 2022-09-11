package riot.account.manager.Core;

import riot.account.manager.Util.STATICS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static java.lang.String.valueOf;

public class Updater {

    public static Thread updaterThread = new Thread(() -> {
        downlaodUpdate();
    });


    public static void downlaodUpdate() {

        InputStream in = null;

        String url = findURL();

        if(url != null){
            try {
                in = new URL(url).openStream();
                System.out.println("Downloading Update");
                Files.copy(in, Paths.get("RiotAccountManager.jar"), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static String findURL() {
        String url = STATICS.CHECKURL;
        url = url.replace("[0]", valueOf(STATICS.getVersionArray()[0]));
        url = url.replace("[1]", valueOf(STATICS.getVersionArray()[1]));
        url = url.replace("[2]", valueOf(STATICS.getVersionArray()[2]));
        try {
            new URL(url).openStream();
        } catch (IOException exception1) {
        }
        int[] finalURL = new int[3];
        finalURL[0] = 0;
        finalURL[1] = 0;
        finalURL[2] = 0;
        for (int i = Integer.valueOf(STATICS.getVersionArray()[0]); i < Integer.valueOf(STATICS.getVersionArray()[0]) + 10; i++) {
            url = STATICS.CHECKURL;
            url = url.replace("[0]", valueOf(i));
            url = url.replace("[1]", "0");
            url = url.replace("[2]", "0");
            try {
                new URL(url).openStream();
                finalURL[0] = i;
            } catch (FileNotFoundException e1) {
                i = Integer.valueOf(STATICS.getVersionArray()[0]) + 10;
            } catch (IOException exception1) {

            }
        }
        for (int i = 0; i < Integer.valueOf(STATICS.getVersionArray()[1]) + 10; i++) {
            url = STATICS.CHECKURL;
            url = url.replace("[0]", valueOf(finalURL[0]));
            url = url.replace("[1]", valueOf(i));
            url = url.replace("[2]", "0");
            try {
                new URL(url).openStream();
                finalURL[1] = i;
            } catch (FileNotFoundException e1) {
                i = Integer.valueOf(STATICS.getVersionArray()[0]) + 10;
            } catch (IOException exception1) {

            }
        }

        if (finalURL[1] == 0) {
            for (int i = 0; i < Integer.valueOf(STATICS.getVersionArray()[2]) + 10; i++) {
                url = STATICS.CHECKURL;
                url = url.replace("[0]", STATICS.getVersionArray()[0]);
                url = url.replace("[1]", STATICS.getVersionArray()[1]);
                url = url.replace("[2]", valueOf(i));
                try {
                    new URL(url).openStream();
                    finalURL[2] = i;
                } catch (FileNotFoundException e1) {
                    i = Integer.valueOf(STATICS.getVersionArray()[0]) + 10;
                } catch (IOException exception1) {

                }
            }
        }

        url = STATICS.CHECKURL;
        if (finalURL[0] == 0) {
            url = url.replace("[0]", STATICS.getVersionArray()[0]);
        } else {
            url = url.replace("[0]", valueOf(finalURL[0]));
        }
        url = url.replace("[1]", valueOf(finalURL[1]));
        url = url.replace("[2]", valueOf(finalURL[2]));

        try {
            new URL(url).openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (finalURL[0] <= Integer.valueOf(STATICS.getVersionArray()[0]) && finalURL[1] <= Integer.valueOf(STATICS.getVersionArray()[1]) && finalURL[2] <= Integer.valueOf(STATICS.getVersionArray()[2])) {
            return null;
        }
        return url;
    }
        public Thread getUpdaterThread() {
            return updaterThread;
        }
    }

