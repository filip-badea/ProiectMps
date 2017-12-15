import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class TheWindow {


    private static final String textOfWindow = "Waiting for commands";


    JTextField basicTextField;
    JFrame frame;

    public TheWindow() {



        //1. Create the frame.
        frame = new JFrame("VoiceRecordingApp");

//2. Optional: What happens when the frame closes?
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // initialize textField
        basicTextField = new JTextField(this.textOfWindow);
        basicTextField.setBackground(Color.black);
        basicTextField.setFont(new Font("verdana", Font.BOLD, 30));
        basicTextField.setForeground(Color.white);
        frame.getContentPane().add(basicTextField, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);

//4. Size the frame.
        frame.pack();
        frame.setSize(1500, 500);

//5. Show it.
        frame.setVisible(true);
    }

    public void changeTextWindow(String text) {
        basicTextField.setText(text);
        frame.repaint();
    }


}
