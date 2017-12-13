//Imports
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import facebook4j.Facebook;
import facebook4j.FacebookFactory;

import java.io.IOException;

/**
 *
 * @author ex094
 */
public class VoiceLauncher {
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

        //Checking if recognizer has recognized the speech
        while ((result = recognize.getResult()) != null) {

            //Get the recognized speech
            String command = result.getHypothesis();
            String work = null;
            Process proc = null;

            if(command.equalsIgnoreCase("open file manager")) {
                work = "explorer.exe";
            } else if (command.equalsIgnoreCase("close file manager")) {
                Facebook facebook = new FacebookFactory().getInstance();
            } else if (command.equalsIgnoreCase("open browser")) {
                try {
                    proc = Runtime.getRuntime().exec("\"/Program Files (x86)/Google/Chrome/Application/chrome.exe\"");
                    proc.waitFor();
                    System.out.println("Google Chrome launched!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //In case command recognized is none of the above hence work might be null
            if(work != null) {
                //Execute the command
                proc = Runtime.getRuntime().exec(work);
            }
        }
    }

}
