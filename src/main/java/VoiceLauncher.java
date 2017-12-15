//Imports
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


/**
 *
 * @author ex094
 */
public class VoiceLauncher {
    private static Process explorerClosing;
    public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
    public static void main(String[] args) throws IOException {
        // Configuration Object
        Configuration configuration = new Configuration();

        // Set path to the acoustic model.
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        // Set path to the dictionary.
        configuration.setDictionaryPath("languageModel/9127.dic");
        // Set path to the language model.
        configuration.setLanguageModelPath("languageModel/9127.lm");

        LiveSpeechRecognizer recognize = new LiveSpeechRecognizer(configuration);

        //Start Recognition Process (The bool parameter clears the previous cache if true)
        recognize.startRecognition(true);

        //Create SpeechResult Object
        SpeechResult result;



        Kernel32.SYSTEM_POWER_STATUS batteryStatus = new Kernel32.SYSTEM_POWER_STATUS();
        Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);



        //Process proc = null;
        ProcessBuilder processExplorer = new ProcessBuilder("explorer.exe");
        ProcessBuilder processExplorerExit = new ProcessBuilder("kill explorer.exe");
        ProcessBuilder processBrowser = new ProcessBuilder("\"/Program Files (x86)/Google/Chrome/Application/chrome.exe\"");
        Process explorer = null;
        Process browser = null;
        Process proc = null;



        System.out.println(batteryStatus);



        MailReader mailReader = new MailReader();





        //Checking if recognizer has recognized the speech
        while ((result = recognize.getResult()) != null) {

            //Get the recognized speech
            String command = result.getHypothesis();
            String work = null;


            if(command.equalsIgnoreCase("open file manager")) {
                    explorer = processExplorer.start();
                    System.out.println("Explorer launched!");
            } else if (command.equalsIgnoreCase("close file manager"))  {
               //openImage("files/aeeOn7B_700b.jpg");
                openGoogle();
            }

            else if (command.equalsIgnoreCase("open browser")) {
                try {
                    browser = Runtime.getRuntime().exec("\"/Program Files (x86)/Google/Chrome/Application/chrome.exe\"");
                    browser.waitFor();
                    System.out.println("Google Chrome launched!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (command.equalsIgnoreCase("close browser")) {
                try {
                    Runtime.getRuntime().exec("cmd.exe /c start files/music.mp3");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //In case command recognized is none of the above hence work might be null
            /*if(work != null) {
                //Execute the command
                proc = Runtime.getRuntime().exec(work);
            }*/
        }
    }


    public void openEmail() throws URISyntaxException, IOException {
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

}