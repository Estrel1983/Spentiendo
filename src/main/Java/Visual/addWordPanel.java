package Visual;

import Realisation.DataOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class addWordPanel extends JPanel {
    public addWordPanel(){
        setLayout(new GridBagLayout());
        setDoubleBuffered(true);
        updateUI();

        GridBagConstraints constr = new GridBagConstraints();
        constr.insets = new Insets(5,5,5,5);
        constr.anchor = GridBagConstraints.WEST;

        constr.gridx = 0;
        constr.gridy = 0;
        JLabel enterHereLabel = new JLabel("Enter new word below :");
        add(enterHereLabel, constr);

        constr.gridx = 1;
        constr.gridy = 0;
        JLabel enterHereLabel1 = new JLabel("                                                           ");
        add(enterHereLabel1, constr);

        constr.gridx = 0;
        constr.gridy = 1;
        JLabel espanLabel = new JLabel("Español: ");
        add(espanLabel, constr);

        constr.gridx = 1;
        JTextField espanWord = new JTextField(20);
        add(espanWord, constr);

        constr.gridx = 0;
        constr.gridy = 2;
        JLabel translateLabel = new JLabel("Translation: ");
        add(translateLabel, constr);

        constr.gridx = 1;
        JTextField translateWord = new JTextField(20);
        add(translateWord, constr);

        constr.gridx = 1;
        constr.gridy = 3;
        constr.anchor = GridBagConstraints.CENTER;
        JButton addWord = new JButton("Add Word");
        addWord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (espanWord.getText().isEmpty()){
                    espanWord.requestFocus();
                    return;
                }
                if (translateWord.getText().isEmpty()){
                    translateWord.requestFocus();
                    return;
                }
                try {
                    DataOperation.putNewWord(espanWord.getText().toLowerCase(), translateWord.getText().toLowerCase());
                } catch (Exception exception) {
                    //TODO написать обработку. Вылетает если одно из полей пустое
                }
                translateWord.setText("");
                espanWord.setText("");
            }
        });

        espanWord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (espanWord.getText().isEmpty()){
                    espanWord.requestFocus();
                    return;
                }
                if (translateWord.getText().isEmpty()){
                    translateWord.requestFocus();
                    return;
                }
                try {
                    DataOperation.putNewWord(espanWord.getText().toLowerCase(), translateWord.getText().toLowerCase());
                } catch (Exception exception) {
                    //TODO написать обработку. Вылетает если одно из полей пустое
                }
                translateWord.setText("");
                espanWord.setText("");
            }
        });
        translateWord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (espanWord.getText().isEmpty()){
                    espanWord.requestFocus();
                    return;
                }
                if (translateWord.getText().isEmpty()){
                    translateWord.requestFocus();
                    return;
                }
                try {
                    DataOperation.putNewWord(espanWord.getText().toLowerCase(), translateWord.getText().toLowerCase());
                } catch (Exception exception) {
                    //TODO написать обработку. Вылетает если одно из полей пустое
                }
                translateWord.setText("");
                espanWord.setText("");
            }
        });



        add (addWord, constr);


    }
}
