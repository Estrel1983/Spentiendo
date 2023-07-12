package Visual;

import Data.AnswerCorrectness;
import Realisation.DataOperation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class mFrame extends JFrame {
    JFrame frame;
    private TrayIcon trayIcon;
    private SystemTray sT = SystemTray.getSystemTray();
    private Timer timer;

    public mFrame() {
        setTitle("Spentiendo");

        //Работа с треем
        try {
            trayIcon = new TrayIcon(ImageIO.read(new File("spain-flag-16x16.png")));
        } catch (IOException e) {}
        trayIcon.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                removeTr();
                timer.stop();
            }
        });
        //обработчик мыши

        MouseListener mouS = new MouseListener() {
            public void mouseClicked(MouseEvent ev) { }
            public void mouseEntered(MouseEvent ev) { }
            public void mouseExited(MouseEvent ev) {  }
            public void mousePressed(MouseEvent ev) { }
            public void mouseReleased(MouseEvent ev) {}
        };
        trayIcon.addMouseListener(mouS);
        MouseMotionListener mouM =new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                boolean flg = false;
                trayIcon.setToolTip("Spentiendo");
            }
        };
        trayIcon.addMouseMotionListener(mouM);
        addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                if (e.getNewState() == JFrame.ICONIFIED){
                    setVisible(true);
                    addT();
                }
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JPanel headingPanel = new JPanel();
        JLabel headingLabel = new JLabel("Translate to Spanish!");
        headingPanel.add(headingLabel);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constr = new GridBagConstraints();
        constr.insets = new Insets(5, 5, 5, 5);
        constr.anchor = GridBagConstraints.WEST;

        constr.gridx = 0;
        constr.gridy = 0;
        JLabel wordLabel = new JLabel("Your Language: ");
        panel.add(wordLabel, constr);

        constr.gridx = 1;
        JLabel wordQuery = new JLabel(DataOperation.getCurTransl());
        panel.add(wordQuery, constr);

        constr.gridx = 0;
        constr.gridy = 1;
        JLabel answerLabel = new JLabel("Español: ");
        JTextField answerInput = new JTextField(20);
        panel.add(answerLabel, constr);
        constr.gridx = 1;
        panel.add(answerInput, constr);
        constr.gridx = 2;

        answerInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = createAnswerDialog(DataOperation.isCorrectTranslation(answerInput.getText().toLowerCase()));
                dialog.setLocationRelativeTo(answerInput);
                dialog.setVisible(true);
                wordQuery.setText(DataOperation.getCurTransl());
                answerInput.setText("");
                addT();
                timer = new Timer (15*60 *1000, event ->{
                removeTr();
                answerInput.requestFocus();
                timer.stop();
            });
                timer.start();
            }
        });

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog dialog = createAnswerDialog(DataOperation.isCorrectTranslation(answerInput.getText().toLowerCase()));
                dialog.setLocationRelativeTo(answerInput);
                dialog.setVisible(true);
                wordQuery.setText(DataOperation.getCurTransl());
                answerInput.setText("");
 //               setVisible(false);
                addT();
                timer = new Timer (15*60*1000, event ->{
                    removeTr();
                    answerInput.requestFocus();
                    timer.stop();
                });
                timer.start();
            }
        });
        panel.add(submitButton, constr);

        constr.gridx = 1;
        constr.gridy = 2;
        constr.anchor = GridBagConstraints.CENTER;
        JButton makeRemembered = new JButton("Already know");
        makeRemembered.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataOperation.makeRemembered();
                wordQuery.setText(DataOperation.getCurTransl());
                answerInput.setText("");
            }
        });
        panel.add(makeRemembered, constr);


        mainPanel.add(headingPanel);
        mainPanel.add(panel);

        addWordPanel adPanel = new addWordPanel();
        mainPanel.add(adPanel);

        add(mainPanel);



        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowClosing(WindowEvent e) {
                DataOperation.saveAll();
                e.getWindow().setVisible(false);
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {}

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}
        });

        setSize(500, 500);
        pack();
        setLocationRelativeTo(null);
//        setLayout(null);
        setVisible(true);
    }
    private JDialog createAnswerDialog (AnswerCorrectness answer){
        JDialog dialog = new JDialog(this, "", true);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constr = new GridBagConstraints();
        constr.anchor = GridBagConstraints.WEST;
        constr.gridy = 0;
        JLabel cOfLabel = new JLabel(answer.isCorrect() ? "Correct Answer" : "Wrong Answer");
        JLabel answerdLabel = new JLabel("For word - \"" + answer.getTranslate() + "\"");
        JLabel answerdLabel2 = new JLabel("translation is: \"" + answer.getSpanish() + "\"");
        if (answer.isCorrect()){
            cOfLabel.setForeground(Color.GREEN);
        }
        else{
            cOfLabel.setForeground(Color.RED);
        }
        panel.add(cOfLabel, constr);
        constr.gridy = 1;
        panel.add(answerdLabel, constr);
        constr.gridy = 2;
        panel.add(answerdLabel2, constr);

        constr.gridy = 3;
        constr.anchor = GridBagConstraints.CENTER;
        JButton okButton = new JButton("OK");
        okButton.addActionListener(event -> dialog.dispose());
        okButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                okButton.doClick();
            }
        });
        panel.add(okButton, constr);


        dialog.add(panel);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(200, 120);
        dialog.pack();
        return dialog;
    }
    private void removeTr(){
        sT.remove(trayIcon);
        setVisible(true);
    }
    private void addT(){
        try {
            sT.add(trayIcon);
            setVisible(false);

        }catch (AWTException ex){
            System.out.println("Все поломалося");
        }
    }


}