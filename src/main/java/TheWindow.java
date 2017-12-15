import javax.swing.*;
import java.awt.*;

public class TheWindow {
    JTextField txt;
    JFrame frame;
    public String getTextOfWindow() {
        return textOfWindow;
    }

    public void setTextOfWindow(String textOfWindow) {
        this.textOfWindow = textOfWindow;
    }

    private String textOfWindow = "Waiting for commands";
public TheWindow(){


    //1. Create the frame.
    frame = new JFrame("VoiceRecordingApp");

//2. Optional: What happens when the frame closes?
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JTextField basicTextField = new JTextField(textOfWindow);
    basicTextField.setBackground(Color.black);
    basicTextField.setFont(new Font("verdana", Font.BOLD, 30));
    basicTextField.setForeground(Color.white);
    frame.getContentPane().add(basicTextField, BorderLayout.CENTER);
    frame.setLocationRelativeTo(null);

//4. Size the frame.
    frame.pack();
    frame.setSize(800, 500);

//5. Show it.
    frame.setVisible(true);
}

    public void changeFrame() {
        //3. Create components and put them in the frame.

        txt.setText(textOfWindow);

        frame.getContentPane().add(txt, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
    }



}
