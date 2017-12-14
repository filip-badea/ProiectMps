//Imports
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import java.io.IOException;

/**
 *
 * @author ex094
 */
public class VoiceLauncher {
    private static Process explorerClosing;
    public static void main(String[] args) throws IOException {
        // Configuration Object
        Configuration configuration = new Configuration();

        // Set path to the acoustic model.
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        // Set path to the dictionary.
        configuration.setDictionaryPath("languageModel/4371.dic");
        // Set path to the language model.
        configuration.setLanguageModelPath("languageModel/4371.lm");

        LiveSpeechRecognizer recognize = new LiveSpeechRecognizer(configuration);

        //Start Recognition Process (The bool parameter clears the previous cache if true)
        recognize.startRecognition(true);

        //Create SpeechResult Object
        SpeechResult result;

        //Process proc = null;
        ProcessBuilder processExplorer = new ProcessBuilder("explorer.exe");
        ProcessBuilder processExplorerExit = new ProcessBuilder("kill explorer.exe");
        ProcessBuilder processBrowser = new ProcessBuilder("\"/Program Files (x86)/Google/Chrome/Application/chrome.exe\"");
        Process explorer = null;
        Process browser = null;
        Process proc = null;

        //Checking if recognizer has recognized the speech
        while ((result = recognize.getResult()) != null) {

            //Get the recognized speech
            String command = result.getHypothesis();
            String work = null;


            if(command.equalsIgnoreCase("open file manager")) {
                    explorer = processExplorer.start();
                    System.out.println("Explorer launched!");
            } else if (command.equalsIgnoreCase("close file manager"))  {
                try {
                    explorer.destroyForcibly();
                    System.out.println("Closing explorer");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
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
                    proc =  Runtime.getRuntime().exec("taskkill /F /IM explorer.exe");
                    proc.waitFor();
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

}