//Imports
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ex094
 */
public class VoiceLauncher {
    public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
    public static final String OPEN_FILE_MANAGER = "open file manager";
    public static final String OPEN_BROWSER = "open browser";
    public static final String OPEN_YOUTUBE = "open youtube";
    public static final String SHOW_DATE_TIME = "show date and time";
    public static final String SHOW_BATTERY_STATUS = "show battery status";
    public static final String SHOW_WEATHER = "show weather";
    public static final String OPEN_IMAGE = "open image";
    public static final String OPEN_MUSIC = "open music";
    public static final String SHOW_BITCOIN_VALUE = "show bitcoin value";
    public static final String TELL_CURRENCY = "tell currency";
    public static final String SEARCH_GOOGLE = "search google";
    public static final String PLAY_VIDEO = "play video";
    public static final String OPEN_EMAIL = "open email";

    public static void main(String[] args) throws IOException {
        // Configuration Object
        Configuration configuration = new Configuration();

        // Set path to the acoustic model.
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        // Set path to the dictionary.
        configuration.setDictionaryPath("languageModel/0716.dic");
        // Set path to the language model.
        configuration.setLanguageModelPath("languageModel/0716.lm");

        LiveSpeechRecognizer recognize = new LiveSpeechRecognizer(configuration);

        //Start Recognition Process (The bool parameter clears the previous cache if true)
        recognize.startRecognition(true);

        //Create SpeechResult Object
        SpeechResult result;

        TheWindow window = new TheWindow();

        Kernel32.SYSTEM_POWER_STATUS batteryStatus = new Kernel32.SYSTEM_POWER_STATUS();
        Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);



        ProcessBuilder processExplorer = new ProcessBuilder("explorer.exe");
        Process explorer = null;
        Process proc = null;






        //MailReader mailReader = new MailReader();





        //Checking if recognizer has recognized the speech
        while ((result = recognize.getResult()) != null) {
            //Get the recognized speech
            String command = result.getHypothesis();
            String work = null;


            if(command.equalsIgnoreCase(OPEN_FILE_MANAGER)) {
                    explorer = processExplorer.start();
                    System.out.println("Explorer launched!");
            } else if (command.equalsIgnoreCase(OPEN_YOUTUBE))  {
                openURI();
            }
            else if (command.equalsIgnoreCase(OPEN_BROWSER)) {
                openGoogle();
            } else if (command.equalsIgnoreCase(OPEN_MUSIC)) {
                doWork("cmd.exe /c start files/music.mp3");
            }
            else if (command.equalsIgnoreCase(OPEN_IMAGE)) {
                openImage("files/aeeOn7B_700b.jpg");
            }
            else if (command.equalsIgnoreCase(OPEN_EMAIL)) {
                try {
                    openEmail();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
            else if (command.equalsIgnoreCase(SHOW_DATE_TIME)) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                System.out.println(dateFormat.format(date)); //2017/12/15 time
                window.setTextOfWindow(date.toString());
                window.changeFrame();
                System.out.println(date.toString());

            }
            else if (command.equalsIgnoreCase(SHOW_BATTERY_STATUS)) {
                System.out.println(batteryStatus);
            }

            else if (command.equalsIgnoreCase(SHOW_BITCOIN_VALUE)) {
                openGoogle();
            }
            else if (command.equalsIgnoreCase(SHOW_WEATHER)) {
                openGoogle();
            }
            else if (command.equalsIgnoreCase(SEARCH_GOOGLE)) {
                openGoogle();
            }




        }
    }


    static void openEmail() throws URISyntaxException, IOException {
        Desktop desktop;
        if(Desktop.isDesktopSupported() && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
            URI mailtoo = null;
            try {
                mailtoo = new URI("mailto:stefan@apateanu.ro?subject=Hello%20MPS");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            desktop.mail(mailtoo);
        }
    }

    public static void openImage(String pathToImage) throws IOException {
        File f = new File(pathToImage);
        Desktop dt = Desktop.getDesktop();
        dt.open(f);
        System.out.println("Opening image.");
    }

    static void openGoogle() {
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI("http://www.google.com");
            desktop.browse(oURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void openURI() {
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI("https://www.youtube.com/watch?v=bpOSxM0rNPM");
            desktop.browse(oURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void doWork(String work) {
        try {
            Runtime.getRuntime().exec(work);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}