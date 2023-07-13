package Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class keyboardPanel extends JPanel implements KeyListener {
    public keyboardPanel(){

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public keyboardPanel(JTextField question, JTextField adding){
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
            String [] latters = new String[]{"ñ", "í", "é", "á", "ó", "ú", "ü"};
            ArrayList <JButton> buttonArray = new ArrayList<>();
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            for (String s : latters) {
                JButton buttonN = new JButton(s);
                buttonN.setFocusable(false);
                buttonN.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String OldString;
                        if (question.hasFocus()) {
                            OldString = question.getText();
                            question.setText(OldString + s);
                        }
                        if (adding.hasFocus()) {
                            OldString = adding.getText();
                            adding.setText(OldString + s);
                        }
                    }
                });
                buttonArray.add(buttonN);
            }
            int i = 0;
            for (JButton button : buttonArray){
                add(button);
            }

    }
}
