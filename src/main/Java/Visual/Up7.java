package Visual;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Up7 extends JFrame {
    public static Up7 app;
    private TrayIcon iconTr;
    private SystemTray sT = SystemTray.getSystemTray();
    public boolean chetTray = false; //переменная, чтобы был вывод сообщения в трее только при первом сворачивании

    public Up7() throws IOException {
        super("Демонстрация сворачивания в трей");
        iconTr = new TrayIcon(ImageIO.read(new File("spain-flag-16x16.png")), "Демонстрация сворачивания в трей"); //Ikonka.png - изображение, которое будет показываться в трее - картинка в каталоге исполняемого приложения
        iconTr.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                setVisible(true);
                setState(JFrame.NORMAL);
                removeTr();
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
        iconTr.addMouseListener(mouS);
        MouseMotionListener mouM = new MouseMotionListener() {
            public void mouseDragged(MouseEvent ev) { }
            //при наведении
            public void mouseMoved(MouseEvent ev) {
                boolean flg = false;
                iconTr.setToolTip("Двойной щелчок - развернуть");
            }
        };

        iconTr.addMouseMotionListener(mouM);
        addWindowStateListener(new WindowStateListener()
        {
            public void windowStateChanged(WindowEvent ev)
            {
                if(ev.getNewState() == JFrame.ICONIFIED)
                {
                    setVisible(false);
                    addT();
                }
            }
        });
    }

    // метод удаления из трея
    private void removeTr(){ sT.remove(iconTr);}

    // метод добавления в трей
    private void addT() {
        try{
            sT.add(iconTr);
            if (chetTray==false) { iconTr.displayMessage("Демонстрация сворачивания в трей", "Программа свернулась", TrayIcon.MessageType.INFO); }
            chetTray = true;
        }
        catch(AWTException ex)
        { ex.printStackTrace(); }
    }

    public static void main(String[] args) throws IOException {
        app = new Up7 ();
        app.setVisible(true);
        app.setAlwaysOnTop(true);
        app.setSize(777, 777);

        //обработчик основного окна - здесь необходимо перечислить все возможные действия - раз взялись обрабатывать, надо делать до конца :)
        app.addWindowListener(new WindowListener() {
            public void windowClosing(WindowEvent winEvent) {
                System.exit(0);//при закрытии окна завершаем программу
            }
            public void windowActivated(WindowEvent winEvent) {}
            public void windowClosed(WindowEvent winEvent) {}
            public void windowDeactivated(WindowEvent winEvent) {}
            public void windowDeiconified(WindowEvent winEvent) {}
            public void windowIconified(WindowEvent winEvent) {}
            public void windowOpened(WindowEvent winEvent) {}
        });
    }
}